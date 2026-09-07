package com.levviata.attmodifier;

import com.expandedevents.api.event.ItemAttributeModifierEvent;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.util.*;

import static com.levviata.attmodifier.AttMod.LOGGER;
import static com.levviata.attmodifier.AttMod.getAttributeMap;

public class LAttributeModifier {
    private static final Map<String, AttributeValues> EMPTY_ATTRIBUTE_MAP = new HashMap<>();

    private final int TOOL_EFFICIENCY_INDEX = 1;
    private final int MATERIAL_HARVEST_LEVEL_INDEX = 5;
    private final int MATERIAL_ENCHANTABILITY_INDEX = 9;

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
       ItemStack item = ItemStack.EMPTY;
       if (event.getItemStack() != null || event.getItemStack().isEmpty()) {
           item = event.getItemStack();
       }

       String key;
       //LOGGER.info("HELD IS KEY {}", item);
       //LOGGER.info("METADATA IS {}", item.getMetadata());

       int correctData = item.getMetadata();

       if (item.isItemDamaged()) { // metadata isnt just different item types
           //LOGGER.info("HELD IS DAMAGED BY {}", item.getItemDamage());
           // fix metadata by removing item damage, this gives me item subtypes I guess?
           correctData = item.getMetadata() - item.getItemDamage();
       }

       if (correctData == 0) {// if correct data is 0
           //LOGGER.info("HELD HAS NO METADATA {}", item.getMetadata());
           key  = String.valueOf(ForgeRegistries.ITEMS.getKey(item.getItem()));
       } else {
           key  = ForgeRegistries.ITEMS.getKey(item.getItem()) + ":" + correctData;
           // LOGGER.info("HELD IS MODIFIED KEY {}", key);
       }

       EntityEquipmentSlot naturalSlot =
               EntityLiving.getSlotForItemStack(event.getItemStack());

       if (event.getSlotType() != naturalSlot) {
           return;
       }

       // MODIFY ATTRIBUTES //
        if (getAttributes().containsKey(key)) {
            AttributeValues attributeValues = getAttributes().get(key);

            Item.ToolMaterial material = null;

            // for int operationIn value:
            // 0 addition, 1 multiply base, 2 multiply total
            if (attributeValues.getAttackSpeed() != 0F) {
                event.removeAttribute(SharedMonsterAttributes.ATTACK_SPEED);
                event.addModifier(SharedMonsterAttributes.ATTACK_SPEED,
                        new AttributeModifier(ATTACK_SPEED_MODIFIER, nameIn, attributeValues.getAttackSpeed(), 1));
            }

            if (attributeValues.getAttackDamage() != 0F && attributeValues.getAttackDamage() > 0F) { // if its not zero and above zero
                event.removeAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
                event.addModifier(SharedMonsterAttributes.ATTACK_DAMAGE,
                        new AttributeModifier(ATTACK_DAMAGE_MODIFIER, nameIn, attributeValues.getAttackDamage(), 0));
            }

            if (attributeValues.getAttackDamage() < -0.0F) { // if its bigger than negative zero. negative zero is just zero no?
                event.removeAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
            }

            if (attributeValues.getArmor() != 0F) {
                event.removeAttribute(SharedMonsterAttributes.ARMOR);
                event.addModifier(SharedMonsterAttributes.ARMOR,
                        new AttributeModifier(ARMOR_UUID, nameIn, attributeValues.getArmor(), 0));
            }

            if (attributeValues.getArmorToughness() != 0F) {
                event.removeAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
                event.addModifier(SharedMonsterAttributes.ARMOR_TOUGHNESS,
                        new AttributeModifier(ARMOR_TOUGHNESS_UUID, nameIn, attributeValues.getArmorToughness(), 0));
            }

            if (attributeValues.getLuck() != 0F) {
                event.removeAttribute(SharedMonsterAttributes.LUCK);
                event.addModifier(SharedMonsterAttributes.LUCK,
                        new AttributeModifier(LUCK_UUID, nameIn, attributeValues.getLuck(), 0));
            }

            if (attributeValues.getFlyingSpeed() != 0F && attributeValues.getFlyingSpeed() > 0f) {
                event.removeAttribute(SharedMonsterAttributes.FLYING_SPEED);
                event.addModifier(SharedMonsterAttributes.FLYING_SPEED,
                        new AttributeModifier(FLYING_SPEED_UUID, nameIn, attributeValues.getFlyingSpeed(), 0));
            } else if (attributeValues.getFlyingSpeed() < -0) {
                event.removeAttribute(SharedMonsterAttributes.FLYING_SPEED);
            }

            if (attributeValues.getMaxHealth() != 0F) {
                event.removeAttribute(SharedMonsterAttributes.MAX_HEALTH);
                event.addModifier(SharedMonsterAttributes.MAX_HEALTH,
                        new AttributeModifier(MAX_HEALTH_UUID, nameIn, attributeValues.getMaxHealth(), 0));
            }

            if (attributeValues.getFollowRange() != 0F && attributeValues.getFollowRange() > 0f) {
                event.removeAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
                event.addModifier(SharedMonsterAttributes.FOLLOW_RANGE,
                        new AttributeModifier(FOLLOW_RANGE_UUID, nameIn, attributeValues.getFollowRange(), 0));
            } else if (attributeValues.getFollowRange() < -0) {
                event.removeAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
            }

            if (attributeValues.getKnockbackResistance() != 0F) {
                event.removeAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
                event.addModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE,
                        new AttributeModifier(KNOCKBACK_RESISTANCE_UUID, nameIn, attributeValues.getKnockbackResistance(), 0));
            }

