package ai.leverton.kata.library.domain;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Publication {
    private String isbn;
    private String title;
    private Set<Author> authors;
}
