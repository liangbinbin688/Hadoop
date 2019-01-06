package com.yus.hadoop.examples1;


import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TSortCompartor extends WritableComparator{
	
	public TSortCompartor() {
		// TODO Auto-generated constructor stub
	super(TQ.class);
	
	}
	
	//前面TQ继承了WritableComparable，这里重写
   @Override
public int compare(WritableComparable a, WritableComparable b) {
	// TODO Auto-generated method stub
	TQ t1=(TQ)a;
	TQ t2=(TQ)b;
	int c1 = Integer.compare(t1.getYear(), t2.getYear());
	if(c1==0){
		int c2 = Integer.compare(t1.getMonth(), t2.getYear());
		if(c2==0){
			return Integer.compare(t1.getWd(), t2.getWd());
		}
		return c2;
	}
	   
	   
	return c1;
}
}
