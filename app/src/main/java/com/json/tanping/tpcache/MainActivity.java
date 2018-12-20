package com.json.tanping.tpcache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.idescout.sql.SqlScoutServer;
import com.tp.cache.CacheManager;

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

        CacheManager.put("object",new DemoBean());
        DemoBean okBean =  CacheManager.get("object");

     /*   CacheManager.put("list",beans);
        beans = CacheManager.get("list");*/
    }
}
