package ru.naumen.lecture.tooling;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToolTesterServlet extends HttpServlet
{
    private static final long serialVersionUID = 579366660553979193L;

    private static final String FAVICON = "favicon.ico";
    private static final String COMMAND_PRM_NAME = "command";

    private final static List<IToolTesterCommand> AVAILABLE_COMMANDS = Arrays.asList(
            new SomeUsefulWorkCommand(),
            new HowAreYouCommand(),
            new MakeAllRightCommand(),
            new LongOperationUnderLockCommand(),
            new LoadCPUInRequestThreadCommand(), 
            new MakeDeadlockCommand(), 
            new ConsumeAllHeapCommand(),
            new LoadCPUInBackgroundThreadsCommand());

    private State currentState = State.ALL_RIGHT;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (request.getRequestURI().endsWith(FAVICON))
        {
            return;
        }
        String command = request.getParameter(COMMAND_PRM_NAME);
        Optional<IToolTesterCommand> cmd = AVAILABLE_COMMANDS.stream().filter(c -> c.getCode().equals(command)).findFirst();

        if (cmd.isPresent())
        {
            ToolTester.LOG.info(String.format("Request on execution command %s received", cmd.get().getCode()));
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        //FIXME: Выполнять обработку запроса под блокировкой, конечно же, не стоит. Но мы будем.
        synchronized (ToolTesterUtils.getCommonLock())
        {
            if (cmd.isPresent() && canExecute(cmd.get().getTargetState()))
            {
                currentState = cmd.get().getTargetState();
                writeState(response);
                cmd.get().execute();
            }
            else if( cmd.isPresent()) 
            {
                writeState(response);
            }
        }

        final StringBuilder form = new StringBuilder();
        form.append("<form method=\"post\" action=\"/\">");
        form.append("<h2>Штатные функции:</h2>");
        
        AVAILABLE_COMMANDS.stream().filter(c -> !c.getTargetState().isError() && !c.getTargetState().isService()).forEach(c -> {
            form.append(buildButton(c, false));
        });
        
        form.append("<h2>Сервисные функции:</h2>");
        
        AVAILABLE_COMMANDS.stream().filter( c -> c.getTargetState().isService()).forEach(c -> {
            form.append(buildButton(c, false));
        });

        form.append("<h2 title=\"Не использовать в production!\">Недокументированные возможности:</h2>");
        AVAILABLE_COMMANDS.stream().filter( c -> c.getTargetState().isError()).forEach(c -> {
            form.append(buildButton(c, currentState.isError()));
        });

        form.append("</form>");
        response.getWriter().println(form);
    }
    
    private String buildButton(IToolTesterCommand c, boolean isDisabled)
    {
        return "<button style=\"margin:4px 2px;\" name=\"" + COMMAND_PRM_NAME + "\" value = \"" + c.getCode() + "\"" + 
                (isDisabled ? " disabled " : "") + ">" + 
                c.getName() + "</button>&nbsp;";
    }

    private void writeState(HttpServletResponse response) throws IOException
    {
        response.getWriter().println("<h1>Состояние: " + 
                (currentState == null ? "Не определено" : currentState.getTitle()) + "</h1>");
        response.getWriter().flush();
    }

    private boolean canExecute(State targetState)
    {
        return !State.SAME_AS_CURRENT.equals(targetState) && (!targetState.isError() || !currentState.isError());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doGet(req, resp);
    }
}