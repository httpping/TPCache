package com.json.tanping.tpcache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.idescout.sql.SqlScoutServer;
import com.tp.cache.CacheManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqlScoutServer.create(this, getPackageName());



        CacheManager.init(this);
        String key = "xx";
        int value = 100;

        CacheManager.count();


        int abc = CacheManager.get("tp",0);

        CacheManager.put(key,value,1000);
        int a = 0;
        value = CacheManager.get(key,0);

        CacheManager.put("double",1.30d);
        double dd =  CacheManager.get("double");

        CacheManager.put("bbe",true);
        boolean bbe =  CacheManager.get("bbe");
        CacheManager.put("object",new DemoBean());
        DemoBean okBean =  CacheManager.get("object");


        CacheManager.put("nil",null);
        String nil = CacheManager.get("nul");


        List<DemoBean> list = new ArrayList<>();
        list.add(okBean);
        list.add(okBean);
        list.add(okBean);
        list.add(okBean);
        list.add(okBean);

     /*   CacheManager.put("list",beans);
        beans = CacheManager.get("list");*/

        int max = 1000;
        long start = System.currentTimeMillis();
        for (int i=0 ;i< max ;i ++){
//            CacheManager.put("object",list);
        }
        long end = System.currentTimeMillis();


        Log.d("cache-time",(end-start) +" s");


        start = System.currentTimeMillis();
        for (int i=0 ;i< max ;i ++){
            CacheManager.get("object",list);
        }
        end = System.currentTimeMillis();
        Log.d(" qps  cache-time",(end-start) +" s");

    }
}
