package Menus.Cammands.Battle_Commands;

import Menus.Cammands.Command;
import Menus.MenuManager;

public class SelectCollectable extends Command {
    public SelectCollectable(){
        super("(select|Select) collectable (\\w+)");
    }

    public void execute(){
        MenuManager.getCurrentMatch().setSelectedCollectable(matcher.group(1));
    }
}
