package ru.naumen.lecture.tooling;

import java.util.ArrayList;

public class ConsumeAllHeapCommand implements IToolTesterCommand
{
    private ArrayList<Person> persons = new ArrayList<>();

    public static volatile boolean STOP = false;

    @Override
    public String getName()
    {
        return "Съешь всю память";
    }

    @Override
    public State getTargetState()
    {
        return State.HIGH_MEMORY_CONSUPTION;
    }

    @Override
    public void execute()
    {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++)
        {
            HeapConsumtionThread t = new HeapConsumtionThread(10000);
            t.start();
        }
        try
        {
            while (true)
            {
                persons.add(Person.createSomePerson());
                if (persons.size() % 1000 == 0)
                {
                    ToolTester.LOG.info(persons.size());
                }
            }
        }
        catch (OutOfMemoryError oome)
        {
            STOP = true;
            persons = new ArrayList<>();
            ToolTester.LOG.error("OutOfMemoryError: " + oome.getMessage());
            ToolTester.LOG.info("Total time: " + (System.currentTimeMillis() - startTime) / 1000 + " sec.");
        }
    }
}
