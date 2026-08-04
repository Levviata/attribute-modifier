package com.levviata.attmodifier;

public class AttributeValues {

    private float maxHealth = -1.0F;
    private float followRange = -1.0F;
    private float knockbackResistance = -1.0F;
    private float movementSpeed = -1.0F;
    private float flyingSpeed = -1.0F;
    private float attackDamage = -1.0F;
    private float attackSpeed = -1.0F;
    private float armor = -1.0F;
    private float armorToughness = -1.0F;
    private float luck = -1.0F;

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

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getFollowRange() {
        return followRange;
    }

    public void setFollowRange(float followRange) {
        this.followRange = followRange;
    }

    public float getKnockbackResistance() {
        return knockbackResistance;
    }

    public void setKnockbackResistance(float knockbackResistance) {
        this.knockbackResistance = knockbackResistance;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
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

    public void setAttackDamage(float attackDamage) {
        this.attackDamage = attackDamage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public float getArmor() {
        return armor;
    }

    public void setArmor(float armor) {
        this.armor = armor;
    }

    public float getArmorToughness() {
        return armorToughness;
    }

    public void setArmorToughness(float armorToughness) {
        this.armorToughness = armorToughness;
    }

    public float getLuck() {
        return luck;
    }

    public void setLuck(float luck) {
        this.luck = luck;
    }
}
