package ru.naumen.lecture.tooling;

import java.util.EnumSet;
import java.util.Map;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;

import net.bull.javamelody.Parameter;

/**
 * Embedded http server including javamelody reports.
 */
public class EmbeddedServer
{
    /**
     * Start the server with a http port and optional javamelody parameters.
     * @param port Http port
     * @param parameters Optional javamelody parameters
     * @throws Exception e
     */
    public static void start(int port, Map<Parameter, String> parameters) throws Exception
    {
        // Init jetty
        final Server server = new Server(port);

        final ContextHandlerCollection contexts = new ContextHandlerCollection();
        final ServletContextHandler context = new ServletContextHandler(contexts, "/", ServletContextHandler.SESSIONS);

        final net.bull.javamelody.MonitoringFilter monitoringFilter = new net.bull.javamelody.MonitoringFilter();
        monitoringFilter.setApplicationType("Standalone");
        
        final FilterHolder filterHolder = new FilterHolder(monitoringFilter);
        if (parameters != null)
        {
            for (final Map.Entry<Parameter, String> entry : parameters.entrySet())
            {
                final net.bull.javamelody.Parameter parameter = entry.getKey();
                final String value = entry.getValue();
                filterHolder.setInitParameter(parameter.getCode(), value);
            }
        }
        context.addFilter(filterHolder, "/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        context.addServlet(ToolTesterServlet.class, "/*");

        final HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[] { contexts});
        server.setHandler(handlers);

        server.start();
    }
}
