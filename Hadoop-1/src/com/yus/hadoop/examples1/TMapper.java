package com.yus.hadoop.examples1;

import static org.mockito.Matchers.intThat;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.yarn.server.resourcemanager.webapp.dao.NewApplication;

import com.amazonaws.services.dynamodb.model.Key;

public class TMapper extends Mapper<LongWritable, Text, TQ, IntWritable>{
   /*
    * 
1949-10-01 14:21:02	34c
1949-10-01 19:21:02	38c
1949-10-02 14:01:02	36c
1950-01-01 11:21:02	32c
1950-10-01 12:21:02	37c
1951-12-01 12:21:02	23c
1950-10-02 12:21:02	41c
1950-10-03 12:21:02	27c
1951-07-01 12:21:02	45c
1951-07-02 12:21:02	46c
1951-07-03 12:21:03	47c

	*/
	TQ mKey=new TQ();
	IntWritable mVal=new IntWritable();
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, TQ, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String[] split = StringUtils.split(value.toString(), '\t');
		SimpleDateFormat sdf=new SimpleDateFormat();
		try {
			Date parse = sdf.parse(split[0]);
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(parse);
			mKey.setYear(calendar.get(Calendar.YEAR));
			mKey.setMonth(calendar.get(Calendar.MONTH)+1);
			mKey.setDay(calendar.get(Calendar.DAY_OF_MONTH));
			int wd=Integer.parseInt(split[1].substring(0, split[1].length()-1));
			mKey.setWd(wd);
			
			mVal.set(wd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		context.write(mKey, mVal);
	}
	
}
