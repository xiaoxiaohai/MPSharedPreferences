package com.baron.MPSharedPreferences;

import android.database.AbstractCursor;

import java.util.HashMap;

/**
 * Created by Steve on 2016/7/6.
 */
public class MPConfigCusor extends AbstractCursor{


    private HashMap<String, String> mCursorMap = new HashMap<String, String>();
    private String[] mProjection;

    MPConfigCusor(String[] keys, HashMap<String, String> map){
        initMap(keys, map);
    }

    private void initMap(String[] keys, HashMap<String, String> map) {
        mProjection = new String[keys.length];
        System.arraycopy(keys, 0, mProjection, 0, keys.length);
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            String value = map.get(key);
            mCursorMap.put(key, value);
        }
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public int getCount() {
        int count = 0;
        if (mProjection != null)
            count = mProjection.length;
        return count;
    }

    @Override
    public String[] getColumnNames() {
        String[] names = null;
        if (mProjection != null)
            names = mProjection;
        return names;
    }

    @Override
    public String getString(int column) {
        if (column >= mProjection.length) {
            new Exception("TclConfigCusor Column Out Of Index");
        }
        String key = mProjection[column];
        String value = mCursorMap.get(key);
        return value;
    }

    @Override
    public short getShort(int column) {
        return 0;
    }

    @Override
    public int getInt(int column) {
        return 0;
    }

    @Override
    public long getLong(int column) {
        return 0;
    }

    @Override
    public float getFloat(int column) {
        return 0;
    }

    @Override
    public double getDouble(int column) {
        return 0;
    }

    @Override
    public boolean isNull(int column) {
        return false;
    }
}
