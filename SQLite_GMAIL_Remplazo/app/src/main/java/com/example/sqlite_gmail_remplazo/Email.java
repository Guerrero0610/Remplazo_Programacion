package com.example.sqlite_gmail_remplazo;

public class Email {
    private long id;
    private String sender;
    private String subject;
    private String date;
    private String time;
    private String content;
    private int icon;

    public Email(long id, String sender, String subject, String date, String time, String content, int icon) {
        this.id = id;
        this.sender = sender;
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.content = content;
        this.icon = icon;
    }

    public Email() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
