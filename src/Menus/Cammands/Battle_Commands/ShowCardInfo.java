package Menus.Cammands.Battle_Commands;

import Menus.Cammands.Command;
import Menus.MenuManager;

public class ShowCardInfo extends Command {
    public ShowCardInfo(){
        super("show info (\\w+)");
    }

    public void execute(){
        view.show(MenuManager.getCurrentMatch().getOwnPlayer().getCard(matcher.group(1)).toString());
        //todo to be handled Card toString.
    }
}
