package ru.naumen.lecture.tooling;

public class EatAllHeapCommand implements IToolTesterCommand
{

    @Override
    public String getName()
    {
        return "Съешь всю память";
    }

    @Override
    public State getTargetState()
    {
        return State.HIGH_MEMORY_CONSUPTION;
    }

    @Override
    public String getCode()
    {
        return "EatAllHeap";
    }

    @Override
    public void execute()
    {

    }
}
