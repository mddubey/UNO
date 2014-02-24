package com.step.uno.model;

import com.step.uno.messages.GameResult;
import com.step.uno.messages.Snapshot;
import com.step.uno.messages.TurnLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private int currentPlayerIndex = 0;
    public final List<Player> players;
    private final Deck closedDeck;
    private final Deck openDeck;
    private boolean isInAscendingOrder = true;
    private Colour runningColour;
    private int draw2Run=0;
    private Card card;
    private Player player;

    public Game(int packs, List<Player> givenPlayers) {
        players = new ArrayList<>(givenPlayers);
        closedDeck = new Deck(Card.createNewPacks(packs));
        openDeck = new Deck();
    }

    public void initialize() {
        Collections.shuffle(players);
        closedDeck.shuffle();
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.take(draw());
            }
        }
        openDeck.add(draw());
    }
    private Card draw(){
        if(closedDeck.isEmpty()){
            closedDeck.addAll(openDeck.drawAllButLast());
            closedDeck.shuffle();
        }
        return closedDeck.draw();
    }

    public void populate(Snapshot snapshot, Player player) {
        player.populateSelf(snapshot);
        snapshot.myPlayerIndex = players.indexOf(player);
        List<PlayerSummary> summaries = new ArrayList<>();
        for (Player p : players) {
            summaries.add(p.generateSummary());
        }
        snapshot.playerSummaries = summaries.toArray(new PlayerSummary[]{});
        snapshot.currentPlayerIndex = currentPlayerIndex;
        snapshot.openCard = openDeck.lookAtLast();
        snapshot.isInAscendingOrder = this.isInAscendingOrder;
        snapshot.runningColour = runningColour;
        snapshot.draw2Run = draw2Run;
        snapshot.log = new TurnLog(player.name,card);
    }

    public void playCard(Player player, Card card, Colour newColour) {
        //handle action of card
        this.player = player;
        this.card = card;
        player.play(this.card);
        openDeck.add(card);
        handleReverse(card);
        handleSkip(card);
        handleDrawTwo(card);
        handleWildCard(card, newColour);
        nextTurn();
    }

    private void handleReverse(Card card) {
        if (!card.sign.equals(Sign._Reverse)) return;
        isInAscendingOrder=!isInAscendingOrder;
    }

    private void handleSkip(Card card) {
        if (!card.sign.equals(Sign._Skip)) return;
        nextTurn();
    }

    private void handleDrawTwo(Card card) {
        if (!card.sign.equals(Sign._DrawTwo)) return;
        draw2Run++;
    }

    private void handleWildCard(Card card, Colour newColour) {
        runningColour = card.colour.equals(Colour.Black) ? newColour : card.colour;
        if (card.sign.equals(Sign._DrawFour)) applyDrawFour();
    }

    private void applyDrawFour() {
        nextTurn();
        Player player = players.get(currentPlayerIndex);
        for (int i = 0; i < 4; i++) {
            player.take(draw());
        }
    }

    private void nextTurn() {
        //handle reverse and skip
        int increment = isInAscendingOrder?1:-1;
        currentPlayerIndex = currentPlayerIndex + increment + players.size();
        currentPlayerIndex %= players.size();
    }

    public Card drawCard(Player player) {
        //Can play the same card in that turn
        Card newCard = draw();
        player.take(newCard);
        return newCard;
    }

    public void declareUno(Player player) {
        player.declareUno();
    }

    public void catchUno(Player player, int playerIndex) {
        Player caughtPlayer = players.get(playerIndex);
        if (caughtPlayer.checkUno()) {
            caughtPlayer.take(draw());
            caughtPlayer.take(draw());
        }
    }

    public void populate(GameResult result) {
        List<PlayerResult> playerResults = new ArrayList<>();
        for (Player player : players) {
            playerResults.add(player.generateResult());
        }
        result.players = playerResults.toArray(new PlayerResult[]{});
    }

    public void drawTwoCards(Player player) {
        for (int i = 0; i < draw2Run * 2; i++) player.take(draw());
        draw2Run = 0;
        nextTurn();
    }

    public void moveForwardAsPlayerTookNoActionOnDrawnCard() {
        nextTurn();
    }

    private boolean handleDraw4(Snapshot snapshot, Card card) {
        Colour runningColor = snapshot.runningColour;
        for (Card myCard : snapshot.myCards) {
            if(myCard.colour.equals(runningColor))
                return false;
        }
        return true;
    }
}

