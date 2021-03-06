package Menus.Cammands.BattleCommands;

import Menus.Cammands.Command;
import Menus.MenuManager;

public class SelectCollectable extends Command {
    public SelectCollectable(){
        super("(select|Select) collectable (\\w+)");
    }

    public void execute(){
        MenuManager.getCurrentMatch().setSelectedCollectAble(matcher.group(1));
    }
}
