package ybigta.webtoon_recommendation.analysis.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import ybigta.webtoon_recommendation.analysis.domain.Morpheme;

public class CSVProcessor {

	private static final char DELIMITER = ',';
	private static final String OUTPUT_FILE_FOLDER = "data";
	private static final String EXTENSION = ".csv";

	public void writeFile(String webtoonName, List<Morpheme> resultList) {

		String outputFilePath = OUTPUT_FILE_FOLDER + File.separator + webtoonName + EXTENSION;
		CSVFormat format = CSVFormat.RFC4180.withDelimiter(DELIMITER);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFilePath)));
				CSVPrinter printer = new CSVPrinter(writer, format);) {

			for (Morpheme morpheme : resultList) {
				printer.printRecord(morpheme.getMorpheme(), morpheme.getMorphemeCount());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
