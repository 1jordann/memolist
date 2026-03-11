package com.example.memolist;

public class Memo {

    private int id;
    private String title;
    private String description;
    private String priority;
    private String date;

    public Memo(int id, String title, String description, String priority, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public String getDate() {
        return date;
    }
}