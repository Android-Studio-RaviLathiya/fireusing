package com.firebase.allcode.Model;

public class DownModel {

    String name,uri;

    public DownModel(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "DownModel{" +
                "name='" + name + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}

