package ai.leverton.kata.library.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.csv.CSVRecord;

import ai.leverton.kata.library.storage.LocalStorage;

import static java.util.stream.Collectors.toSet;

@Data
public class Magazine {
    private String title;
    private Set<Author> authors;
    private LocalDate publishedAt;
    private String isbn;

    public Magazine(CSVRecord record) {
        this.title = record.get(MagazineHeaders.TITLE);
        this.authors = Stream.of(record.get(MagazineHeaders.AUTHORS).split(","))
                             .map(LocalStorage.getAuthorHashMap()::get)
                             .collect(toSet());

        this.publishedAt = LocalDate.parse(record.get(MagazineHeaders.PUBLISHED_AT), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        this.isbn = record.get(MagazineHeaders.ISBN);
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
