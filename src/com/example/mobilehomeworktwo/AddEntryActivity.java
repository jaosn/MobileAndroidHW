package com.example.mobilehomeworktwo;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEntryActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense_add);	
		Button btn_save = (Button)findViewById(R.id.btn_save);
		Button btn_cancel = (Button)findViewById(R.id.btn_cancel);
		
		btn_cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(Activity.RESULT_CANCELED);
				finish();
				//Intent goToMain = new Intent(AddEntryActivity.this,MainActivity.class);
				//startActivity(goToMain);
			}
		});
		
		btn_save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText et_expense = (EditText)findViewById(R.id.editText_expense);
				EditText et_note = (EditText)findViewById(R.id.editText_note);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("stringOne", et_expense.getText().toString());
				bundle.putString("stringTwo", et_note.getText().toString());
				intent.putExtras(bundle);
				
				setResult(Activity.RESULT_OK,intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_input_entry, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
