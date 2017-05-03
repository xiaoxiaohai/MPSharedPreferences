package com.baron.MPSharedPreferences;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by Steve on 2016/7/6.
 */
public class MPConfigProvider extends ContentProvider{

    public static final int OPT_PUT = 1 ;
    public static final int OPT_REMOVE = 2  ;

    public static final String CONTENT_KEY = "config_key";
    public static final String CONTENT_VALUE = "config_value";
    public static final String CONTENT_NAME = "config_name" ;
    public static final String CONTENT_OPT = "config_opt" ;
    public static final String CONTENT_MODE = "config_mode" ;
    private MPConfigCursorFactory mFactory;

    private static boolean init = false ;


    public static boolean isInit(){
        return init ;
    }


    @Override
    public boolean onCreate() {
        mFactory = MPConfigCursorFactory.getInstance(getContext());
        init = true ;
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
//        try {
//            if (mFactory != null)
//                cursor = mFactory.queryData(projection);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
        return cursor;
    }



    @Nullable
    @Override
    public String getType(Uri uri) {
        List<String> keys = uri.getPathSegments();
        String name = keys.get(0) ;
        int mode = Integer.valueOf(keys.get(1)) ;
        String key = keys.get(2) ;
        String value = null;
        if (mFactory != null)
            value = mFactory.getData(name , mode ,key);
        return value;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        try {
            String spName = values.getAsString(CONTENT_NAME) ;
            int opt = values.getAsInteger(CONTENT_OPT) ;
            int mode = values.getAsInteger(CONTENT_MODE)  ;
            String key = (String) values.get(CONTENT_KEY);
            String value = (String) values.get(CONTENT_VALUE);
            if (mFactory != null){
                if (opt == MPConfigProvider.OPT_PUT){
                    mFactory.setData(spName  , mode , key, value);
                }else if (opt == MPConfigProvider.OPT_PUT){
                    mFactory.removeData(spName , mode , key);
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
