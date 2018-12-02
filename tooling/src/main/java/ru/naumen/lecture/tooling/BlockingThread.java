package ru.naumen.lecture.tooling;

/**
 * При помощи этого класса будут демонстрироваться взаимные блокироки
 * 
 * @author amokhov
 * @since Oct 11, 2018
 *
 */
class BlockingThread extends Thread
{
    private Object lock1;
    private Object lock2;

    public BlockingThread(String name, Object lock1, Object lock2)
    {
        super(name);
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run()
    {
        String name = Thread.currentThread().getName();
        ToolTester.LOG.info(name + " acquiring lock on " + lock1);
        synchronized (lock1)
        {
            ToolTester.LOG.info(name + " acquired lock on " + lock1);
            ToolTesterUtils.doSomeWork(ToolTesterUtils.DEFAULT_WORK_TIME * 2);
            ToolTester.LOG.info(name + " acquiring lock on " + lock2);
            synchronized (lock2)
            {
                ToolTester.LOG.info(name + " acquired lock on " + lock2);
                ToolTesterUtils.doSomeWork(ToolTesterUtils.DEFAULT_WORK_TIME * 2);
            }
            ToolTester.LOG.info(name + " released lock on " + lock2);
        }
        ToolTester.LOG.info(name + " released lock on " + lock1);
        ToolTester.LOG.info(name + " finished execution.");
    }
}