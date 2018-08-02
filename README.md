# TPCache
TPCache for hawk 拓展 db storage

# 使用地址
https://bintray.com/gbtp/TPCache/TPCache
```java
compile 'gb.tp.cache:TPCache:1.0.1'
```

```java
  //init化
   CacheManager.init(this);
   
        String key = "xx";
        int value = 100;

        CacheManager.count();

        CacheManager.put(key,value,10);
        int a = 0;
        value = CacheManager.get(key,0);

        CacheManager.put("double",1.30d);
        double dd =  CacheManager.get("double");

        CacheManager.put("object",new DemoBean());
        DemoBean okBean =  CacheManager.get("object");
```

# 存储支持类型
  1、基础类型
  2、对象类型
  3、List、Set
 
# 不支持类型
  嵌套泛型
