package com.tw.unoProject.model;

import com.tw.unoProject.controller.UNOFactory;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class RandomizerTest {
    @Test
    public void testShuffleCards()  {
        UNOFactory stub = mock(UNOFactory.class);

        Randomizer randomizer = new Randomizer(stub);
        randomizer.shuffleCards(new ArrayList());

        verify(stub, times(1)).shuffleCards(anyList());
    }
}
