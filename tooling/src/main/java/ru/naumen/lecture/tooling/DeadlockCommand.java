package ru.naumen.lecture.tooling;

public class DeadlockCommand implements IToolTesterCommand
{

    @Override
    public String getName()
    {
        return "Сделай Deadlock!";
    }

    @Override
    public String getCode()
    {
        return "Deadlock";
    }

    @Override
    public State getTargetState()
    {
        return State.DEADLOCK;
    }

    @Override
    public void execute()
    {
        Object lock1 = new Object();
        Object lock2 = new Object();
        Object lock3 = new Object();

        Thread blockingThread1 = new BlockingThread("BlockingThread1", lock1, lock2);
        Thread blockingThread2 = new BlockingThread("BlockingThread2", lock2, lock3);
        Thread blockingThread3 = new BlockingThread("BlockingThread3", lock3, lock1);

        blockingThread1.start();
        ToolTesterUtils.doSomeWork(5);
        blockingThread2.start();
        ToolTesterUtils.doSomeWork(5);
        blockingThread3.start();
    }
}
