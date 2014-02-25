package com.step.uno.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DeckTest {
    List<Card> cards = new ArrayList<>();
    Deck deck;

    @Before
    public void setUp(){
        Card card = new Card();
        card.colour = Colour.Blue;
        card.sign = Sign._2;
        cards.add(card);
    }

    @Test
    public void shouldDrawACard(){
        deck = new Deck( cards.toArray(new Card[]{}));
        deck.draw();
        assertTrue(deck.isEmpty());
    }

    @Test
    public void shouldAddACard()  {
        cards.remove(0);
        deck = new Deck(cards.toArray(new Card[]{}));
        deck.add(new Card());
        assertFalse(deck.isEmpty());
    }

    @Test
    public void shouldGiveLastCard()  {
        deck = new Deck(cards.toArray(new Card[]{}));
        assertThat(deck.lookAtLast(),is(cards.get(0)));
    }

    @Test
    public void shouldCheckIfEmpty()  {
        cards.remove(0);
        deck = new Deck(cards.toArray(new Card[]{}));
        assertTrue(deck.isEmpty());
    }

    @Test
    public void shouldDrawAllButLast()  {
        deck = new Deck( cards.toArray(new Card[]{}));
        Card card = new Card();
        card.colour = Colour.Blue;
        card.sign = Sign._2;
        deck.add(card);
        deck.drawAllButLast();
        assertEquals(deck.lookAtLast(),card);
    }
}
