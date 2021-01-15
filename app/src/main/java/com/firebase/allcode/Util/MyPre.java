package com.firebase.allcode.Util;


import android.app.Application;
import android.content.SharedPreferences;

public class MyPre extends Application {


    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences("my", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void setAndroidfilepos(String Androidfilepos) {
        editor.putString("Androidfilepos", Androidfilepos).commit();
    }

    public static String getAndroidfilepos() {
        return sharedPreferences.getString("Androidfilepos", "");
    }

    public static void setAndroidmaincatename(String Androidmaincatename) {
        editor.putString("Androidmaincatename", Androidmaincatename).commit();
    }

    public static String getAndroidmaincatename() {
        return sharedPreferences.getString("Androidmaincatename", "");
    }

    public static void setAndroidID(String AndroidID) {
        editor.putString("AndroidID", AndroidID).commit();
    }

    public static String getAndroidID() {
        return sharedPreferences.getString("AndroidID", "");
    }


    public static void setFirebaseKEY(String FirebaseKEY) {
        editor.putString("FirebaseKEY", FirebaseKEY).commit();
    }

    public static String getFirebaseKEY() {
        return sharedPreferences.getString("FirebaseKEY", "");
    }


    public static void setandroid_tutorial_status(String android_tutorial_status) {
        editor.putString("android_tutorial_status", android_tutorial_status).commit();
    }

    public static String getandroid_tutorial_status() {
        return sharedPreferences.getString("android_tutorial_status", "");
    }


    public static void setlayout_status(String layout_status) {
        editor.putString("layout_status", layout_status).commit();
    }

    public static String getlayout_status() {
        return sharedPreferences.getString("layout_status", "");
    }


    public static void setapp_code_status(String app_code_status) {
        editor.putString("app_code_status", app_code_status).commit();
    }

    public static String getapp_code_status() {
        return sharedPreferences.getString("app_code_status", "");
    }


    public static void setandroid_tutorial_Todaydate(String android_tutorial_Todaydate) {
        editor.putString("android_tutorial_Todaydate", android_tutorial_Todaydate).commit();
    }

    public static String getandroid_tutorial_Todaydate() {
        return sharedPreferences.getString("android_tutorial_Todaydate", "");
    }


    public static void setandroid_tutorial_downlod_date(String android_tutorial_downlod_date) {
        editor.putString("android_tutorial_downlod_date", android_tutorial_downlod_date).commit();
    }

    public static String getandroid_tutorial_downlod_date() {
        return sharedPreferences.getString("android_tutorial_downlod_date", "");
    }


      public static void setlayout_Todaydate(String layout_Todaydate) {
        editor.putString("layout_Todaydate", layout_Todaydate).commit();
    }

    public static String getlayout_Todaydate() {
        return sharedPreferences.getString("layout_Todaydate", "");
    }


    public static void setlayout_downlod_date(String layout_downlod_date) {
        editor.putString("layout_downlod_date", layout_downlod_date).commit();
    }

    public static String getlayout_downlod_date() {
        return sharedPreferences.getString("layout_downlod_date", "");
    }


  public static void setsub_cate_list_title(String sub_cate_list_title) {
        editor.putString("sub_cate_list_title", sub_cate_list_title).commit();
    }

    public static String getsub_cate_list_title() {
        return sharedPreferences.getString("sub_cate_list_title", "");
    }


















    public static void setStartvalue(boolean Startvalue) {
        editor.putBoolean("Startvalue", Startvalue).commit();
    }

    public static boolean getStartvalue() {
        return sharedPreferences.getBoolean("Startvalue", false);

    }


}