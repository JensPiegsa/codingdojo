package gildedrose2;


import static org.mockito.BDDMockito.*;

import org.assertj.core.api.BDDAssertions;
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
    @Mock
    ItemUpdater itemUpdater;
    
    @Captor
    ArgumentCaptor<Item> itemCaptor;

    @Test
    @DisplayName("Updates all qualities")
    void updateAllQualities() {
        GildedRose app = new GildedRose(new ItemUpdater(), new Item[] {itemOne, itemTwo});

        app.updateAllItemQualities();

        BDDMockito.then(itemUpdater).should()
                .updateItem(itemCaptor.capture());
        BDDAssertions.then(itemCaptor.getAllValues())
                .containsExactlyInAnyOrder(itemOne, itemTwo);
                
    }
}