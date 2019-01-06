package com.yus.hadoop.examples1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class TPartitioner extends Partitioner<TQ,IntWritable>{

	
	@Override
	public int getPartition(TQ arg0, IntWritable arg1, int arg2) {
		// TODO Auto-generated method stub
		return arg0.hashCode()%arg2;
	}

}
