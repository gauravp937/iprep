package com.example.iPrep.Fragment1;

public class VideoLecturesModel {
    String name,topicName,onlineLink,id;
    String topics;

    public VideoLecturesModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getOnlineLink() {
        return onlineLink;
    }

    public void setOnlineLink(String onlineLink) {
        this.onlineLink = onlineLink;
    }
}

