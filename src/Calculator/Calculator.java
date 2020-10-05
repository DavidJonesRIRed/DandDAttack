package Calculator;

import GameDice.Die;

import static java.lang.Integer.parseInt;

import static java.lang.System.out;

public class Calculator {
    private int naturalTwenty = 20;
    private int naturalOne = 1;
    private boolean isCriticalHit;
    private boolean isCriticalMiss;
    private int twentySidedDieResult;
    private int totalAttack;
    private int totalTargetDefence;
    private int totalDamage;

    public Calculator(){
        this.isCriticalHit = false;
        this.isCriticalMiss = false;
        this.twentySidedDieResult = 0;
        this.totalAttack = 0;
        this.totalTargetDefence = 0;
        this.totalDamage = 0;
    }

    private int parseAndRollDamageDice(String damageDice){
        int result=0;
        String dieFormat[] = damageDice.split("x");

        for(int dice = 0; dice < parseInt(dieFormat[0]); dice++){
            Die newDie = new Die(parseInt(dieFormat[1]));
            result = result + newDie.dieRoll();

            try
            {
                Thread.sleep(50);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }

        return result;
    }

    public int attack(int ac, int defenseMod, int attackModifier, String damageDice) {
        Die twentySidedDie = new Die(20);
        this.twentySidedDieResult = twentySidedDie.dieRoll();
        this.totalAttack = this.twentySidedDieResult + attackModifier;
        this.totalTargetDefence = ac + defenseMod;

        switch (this.twentySidedDieResult) {
            case 20:
                this.isCriticalHit = true;
            case 1:
                this.isCriticalMiss = true;
            default:
                this.isCriticalHit = false;
                this.isCriticalMiss = false;
        }

        if (this.totalAttack > this.totalTargetDefence) {
            int xDamage = 0;
            int yDamage = 0;
            if (this.isCriticalHit) {
                xDamage = parseAndRollDamageDice(damageDice);
                yDamage = parseAndRollDamageDice(damageDice);
                this.totalDamage = xDamage + yDamage;
                out.println("Critical Hit! " + xDamage + " " + yDamage + " = " + this.totalDamage + " damage");
            } else {
                this.totalDamage = parseAndRollDamageDice(damageDice);
                out.println("Hit " + this.totalDamage + " damage delt");
            }

        } else {
            if(isCriticalMiss){
                out.println("Critical Miss!");
            }else{
                out.println("Miss 0 damage");
            }
        }

        return totalDamage;
    }
}
