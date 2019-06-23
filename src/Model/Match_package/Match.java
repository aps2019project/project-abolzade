package Model.Match_package;

import Account_package.Account;
import Account_package.MatchResult;
import Account_package.MatchResultType;
import Exceptions.*;
import Menus.GameMode;
import Model.Card_package.Card;
import Model.Card_package.Force;
import Model.Card_package.Minion;
import Model.Card_package.Spell;
import Model.Card_package.collectable.CollectAble;
import Model.Match_package.Battle_Type.*;
import Model.Match_package.cell.Cell;

import static Model.Match_package.Battle_Type.MatchType.*;

abstract public class Match {
    private Player ownPlayer;
    private Player opponent;
    private Account account1, account2;
    private Map map = new Map();
    private int turn = 1;
    private MatchType matchType;

    public Match(Account account1, Account account2) {
        this.account1 = account1;
        this.account2 = account2;
        ownPlayer = new Player(account1);
        opponent = new Player(account2);
        setMatchType();
        //todo set hero, collectAbles ... in map
    }

//    protected void checkGame() todo

    public void changeTurn() {
        turn++;
        switchPlayers();
        ownPlayer.setMana();
        ownPlayer.setSelectedCard(null, null);
        ownPlayer.setSelectedCollectAble(null);
    } // todo check

    public boolean isAITurn() {
        return turn % 2 == 0;
    }

    public void addToGraveYard(Card card) {
        card.getPlayer().getGraveYard().addToDeadCards(card);
    }


    public Map getMap() {
        return map;
    }

    public void Attack(Force enemy) {



    } //todo

    public void setSelectedCollectAble(String id) throws UnitNotFoundException {
        for (CollectAble collectable : ownPlayer.getCollectAbles()) {
            if (collectable.getID() == id) {
                ownPlayer.setSelectedCollectAble(collectable);
                return;
            }
        }
        throw new UnitNotFoundException();
    }

    public void setSelectedCard(String id) throws UnitNotFoundException {
        for (Force force : map.getForcesInMap(ownPlayer))
            if (force.getID().equals(id)) {
                ownPlayer.setSelectedCard(force, SelectedCardPosition.IN_MAP);
                return;
            }
        for (Card card : ownPlayer.getHand().getShowAbleCards())
            if (card.getID().equals(id)) {
                ownPlayer.setSelectedCard(card, SelectedCardPosition.IN_HAND);
                return;
            }
        throw new UnitNotFoundException();
    }
    // sets selected card or throw a particular exception


    public void insert(String id, int x, int y) { // always gets
        //todo check can insert
        // spell check wrote in spell effect target
        Card card = null;
        for (Card showAbleCard : ownPlayer.getHand().getShowAbleCards()) {
            if (showAbleCard.getID().equals(id))
                card = showAbleCard;
        }
        if (card == null)
            throw new CardNotInHandException();
        if (card.getMana() > ownPlayer.getMana())
            throw new NotEnoughManaException();
        if (card instanceof Spell)
            ((Spell) card).put(x, y);
        if (card instanceof Minion)
            ((Minion) card).put(x, y);

    }


    public void move(int x, int y) {
        //todo handle can move again (check number of move an attack)
        map.move(x, y);
    }

    public int getTurn() {
        return turn;
    }

    public Player getCardOwner(Card card) {
        return card.getPlayer();
    }

    private void setMatchType() {
        if (this instanceof CollectFlag)
            matchType = COLLECT_FLAG;
        else if (this instanceof HoldFlag)
            matchType = HOLD_FLAG;
        else if (this instanceof KillHero)
            matchType = KILL_HERO;
    }

    private void switchPlayers() {
        Player temp = ownPlayer;
        ownPlayer = opponent;
        opponent = temp;
    }

    public MatchType getMatchType() {
        return matchType;
    }


    public Player getOwnPlayer() {
        return ownPlayer;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setWinner(Player winnerPlayer, Player loserPlayer, GameMode mode) {
        Account winner = Account.findAccount(winnerPlayer.getName());
        Account loser = Account.findAccount(loserPlayer.getName());
        if (mode.equals(GameMode.MULTI_PLAYER)) {
            winner.earn(1000);
//            loser.pay(1000); // todo money to come to game not checked
        }
        winner.addToMatchHistory(new MatchResult(loser, MatchResultType.WON));
        loser.addToMatchHistory(new MatchResult(winner, MatchResultType.LOST));
    }

    @Override // TODO ? I don't know why? :)
    public String toString() {
        StringBuilder buffer = new StringBuilder("Game mode : ");
        buffer.append(matchType.getTitle() + "\n");
        buffer.append("own mana :" + ownPlayer.getMana() + "\n");
        buffer.append("opponent mana :" + opponent.getMana() + "\n");
        switch (matchType) {
            case KILL_HERO:
                buffer.append("Hero HPs : " + ownPlayer.getHand().getHero().getHp() + " vs " + opponent.getHand().getHero().getHp());
                break;
            case HOLD_FLAG:
                for (Cell[] cells : map.getCells())
                    for (Cell cell : cells)
                        if (cell.hasForce())
                            if (cell.getForce().hasFlag())
                                buffer.append(cell.getForce().getID() + "has Flag");
                break;
            case COLLECT_FLAG:
                for (Cell[] cells : map.getCells())
                    for (Cell cell : cells) {
                        if (cell.hasFlag())
                            buffer.append("Cell : " + cell.toString() + "\n");
                        if (cell.hasForce())
                            if (cell.getForce().hasFlag())
                                buffer.append(cell.getForce().getID() + "has a Flag");
                    }
        }
        return buffer.toString();
    }
}