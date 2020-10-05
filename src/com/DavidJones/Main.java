package com.DavidJones;

import Calculator.Calculator;
import GameDice.Die;
import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Die myDie = new Die(20);
        int damage = 0;

        Calculator newAttack = new Calculator();

        damage = newAttack.attack(3,4,5,"2x6");

        out.println(damage);
    }
}
