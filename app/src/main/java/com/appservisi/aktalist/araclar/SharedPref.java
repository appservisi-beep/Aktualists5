package com.appservisi.aktalist.araclar;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    static final String PREF_NAME="DOSYA";


    public void stringkaydet(Context context,String key,String deger){

        SharedPreferences setting=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=setting.edit();
        editor.putString(key,deger);
        editor.commit();
    }

    public String stringgetir(Context context,String key){

        SharedPreferences setting=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        String donendeger=setting.getString(key,null);
        return donendeger;
    }

    public void intkaydet(Context context, String key,int deger){

        SharedPreferences setting=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=setting.edit();
        editor.putInt(key,deger);
        editor.commit();
    }

    public int intgetir(Context context,String key){

        SharedPreferences setting=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        int donendeger=setting.getInt(key,99);
        return donendeger;
    }

    public void sil(Context context){
        SharedPreferences setting=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=setting.edit();
        editor.clear();
        editor.commit();

    }


    public void kaldir(Context context){
        SharedPreferences setting=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=setting.edit();
        editor.remove(PREF_NAME);
        editor.commit();

    }

}
