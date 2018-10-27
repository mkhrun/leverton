package ai.leverton.kata.library.task;

import java.util.TreeMap;
import java.util.stream.Stream;

import ai.leverton.kata.library.storage.LocalStorage;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public final class SortAllPublicationByTitle {
    private SortAllPublicationByTitle() {
    }

    public static void sortByTitle() {
        LocalStorage.getPublicationHashMap()
                    .entrySet()
                    .stream()
                    .collect(toMap(entry -> entry.getValue().getTitle(),
                                   entry -> singletonList(entry.getValue()),
                                   (list1, list2) -> Stream.concat(list1.stream(), list2.stream()).collect(toList()),
                                   TreeMap::new))
                    .forEach((title, publications) -> {
                        System.out.println("{TITLE: " + title + "}");
                        System.out.println("{PUBLICATION: ");
                        publications.forEach(System.out::print);
                        System.out.print("}");
                    });
    }
}
