package ai.leverton.kata.library.storage;

import java.util.HashMap;
import java.util.Map;

import ai.leverton.kata.library.domain.Author;
import ai.leverton.kata.library.domain.Book;
import ai.leverton.kata.library.domain.Magazine;


public final class LocalStorage {
    private static Map<String, Author> AUTHOR_HASH_MAP = new HashMap<>();
    private static Map<String, Book> BOOK_HASH_MAP = new HashMap<>();
    private static Map<String, Magazine> MAGAZINE_HASH_MAP = new HashMap<>();

    private LocalStorage() {}

    public static Map<String, Author> getAuthorHashMap() {
        return AUTHOR_HASH_MAP;
    }

    public static void setAuthorHashMap(final Map<String, Author> authorHashMap) {
        AUTHOR_HASH_MAP = authorHashMap;
    }

    public static Map<String, Book> getBookHashMap() {
        return BOOK_HASH_MAP;
    }

    public static void setBookHashMap(final Map<String, Book> bookHashMap) {
        BOOK_HASH_MAP = bookHashMap;
    }

    public static Map<String, Magazine> getMagazineHashMap() {
        return MAGAZINE_HASH_MAP;
    }

    public static void setMagazineHashMap(final Map<String, Magazine> magazineHashMap) {
        MAGAZINE_HASH_MAP = magazineHashMap;
    }
}
