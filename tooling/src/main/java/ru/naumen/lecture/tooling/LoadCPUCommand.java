package ru.naumen.lecture.tooling;

import java.lang.management.ManagementFactory;
import java.util.Random;

import javax.management.MBeanServer;

public class LoadCPUCommand implements IToolTesterCommand
{
    public static interface ToolTesterBeanMBean
    {
        public boolean isStopped();
        public void setStopped(boolean stopped);
    }
    
    static class ToolTesterBean implements ToolTesterBeanMBean
    {
        boolean stopped = false;

        @Override
        public boolean isStopped()
        {
            return stopped;
        }

        @Override
        public void setStopped(boolean stopped)
        {
            this.stopped = stopped;
        }
    }

    @Override
    public String getName()
    {
        return "Нагрузи проц";
    }

    @Override
    public State getTargetState()
    {
        return State.HIGH_CPU_LOAD;
    }

    @Override
    public void execute()
    {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        try
        {
            long summ = 0;
            Random random = new Random();
            while (!Boolean.TRUE.equals(server.getAttribute(ToolTesterUtils.getMbeanNameInstance(), "Stopped")))
            {
                summ += random.nextLong();
            }
            System.out.println(Long.toString(summ));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
