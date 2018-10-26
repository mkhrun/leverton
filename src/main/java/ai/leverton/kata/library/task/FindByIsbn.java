package ai.leverton.kata.library.task;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import ai.leverton.kata.library.domain.Book;
import ai.leverton.kata.library.storage.LocalStorage;

public final class FindByIsbn {
    private FindByIsbn() {
    }

    private static <T> Optional<T> findPublicationByIsbn(String isbn, Supplier<Map<String, T>> mapSupplier) {
        return Optional.ofNullable(mapSupplier.get().get(isbn));
    }

    public static void findBookOrMagazineByIsbn(String isbn) {
        Optional<Book> book = findPublicationByIsbn(isbn, LocalStorage::getBookHashMap);
        if (book.isPresent()) {
            System.out.println(book.get());
        } else {
            System.out.println(findPublicationByIsbn(isbn, LocalStorage::getMagazineHashMap).orElse(null));
        }
    }
}
