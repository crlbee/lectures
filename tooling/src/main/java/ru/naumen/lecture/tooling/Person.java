package ru.naumen.lecture.tooling;

public class Person
{
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int DEF_MAX = 10;

    private enum GENDER
    {
        MALE, FEMALE
    }

    private String firstName;
    private String lastName;
    private int age;
    private GENDER gender;

    private Person(String firstName, String lastName, int age, GENDER gender)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public static Person createSomePerson()
    {
        return new Person(createRndString(), createRndString(), rndInt(100), GENDER.values()[rndInt(2)]);
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getAge()
    {
        return age;
    }

    public GENDER getGender()
    {
        return gender;
    }

    public static String createRndString()
    {
        return createRndString(DEF_MAX);
    }

    public static String createRndString(int count)
    {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0)
        {
            builder.append(ALPHA_NUMERIC_STRING.charAt(rndInt(ALPHA_NUMERIC_STRING.length())));
        }
        return builder.toString();
    }

    private static int rndInt(int max)
    {
        return (int)(Math.random() * max);
    }

}
