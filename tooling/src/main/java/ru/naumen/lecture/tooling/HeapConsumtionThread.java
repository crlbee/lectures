package ru.naumen.lecture.tooling;

import java.util.ArrayList;

public class HeapConsumtionThread extends Thread 
{
    private int maxArraySize;

    public HeapConsumtionThread(int maxArraySize)
    {
        this.maxArraySize = maxArraySize;
    }

    @Override
    public void run()
    {
        ArrayList<Person> persons = new ArrayList<>();
        while (!ConsumeAllHeapCommand.STOP)
        {
            persons.add(Person.createSomePerson());
            if (persons.size() % (maxArraySize / 10) == 0)
            {
                System.out.println(persons.size());
            }
            if (persons.size() >= maxArraySize)
            {
                persons = new ArrayList<>();
            }
        }
    }
}
