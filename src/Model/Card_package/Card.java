package Model.Card_package;

import Model.Match_package.Coordination;
import Model.Match_package.Player;

import java.util.ArrayList;

abstract public class Card {
    private static ArrayList<Card> cards;

    private int cost;
    private String ID;
    private int mana;
    protected Player player;



    protected Card(String ID, int cost, int mana) { //in constructor baraye sakhtan card asli mibashad
        this.cost = cost;
        this.ID = ID;
        this.mana = mana;
    }

    public static ArrayList<Card> getCards() {
        return cards;
    }

    public int getCost() {
        return cost;
    }

    public String getID() {
        return ID;
    }

    protected Card(String ID, Card mainCard) {// in constructor baraye sakhtan card dar match mibashad
        this.ID = ID;
        this.mana = mainCard.mana;
    }

    public Player getPlayer() {
        return player;
    }
    abstract public Card getCopy(String ID);
    public int getMana() {
        return this.mana;
    }
    abstract public boolean canPutCard(Coordination coordination);
    abstract public void putCard(Coordination coordination);
    public boolean isTeammate(Card card) {
        if (this.player == card.player)
            return true;
        return false;
    }
}
