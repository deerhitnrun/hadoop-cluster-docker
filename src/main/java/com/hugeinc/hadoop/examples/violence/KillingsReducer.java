package com.hugeinc.hadoop.examples.violence;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * HUGE-mnewball
 * Date: 2/15/17
 * Time: 10:55 AM
 */
public class KillingsReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

	@Override
	public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
		Long totalCount = 0L;

		for (LongWritable value : values) {
			totalCount += value.get();
		}

		context.write(key, new LongWritable(totalCount));
	}
}