            if (attributeValues.getMovementSpeed() != 0F) {
                event.removeAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
                event.addModifier(SharedMonsterAttributes.MOVEMENT_SPEED,
                        new AttributeModifier(MOVEMENT_SPEED_UUID, nameIn, attributeValues.getMovementSpeed(), 2));
            }

            if (attributeValues.getDurability() <= -1.0F) { // if equal or above -1. -1 is unbreakable so I need to check for that
                event.getItemStack().getItem().setMaxDamage(attributeValues.getDurability()); // set durability
            }

            if (attributeValues.getStackSize() != 0F) {
                event.getItemStack().getItem().setMaxStackSize(attributeValues.getStackSize());
            }

            if (attributeValues.getEfficiency() != 0F) { // what am i trying to set? is this private variable accessible? it works. yes the setPrivateValue() does that
                //Class <? super T > classToAccess, T instance, E value, int fieldIndex
                ReflectionHelper.setPrivateValue(ItemTool.class,
                        (ItemTool)event.getItemStack().getItem(),
                        (float)attributeValues.getEfficiency(), //maybe remove cast
                        TOOL_EFFICIENCY_INDEX); // effectiveBlocks > efficiency i guess? yes

                /*Float efficiency = ReflectionHelper.getPrivateValue(ItemTool.class,
                        (ItemTool)event.getItemStack().getItem(),
                        1);
                LOGGER.info("WE ARE GETTING " + efficiency + " VALUE");*/
            }

            if (attributeValues.getEnchantability() != 0F) {
                material = getObjectFromClass(ItemTool.class, Item.ToolMaterial.class, event.getItemStack().getItem());

                if (material == null) { // might not be necessary
                    material = getObjectFromClass(ItemSword.class, Item.ToolMaterial.class, event.getItemStack().getItem());
                }
                // TODO if its true that setting the enchantability for one item sets it for the whole material, restore the previous material enchantability.
                if  (material != null) {
                    ReflectionHelper.setPrivateValue(Item.ToolMaterial.class,
                            material,
                            (int)attributeValues.getEnchantability(),
                            MATERIAL_ENCHANTABILITY_INDEX);

                    /*int ench = ReflectionHelper.getPrivateValue(Item.ToolMaterial.class,
                            material,
                            MATERIAL_ENCHANTABILITY_INDEX);
                    LOGGER.info("WE ARE GETTING " + ench + " VALUE");*/
                }
            }

        }
        // END MODIFY ATTRIBUTES //
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            // FLY SPEED FIX //
            EntityPlayer player = event.player;
            // I suppose im getting "this.getHeldItemMainhand()"?
            ItemStack heldMainHand = player.getHeldEquipment().iterator().next();

            float defaultFlyingSpeed = 0.05F;
            if (heldMainHand != ItemStack.EMPTY) {
                Multimap<String, AttributeModifier> attributes = heldMainHand.getAttributeModifiers(EntityEquipmentSlot.MAINHAND);
                if (attributes.containsKey("generic.flyingSpeed")) {
                    player.capabilities.setFlySpeed((float) attributes.get("generic.flyingSpeed").iterator().next().getAmount());
                }
                else player.capabilities.setFlySpeed(defaultFlyingSpeed);
            }
        }
    }

    // from https://github.com/Lellson/Material-Changer/blob/master/src/main/java/de/lellson/materialchanger/MaterialChanger.java#L251
    // added item var to the ctor
    public <T> T getObjectFromClass(Class fromClazz, Class<T> clazz, Item item) {

        if (!fromClazz.isInstance(item)) return null;

        try
        {
            for (Field field : fromClazz.getDeclaredFields())
            {
                field.setAccessible(true);
                Object object = field.get(item);
                if (clazz.isInstance(object))
                {
                    return (T) object;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static Map<String, AttributeValues> getAttributes() {
        return (getAttributeMap() != null) ? getAttributeMap() : EMPTY_ATTRIBUTE_MAP;
    }
}
