package com.kcv.kcv;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {

        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setUserId(String userId) {
        prefs.edit().putString("userId", userId).commit();
    }
    public void setEmail(String email) {
        prefs.edit().putString("email", email).commit();
    }
    public void setMobile(String mobile) {
        prefs.edit().putString("mobile", mobile).commit();
    }
    public void setName(String name) {
        prefs.edit().putString("name", name).commit();
    }
    public void setUserType(String userType) {
        prefs.edit().putString("userType", userType).commit();
    }
    public void setShowInfo(Boolean showInfo) {
        prefs.edit().putBoolean("showInfo", showInfo).commit();
    }
    public void setcurPage(int curPage) {
        prefs.edit().putInt("curPage", curPage).commit();
    }


    public String getUserId() {
        String userId = prefs.getString("userId","");
        return userId;
    }
    public String getEmail() {
        String email = prefs.getString("email","");
        return email;
    }
    public String getMobile() {
        String mobile = prefs.getString("mobile","");
        return mobile;
    }
    public String getName() {
        String name = prefs.getString("name","");
        return name;
    }
    public String getUserType() {
        String userType = prefs.getString("userType","");
        return userType;
    }
    public Boolean getShowInfo() {
        Boolean showInfo = prefs.getBoolean("showInfo",true);
        return showInfo;
    }public int getcurPage() {
        int curPage = prefs.getInt("curPage",1);
        return curPage;
    }
}