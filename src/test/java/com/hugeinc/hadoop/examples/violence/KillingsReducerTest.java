package com.hugeinc.hadoop.examples.violence;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * HUGE-mnewball
 * Date: 2/15/17
 * Time: 1:52 PM
 */
public class KillingsReducerTest {

	@Test
	public void shouldSumAllKillings() throws IOException {
		List<LongWritable> counts =
				Arrays.asList(1, 23, 4, 0, 6, 7, 1, 3, 6, 55, 6, 32, 20, 1, 1, 1, 1, 0, 8, 12, 32, 4, 2, 3, 2, 4).stream()
						.map(count -> new LongWritable(count))
						.collect(Collectors.toList());

		new ReduceDriver<Text, LongWritable, Text, LongWritable>()
				.withReducer(new KillingsReducer())
				.withInput(new Text("1970:Dominican Republic"), counts)
				.withOutput(new Text("1970:Dominican Republic"), new LongWritable(235))
				.runTest();
	}
}
