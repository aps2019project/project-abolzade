package Model.Match_package;


import Model.Card_package.Card;
import Model.Card_package.Force;

import Model.Item_package.Flag;
import Model.Item_package.Item;

import java.util.ArrayList;

public class Cell {
    private Force force;
    private Item item; // ITEM
    private ArrayList<CellEffect> cellEffects = new ArrayList<>();
    private Flag flag;
    private Coordination coordination;

    Cell(int x, int y){
        coordination = new Coordination(x, y);
    }

    public Coordination getCoordination() {
        return coordination;
    }

    void doCellEffect(){
//        for(CellEffect cellEffect: cellEffects){
//            switch (cellEffect.getCellEffectType()){
//                case FIRE:
//                    if(true) // GameSituation == ChangeTurn
//                    Buff.execute(force, BuffType.POISON );
//                    break;
//                case POISON:
//                    Buff.execute(force, BuffType.POISON);
//                    break;
//                case HOLY:
//                    Buff.execute(force, BuffType.HOLY);
//                    break;
//            }
//        }
    }

    void assignCollectable() {
//        if(card != null) {
//            findPlayer(card).catchItem(item);
//            item = null;
//        }
    }

    public boolean hasPlayerCard(Player player) {
        return force.getPlayer().equals(player);
    }

    void setForce(Force force) {
        this.force = force;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    Cell addToCellEffect(CellEffect cellEffect) {
        cellEffects.add(cellEffect);
        return this;
    }

    void deleteForce() {
        force = null;
    }

    Item getItem() {
        return item;
    }

    public void setFlag(Flag flag){
        this.flag = flag;
    }

    public Force getForce() {
        return force;
    }

    public boolean isEmpty() {
        if (this.force == null)
            return true;
        return false;
    }

    public void addCellEffect(CellEffect cellEffect) {
        this.cellEffects.add(cellEffect);
    }

    public boolean hasItem() {
        return item != null;
    }

    public boolean hasCard() {
        return force != null;
    }

    public boolean hasCard(Force force) {
        return force.equals(this.force);
    }

    public void deleteCard(){
        force = null;
    }

    public void setCard(Card card){
        this.force = (Force) card;
    }
}
