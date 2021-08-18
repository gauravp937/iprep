package com.example.iPrep.Fragment1;

public class videosmodel {
    String link;
    String name;
    String topicname;
    long id;

    public videosmodel() {
    }

    public String getLink() {
        return link;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }
}
