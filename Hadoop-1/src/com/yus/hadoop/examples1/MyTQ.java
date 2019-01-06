package com.yus.hadoop.examples1;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.TestMapReduceLazyOutput.TestMapper;
import org.apache.hadoop.mapreduce.TestMapReduceLazyOutput.TestReducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class MyTQ {
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		Configuration configuration=new Configuration(true);
		Job job=Job.getInstance(configuration);
		
		job.setJarByClass(MyTQ.class);
		
		//------conf-----
		//----Map:
		//设置输入格式
		//job.setInputFormatClass(Class<InputFormat>);
		job.setMapperClass(TMapper.class);
		job.setMapOutputKeyClass(TQ.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setPartitionerClass(TPartitioner.class);
		job.setSortComparatorClass(TSortCompartor.class);
		
		//job.setCombinerClass(TConbiner.class);
		
		//----map  end---
		//---reduce---
		job.setGroupingComparatorClass(TGroupingComparator.class);
		job.setReducerClass(TReducer.class);
		
		//----Reduce   --end
		
		Path input=new Path("/input/");
		FileInputFormat.setInputPaths(job, input);
		
		Path output=new Path("/output/");
		if(output.getFileSystem(configuration).exists(output)){
			output.getFileSystem(configuration).delete(output, true);
		}
		FileOutputFormat.setOutputPath(job, output);
		
		job.setNumReduceTasks(2);//设置reduce数量
		
		job.waitForCompletion(true);
	}

}
