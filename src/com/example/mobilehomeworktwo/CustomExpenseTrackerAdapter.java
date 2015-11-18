package com.example.mobilehomeworktwo;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobilehomeworktwo.ListItem;

public class CustomExpenseTrackerAdapter extends BaseAdapter{
		
	private ArrayList<ListItem> myList;
	private LayoutInflater inflater;
	private Context context;
	
	public CustomExpenseTrackerAdapter(Context context, ArrayList<ListItem> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.myList.size();
	}

	@Override
	public ListItem getItem(int position) {
		// TODO Auto-generated method stub
		return myList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
		if (convertView == null) {convertView = inflater.inflate(R.layout.expense_entry, parent, false);}
     	TextView expense = (TextView)convertView.findViewById(R.id.entry_expense);
        TextView note = (TextView)convertView.findViewById(R.id.entry_note);
        TextView date = (TextView)convertView.findViewById(R.id.entry_time);
        Button deleteBtn = (Button)convertView.findViewById(R.id.btn_delete);
        
        ListItem item = getItem(position);
        expense.setText(item.getExpense());
        note.setText(item.getNote());
        date.setText(item.getDate());
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { 
                //do something
            	myList.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });
        
		return convertView;
	}
}
