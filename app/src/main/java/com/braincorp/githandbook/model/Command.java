package com.braincorp.githandbook.model;

import java.util.ArrayList;

/**
 * Command class
 * Created by Alan Camargo - December 2016
 */
public class Command
{

    private int id;
    private String title, meaning;
    private ArrayList<String> params;

    /**
     * Creates an instance of Command
     * @param id - the ID
     * @param title - the title
     * @param params - the parameters, if any
     * @param meaning - the meaning
     */
    public Command(int id, String title, ArrayList<String> params, String meaning)
    {
        this.id = id;
        this.title = title;
        this.params = params;
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
     * Gets the parameters
     * @return parameters
     */
    public ArrayList<String> getParams()
    {
        return params;
    }

    /**
     * Sets the parameters
     * @param params - the parameters
     */
    public void setParams(ArrayList<String> params)
    {
        this.params = params;
    }

}
