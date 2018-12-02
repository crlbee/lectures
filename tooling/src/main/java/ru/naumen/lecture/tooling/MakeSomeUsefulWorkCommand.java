package ru.naumen.lecture.tooling;

public class MakeSomeUsefulWorkCommand implements IToolTesterCommand
{

    @Override
    public String getName()
    {
        return "Сделай некоторую полезную работу";
    }

    @Override
    public State getTargetState()
    {
        return State.DO_SOME_USEFUL_WORK;
    }

    @Override
    public void execute()
    {

    }

}
