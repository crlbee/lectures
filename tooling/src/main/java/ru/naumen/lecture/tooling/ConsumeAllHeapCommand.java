package ru.naumen.lecture.tooling;

import java.util.ArrayList;

public class ConsumeAllHeapCommand implements IToolTesterCommand
{
    private ArrayList<Person> persons = new ArrayList<>();

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
        for (int i = 0; i < 10; i++)
        {
            HeapConsumtionThread t = new HeapConsumtionThread(10000);
            t.start();
        }
        while (true)
        {
            persons.add(Person.createSomePerson());
            if (persons.size() % 1000 == 0)
            {
                System.out.println(persons.size());
            }
        }
    }
}
