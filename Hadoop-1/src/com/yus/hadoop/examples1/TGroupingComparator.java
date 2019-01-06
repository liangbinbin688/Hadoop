package com.yus.hadoop.examples1;

import org.apache.hadoop.cli.util.RegexpComparator;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TGroupingComparator extends WritableComparator{
  
	public TGroupingComparator(){
		super(TQ.class);
	}
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		// TODO Auto-generated method stub
		TQ t1=(TQ)a;
		TQ t2=(TQ)b;
		int c1 = Integer.compare(t1.getYear(), t2.getYear());
		if(c1==0){
			return Integer.compare(t1.getMonth(), t2.getMonth());
		}
		return c1;
	}
	
	
}
