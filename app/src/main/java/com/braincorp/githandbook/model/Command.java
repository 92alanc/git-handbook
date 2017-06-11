package com.braincorp.githandbook.model;

/**
 * Command class
 * Created by Alan Camargo - December 2016
 */
public class Command {

    private int id;
    private String title;

    /**
     * Creates an instance of Command
     * @param id - the ID
     * @param title - the title
     */
    public Command(int id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * Gets the ID
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the title
     * @return title
     */
    public String getTitle() {
        return title;
    }

}
