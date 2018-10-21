package ru.naumen.lecture.tooling;

public class MakeAllRightCommand implements IToolTesterCommand
{

    @Override
    public String getName()
    {
        return "Починись!";
    }

    @Override
    public State getTargetState()
    {
        return State.ALL_RIGHT;
    }

    @Override
    public void execute()
    {

    }
}
