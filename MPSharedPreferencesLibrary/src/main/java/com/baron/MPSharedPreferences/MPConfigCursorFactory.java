package com.baron.MPSharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Steve on 2016/7/6.
 */
public class MPConfigCursorFactory {

    private static MPConfigCursorFactory mTclConfigCusorFactory;

    synchronized static MPConfigCursorFactory getInstance(Context context) {
        if (mTclConfigCusorFactory == null) {
            mTclConfigCusorFactory = new MPConfigCursorFactory(context);
        }
        return mTclConfigCusorFactory;
    }

    private Context context ;

    private MPConfigCursorFactory(Context context) {
        this.context = context ;
    }


    synchronized MPConfigCusor queryData(String name , int mode ,String[] keys) {
        SharedPreferences sp = context.getSharedPreferences(name , mode) ;
        HashMap mCache = new HashMap<>() ;
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            if (!mCache.containsKey(key)) {
                String value = sp.getString(key, null);
                mCache.put(key, value);
            }
        }
        return new MPConfigCusor(keys, mCache);
    }

    synchronized void setData(String name , int mode , String key, String value) {
        SharedPreferences.Editor edit = context.getSharedPreferences(name , mode).edit();
        edit.putString(key, value);
        edit.commit();
    }

    synchronized void removeData(String name , int mode , String key){
        context.getSharedPreferences(name , mode).edit().remove(key).commit() ;
    }

    synchronized String getData(String name , int mode , String key) {
        String value = context.getSharedPreferences(name, mode).getString(key , "");
        return value;
    }



}
