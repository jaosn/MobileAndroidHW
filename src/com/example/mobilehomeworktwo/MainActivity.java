package com.example.mobilehomeworktwo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ArrayList<ListItem> myList;
	private List<ListItem> myLs;
	private CustomExpenseTrackerAdapter adapter;
	DatabaseHelper db = new DatabaseHelper(this);


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		db.onUpgrade(db.getWritableDatabase(), 1, 2);
		db.createItems(new ListItem("Expense1", "Note1"));
		db.createItems(new ListItem("Expense2", "Note2"));
		db.createItems(new ListItem("Expense3", "Note3"));
		db.createItems(new ListItem("Expense4", "Note4"));
		db.createItems(new ListItem("Expense5", "Note5"));
		db.createItems(new ListItem("Expense6", "Note6"));
		db.createItems(new ListItem("Expense7", "Note7"));
		db.createItems(new ListItem("Expense8", "Note8"));
		db.createItems(new ListItem("Expense9", "Note9"));
		db.createItems(new ListItem("Expense10", "Note10"));
		
		myLs = db.getAllItems();
		
		
		myList=new ArrayList<ListItem>();
		
		for (int i = 0; i < myLs.size(); i++) {
			myList.add(i, new ListItem(myLs.get(i).getExpense(),myLs.get(i).getNote()));
		}

		//Button btn_add = (Button)findViewById(R.id.btn_add);
		//myList=new ArrayList<ListItem>();
		adapter = new CustomExpenseTrackerAdapter(this,myList);
		ListView ls = (ListView)findViewById(R.id.listView);
		ls.setAdapter(adapter);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    // Check which request we're responding to
	    if (requestCode == 1) {
	        // Make sure the request was successful
	        if (resultCode == RESULT_OK) {
	            // The user picked a contact.
	            // The Intent's data Uri identifies which contact was selected.
	        	Bundle bd = data.getExtras();
	        	String expense = bd.getString("stringOne");
	        	String note = bd.getString("stringTwo");
	        	ListItem item = new ListItem(expense,note);
	        	myList.add(item);
	        	adapter.notifyDataSetChanged();
	            // Do something with the contact here (bigger example below)
	        }
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent goToInput = new Intent(MainActivity.this,AddEntryActivity.class);
			startActivityForResult(goToInput,1);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
