package Menus.Cammands.Battle_Commands;

import Menus.Cammands.Command;

public class UseItem extends Command {
    public UseItem(){
        super ("(use|Use) (\\w+)");
    }

}
