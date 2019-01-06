package com.yus.hadoop.examples1;

import static org.mockito.Matchers.intThat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class TQ implements WritableComparable<TQ>{
   
	private  int year;
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getWd() {
		return wd;
	}

	public void setWd(int wd) {
		this.wd = wd;
	}

	private int month;
	private int day;
	private int wd;
	
	
	
	@Override
	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		this.year=arg0.readInt();
		this.month=arg0.readInt();
		this.day=arg0.readInt();
		this.wd=arg0.readInt();
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		arg0.write(year);
		arg0.write(month);
		arg0.write(day);
		arg0.write(wd);
	}

	@Override
	public int compareTo(TQ that) {
		// TODO Auto-generated method stub
		
		int c1=Integer.compare(this.year, that.getYear());
		
		if(c1==0){
			int c2=Integer.compare(this.month, that.getMonth());
			
			if(c2==0){
				return Integer.compare(this.day, that.getDay());
			}
			return c2;
		}
		
		return c1;
	}

	
	
}
