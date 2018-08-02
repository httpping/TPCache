package com.tp.cache;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 用于替换 sp 缓存占用内存的问题。
 * @author tp
 */
public class DatabaseHelper extends SQLiteOpenHelper{
	
	private static final int VERSION = 9;
	public static final String DB_NAME ="tp_cache_database";
	private static DatabaseHelper instance;
	
	//构造函数
	public DatabaseHelper(Context context , CursorFactory factory,
			int version) {
		super(context, DB_NAME, factory, version);
		 
	}
	
	public DatabaseHelper(Context context , int version) {
		super(context, DB_NAME, null, version);
		 
	}
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	
	//获得实例
	public static DatabaseHelper getInstance(Context context){
		if(instance==null){
			instance = new DatabaseHelper(context.getApplicationContext());
		}
		return instance;
	}
	
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		//创建表 
		for (int i = 0; i < table.length; i++) {
			db.execSQL(table[i]);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		try {
			Log.d("onUpgrade", "onUpgrade");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//删除表 
		for (int i = 0; i < dropTables.length; i++) {
			db.execSQL(dropTables[i]);
		}
		
		//创建表 
		for (int i = 0; i < table.length; i++) {
			db.execSQL(table[i]);
		}		
	}
	
	
	private static String[] dropTables = new String[]{
		"DROP TABLE IF EXISTS "+DbCacheBean.TABLE_NAME,

	};
	
    private static String[] table =  new String[]	{
    	 "CREATE TABLE  IF NOT EXISTS "+DbCacheBean.TABLE_NAME+"( " +
				 DbCacheBean.T_KEY  + " text PRIMARY KEY ," +
				 DbCacheBean.T_VALUE+" text )"

    };
  
   /**
    * 关闭游标
    */
   public static void closeCursor(Cursor c){
	   if (c!=null && !c.isClosed()) {
			c.close();
		}
   }
}