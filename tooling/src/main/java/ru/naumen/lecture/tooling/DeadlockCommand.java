package ru.naumen.lecture.tooling;

public class DeadlockCommand implements IToolTesterCommand
{

    @Override
    public String getName()
    {
        return "Заблокируйся!";
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
        try
        {
            Thread.sleep(50000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        
        Thread t1 = new Thread(new SyncThread(obj1, obj2), "t1");
        Thread t2 = new Thread(new SyncThread(obj2, obj3), "t2");
        Thread t3 = new Thread(new SyncThread(obj3, obj1), "t3");
        
        t1.start();
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t2.start();
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t3.start();
        */
    }
}
