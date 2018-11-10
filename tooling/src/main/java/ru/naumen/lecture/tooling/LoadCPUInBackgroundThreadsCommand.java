package ru.naumen.lecture.tooling;

public class LoadCPUInBackgroundThreadsCommand implements IToolTesterCommand
{

    @Override
    public String getName()
    {
        return "Нагрузи CPU в фоновых потоках с потреблением памяти";
    }

    @Override
    public State getTargetState()
    {
        return State.MULTI_THREAD_HIGH_CPU_LOAD;
    }

    @Override
    public void execute()
    {
        for(int i = 0 ; i < 3; i ++)
        {
            new CPULoadThread(true).start();
        }
    }
}
