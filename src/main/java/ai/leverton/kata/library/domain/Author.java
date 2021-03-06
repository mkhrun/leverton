package ai.leverton.kata.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.csv.CSVRecord;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Author {
    private String email;
    private String firstName;
    private String lastName;

    public Author(CSVRecord record) {
        this.email = record.get(AuthorHeaders.EMAIL);
        this.firstName = record.get(AuthorHeaders.FIRST_NAME);
        this.lastName = record.get(AuthorHeaders.LAST_NAME);
    }

    @Getter
    @AllArgsConstructor
    public enum AuthorHeaders {
        EMAIL("email"),
        FIRST_NAME("firstname"),
        LAST_NAME("lastname");

        private String headerValue;
    }
}

