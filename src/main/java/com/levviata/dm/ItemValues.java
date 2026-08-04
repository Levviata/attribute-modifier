package com.levviata.dm;

public class ItemValues {
    private float attackDamage = -1.0F;

    private float attackSpeed = -1.0F;

    public ItemValues(float attackDamage, float attackSpeed) {
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public float getAttackSpeed() {
        return this.attackSpeed;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }
}

