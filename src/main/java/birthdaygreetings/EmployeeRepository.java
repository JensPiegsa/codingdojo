package birthdaygreetings;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository {
    List<Person> findAllByBirthday(LocalDate birthdayDate);
}
