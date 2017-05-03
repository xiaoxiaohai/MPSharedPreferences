package com.baron.MPSharedPreferences;

import android.content.Context;

/**
 * Created by xiaohai1 on 2017/5/3.
 */

public abstract class IOptionProxy {

    private static IOptionProxy putProxy ;

    private static SharePProxy sharePProxy ;
    private static MPProxy mpProxy ;

    protected synchronized static  IOptionProxy getIns(Context context){
        if (sharePProxy == null)
            sharePProxy = new SharePProxy(context) ;

        if (mpProxy == null)
            mpProxy = new MPProxy(context) ;

        if (putProxy == null){
            putProxy =  MPConfigProvider.isInit() ? sharePProxy : mpProxy ;
        }

        return putProxy ;
    }

    abstract protected void updateValue(final String name , final int mode , final int opt ,final  String key,final  String value) ;

    abstract protected boolean execUpdate(String name , int mode , int opt , String key, String value) ;

    abstract protected String queryProvidor(String Name , int Mode , String key) ;
}
