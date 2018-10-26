package ai.leverton.kata.library.task;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import ai.leverton.kata.library.domain.Book;
import ai.leverton.kata.library.domain.Magazine;
import ai.leverton.kata.library.storage.LocalStorage;

public final class SortAllPublicationByTitle {
    private SortAllPublicationByTitle() {
    }

    public static void sortByTitle() {
        Map<String, Book> titleBookMap = LocalStorage.getBookHashMap()
                                                     .entrySet()
                                                     .stream()
                                                     .collect(Collectors.toMap(entry -> entry.getValue().getTitle(), Map.Entry::getValue));

        Map<String, Magazine> titleMagazineMap = LocalStorage.getMagazineHashMap()
                                                             .entrySet()
                                                             .stream()
                                                             .collect(Collectors.toMap(entry -> entry.getValue().getTitle(), Map.Entry::getValue));

        Map<String, Object> sortedMap = new TreeMap<>();
        sortedMap.putAll(titleBookMap);
        sortedMap.putAll(titleMagazineMap);

        sortedMap.forEach((title, publication) -> {
            System.out.println();
            System.out.println("{TITLE: " + title + "}");
            System.out.println("{PUBLICATION: " + publication + "}");
        });
    }
}
