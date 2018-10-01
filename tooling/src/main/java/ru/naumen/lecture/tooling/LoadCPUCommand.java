package ru.naumen.lecture.tooling;

public class LoadCPUCommand implements IToolTesterCommand
{

    @Override
    public String getName()
    {
        return "Нагрузи проц";
    }

    @Override
    public State getTargetState()
    {
        return State.HIGH_CPU_LOAD;
    }

    @Override
    public String getCode()
    {
        return "LoadCPU";
    }

    @Override
    public void execute()
    {

    }
}
