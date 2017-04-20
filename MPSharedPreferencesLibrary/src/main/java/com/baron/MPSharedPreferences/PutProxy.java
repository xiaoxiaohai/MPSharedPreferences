package com.baron.MPSharedPreferences;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;

/**
 * Created by xiaohai1 on 2017/4/20.
 */

public class PutProxy {

    private static PutProxy putProxy ;
    private HandlerThread handlerThread ;
    private Handler mHandler ;
    private ContentResolver contentResolver ;

    protected synchronized static  PutProxy getIns(Context context){
        if (putProxy == null)
            putProxy = new PutProxy(context) ;
        return putProxy ;
    }

    protected PutProxy(Context context) {
        contentResolver = context.getContentResolver() ;
        handlerThread = new HandlerThread("sp writer") ;
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper()) ;
    }

    protected void updateValue(final String name , final int mode , final int opt ,final  String key,final  String value) {
        if (!execUpdate(name , mode , opt , key ,value)){
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    execUpdate(name , mode , opt , key ,value) ;
                }
            } , 50) ;
        }
    }

    private boolean execUpdate(String name , int mode , int opt , String key, String value){

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(key)){
            throw new RuntimeException( "SP Name || Key  Can not be null " ) ;
        }
        try {
            ContentValues cv = new ContentValues();
            cv.put(MPConfigProvider.CONTENT_KEY, key);
            cv.put(MPConfigProvider.CONTENT_VALUE, value);
            cv.put(MPConfigProvider.CONTENT_NAME , name);
            cv.put(MPConfigProvider.CONTENT_MODE ,  mode) ;
            cv.put(MPConfigProvider.CONTENT_OPT , opt);
            contentResolver.insert(Uri.parse(MPConfigProvider.URI_CONFIG), cv);
            return true ;
        }catch (Exception e){
            return false ;
        }
    }

    protected String queryProvidor(String Name , int Mode , String key) {
        if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(key)){
            throw new RuntimeException( "SP Name || Key  Can not be null " ) ;
        }
        try {
            Uri uri = Uri.parse(MPConfigProvider.URI_CONFIG + "/" + Name + "/" + Mode+  "/" + key);
            return contentResolver.getType(uri);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
