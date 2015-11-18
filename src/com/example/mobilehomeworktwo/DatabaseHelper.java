package com.example.mobilehomeworktwo;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	// database version
	private static final int database_VERSION = 1;
	// database name
	private static final String database_NAME = "HW2DB";
	private static final String table_ITEMS = "items";
	private static final String items_ID = "id";
	private static final String items_EXPENSE = "expense";
	private static final String items_NOTE = "note";
	private static final String items_DATE = "date";

	private static final String[] COLUMNS = {items_ID, items_EXPENSE, items_NOTE, items_DATE};
	
	public DatabaseHelper(Context context) {
		super(context, database_NAME, null, database_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_EXPENSE_TABLE = "CREATE TABLE items ( " 
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, " 
				+ "expense TEXT, " 
				+ "note TEXT, " 
				+ "date TEXT )";
		db.execSQL(CREATE_EXPENSE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS items");
		this.onCreate(db);
	}
	
	public void createItems(ListItem item){
		// get reference of the ItemsDB database
		SQLiteDatabase db = this.getWritableDatabase();
		// make values to be inserted
		ContentValues values = new ContentValues();
		values.put(items_EXPENSE, item.getExpense());
		values.put(items_NOTE, item.getNote());
		values.put(items_DATE, item.getDate());
		// insert item
		db.insert(table_ITEMS, null, values);
		// close database transaction
		db.close();
	}
	public ListItem readItem(int id){
		// get reference of the ItemDB database
		SQLiteDatabase db = this.getReadableDatabase();
		
		// get book query
		Cursor cursor = db.query(table_ITEMS, // a. table
				COLUMNS, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		ListItem item = new ListItem();
		item.setId(Integer.parseInt(cursor.getString(0)));
		item.setExpense(cursor.getString(1));	
		item.setNote(cursor.getString(2));
		item.setDate(cursor.getString(3));	
		return item;
	}
	public List<ListItem> getAllItems(){
		List<ListItem> items = new LinkedList<ListItem>	();
		String query = "SELECT  * FROM " + table_ITEMS;
		
		// get reference of the ItemDB database
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		// parse all results
		ListItem item = null;
		if (cursor.moveToFirst()) {
			do {
				item = new ListItem();
				item.setId(Integer.parseInt(cursor.getString(0)));
				item.setExpense(cursor.getString(1));	
				item.setNote(cursor.getString(2));
				item.setDate(cursor.getString(3));
				
				// Add item to items
				items.add(item);
			} while (cursor.moveToNext());
		}
		return items;
	}
	
	public void deleteItem(ListItem item) {
		// get reference of the ItemDB database
		SQLiteDatabase db = this.getWritableDatabase();
		// delete item	
		db.delete(table_ITEMS, items_ID + " = ?", new String[] { String.valueOf(item.getId()) });
		db.close();
	}

}
