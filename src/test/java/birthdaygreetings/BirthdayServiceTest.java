package birthdaygreetings;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class BirthdayServiceTest {

    @Test
    @DisplayName("example")
    void example() {

        // Arrange
        LocalDate someDate = LocalDate.of(2020, 2, 12);

        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        NotificationService emailService = mock(NotificationService.class);

        Person recipient = new Person("Doe", "John", someDate, "john.doe@foobar.com");

        given(employeeRepository.findAllByBirthday(someDate))
                .willReturn(Collections.singletonList(recipient));

        // Act
        BirthdayService birthdayService = new BirthdayService(employeeRepository, emailService);
        birthdayService.sendGreetings(someDate);

        // Assert
        String subject = "Happy Birthday";
        String message = "Happy birthday, dear John!";
        Notification notification = new Notification(recipient, subject, message);

        then(emailService).should().send(notification);
    }

}