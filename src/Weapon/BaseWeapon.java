package Weapon;

public abstract class BaseWeapon {
    protected int attackValue=0;
    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

}
