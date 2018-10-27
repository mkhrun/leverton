package ai.leverton.kata.library.task;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import ai.leverton.kata.library.domain.Publication;
import ai.leverton.kata.library.storage.LocalStorage;

public final class FindByIsbn {
    private FindByIsbn() {
    }

    private static <T extends Publication> Optional<T> findPublicationByIsbn(String isbn, Supplier<Map<String, T>> mapSupplier) {
        return Optional.ofNullable(mapSupplier.get().get(isbn));
    }

    public static void findBookOrMagazineByIsbn(String isbn) {
        findPublicationByIsbn(isbn, LocalStorage::getPublicationHashMap).ifPresent(System.out::println);
    }
}
