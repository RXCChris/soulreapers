/**
 * 
 */
package com.soulreapers.util;

import org.andengine.util.debug.Debug;

import com.soulreapers.core.SceneManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author chris
 *
 */
public class GameDataBase extends SQLiteOpenHelper {
	private static final String DB_NAME = "soulreapers.db";

	private static class DbReaper {
		private static final String DB_TABLE_NAME = "Reaper";
		private static final String DB_REAPER_NAME = "name";
		private static final String DB_REAPER_LEVEL = "level";

		private static String stringCreateTable() {
			String tableCreate = "CREATE TABLE IF NOT EXISTS "
					+ DB_TABLE_NAME + " ("
					+ DB_REAPER_NAME + " TEXT PRIMARY KEY, "
					+ DB_REAPER_LEVEL + " INTEGER"
					+ ")";
			return tableCreate;
		}

		private static void createTable(SQLiteDatabase db, String name, int level) {
			ContentValues cv = new ContentValues();
			cv.put(DB_REAPER_NAME, name);
			cv.put(DB_REAPER_LEVEL, level);
			db.insert(DB_TABLE_NAME, null, cv);
		}

		private static void dropTable(SQLiteDatabase db) {
			Debug.d("[DB] drop table " + DB_TABLE_NAME);
			db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
		}


	}

	/**
	 * @param context
	 */
	public GameDataBase() {
		super(SceneManager.getInstance().getActivity(), DB_NAME, null, 2);
	}


	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
//		db.execSQL("DROP TABLE IF EXISTS " + DbReaper.DB_TABLE_NAME);
		DbReaper.dropTable(db);
		db.execSQL(DbReaper.stringCreateTable());

		DbReaper.createTable(db, "Horace", 0);
		DbReaper.createTable(db, "August", 7);

//		ContentValues cv = new ContentValues();
//		cv.put(DbReaper.DB_REAPER_NAME, "Dante");
//		cv.put(DbReaper.DB_REAPER_LEVEL, 2);
//		db.insert(DbReaper.DB_TABLE_NAME, null, cv);
//
//		cv.put(DbReaper.DB_REAPER_NAME, "Vergil");
//		cv.put(DbReaper.DB_REAPER_LEVEL, 3);
//		db.insert(DbReaper.DB_TABLE_NAME, null, cv);
//
//		cv.put(DbReaper.DB_REAPER_NAME, "Beatrix");
//		cv.put(DbReaper.DB_REAPER_LEVEL, 1);
//		db.insert(DbReaper.DB_TABLE_NAME, null, cv);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + DbReaper.DB_TABLE_NAME);
		onCreate(db);
	}

	public Cursor getAllReapers() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cur = db.rawQuery("SELECT * FROM " + DbReaper.DB_TABLE_NAME, null);
		return cur;
	}

	public void debug() {
		Debug.i("DB debug");
		Cursor cur = getAllReapers();
		Debug.d("-- row:" + cur.getCount() + " -- column:" + cur.getColumnCount());
		cur.moveToFirst();
		Debug.d("name:" + cur.getString(0) + ", level:" + cur.getShort(1));
		cur.moveToNext();
		Debug.d("name:" + cur.getString(0) + ", level:" + cur.getShort(1));
	}
}
