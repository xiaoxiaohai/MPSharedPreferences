package com.baron.MPSharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by xiaohai1 on 2017/4/20.
 */

public class MPPreferenceManager {


    protected static  String URI_CONFIG ;
    private static Context sContext  ;

    /**
     * Retrieve and hold the contents of the preferences file 'name', returning
     * a SharedPreferences through which you can retrieve and modify its
     * values.  Only one instance of the SharedPreferences object is returned
     * to any callers for the same name, meaning they will see each other's
     * edits as soon as they are made.
     *
     * @param name Desired preferences file. If a preferences file by this name
     * does not exist, it will be created when you retrieve an
     * editor (SharedPreferences.edit()) and then commit changes (Editor.commit()).
     * @param mode Operating mode.  Use 0 or {@link Context#MODE_PRIVATE} for the
     * default operation.
     *
     * @return The single {@link SharedPreferences} instance that can be used
     *         to retrieve and modify the preference values.
     *
     * @see Context#MODE_PRIVATE
     */
    public static MPSharedPreferences getSharedPreferences(String name, int mode ){

        if (sContext == null || TextUtils.isEmpty(URI_CONFIG)){
            throw new RuntimeException("MPPreferenceManager need init") ;
        }

        return  new MPSharedPreferencesImpl(name , mode , sContext) ;
    }

    public synchronized static void init(Context context , String authorities){
        if (context == null || TextUtils.isEmpty(authorities)){
            throw new RuntimeException("context & authorities can not be null ") ;
        }

        if (sContext == null){
            sContext = context ;
        }

        if (TextUtils.isEmpty(URI_CONFIG)){
            URI_CONFIG =  "content://" + authorities ;
        }
    }

}
