package ru.naumen.lecture.tooling;

public class ToolTesterUtils
{
    private static final long DEFAULT_PAUSE = 1000L;

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
}
