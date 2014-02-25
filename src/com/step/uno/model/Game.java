package com.step.uno.model;

import com.step.uno.messages.GameResult;
import com.step.uno.messages.Snapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Game {
    private int currentPlayerIndex = 0;
    public final List<Player> players;
    private final Deck closedDeck;
    private final Deck openDeck;
    private boolean isInAscendingOrder = true;
    private Colour runningColour;
    private int draw2Run = 0;
    private Card card;
    private Player player;
    private List<String> log = new ArrayList<>();

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

        Card drawnCard = drawCardButWild();
        openDeck.add(drawnCard);
        handleReverse(drawnCard);
        handleSkip(drawnCard);
        handleDrawTwo(drawnCard);

        updateLogAfterInitialize(drawnCard);
    }

    private Card drawCardButWild() {
        Card drawnCard = draw();
        if (drawnCard.isWild() || drawnCard.isDrawFour()) {
            closedDeck.add(drawnCard);
            closedDeck.shuffle();
            return drawCardButWild();
        }
        return drawnCard;
    }

    private void updateLogAfterInitialize(Card card) {
        log.add(getTime() + " " +" Game starts with " + card.colour + " " + getSign(card) + "\n");
    }

    private String getSign(Card card) {
        return card.sign.toString().split("_")[1];
    }

    private String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        return format.format(new Date());
    }

    private Card draw() {
        if (closedDeck.isEmpty()) {
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
        snapshot.currentTurnLog = this.log.get(log.size() - 1);
    }

    public void playCard(Player player, Card card, Colour newColour) {
        this.player = player;
        this.card = card;
        player.play(this.card);
        openDeck.add(card);
        handleReverse(card);
        handleSkip(card);
        handleDrawTwo(card);
        handleWildCard(card, newColour);
        nextTurn();
        updateLogAfterPlay(player, card);
    }

    private void updateLogAfterDraw(Player player) {
        log.add(getTime() +" " + player.name + " drew a card " + "\n");
    }

    private void updateLogAfterPlay(Player player, Card card) {
        log.add(getTime() + " "+player.name + " played a " + card.colour + " " + getSign(card) + "\n");
    }

    private void handleReverse(Card card) {
        if (!card.sign.equals(Sign._Reverse)) return;
        isInAscendingOrder = !isInAscendingOrder;
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
        int increment = isInAscendingOrder ? 1 : -1;
        currentPlayerIndex = currentPlayerIndex + increment + players.size();
        currentPlayerIndex %= players.size();
    }

    public Card drawCard(Player player) {
        Card newCard = draw();
        player.take(newCard);
        updateLogAfterDraw(player);
        return newCard;
    }

    public void declareUno(Player player) {
        player.declareUno();
        log.add(player.name + " has declared uno\n");
    }

    public void catchUno(int playerIndex) {
        Player caughtPlayer = players.get(playerIndex);
        if (caughtPlayer.checkUno()) {
            caughtPlayer.take(draw());
            caughtPlayer.take(draw());
            this.log.add(caughtPlayer.name + " has been catched " + getTime() + "\n");
        }
        else
            this.log.add("catch was not valid on " + caughtPlayer.name + " " + getTime() + "\n");
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
}

