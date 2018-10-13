package ru.naumen.lecture.tooling;

import java.lang.management.ManagementFactory;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

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
    public String getCode()
    {
        return "LoadCPU";
    }

    @Override
    public void execute()
    {
        String objectName = "ru.naumen.lecture.tooling:type=SomeMBeanImpl";

        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        try
        {

            ObjectName mbeanName = new ObjectName(objectName);

            ToolTesterBean mbean = new ToolTesterBean();

            server.registerMBean(mbean, mbeanName);

            Set<ObjectInstance> instances;
            instances = server.queryMBeans(new ObjectName(objectName), null);
            ObjectInstance instance = (ObjectInstance)instances.toArray()[0];
            System.out.println("Class Name:t" + instance.getClassName());
            System.out.println("Object Name:t" + instance.getObjectName());

//            long summ = 0;
//            Random random = new Random();
//            while (true)
//            {
//                summ += random.nextLong();
//            }
            //System.out.println(Long.toString(summ));


        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
