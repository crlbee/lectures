package ru.naumen.lecture.tooling;

public enum State
{
    ALL_RIGHT("Спасибо, все ок!", false), 
    SAME_AS_CURRENT("Текущее состояние", false),
    DEADLOCK("Похоже, все заблокировалось!", true),
    HIGH_MEMORY_CONSUPTION("Мне плохо, память заканчивается!", true), 
    HIGH_CPU_LOAD("Мне плохо, процессор сильно нагружен!", true);

    State(String title, boolean error)
    {
        this.title = title;
        this.error = error;
    }

    String title;
    private boolean error;

    String getTitle()
    {
        return title;
    }

    public boolean isError()
    {
        return error;
    }
}
