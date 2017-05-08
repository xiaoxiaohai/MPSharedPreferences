MPSharedPreferences
===========================
该项目主要解决多进程 SharedPreferences 数据同步的问题 ，如果你的项目使用的是单进程则没必要使用

****
### Author:Baron
### E-mail:Barongeng@tcl.com
****

## 使用方法

1. 配置Gradlw:
```gradle
compile 'com.mpsp:MPSharedPreferences:1.0.3'
```

2. 在 AndroidManifest.xml 添加 MPConfigProvider， authorities & process 自己定义
```
<provider
    android:authorities="************************************"
    android:process=":remote"
    android:name="com.baron.MPSharedPreferences.MPConfigProvider">
</provider>
```

3. 在 Application 中初始化

``` java
 MPPreferenceManager.init(getApplicationContext() , "AndroidManifest中MPConfigProvider的authorities");
```

4. 使用
``` java
MPSharedPreferences mpSharedPreferences = MPPreferenceManager.getSharedPreferences("spName" , Context.MODE_PRIVATE ) ;
// MPSharedPreferences 中添加数据
mpSharedPreferences.edit().putString("keyName" , "value").commit() ;

// MPSharedPreference 中读取数据
mpSharedPreferences.getString("keyName" , "defaultValue") ;

