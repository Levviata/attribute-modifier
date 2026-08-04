package com.levviata.dm;

import com.expandedevents.api.event.ItemAttributeModifierEvent;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.UUID;

public class DamageAttributeModifier {
    private static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

   @SubscribeEvent
    public void onAttributeModifier(ItemAttributeModifierEvent event) {
        String key = String.valueOf(ForgeRegistries.ITEMS.getKey(event.getItemStack().getItem()));
        if (key.equals("minecraft:diamond_sword") && event.getSlotType() == EntityEquipmentSlot.MAINHAND) {
            //ItemValues itemValues = getItemValues().get(key);
            float speedModifier = 3;
            float damageModifier = 3;
            // in theory for operation int value:
            // 0 addition, 1 multiply base, 2 multiply total
            if (speedModifier != -1.0F) {
                event.removeAttribute(SharedMonsterAttributes.ATTACK_SPEED);
                event.addModifier(SharedMonsterAttributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", /*- 4.0D +*/ speedModifier, 0));
            }
            if (damageModifier != -1.0F) {
                event.removeAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
                event.addModifier(SharedMonsterAttributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", damageModifier, 0));
            }
        }
    }
    // todo config
}
