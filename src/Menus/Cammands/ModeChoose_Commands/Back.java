package Menus.Cammands.ModeChoose_Commands;

import Menus.Cammands.Command;
import Menus.MenuManager;
import Menus.Menus;

public class Back extends Command {
    public Back(){
        super("(Back|back)");
    }

    public void execute(){
        MenuManager.back(Menus.MAIN);
    }
}
