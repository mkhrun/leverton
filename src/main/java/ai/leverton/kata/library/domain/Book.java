package ai.leverton.kata.library.domain;

import java.util.Set;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.csv.CSVRecord;

import ai.leverton.kata.library.storage.LocalStorage;

import static java.util.stream.Collectors.toSet;

@Data
public class Book {
    private String title;
    private String description;
    private Set<Author> authors;
    private String isbn;

    public Book(CSVRecord record) {
        this.title = record.get(BookHeaders.TITLE);
        this.description = record.get(BookHeaders.DESCRIPTION);
        this.authors = Stream.of(record.get(BookHeaders.AUTHORS).split(","))
                             .map(LocalStorage.getAuthorHashMap()::get)
                             .collect(toSet());
        this.isbn = record.get(BookHeaders.ISBN);
    }

    @Getter
    @AllArgsConstructor
    public static enum BookHeaders {
        TITLE("title"),
        ISBN("isbn"),
        AUTHORS("auhtors"),
        DESCRIPTION("description");

        private String headerValue;
    }
}
