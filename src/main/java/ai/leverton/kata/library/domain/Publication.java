package ai.leverton.kata.library.domain;

import java.util.Set;

import lombok.Data;

@Data
public abstract class Publication {
    private String title;
    private Set<Author> authors;
}
