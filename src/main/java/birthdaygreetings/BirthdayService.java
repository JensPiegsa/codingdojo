package birthdaygreetings;

import java.time.LocalDate;
import java.util.List;

public class BirthdayService {

    private final EmployeeRepository employeeRepository;
    private final NotificationService notificationService;

    public BirthdayService(EmployeeRepository employeeRepository, NotificationService notificationService) {

        this.employeeRepository = employeeRepository;
        this.notificationService = notificationService;
    }

    public void sendGreetings(LocalDate date) {

        final List<Person> happyPersons = employeeRepository.findAllByBirthday(date);

        for (Person happyPerson : happyPersons) {
            final Notification notification = new Notification(happyPerson,
                    "Happy Birthday",
                    "Happy birthday, dear " + happyPerson.getFirstName() + "!");

            notificationService.send(notification);
        }

    }
}
