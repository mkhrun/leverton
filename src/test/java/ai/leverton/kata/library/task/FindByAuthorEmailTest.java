package ai.leverton.kata.library.task;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ai.leverton.kata.library.domain.Author;
import ai.leverton.kata.library.domain.Book;
import ai.leverton.kata.library.domain.Magazine;
import ai.leverton.kata.library.domain.Publication;
import ai.leverton.kata.library.parser.CsvParser;
import ai.leverton.kata.library.storage.LocalStorage;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FindByAuthorEmailTest {
    @BeforeAll
    static void initTestValues() {
        CsvParser.processCsvFile("ai/leverton/kata/library/data/authors.csv", Author.AuthorHeaders.class, Author::new,
                                 LocalStorage::setAuthorHashMap, Author::getEmail);
        CsvParser.processCsvFile("ai/leverton/kata/library/data/books.csv", Book.BookHeaders.class, Book::new,
                                 LocalStorage::setPublicationHashMap, Book::getIsbn);
        CsvParser.processCsvFile("ai/leverton/kata/library/data/magazines.csv", Magazine.MagazineHeaders.class, Magazine::new,
                                 LocalStorage::setPublicationHashMap, Magazine::getIsbn);
    }

    @Test
    void findPublicationByAuthorsEmail_WithValidFilePath_ShouldReturnStreamWithValue() {
        Author author = LocalStorage.getAuthorHashMap().get("test@leverton.ai");

        Optional<? extends Publication> expectedValue = Optional.of(Book.builder()
                                                                        .title("Loren ipsum großem")
                                                                        .isbn("5554-5545-4518")
                                                                        .authors(Stream.of(author).collect(toSet()))
                                                                        .description("Loren ipsum")
                                                                        .build());

        Optional<? extends Publication> actualValue = FindByAuthorEmail.findPublicationByAuthorsEmail("test@leverton.ai").findFirst();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void findPublicationByAuthorsEmail_WithNullFilePath_ShouldReturnEmptyStream() {
        Optional<? extends Publication> actualValue = FindByAuthorEmail.findPublicationByAuthorsEmail(null).findFirst();

        assertEquals(Optional.empty(), actualValue);
    }
}
