package com.firebase.allcode.Model;

public class FirebaseModel {

    String android_id,status;

    public FirebaseModel(Object value) {
    }

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FirebaseModel(String android_id, String status) {
        this.android_id = android_id;
        this.status = status;
    }
}
