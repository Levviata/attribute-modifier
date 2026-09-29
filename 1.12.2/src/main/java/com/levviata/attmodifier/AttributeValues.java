package com.levviata.attmodifier;

public class AttributeValues {
    private double maxHealth;
    private double followRange;
    private double knockbackResistance;
    private double movementSpeed;
    private double flyingSpeed;
    private double attackDamage;
    private double attackSpeed;
    private double armor;
    private double armorToughness;
    private double luck;
    private int durability;
    private int stackSize;
    private float efficiency;
    private int enchantability;
    private int harvestLevel;

    // every time FMLPreInitializationEvent runs, attributeMap gets written with the entries of attributeModifier.json (cfg)
    // holding an entry for the map as <String, AttributeValues>, these are then requested at LAttributeModifier.java.
    public AttributeValues(
            double maxHealth,
            double followRange,
            double knockbackResistance,
            double movementSpeed,
            double flyingSpeed,
            double attackDamage,
            double attackSpeed,
            double armor,
            double armorToughness,
            double luck,
            int durability,
            int stackSize,
            float efficiency,
            int enchantability,
            int harvestLevel) {
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
        this.durability = durability;
        this.stackSize = stackSize;
        this.efficiency = efficiency;
        this.enchantability = enchantability;
        this.harvestLevel = harvestLevel;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getFollowRange() {
        return followRange;
    }

    public double getKnockbackResistance() {
        return knockbackResistance;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public double getFlyingSpeed() {
        return flyingSpeed;
    }

    public void setFlyingSpeed(double flyingSpeed) {
        this.flyingSpeed = flyingSpeed;
    }

    public double getAttackDamage() {
        return attackDamage;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public double getArmor() {
        return armor;
    }

    public double getArmorToughness() {
        return armorToughness;
    }

    public double getLuck() {
        return luck;
    }

    public int getDurability() {
        return durability;
    }

    public int getStackSize() {
        return stackSize;
    }

    public float getEfficiency() {
        return efficiency;
    }

    public int getEnchantability() {
        return enchantability;
    }

    public int getHarvestLevel() {
        return harvestLevel;
    }
}
