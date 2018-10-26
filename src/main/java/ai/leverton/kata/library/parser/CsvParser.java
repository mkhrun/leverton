package ai.leverton.kata.library.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import ai.leverton.kata.library.MainApp;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public final class CsvParser {
    private static final String RESOURCE_PACKAGE = "ai/leverton/kata/library/data/";
    private static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.withDelimiter(';').withIgnoreEmptyLines(true).withQuote('"').withRecordSeparator("\r\n");

    private CsvParser() {}

    private static <T, E extends Enum<E>> List<T> parseCsvFile(InputStream csvFile, Class<E> headerClass, Function<CSVRecord, T> tSupplier) {
        try (Reader fileIn = new InputStreamReader(csvFile)) {
            return CSV_FORMAT.withHeader(headerClass)
                             .withSkipHeaderRecord(true)
                             .parse(fileIn)
                             .getRecords()
                             .stream()
                             .map(tSupplier)
                             .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public static <T, E extends Enum<E>> void processCsvFile(String fileName, Class<E> enumClass, Function<CSVRecord, T> tSupplier,
                                                              Consumer<Map<String, T>> mapConsumer, Function<T, String> keyMapper) {

        try (InputStream file = MainApp.class.getClassLoader().getResourceAsStream(RESOURCE_PACKAGE + fileName)) {
            mapConsumer.accept(CsvParser.parseCsvFile(file, enumClass, tSupplier)
                                        .stream()
                                        .collect(toMap(keyMapper, identity())));
        } catch (IOException e) {
            System.err.println("Cannot read file " + fileName);
        }
    }
}
