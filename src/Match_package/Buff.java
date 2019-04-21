package Match_package;


import Card_package.Card;
import Card_package.Force;

public class Buff {
    private BuffType buffType;
    private BuffTime buffTime;
    private int time;
    private int unit;
    private Force force;

    public Buff(Force force, BuffType buffType, BuffTime buffTime) {
        this.force = force;
        this.buffType = buffType;
        this.buffTime = buffTime;
        this.unit = 1;
    }
    public Buff(Force force, BuffType buffType, int time) {
        this.force = force;
        this.buffType = buffType;
        this.time = time;
        this.buffTime = null;
        this.unit = 1;
    }
    public Buff(Force force , BuffType buffType, int time, int unit) {
        this.force = force;
        this.buffType = buffType;
        this.time = time;
        this.buffTime = null;
        this.unit = unit;
    }


    public boolean isPositiveBuff() {
        switch (this.buffType) {
            case HOLY:
            case POWER_AP:
            case POWER_HP:
                return true;
        }
        return false;
    }



    public static void execute(Object object, BuffType buffType, int amount) { // CARD
        switch (buffType) {
            case HOLY:
                // card.HP++;
                break;
            case STUN:
                // card.cantMove = amount;
                break;
            case DISARM:
                // card.cantCounterAttack = amount;
                break;
            case POWER_AP:
                // card.Ap += amount;
                break;
            case POWER_HP:
                // card.HP += amount;
                break;
            case WEAKNESS_AP:
                // card.AP -= amount;
                break;
            case WEAKNESS_HP:
            case POISON:
                // card.HP -= amount;
                break;
        }
    }

    static void execute(Object object, BuffType buffType) {
        execute(object, buffType, 0);
    }

}


