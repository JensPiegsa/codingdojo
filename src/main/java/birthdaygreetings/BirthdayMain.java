package birthdaygreetings;

import java.time.LocalDate;

public class BirthdayMain {

    public static void main(String[] args) {

        EmployeeRepository employeeRepository = null;
        NotificationService emailService = null;

        BirthdayService birthdayService = new BirthdayService(
                employeeRepository, emailService);

        birthdayService.sendGreetings(today());
    }

    private static LocalDate today() {
        return LocalDate.now();
    }
}
