package Model.Match_package;

import Exceptions.CardNotInHandException;
import Model.Card_package.Card;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> handCards = new ArrayList<>();
    private Card nextCard;

    Hand(ArrayList<Card> cards, Card nextCard) {
        setNextCard(nextCard);
        handCards.addAll(cards);
    }

    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    public void setNextCard(Card card) {
        nextCard = card;
    }

    public void setCardAsOnGround(Card usedCard, Card newDeckCard) {
        int recentIndex = handCards.indexOf(usedCard);
        handCards.remove(usedCard);
        addNextCard(recentIndex);
        if (newDeckCard != null)
            setNextCard(newDeckCard);
    }

    public void addNextCard(int index) {
        if (nextCard != null)
            handCards.add(index, nextCard);
        else {
            // do nothing
        }
    }

    public boolean hasCard(){
        if(handCards.size()>0)
            return true;
        return false;
    }

    public boolean hasCard(Card card){
        return handCards.contains(card);
    }

    public Card getNextCard(){
        return nextCard;
    }

    public Card getCard(String id){
        for(Card card:handCards)
            if(card.getID().equals(card))
                return card;
            throw new CardNotInHandException();
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("Hand Cards : \n");
        for(Card card:handCards)
            stringBuilder.append(" - " + card.getID() + "\n");
        stringBuilder.append("Next card : " + nextCard.getID());
        return stringBuilder.toString();
    }
}
