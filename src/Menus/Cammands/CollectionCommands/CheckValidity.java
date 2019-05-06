package Menus.Cammands.CollectionCommands;

import Exceptions.DeckNotFoundException;
import Menus.Buffer;
import Menus.Cammands.Command;
import Model.Match_package.Deck;

public class CheckValidity extends Command {

    public CheckValidity() {
        super("validate deck (\\w+)");
    }

    public void execute() {
        Deck deck = Buffer.getBufferCollection().getDeck(matcher.group(1));
        if(deck == null)
            throw new DeckNotFoundException();
        view.show(deck.isValid()?"Is Valid":"Not Valid");
    }
}