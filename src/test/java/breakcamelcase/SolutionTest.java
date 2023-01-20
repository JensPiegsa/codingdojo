package breakcamelcase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SolutionTest {

    @Test @DisplayName("works for empty string.")
    void worksForEmptyString() {
        assertThat(Solution.camelCase("")).isEqualTo("");
    }

    @Test @DisplayName("keeps nonCamelcase.")
    void keepsNonCamelCase() {
        assertThat(Solution.camelCase("test")).isEqualTo("test");
    }

    @Test @DisplayName("splits camelCase.")
    void splitsCamelCase() {
        assertThat(Solution.camelCase("camelCase")).isEqualTo("camel Case");
    }
    
    @Test
    public void tests() {
      assertEquals( "camel Casing", Solution.camelCase("camelCasing"));
      assertEquals( "camel Casing Test", Solution.camelCase("camelCasingTest"));
      assertEquals( "camelcasingtest", Solution.camelCase("camelcasingtest"));
    }
}