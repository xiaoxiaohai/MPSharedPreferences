package com.baron.MPSharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xiaohai1 on 2017/5/3.
 */

public class SharePProxy extends IOptionProxy {

    private Context context ;

    public SharePProxy(Context context) {
        this.context = context ;
    }

    @Override
    protected void updateValue(String name, int mode, int opt, String key, String value) {
        execUpdate(name , mode , opt , key , value) ;
    }

    @Override
    protected boolean execUpdate(String name, int mode, int opt, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(name , mode).edit() ;
        if (opt == MPConfigProvider.OPT_PUT){
            editor.putString(key , value) ;
        }else {
            editor.remove(key) ;
        }
        return editor.commit() ;
    }

    @Override
    protected String queryProvidor(String name, int mode, String key) {
        SharedPreferences sharedPreferences =  context.getSharedPreferences(name , mode);
        return sharedPreferences.getString(key , null) ;
    }
}
