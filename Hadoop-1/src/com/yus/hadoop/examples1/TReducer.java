package com.yus.hadoop.examples1;

import static org.mockito.Matchers.intThat;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.tools.rumen.Pre21JobHistoryConstants.Values;
import org.apache.hadoop.yarn.server.resourcemanager.webapp.dao.NewApplication;

import com.amazonaws.services.dynamodb.model.Key;

public class TReducer extends Reducer<TQ, IntWritable, Text, IntWritable>{
	 
	Text rkey=new Text();
	IntWritable rval= new IntWritable();
	
	
	@Override
	protected void reduce(TQ key, Iterable<IntWritable> arg1, Reducer<TQ, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//1970 01 01 88
		//1970 01 11 78
		//1970 01 22 68
		//1970 01 33 58
		
		int flg=0;
		int day=0;
	  for(IntWritable v:arg1){
	  if(flg==0){
		  rkey.set( key.getYear()+"-"+key.getMonth()+"-"+key.getDay()+":"+key.getWd());
		  rval.set(key.getWd());
		  flg++;
		  day=key.getDay();
		  context.write(rkey, rval);
	  }
	  if(flg!=0&&day!=key.getDay()){
		  rkey.set( key.getYear()+"-"+key.getMonth()+"-"+key.getDay()+":"+key.getWd());
		  rval.set(key.getWd());
		  context.write(rkey, rval);
	  }
	  }
		
	}
}
