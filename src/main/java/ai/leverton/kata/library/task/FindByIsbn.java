package ai.leverton.kata.library.task;

import java.util.Optional;

import ai.leverton.kata.library.domain.Publication;
import ai.leverton.kata.library.storage.LocalStorage;

public final class FindByIsbn {
    private FindByIsbn() {
    }

    public static Optional<? extends Publication> findPublicationByIsbn(String isbn) {
        return Optional.ofNullable(LocalStorage.getPublicationHashMap().get(isbn));
    }
}
