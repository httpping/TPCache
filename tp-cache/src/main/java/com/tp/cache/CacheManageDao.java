package com.tp.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 生气了缓存存储 - 可序列化
 * 
 * @author tanping
 * 
 */
public class CacheManageDao implements Serializable {

	public static final String TAG = "CacheManageDao";
	
	private static CacheManageDao instance;
	private SQLiteDatabase db;

	private CacheManageDao(Context context) {
		db = DatabaseHelper.getInstance(context).getWritableDatabase();
		System.out.println("db----->" + db);
	}

	public static CacheManageDao getInstance(Context context) {
		if (instance == null) {
			instance = new CacheManageDao(context);
		}
		return instance;
	}

	/**
	 * 插入或
	 * 
	 * @param pushBean
	 * @return
	 */
	public synchronized long saveOrUpdate(DbCacheBean pushBean) {

		if (pushBean == null) {
			return 0;
		} else {
			 
			ContentValues cvs = new ContentValues();
			cvs.put(DbCacheBean.T_VALUE, pushBean.t_value);
			cvs.put(DbCacheBean.T_KEY, pushBean.t_key);
			List<DbCacheBean> pushNoticeBeans =  isExistence(pushBean.t_key);
			// 传送文件重复插入bug处理方法
			if (pushNoticeBeans == null || pushNoticeBeans.size() == 0) {
				System.out.println("PushNoticeBean insert..." + pushBean.t_key);
				long backId = db.insert(DbCacheBean.TABLE_NAME, null, cvs);
				return backId;
			} else {
				try {
					return db
							.update(DbCacheBean.TABLE_NAME, cvs, DbCacheBean.T_KEY +"=?" , new String[] {
									pushBean.t_key });
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return 0;
	}
	
	

	/**
	 * 是否存在
	 * 
	 * @return
	 */
	public List<DbCacheBean> isExistence(String key) {
		
		String sql = "select * from "+DbCacheBean.TABLE_NAME+" where " + DbCacheBean.T_KEY + " =? ";
		Cursor mCursor = db.rawQuery(sql, new String[] {key+""});
		List<DbCacheBean> chatpushBeans = new ArrayList<DbCacheBean>();

		while (mCursor.moveToNext()) {
			DbCacheBean pushBean = getObject(mCursor);
			chatpushBeans.add(pushBean);
		}
		if (mCursor != null && !mCursor.isClosed()) {
			mCursor.close();
		}
		return chatpushBeans;
	}


	/**
	 * 清理会话
	 * @param loginId
	 * @return
	 */
	public boolean delete(String loginId) {
		
		Log.d("dele", "chat:del");
		String sql ="delete from " +DbCacheBean.TABLE_NAME +" where "+ DbCacheBean.T_KEY + "='"+loginId+"'";
		db.execSQL(sql);
		return true;
	}

	/**
	 * 清理会话
	 * @return
	 */
	public boolean deleteAll() {
		Log.d("dele", "chat:del");
		String sql ="delete from " +DbCacheBean.TABLE_NAME ;
		db.execSQL(sql);
		return true;
	}


 
 
	/**
	 * 查询总条数
	 * 
	 * @return
	 */
	public long getCount() {
		Cursor cursor = db.rawQuery("select count(*)from "
				+ DbCacheBean.TABLE_NAME, null);
		cursor.moveToFirst();
		long count = cursor.getLong(0);
		cursor.close();
		Log.d(" pushBean count", count + "");
		return count;
	}
	



	/**
	 * 根据key查找
	 * 
	 * @param key
	 * @return
	 */
	public DbCacheBean findByKey(String key) {

		Cursor c = db.query(DbCacheBean.TABLE_NAME, null, DbCacheBean.T_KEY
				+ " = ?", new String[] { "" + key }, null, null, null);
		DbCacheBean fluid = null;
		while (c.moveToNext()) {
			fluid = getObject(c);
		}
		DatabaseHelper.closeCursor(c);
		return fluid;
	}

	// 赋值
	public DbCacheBean getObject(Cursor c) {
		DbCacheBean pushBean = new DbCacheBean();
		
		pushBean.t_key = c.getString(c.getColumnIndex(DbCacheBean.T_KEY));
		pushBean.t_value = c.getString(c.getColumnIndex(DbCacheBean.T_VALUE));
		
		return pushBean;

	}

	 

}
