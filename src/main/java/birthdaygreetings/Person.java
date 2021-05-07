package birthdaygreetings;

import java.time.LocalDate;

public class Person {
    private final String lastName;
    private final String firstName;
    private final LocalDate birthday;
    private final String email;

    public Person(String lastName, String firstName, LocalDate birthday, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
}
