package ai.leverton.kata.library.task;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import ai.leverton.kata.library.domain.Publication;
import ai.leverton.kata.library.storage.LocalStorage;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public final class SortAllPublicationByTitle {
    private SortAllPublicationByTitle() {
    }

    public static Map<String, List<? extends Publication>> sortByTitle() {
        return LocalStorage.getPublicationHashMap()
                           .entrySet()
                           .stream()
                           .collect(toMap(entry -> entry.getValue().getTitle(),
                                          entry -> singletonList(entry.getValue()),
                                          (listLeft, listRight) -> Stream.concat(listLeft.stream(), listRight.stream())
                                                                         .collect(toList()),
                                          TreeMap::new));
    }
}
