package ru.naumen.lecture.tooling;

public interface IToolTesterCommand
{
    String getName();
    
    default String getCode()
    {
        return this.getClass().getSimpleName();
    }

    State getTargetState();

    void execute();
}
