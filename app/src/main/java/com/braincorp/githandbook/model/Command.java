package com.braincorp.githandbook.model;

/**
 * Command class
 * Created by Alan Camargo - December 2016
 */
public class Command
{

    private int id;
    private String title, meaning, parameter;

    /**
     * Creates an instance of Command
     * @param id - the ID
     * @param title - the title
     * @param parameter - the parameter, if any
     * @param meaning - the meaning
     */
    public Command(int id, String title, String parameter, String meaning)
    {
        this.id = id;
        this.title = title;
        this.parameter = parameter;
        this.meaning = meaning;
    }

    /**
     * Gets the ID
     * @return ID
     */
    public int getId()
    {
        return id;
    }

    /**
     * Gets the title
     * @return title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Gets the meaning
     * @return meaning
     */
    public String getMeaning()
    {
        return meaning;
    }

    /**
     * Gets the parameter
     * @return parameter
     */
    public String getParameter()
    {
        return parameter;
    }

}
