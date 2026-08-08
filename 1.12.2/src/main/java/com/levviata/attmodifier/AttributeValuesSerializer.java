package com.levviata.attmodifier;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class AttributeValuesSerializer implements JsonSerializer<AttributeValues> {

    @Override
    public JsonElement serialize(AttributeValues src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();

        if (src.getMaxHealth() != 0)
            obj.addProperty("maxHealth", src.getMaxHealth());

        if (src.getFollowRange() != 0)
            obj.addProperty("followRange", src.getFollowRange());

        if (src.getKnockbackResistance() != 0)
            obj.addProperty("knockbackResistance", src.getKnockbackResistance());

        if (src.getMovementSpeed() != 0)
            obj.addProperty("movementSpeed", src.getMovementSpeed());

        if (src.getFlyingSpeed() != 0)
            obj.addProperty("flyingSpeed", src.getFlyingSpeed());

        if (src.getAttackDamage() != 0)
            obj.addProperty("attackDamage", src.getAttackDamage());

        if (src.getAttackSpeed() != 0)
            obj.addProperty("attackSpeed", src.getAttackSpeed());

        if (src.getArmor() != 0)
            obj.addProperty("armor", src.getArmor());

        if (src.getArmorToughness() != 0)
            obj.addProperty("armorToughness", src.getArmorToughness());

        if (src.getLuck() != 0)
            obj.addProperty("luck", src.getLuck());

        return obj;
    }
}