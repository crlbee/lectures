package ru.naumen.lecture.tooling;

public class HowAreYouCommand implements IToolTesterCommand
{

    @Override
    public String getName()
    {
        return "Как ты?";
    }

    @Override
    public State getTargetState()
    {
        return State.SAME_AS_CURRENT;
    }

    @Override
    public String getCode()
    {
        return "HowAreYou";
    }

    @Override
    public void execute()
    {

    }

}
