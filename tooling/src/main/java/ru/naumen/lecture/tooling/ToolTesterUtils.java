package ru.naumen.lecture.tooling;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import ru.naumen.lecture.tooling.LoadCPUCommand.ToolTesterBean;

public class ToolTesterUtils
{
    private static final long DEFAULT_PAUSE = 1000L;

    private static final String OBJECT_NAME = "ru.naumen.lecture.tooling:type=SomeMBeanImpl";
    private static ObjectName mbeanNameInstance = null;
    private static Object commonLock = new Object();
    public static int DEFAULT_WORK_TIME = 2; //sec.

    public static void doEndlessWork()
    {
        doSomeWork(Integer.MAX_VALUE, DEFAULT_PAUSE);
    }

    public static void doSomeWork(int iterationCount)
    {
        doSomeWork(iterationCount, DEFAULT_PAUSE);
    }

    public static void doSomeWork(int iterationCount, long pauseMills)
    {
        try
        {
            for (int i = 0; i < iterationCount; i++)
            {
                Thread.sleep(pauseMills);
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void registerMBean()
    {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        try
        {
            ToolTesterBean mbean = new ToolTesterBean();
            if (!server.isRegistered(getMbeanNameInstance()))
            {
                server.registerMBean(mbean, getMbeanNameInstance());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static ObjectName getMbeanNameInstance()
    {
        if (mbeanNameInstance == null)
        {
            synchronized (ToolTesterUtils.class)
            {
                try
                {
                    mbeanNameInstance = new ObjectName(OBJECT_NAME);
                }
                catch (MalformedObjectNameException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return mbeanNameInstance;
    }

    public static Object getCommonLock()
    {
        return commonLock;
    }
}
