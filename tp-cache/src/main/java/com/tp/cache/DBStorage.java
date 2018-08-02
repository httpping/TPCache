package com.tp.cache;

import android.content.Context;

import com.tp.cache.hawk.Storage;

import java.util.List;

/**
 * Db 缓存模式
 */
public final class DBStorage implements Storage {

    private CacheManageDao cacheManageDao;

    public DBStorage(Context context) {
        cacheManageDao = CacheManageDao.getInstance(context);
    }



    @Override
    public <T> boolean put(String key, T value) {
        DbCacheBean cacheBean = new DbCacheBean();
        cacheBean.t_key = key;
        cacheBean.t_value = String.valueOf(value);
        cacheManageDao.saveOrUpdate(cacheBean);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        DbCacheBean bean = cacheManageDao.findByKey(key);
        if (bean != null) {
            return (T) bean.t_value;
        }
        return null;
    }

    @Override
    public boolean delete(String key) {
        return cacheManageDao.delete(key);
    }

    @Override
    public boolean contains(String key) {
        List<DbCacheBean> result = cacheManageDao.isExistence(key);
        if (StringUtil.isNotEmpty(result)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        return cacheManageDao.deleteAll();
    }

    @Override
    public long count() {
        return cacheManageDao.getCount();
    }


}
