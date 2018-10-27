package ai.leverton.kata.library;

import java.util.Scanner;

import ai.leverton.kata.library.domain.Author;
import ai.leverton.kata.library.domain.Book;
import ai.leverton.kata.library.domain.Magazine;
import ai.leverton.kata.library.domain.Publication;
import ai.leverton.kata.library.storage.LocalStorage;

import static ai.leverton.kata.library.parser.CsvParser.processCsvFile;
import static ai.leverton.kata.library.task.FindByAuthorEmail.findPublicationByAuthorsEmail;
import static ai.leverton.kata.library.task.FindByIsbn.findPublicationByIsbn;
import static ai.leverton.kata.library.task.SortByTitle.sortPublicationByTitle;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public class MainApp {
    private static final String RESOURCE_PACKAGE = "ai/leverton/kata/library/data/";

    public static void main(String[] args) {
        processFiles();

        System.out.println("Input ISBN and author's email in one by one lines, then hit <Enter> button");
        String[] input = new String[2];
        Scanner scanner = new Scanner(System.in);

        int i = 0;
        while (i < 2) {
            input[i] = scanner.nextLine();
            i++;
        }

        scanner.close();

        performTasks(input[0], input[1]);
    }

    private static void performTasks(String isbn, String email) {
        System.out.println("{PUBLICATION BY ISBN: " + isbn + "}");
        System.out.println(findPublicationByIsbn(isbn).map(Publication::toString).orElse("None was found"));

        System.out.println();
        System.out.println("{PUBLICATION(S) BY AUTHORS EMAIL: " + email + "}");
        findPublicationByAuthorsEmail(email).forEach(System.out::println);

        System.out.println();
        System.out.println("{PUBLICATIONS SORTED BY TITLE}");
        sortPublicationByTitle().forEach((title, publications) -> {
            System.out.println("{TITLE: " + title + "}");
            System.out.println("{PUBLICATION: ");
            publications.forEach(System.out::println);
            System.out.println("}");
        });
    }

    private static void processFiles() {
        processCsvFile(RESOURCE_PACKAGE + "authors.csv", Author.AuthorHeaders.class, Author::new, LocalStorage::setAuthorHashMap, Author::getEmail);
        processCsvFile(RESOURCE_PACKAGE + "books.csv", Book.BookHeaders.class, Book::new, LocalStorage::setPublicationHashMap, Book::getIsbn);
        processCsvFile(RESOURCE_PACKAGE + "magazines.csv", Magazine.MagazineHeaders.class, Magazine::new, LocalStorage::setPublicationHashMap, Magazine::getIsbn);
    }
}
