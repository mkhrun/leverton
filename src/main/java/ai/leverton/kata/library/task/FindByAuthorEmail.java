package ai.leverton.kata.library.task;

import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import ai.leverton.kata.library.domain.Author;
import ai.leverton.kata.library.domain.Publication;
import ai.leverton.kata.library.storage.LocalStorage;

public final class FindByAuthorEmail {
    private FindByAuthorEmail() {
    }

    private static <T extends Publication> Stream<T> findPublicationByAuthorEmail(Supplier<Map<String, T>> mapSupplier, Predicate<T> predicate) {
        return mapSupplier.get()
                          .entrySet()
                          .stream()
                          .filter(stringTEntry -> predicate.test(stringTEntry.getValue()))
                          .map(Map.Entry::getValue);
    }

    public static void findBookOrMagazineByAuthorsEmail(String email) {
        Predicate<Author> authorPredicate = (author) -> author.getEmail().equals(email);

        findPublicationByAuthorEmail(LocalStorage::getPublicationHashMap,
                                     book -> book.getAuthors().stream().anyMatch(authorPredicate))
                    .forEach(System.out::println);
    }
}
