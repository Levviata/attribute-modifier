package com.levviata.attmodifier;

public class AttributeValues {

    private float maxHealth;
    private float followRange;
    private float knockbackResistance;
    private float movementSpeed;
    private float flyingSpeed;
    private float attackDamage;
    private float attackSpeed;
    private float armor;
    private float armorToughness;
    private float luck;

    public AttributeValues(
            float maxHealth,
            float followRange,
            float knockbackResistance,
            float movementSpeed,
            float flyingSpeed,
            float attackDamage,
            float attackSpeed,
            float armor,
            float armorToughness,
            float luck) {

        this.maxHealth = maxHealth;
        this.followRange = followRange;
        this.knockbackResistance = knockbackResistance;
        this.movementSpeed = movementSpeed;
        this.flyingSpeed = flyingSpeed;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.armor = armor;
        this.armorToughness = armorToughness;
        this.luck = luck;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public float getFollowRange() {
        return followRange;
    }

    public float getKnockbackResistance() {
        return knockbackResistance;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public float getFlyingSpeed() {
        return flyingSpeed;
    }

    public void setFlyingSpeed(float flyingSpeed) {
        this.flyingSpeed = flyingSpeed;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public float getArmor() {
        return armor;
    }

    public float getArmorToughness() {
        return armorToughness;
    }

    public float getLuck() {
        return luck;
    }
}
