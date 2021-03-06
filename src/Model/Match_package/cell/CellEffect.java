package Model.Match_package.cell;

import java.util.ArrayList;

public class CellEffect {
    private CellEffectType cellEffectType;
    private int time;
    public CellEffect(CellEffectType cellEffectType, int time) {
        this.cellEffectType = cellEffectType;
        this.time = time;
    }

    public static ArrayList<CellEffect> getCopy(ArrayList<CellEffect> cellEffects) {
        if (cellEffects == null)
            return null;
        ArrayList<CellEffect> copy = new ArrayList<>();
        for (CellEffect cellEffect : cellEffects)
            copy.add(cellEffect.getCopy());
        return copy;
    }


    public CellEffectType getCellEffectType() {
        return cellEffectType;
    }


    public CellEffect getCopy() {
        return new CellEffect(this.cellEffectType, this.time);
    }

    public void decrementTime() {
        time --;
    }

    public int getTime() {
        return time;
    }
}

