package Model.Card_package.minion_special_power;

import Model.Card_package.buff.Buff;
import Model.Card_package.effect.Effect;

import java.util.ArrayList;

public class MinionSpecialPower {
    MinionSpecialPowerActivationTime activationTime;
    MinionSpecialPowerTarget target;
    MinionSpecialPowerType type;
    ArrayList<Buff> buffs;
    ArrayList<Effect> effects;

}