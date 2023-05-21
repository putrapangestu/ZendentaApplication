package com.example.zendenta.SaveData;

import android.content.Context;
import android.content.SharedPreferences;
import android.service.autofill.UserData;

import com.example.zendenta.Model.DataUser;
import com.example.zendenta.View.Profile;

import java.util.ArrayList;
import java.util.List;

public class UserSave {
   private static SharedPreferences sharedPreferences;
   private static SharedPreferences.Editor editor;

    private static DataUser  data;

    public static void load() {
        data = Profile.user;
        // use SharedPreferences to retrieve all your data


    }

    public static void save() {
        // save all contents from data
    }
}
