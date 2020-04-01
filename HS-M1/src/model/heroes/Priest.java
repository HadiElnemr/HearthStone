package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.DivineSpirit;
import model.cards.spells.HolyNova;
import model.cards.spells.ShadowWordDeath;

public class Priest extends Hero {

	public Priest() throws IOException, CloneNotSupportedException {
		super("Anduin Wrynn");
	}

	@Override
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 13);
//		getDeck().addAll(neutrals);
		for (int i = 0; i < neutrals.size(); i++)
			getDeck().add((Minion) neutrals.get(i).clone());

		for (int i = 0; i < 2; i++) {
			getDeck().add(new DivineSpirit());
			getDeck().add(new HolyNova());
			getDeck().add(new ShadowWordDeath());
		}
		Minion velen = new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false, false, false);

		getDeck().add(velen);

		for (int i = 0; i < getDeck().size(); i++) {
			Card c = getDeck().get(i);
			if (c instanceof Minion)
				((Minion) c).setListener(this);
		}

		Collections.shuffle(getDeck());

	}

	public void useHeroPower(Minion target) throws NotEnoughManaException, HeroPowerAlreadyUsedException,
			NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
//		Minion v = new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false,false,false);
		int p = 2;
//		if(getField().contains(v))
//			p = 8;
		for (Card card : getField())
			if (card.getName().equals("Prophet Velen"))
				p = 8;
		target.setCurrentHP(target.getCurrentHP() + p);

	}

	public void useHeroPower(Hero target) throws NotEnoughManaException, HeroPowerAlreadyUsedException,
			NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
//		Minion v = new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false, false, false);
//		if(getField().contains(v))
//			p = 8;
		int p = 2;
		for (Card card : getField())
			if (card.getName().equals("Prophet Velen"))
				p = 8;
		target.setCurrentHP(target.getCurrentHP() + p);
	}

}
