package Model.Card_package;

import Exceptions.CannotPutException;
import Exceptions.NotEnoughManaException;
import Menus.MenuManager;
import Model.Card_package.minion_special_power.MinionSpecialPower;
import Model.Card_package.minion_special_power.MinionSpecialPowerActivationTime;
import Model.Match_package.Map;
import Model.Match_package.Match;
import Model.Match_package.Player;

import java.util.ArrayList;
import java.util.HashSet;

public class  Minion extends Force {

    private ArrayList<MinionSpecialPower> specialPowers;

    public Minion(String name, int price, int mana, int hp, int ap, String desc,
                  Player player, AttackType attackType, int range, ArrayList<MinionSpecialPower> specialPowers) {
        super(name, price, mana, desc, player, ap, hp, attackType, range);
        this.setID(String.format("Minion_%s", name));
        this.specialPowers = specialPowers;
    }

    protected Minion getCopy(Player player, String ID) {
        Minion newMinion =  new Minion(this.getName(), this.getPrice(), this.getMana(), getHp(), getAp(), getDesc()
                , player, getAttackType(), getRange(), MinionSpecialPower.getCopy(this.specialPowers));
        newMinion.setID(ID);
        return newMinion;
    }

    public void put(int x, int y) {
        Match match = MenuManager.getCurrentMatch();
        Map map = MenuManager.getCurrentMatch().getMap();
        if (!map.getCell(x, y).isEmpty())
            throw new CannotPutException();
        if (this.getMana() > match.getOwnPlayer().getMana())
            throw new NotEnoughManaException();
        boolean canPut = false;
        for (Force force : map.getForcesInMap(Math.max(0, x - 1), Math.max(0, y - 1), Math.min(4, x + 1), Math.min(8, y + 1))) {
            if (force.isTeammate(this))
                canPut = true;
        }
        if (!canPut)
            throw new CannotPutException();
        map.getCell(x, y).setForce(this);
        match.getOwnPlayer().getHand().getShowAbleCards().remove(this);
        match.getOwnPlayer().reduceMana(this.getMana());
        for (MinionSpecialPower specialPower : specialPowers) {
            if (specialPower.getActivationTime() == MinionSpecialPowerActivationTime.ON_SPAWN)
                specialPower.doOnSpawnSpecialPower(x, y);
        }
    }


    public void addSpecialPowerByCopy(ArrayList<MinionSpecialPower> minionSpecialPowers) {
        for (MinionSpecialPower minionSpecialPower : minionSpecialPowers) {
            this.specialPowers.add(minionSpecialPower.getCopy());
        }
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Minion :  ");
        stringBuilder.append("name :  " + this.getName() + "  ");
        stringBuilder.append("SpecialPower :  " + this.getDesc() + "  ");
        stringBuilder.append("class :  " + this.getAttackType().toString() + "  ");
        stringBuilder.append("AP :  " + this.getAp() + "  ");
        stringBuilder.append("HP :  " + this.getHp() + "  ");
        stringBuilder.append("MP :  " + this.getMana() + "  ");
        return stringBuilder.toString();
    }
}
