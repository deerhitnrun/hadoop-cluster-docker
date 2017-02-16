package com.hugeinc.hadoop.examples.violence;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

import java.io.IOException;

/**
 * HUGE-mnewball
 * Date: 2/15/17
 * Time: 1:51 PM
 */
public class YearCountryMapperTest {

	@Test
	public void shouldMapInputToYearCountryKeyWithKillingsCount() throws IOException {
		Text value = new Text("197000000001,1970,0,0,,0,,58,Dominican Republic,2," +
				"Central America & Caribbean,,Santo Domingo,18.456792,-69.951164,1,0,,,1,1," +
				"1,0,,.,0,1,0,1,Assassination,,.,,.,14,Private Citizens & Property,68," +
				"Named Civilian,,Julio Guzman,58,Dominican Republic,,.,,.,,,,.,,.,,.,,,,.," +
				"MANO-D,,,,,3629,,,,,0,,,,,,,.,,,.,,,.,,13,Unknown,,.,,.,,.,,.,,.,,.,,.,," +
				"1,,,0,,,0,,.,,,0,,,,,,,0,,,,,,,.,,,,,,PGIS,0,0,0,0,");

		new MapDriver<LongWritable, Text, Text, LongWritable>()
				.withMapper(new YearCountryMapper())
				.withInput(new LongWritable(0), value)
				.withOutput(new Text("1970:Dominican Republic"), new LongWritable(1L))
				.runTest();

	}
}
