package ru.naumen.lecture.tooling;

public enum State
{
    ALL_RIGHT("Спасибо, все ок!", false, true), 
    SAME_AS_CURRENT("Текущее состояние", false, true),
    DO_SOME_USEFUL_WORK("Делаю некоторую полезную работу", false, false),
    LONG_OPERATION_UNDER_LOCK("Осуществляется длительная работа под блокировкой"),
    DEADLOCK("Похоже, все заблокировалось!"),
    HIGH_MEMORY_CONSUPTION("Мне плохо, память заканчивается!"), 
    SINGLE_THREAD_HIGH_CPU_LOAD("Мне плохо, процессор сильно нагружен!"),
    MULTI_THREAD_HIGH_CPU_LOAD("Мне плохо, процессор сильно нагружен!");

    State(String title)
    {
        this(title, true, false);
    }

    State(String title, boolean error, boolean service)
    {
        this.title = title;
        this.error = error;
        this.service = service;
    }

    String title;
    private boolean error;
    private boolean service;

    String getTitle()
    {
        return title;
    }

    public boolean isError()
    {
        return error;
    }

    public boolean isService()
    {
        return service;
    }

}
