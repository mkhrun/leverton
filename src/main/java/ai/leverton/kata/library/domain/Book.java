package ai.leverton.kata.library.domain;

import java.util.Set;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Book extends Publication {
    private String description;

    @Builder
    public Book(final String isbn, final String title, final Set<Author> authors, String description) {
        super(isbn, title, authors);
        this.description = description;
    }

    public Book(CSVRecord record) {
        this.description = record.get(BookHeaders.DESCRIPTION);
        setIsbn(record.get(BookHeaders.ISBN));
        setTitle(record.get(BookHeaders.TITLE));
        setAuthors(Stream.of(record.get(BookHeaders.AUTHORS).split(","))
                             .map(LocalStorage.getAuthorHashMap()::get)
                             .collect(toSet()));
    }

    @Getter
    @AllArgsConstructor
    public enum BookHeaders {
        TITLE("title"),
        ISBN("isbn"),
        AUTHORS("auhtors"),
        DESCRIPTION("description");

        private String headerValue;
    }
}
