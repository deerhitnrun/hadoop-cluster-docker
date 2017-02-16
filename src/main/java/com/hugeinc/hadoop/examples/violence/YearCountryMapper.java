package com.hugeinc.hadoop.examples.violence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * HUGE-mnewball
 * Date: 2/15/17
 * Time: 10:55 AM
 */
public class YearCountryMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

	private static final int IYEAR = 1;
	private static final int COUNTRY_TXT = 8;
	private static final int NKILL = 100;

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String csvRow = value.toString();

		try(CSVParser csvParser = CSVParser.parse(csvRow, CSVFormat.DEFAULT)) {
			Optional<CSVRecord> recordResult = getRecord(csvParser);

			if(recordResult.isPresent()) {
				CSVRecord csvRecord = recordResult.get();

				String yearValue = csvRecord.get(IYEAR);
				String countryTextValue = csvRecord.get(COUNTRY_TXT);
				String numberKilledValue = csvRecord.get(NKILL);

				// Convert to Integer. If the number isn't valid assume 0. We're only interested in accurate data.
				Long numberKilled = toValidLong(numberKilledValue);

				// Build the key by joining year and country
				String outputKey = buildKey(yearValue, countryTextValue);

				context.write(new Text(outputKey), new LongWritable(numberKilled));
			}
		}
	}

	/**
	 *
	 * @param csvParser
	 * @return
	 * @throws IOException
	 */
	private Optional<CSVRecord> getRecord(CSVParser csvParser) throws IOException {
		List<CSVRecord> csvRecordList = csvParser.getRecords();
		if(csvRecordList != null && csvRecordList.size() >= 1) {
			return Optional.of(csvRecordList.get(0));
		}

		return Optional.empty();
	}

	private String buildKey(String... elements) {
		return String.join(":", elements);
	}

	private Long toValidLong(String value) {
		try {
			return Long.parseUnsignedLong(value);
		} catch(NumberFormatException nfe) {
			return 0L;
		}
	}
}