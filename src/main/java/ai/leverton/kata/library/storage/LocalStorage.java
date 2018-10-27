package ai.leverton.kata.library.storage;

import java.util.HashMap;
import java.util.Map;

import ai.leverton.kata.library.domain.Author;
import ai.leverton.kata.library.domain.Publication;

public final class LocalStorage {
    private static final Map<String, Author> AUTHOR_HASH_MAP = new HashMap<>();
    private static final Map<String, ? super Publication> PUBLICATION_HASH_MAP = new HashMap<>();

    private LocalStorage() {}

    public static Map<String, Author> getAuthorHashMap() {
        return AUTHOR_HASH_MAP;
    }

    public static void setAuthorHashMap(final Map<String, Author> authorHashMap) {
        AUTHOR_HASH_MAP.putAll(authorHashMap);
    }

    public static Map<String, ? extends Publication> getPublicationHashMap() {
        // restrict types poosible to add into PUBLICATION_HASH_MAP
        return (Map<String, ? extends Publication>) PUBLICATION_HASH_MAP;
    }

    public static void setPublicationHashMap(final Map<String, ? extends Publication> publicationHashMap) {
        PUBLICATION_HASH_MAP.putAll(publicationHashMap);
    }
}
