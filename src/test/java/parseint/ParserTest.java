package parseint;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("A Parser")
class ParserTest {

    @ParameterizedTest
    @DisplayName("test")
    @CsvSource({
            "one,1",
            "two,2",
            "three,3",
            "four,4",
            "five,5",
            "six,6",
            "seven,7",
            "eight,8",
            "nine,9",
            "ten,10",
            "eleven,11",
            "twelve,12",
            "thirteen,13",
            "fourteen,14",
            "fifteen,15",
            "sixteen,16",
            "seventeen,17",
            "eighteen,18",
            "nineteen,19",
            "twenty,20",
            "twenty-one,21",
            "twenty-two,22",
            "thirty,30",
            "forty,40",
            "fifty,50",
            "sixty,60",
            "seventy,70",
            "eighty,80",
            "ninety,90",
            "one hundred,100",
            "one hundred one, 101",
            "one hundred and one, 101",
            "one hundred twenty-one, 121",
            "one hundred and twenty-one, 121",
            "one thousand, 1000",
            "seven thousand two hundred twenty-three, 7223",
            "seven thousand twenty-three, 7023",
            "one hundred thirty-eight thousand twenty-three, 138_023",
            "one million, 1_000_000"
    })
    void test(final String input, final int expected) {
        assertThat(Parser.parseInt(input)).isEqualTo(expected);
    }

}