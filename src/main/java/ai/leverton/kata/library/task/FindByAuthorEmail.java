package ai.leverton.kata.library.task;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

import ai.leverton.kata.library.domain.Author;
import ai.leverton.kata.library.domain.Book;
import ai.leverton.kata.library.domain.Magazine;
import ai.leverton.kata.library.storage.LocalStorage;

import static java.util.stream.Collectors.toList;

public final class FindByAuthorEmail {
    private FindByAuthorEmail() {
    }

    private static <T> List<T> findPublicationByAuthorEmail(Supplier<Map<String, T>> mapSupplier, Predicate<T> predicate) {
        return mapSupplier.get()
                          .entrySet()
                          .stream()
                          .filter(stringTEntry -> predicate.test(stringTEntry.getValue()))
                          .map(Map.Entry::getValue)
                          .collect(toList());
    }

    public static void findBookOrMagazineByAuthorsEmail(String email) {
        Predicate<Author> authorPredicate = (author) -> author.getEmail().equals(email);

        List<Book> books = findPublicationByAuthorEmail(LocalStorage::getBookHashMap,
                                                        book -> book.getAuthors().stream().anyMatch(authorPredicate));
        books.forEach(System.out::println);

        List<Magazine> magazines = findPublicationByAuthorEmail(LocalStorage::getMagazineHashMap,
                                                                magazine -> magazine.getAuthors().stream().anyMatch(authorPredicate));

        magazines.forEach(System.out::println);
    }
}
