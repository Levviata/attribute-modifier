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
    private int durability;
    private int stackSize;
    private float efficiency;
    private int enchantability;

    // every time FMLPreInitializationEvent runs, attributeMap gets written with the entries of attributeModifier.json (cfg).
    // Holding an entry for the map as <String, AttributeValues>, these are then requested at LAttributeModifier.java and modified.
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
            float luck,
            int durability,
            int stackSize,
            float efficiency,
            int enchantability) {
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
}
