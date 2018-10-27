package ai.leverton.kata.library.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.csv.CSVRecord;

import ai.leverton.kata.library.storage.LocalStorage;

import static java.util.stream.Collectors.toSet;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Magazine extends Publication {
    private LocalDate publishedAt;
    private String isbn;

    public Magazine(CSVRecord record) {
        this.publishedAt = LocalDate.parse(record.get(MagazineHeaders.PUBLISHED_AT), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        this.isbn = record.get(MagazineHeaders.ISBN);
        setTitle(record.get(MagazineHeaders.TITLE));
        setAuthors(Stream.of(record.get(MagazineHeaders.AUTHORS).split(","))
                         .map(LocalStorage.getAuthorHashMap()::get)
                         .collect(toSet()));
    }

    @Getter
    @AllArgsConstructor
    public enum MagazineHeaders {
        TITLE("title"),
        ISBN("isbn"),
        AUTHORS("auhtors"),
        PUBLISHED_AT("publishedAt");

        private String headerValue;
    }
}
