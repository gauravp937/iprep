package com.example.iPrep.Fragment2;

public class bookmodel {
    String link;
    String name;
    String topicname;
    Long id;

    public bookmodel() {
    }

    public String getLink() {
        return link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
