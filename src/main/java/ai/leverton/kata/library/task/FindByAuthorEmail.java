package ai.leverton.kata.library.task;

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import ai.leverton.kata.library.domain.Author;
import ai.leverton.kata.library.domain.Publication;
import ai.leverton.kata.library.storage.LocalStorage;

public final class FindByAuthorEmail {
    private FindByAuthorEmail() {
    }

    public static Stream<? extends Publication> findPublicationByAuthorsEmail(String email) {
        Predicate<Author> authorPredicate = (author) -> author.getEmail().equals(email);

        return LocalStorage.getPublicationHashMap()
                           .entrySet()
                           .stream()
                           .filter(stringTEntry -> Optional.ofNullable(stringTEntry.getValue())
                                                           .map(publication -> publication.getAuthors().stream().anyMatch(authorPredicate))
                                                           .orElse(false))
                           .map(Map.Entry::getValue);
    }
}
