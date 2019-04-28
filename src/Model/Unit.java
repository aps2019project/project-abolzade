package Model;

import java.util.ArrayList;
import java.util.Random;

abstract public class Unit {
    private int price;
    private String ID;
    private int mana;
    private ArrayList<Integer> IDNumbers = new ArrayList<Integer>();

    public Unit(){}

    public Unit(String ID, int price, int mana){
        this.price = price;
        this.ID = ID;
        this.mana = mana;
    }

    public Unit(String ID, int mana){
        this.ID = ID;
        this.mana = mana;
    } // just for shop

    public int getMana() {
        return this.mana;
    }

    public int getPrice() {
        return price;
    }

    public String getID() {
        return ID;
    }

    abstract protected Unit getCopy(String ID);

    public Unit getCopy(){
        String newID;
        newID = generateID();
        return getCopy(newID);
    }

    private String generateID(){
        return String.format("Card_%d", getRandomNumber());
    }

    private int getRandomNumber(){
        Random random = new Random();
        int result;
        do{
            result = random.nextInt(10000);
        }while(IDNumbers.contains(result));
        return result;
    }
}

