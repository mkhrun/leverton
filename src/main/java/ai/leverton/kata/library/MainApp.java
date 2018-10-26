package ai.leverton.kata.library;

import java.util.Scanner;

import ai.leverton.kata.library.domain.Author;
import ai.leverton.kata.library.domain.Book;
import ai.leverton.kata.library.domain.Magazine;
import ai.leverton.kata.library.storage.LocalStorage;

import static ai.leverton.kata.library.parser.CsvParser.processCsvFile;
import static ai.leverton.kata.library.task.FindByAuthorEmail.findBookOrMagazineByAuthorsEmail;
import static ai.leverton.kata.library.task.FindByIsbn.findBookOrMagazineByIsbn;
import static ai.leverton.kata.library.task.SortAllPublicationByTitle.sortByTitle;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public class MainApp {

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

        performTasts(input[0], input[1]);
    }

    private static void performTasts(String isbn, String email) {
        System.out.println("{PUBLICATION BY ISBN: " + isbn + "}");
        findBookOrMagazineByIsbn(isbn);

        System.out.println();
        System.out.println("{PUBLICATION(S) BY AUTHORS EMAIL: " + email + "}");
        findBookOrMagazineByAuthorsEmail(email);

        System.out.println();
        System.out.println("{PUBLICATIONS SORTED BY TITLE}");
        sortByTitle();
    }

    private static void processFiles() {
        processCsvFile("authors.csv", Author.AuthorHeaders.class, Author::new, LocalStorage::setAuthorHashMap, Author::getEmail);
        processCsvFile("books.csv", Book.BookHeaders.class, Book::new, LocalStorage::setBookHashMap, Book::getIsbn);
        processCsvFile("magazines.csv", Magazine.MagazineHeaders.class, Magazine::new, LocalStorage::setMagazineHashMap, Magazine::getIsbn);
    }
}
