package com.levviata.attmodifier;

import com.expandedevents.api.event.ItemAttributeModifierEvent;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.*;

import static com.levviata.attmodifier.AttMod.getAttributeMap;

public class LAttributeModifier {
    private static final Map<String, AttributeValues> EMPTY_ATTRIBUTE_MAP = new HashMap<>();

    private static final List<IAttribute> ATTRIBUTES_AVAILABLE = new ArrayList<>();
    static {
        ATTRIBUTES_AVAILABLE.add(SharedMonsterAttributes.MAX_HEALTH);
        ATTRIBUTES_AVAILABLE.add(SharedMonsterAttributes.FOLLOW_RANGE);
        ATTRIBUTES_AVAILABLE.add(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
        ATTRIBUTES_AVAILABLE.add(SharedMonsterAttributes.MOVEMENT_SPEED);
        ATTRIBUTES_AVAILABLE.add(SharedMonsterAttributes.FLYING_SPEED);
        ATTRIBUTES_AVAILABLE.add(SharedMonsterAttributes.ATTACK_DAMAGE);
        ATTRIBUTES_AVAILABLE.add(SharedMonsterAttributes.ATTACK_SPEED);
        ATTRIBUTES_AVAILABLE.add(SharedMonsterAttributes.ARMOR);
        ATTRIBUTES_AVAILABLE.add(SharedMonsterAttributes.ARMOR_TOUGHNESS);
        ATTRIBUTES_AVAILABLE.add(SharedMonsterAttributes.LUCK);
    }

    private static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

   @SubscribeEvent
    public void onAttributeModifier(ItemAttributeModifierEvent event) {
        String key = String.valueOf(ForgeRegistries.ITEMS.getKey(event.getItemStack().getItem()));
        if (getAttributes().containsKey(key) && event.getSlotType() == EntityEquipmentSlot.MAINHAND) {
            AttributeValues attributeValues = getAttributes().get(key);
            // in theory for operation int value:
            // 0 addition, 1 multiply base, 2 multiply total
            if (attributeValues.getAttackSpeed() != -1.0F) {
                event.removeAttribute(SharedMonsterAttributes.ATTACK_SPEED);
                event.addModifier(SharedMonsterAttributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", /*- 4.0D +*/ attributeValues.getAttackSpeed(), 0));
            }
            if (attributeValues.getAttackDamage() != -1.0F) {
                event.removeAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
                event.addModifier(SharedMonsterAttributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", attributeValues.getAttackDamage(), 0));
            }
        }
    }
    public static Map<String, AttributeValues> getAttributes() {
        return (getAttributeMap() != null) ? getAttributeMap() : EMPTY_ATTRIBUTE_MAP;
    }
}
