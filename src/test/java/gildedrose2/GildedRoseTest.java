package gildedrose2;


import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


/**
 * see:  
 */
@ExtendWith(MockitoExtension.class)
class GildedRoseTest {
    @Mock
    Item itemOne;
    @Mock
    Item itemTwo;

    @Captor
    ArgumentCaptor<Item> itemCaptor;

    @Test
    @Disabled
    @DisplayName("Updates all qualities")
    void updateAllQualities() {
        GildedRose app = new GildedRose(new Item[] {itemOne, itemTwo});

        app.updateAllItemQualities();

        BDDAssertions.then(itemCaptor.getAllValues())
                .containsExactlyInAnyOrder(itemOne, itemTwo);
                
    }
}