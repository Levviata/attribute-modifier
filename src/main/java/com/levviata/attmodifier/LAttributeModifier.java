package com.levviata.attmodifier;

import com.expandedevents.api.event.ItemAttributeModifierEvent;
import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.*;

import static com.levviata.attmodifier.AttMod.LOGGER;
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

    //vanilla uuids
    private static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
    // randomized uuids
    private static final UUID MAX_HEALTH_UUID =
            UUID.fromString("5b94c2f0-6a6e-4b7d-9f6f-8d2a4d7c1e01");
    private static final UUID FOLLOW_RANGE_UUID =
            UUID.fromString("7d31a6c2-15bb-47d5-aef5-3c94a87f3202");
    private static final UUID KNOCKBACK_RESISTANCE_UUID =
            UUID.fromString("93ef45e8-12c0-4f17-8c3e-61d2a94b5f03");
    private static final UUID MOVEMENT_SPEED_UUID =
            UUID.fromString("b7d3a6f9-58d4-4b2f-a0f7-9c13e4d8a904");
    private static final UUID FLYING_SPEED_UUID =
            UUID.fromString("d2f9c781-7b48-4c81-93ae-0d7f2b6e1505");
    private static final UUID ARMOR_UUID =
            UUID.fromString("e5a14d92-4f33-4d0f-b1ce-7a8d0f2c3606");
    private static final UUID ARMOR_TOUGHNESS_UUID =
            UUID.fromString("f84c7b13-2d75-4d8b-9ef4-4b0a91d54707");
    private static final UUID LUCK_UUID =
            UUID.fromString("18b4f6d0-8ec1-4cba-a57e-52d6f83a7808");

    private static final String nameIn = "Lev Attribute Modifier";

   @SubscribeEvent
    public void onAttributeModifier(ItemAttributeModifierEvent event) {
        String key = String.valueOf(ForgeRegistries.ITEMS.getKey(event.getItemStack().getItem()));
        if (getAttributes().containsKey(key) && event.getSlotType() == EntityEquipmentSlot.MAINHAND) {
            AttributeValues attributeValues = getAttributes().get(key);
            // for int operationIn value:
            // 0 addition, 1 multiply base, 2 multiply total
            if (attributeValues.getAttackSpeed() != 0F && attributeValues.getAttackSpeed() > 0F) {
                event.removeAttribute(SharedMonsterAttributes.ATTACK_SPEED);
                event.addModifier(SharedMonsterAttributes.ATTACK_SPEED,
                        new AttributeModifier(ATTACK_SPEED_MODIFIER, nameIn, attributeValues.getAttackSpeed(), 0));
            } else if (attributeValues.getAttackSpeed() < -0.0F) {
                event.removeAttribute(SharedMonsterAttributes.ATTACK_SPEED);
            }

           if (attributeValues.getAttackDamage() != 0F && attributeValues.getAttackDamage() > 0F) {
                event.removeAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
                event.addModifier(SharedMonsterAttributes.ATTACK_DAMAGE,
                        new AttributeModifier(ATTACK_DAMAGE_MODIFIER, nameIn, attributeValues.getAttackDamage(), 0));
            }
            if (attributeValues.getAttackDamage() < -0.0F) {
                event.removeAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
            }

            if (attributeValues.getArmor() != 0F && attributeValues.getArmor() > 0f) {
                event.removeAttribute(SharedMonsterAttributes.ARMOR);
                event.addModifier(SharedMonsterAttributes.ARMOR,
                        new AttributeModifier(ARMOR_UUID, nameIn, attributeValues.getArmor(), 0));
            } else if (attributeValues.getArmor() < -0.0F) {
                event.removeAttribute(SharedMonsterAttributes.ARMOR);
            }

            if (attributeValues.getArmorToughness() != 0F && attributeValues.getArmorToughness() > 0F) {
                event.removeAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
                event.addModifier(SharedMonsterAttributes.ARMOR_TOUGHNESS,
                        new AttributeModifier(ARMOR_TOUGHNESS_UUID, nameIn, attributeValues.getArmorToughness(), 0));
            } else if (attributeValues.getArmorToughness() < -0) {
                event.removeAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);

            }

            if (attributeValues.getLuck() != 0F && attributeValues.getLuck() > 0f) {
                event.removeAttribute(SharedMonsterAttributes.LUCK);
                event.addModifier(SharedMonsterAttributes.LUCK,
                        new AttributeModifier(LUCK_UUID, nameIn, attributeValues.getLuck(), 0));
            } else if (attributeValues.getLuck() < -0F) {
                event.removeAttribute(SharedMonsterAttributes.LUCK);
            }

            if (attributeValues.getFlyingSpeed() != 0F && attributeValues.getFlyingSpeed() > 0f) {
                event.removeAttribute(SharedMonsterAttributes.FLYING_SPEED);
                event.addModifier(SharedMonsterAttributes.FLYING_SPEED,
                        new AttributeModifier(FLYING_SPEED_UUID, nameIn, attributeValues.getFlyingSpeed(), 0));
            } else if (attributeValues.getFlyingSpeed() < -0) {
                event.removeAttribute(SharedMonsterAttributes.FLYING_SPEED);
            }

            if (attributeValues.getMaxHealth() != 0F && attributeValues.getMaxHealth() > 0f) {
                event.removeAttribute(SharedMonsterAttributes.MAX_HEALTH);
                event.addModifier(SharedMonsterAttributes.MAX_HEALTH,
                        new AttributeModifier(MAX_HEALTH_UUID, nameIn, attributeValues.getMaxHealth(), 0));
            } else if (attributeValues.getMaxHealth() < -0) {
                event.removeAttribute(SharedMonsterAttributes.MAX_HEALTH);
            }

            if (attributeValues.getFollowRange() != 0F && attributeValues.getFollowRange() > 0f) {
                event.removeAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
                event.addModifier(SharedMonsterAttributes.FOLLOW_RANGE,
                        new AttributeModifier(FOLLOW_RANGE_UUID, nameIn, attributeValues.getFollowRange(), 0));
            } else if (attributeValues.getFollowRange() < -0) {
                event.removeAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
            }

            if (attributeValues.getKnockbackResistance() != 0F && attributeValues.getKnockbackResistance() > 0f) {
                event.removeAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
                event.addModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE,
                        new AttributeModifier(KNOCKBACK_RESISTANCE_UUID, nameIn, attributeValues.getKnockbackResistance(), 0));
            } else if (attributeValues.getKnockbackResistance() < -0) {
                event.removeAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
            }

            if (attributeValues.getMovementSpeed() != 0F && attributeValues.getMovementSpeed() > 0f) {
                event.removeAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
                event.addModifier(SharedMonsterAttributes.MOVEMENT_SPEED,
                        new AttributeModifier(MOVEMENT_SPEED_UUID, nameIn, attributeValues.getMovementSpeed(), 2));
            } else if (attributeValues.getMovementSpeed() < -0) {
                event.removeAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
            }
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            EntityPlayer player = event.player;
            // I suppose im getting "this.getHeldItemMainhand()"?
            ItemStack heldMainHand = player.getHeldEquipment().iterator().next();

            float defaultFlyingSpeed = 0.05F;
            if (heldMainHand != ItemStack.EMPTY) {
                Multimap<String, AttributeModifier> attributes = heldMainHand.getAttributeModifiers(EntityEquipmentSlot.MAINHAND);
                if (attributes.containsKey("generic.flyingSpeed")) {
                    player.capabilities.setFlySpeed((float) attributes.get("generic.flyingSpeed").iterator().next().getAmount());
                    //LOGGER.info((float) attributes.get("generic.flyingSpeed").iterator().next().getAmount());
                }
                else player.capabilities.setFlySpeed(defaultFlyingSpeed);
            }
        }
    }
    public static Map<String, AttributeValues> getAttributes() {
        return (getAttributeMap() != null) ? getAttributeMap() : EMPTY_ATTRIBUTE_MAP;
    }
}
