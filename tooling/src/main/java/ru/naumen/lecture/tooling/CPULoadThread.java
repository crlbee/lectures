package ru.naumen.lecture.tooling;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Random;

import javax.management.MBeanServer;

public class CPULoadThread extends Thread
{
    private boolean needConsumeHeap;
    private ArrayList<Long> randoms = new ArrayList<>();
    
    public CPULoadThread()
    {
        this(false);
    }
    
    public CPULoadThread(boolean needConsumeHeap)
    {
        this.setNeedConsumeHeap(needConsumeHeap);
    }
    
    @Override
    public void run()
    {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        try
        {
            long summ = 0;
            Random random = new Random();
            while (!Boolean.TRUE.equals(server.getAttribute(ToolTesterUtils.getMbeanNameInstance(), ToolTesterUtils.STOPPED_ATTR)))
            {
                long nextLong = random.nextLong(); 
                summ += nextLong;
                if(isNeedConsumeHeap())
                {
                    randoms.add(nextLong);
                    if(randoms.size() % 10000 == 0)
                    {
                        randoms.clear();
                    }
                }
            }
            ToolTester.LOG.info(Long.toString(summ));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean isNeedConsumeHeap()
    {
        return needConsumeHeap;
    }

    public void setNeedConsumeHeap(boolean needConsumeHeap)
    {
        this.needConsumeHeap = needConsumeHeap;
    }
}
