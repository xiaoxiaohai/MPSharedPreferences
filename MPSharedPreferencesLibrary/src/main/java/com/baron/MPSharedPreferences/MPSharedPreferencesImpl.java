package com.baron.MPSharedPreferences;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;


/**
 * Created by xiaohai1 on 2017/3/30.
 */

public class MPSharedPreferencesImpl implements MPSharedPreferences {
    private static final String TAG = "SharedPreferencesImpl";

    private final int mMode;
    private String spName = "";
    private Context context;

    MPSharedPreferencesImpl(String name, int mode , Context context) {
        mMode = mode;
        spName = name;
        this.context = context ;
    }

    @Nullable
    public String getString(String key, @Nullable String defValue) {
        String res = MPProxy.getIns(context).queryProvidor(spName, mMode, key);
        if (TextUtils.isEmpty(res)) {
            return defValue;
        }
        return res;
    }


    public int getInt(String key, int defValue) {
        String res = MPProxy.getIns(context).queryProvidor(spName, mMode, key);
        if (TextUtils.isEmpty(res)) {
            return defValue;
        }
        try {
            return Integer.parseInt(res);
        } catch (Exception e) {
            return defValue;
        }
    }

    public long getLong(String key, long defValue) {
        String res = MPProxy.getIns(context).queryProvidor(spName, mMode, key);
        if (TextUtils.isEmpty(res)) {
            return defValue;
        }
        try {
            return Long.parseLong(res);
        } catch (Exception e) {
            return defValue;
        }
    }

    public float getFloat(String key, float defValue) {
        String res = MPProxy.getIns(context).queryProvidor(spName, mMode, key);
        if (TextUtils.isEmpty(res)) {
            return defValue;
        }
        try {
            return Float.parseFloat(res);
        } catch (Exception e) {
            return defValue;
        }
    }

    public boolean getBoolean(String key, boolean defValue) {
        String res = MPProxy.getIns(context).queryProvidor(spName, mMode, key);
        if (TextUtils.isEmpty(res))
            return defValue;
        try {
            return Boolean.parseBoolean(res);
        } catch (Exception e) {
            return defValue;
        }
    }

    public Editor edit() {
        return new EditorImpl(spName, mMode);
    }

    public class EditorImpl implements MPSharedPreferences.Editor {

        private String name;
        private int mode;

        public EditorImpl(String name, int Mode) {
            this.name = name;
            this.mode = Mode;
        }

        public MPSharedPreferences.Editor putString(String key, @Nullable String value) {
            IOptionProxy.getIns(context).updateValue(name, mode, MPConfigProvider.OPT_PUT, key, value);
            return this;
        }

        public MPSharedPreferences.Editor putInt(String key, int value) {
            IOptionProxy.getIns(context).updateValue(name, mode, MPConfigProvider.OPT_PUT, key, value + "");
            return this;
        }

        public MPSharedPreferences.Editor putLong(String key, long value) {
            IOptionProxy.getIns(context).updateValue(name, mode, MPConfigProvider.OPT_PUT, key, value + "");
            return this;
        }

        public MPSharedPreferences.Editor putFloat(String key, float value) {
            IOptionProxy.getIns(context).updateValue(name, mode, MPConfigProvider.OPT_PUT, key, value + "");
            return this;
        }

        public MPSharedPreferences.Editor putBoolean(String key, boolean value) {
            IOptionProxy.getIns(context).updateValue(name, mode, MPConfigProvider.OPT_PUT, key, value + "");
            return this;
        }

        public MPSharedPreferences.Editor remove(String key) {
            IOptionProxy.getIns(context).updateValue(name, mode, MPConfigProvider.OPT_REMOVE, key, "");
            return this;
        }


        public MPSharedPreferences.Editor clear() {
            synchronized (this) {
                return this;
            }
        }

        @Override
        public boolean commit() {
            return true;
        }

        @Override
        public void apply() {

        }
    }
}


