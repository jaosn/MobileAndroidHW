package com.example.mobilehomeworktwo;

import java.util.Date;

public class ListItem {
	private int id;
	private String expense;
	private String note;
	private String date;
	
	public ListItem(String expense, String note){
		super();
		this.expense = expense;
		this.note = note;
		Date date = new Date();
		this.date = date.toString();
	}
	public ListItem(){}
	public void setId(int idInput){
		this.id = idInput;
	}
	public void setExpense(String expenseInput){
		this.expense = expenseInput;
	}
	public void setNote(String noteInput){
		this.note = noteInput;
	}
	public void setDate(String dateInput){
		this.date = dateInput;
	}
	public int getId(){
		return this.id;
	}
	public String getExpense(){
		return this.expense;
	}
	public String getNote(){
		return this.note;
	}
	public String getDate(){
		return this.date;
	}
}
