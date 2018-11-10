package ru.naumen.lecture.tooling;

public class LoadCPUInRequestThreadCommand implements IToolTesterCommand
{
    @Override
    public String getName()
    {
        return "Нагрузи CPU в потоке обработки запроса";
    }

    @Override
    public State getTargetState()
    {
        return State.SINGLE_THREAD_HIGH_CPU_LOAD;
    }

    @Override
    public void execute()
    {
        new CPULoadThread().run();
    }
}
