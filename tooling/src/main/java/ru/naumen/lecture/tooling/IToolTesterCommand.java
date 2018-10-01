package ru.naumen.lecture.tooling;

public interface IToolTesterCommand
{
    String getName();
    
    String getCode();

    State getTargetState();

    void execute();
}
