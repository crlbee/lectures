package ru.naumen.lecture.tooling;

import java.util.ArrayList;

public class EatAllHeapCommand implements IToolTesterCommand
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
    public String getCode()
    {
        return "EatAllHeap";
    }

    @Override
    public void execute()
    {
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
