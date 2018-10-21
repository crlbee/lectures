package ru.naumen.lecture.tooling;

/**
 * Длительная работа под блокировкой
 * 
 * @author amokhov
 * @since Oct 11, 2018
 *
 */
public class LongOperationUnderLockCommand implements IToolTesterCommand
{

    @Override
    public String getName()
    {
        return "Выполни длительную работу под блокировкой";
    }

    @Override
    public State getTargetState()
    {
        return State.LONG_OPERATION_UNDER_LOCK;
    }

    @Override
    public void execute()
    {
        ToolTesterUtils.doEndlessWork();
    }
}
