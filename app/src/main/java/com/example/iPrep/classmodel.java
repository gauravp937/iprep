package com.example.iPrep;

public class classmodel {

    String clss;
    String icon;
    String subject;
    Long id;


    public classmodel() {
    }


    public String getClss() {
        return clss;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClss(String clss) {
        this.clss = clss;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
