package com.levviata.attmodifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class AttMod {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    private static Map<String, AttributeValues> attributeMap;

    public static Map<String, AttributeValues> getAttributeMap() {
        return attributeMap;
    }

    private File configFile;

    /**
     * <a href="https://cleanroommc.com/wiki/forge-mod-development/event#overview">
     *     Take a look at how many FMLStateEvents you can listen to via the @Mod.EventHandler annotation here
     * </a>
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        try {
            Gson gson = (new GsonBuilder()).setLenient().setPrettyPrinting().registerTypeAdapter(AttributeValues.class, new AttributeValuesSerializer()).create();
            this.configFile = new File("config/attributeModifiers.json");
            if (!this.configFile.exists()) { // make config and examples
                this.configFile.createNewFile();
                attributeMap = new HashMap<>();
                attributeMap.put("minecraft:diamond_hoe", new AttributeValues(
                        10,    // maxHealth
                        60,    // followRange
                        2,     // knockbackResistance
                        0.7F,   // movementSpeed
                        0.15F,  // flyingSpeed
                        10.0F,  // attackDamage
                        2.0F,   // attackSpeed
                        2.0F,   // armor
                        1.0F,   // armorToughness
                        5.0F    // luck
                ));

                attributeMap.put("minecraft:diamond_sword", new AttributeValues(
                        10,    // maxHealth
                        0,     // followRange
                        0,     // knockbackResistance
                        0,     // movementSpeed
                        0,     // flyingSpeed
                        -1,     // attackDamage
                        0,     // attackSpeed
                        5,     // armor
                        0,     // armorToughness
                        5.0F    // luck
                ));

                attributeMap.put("minecraft:diamond_pickaxe", new AttributeValues(
                        0,     // maxHealth
                        0,     // followRange
                        0,     // knockbackResistance
                        1.1F,   // movementSpeed
                        0,     // flyingSpeed
                        -1,     // attackDamage
                        -1,     // attackSpeed
                        0,     // armor
                        0,     // armorToughness
                        0      // luck
                ));
                FileUtils.writeStringToFile(this.configFile, gson.toJson(attributeMap), StandardCharsets.UTF_8);
            } else { // read and write as normal
                Type mapType = (new TypeToken<HashMap<String, AttributeValues>>() {

                }).getType();
                attributeMap = gson.fromJson(FileUtils.readFileToString(this.configFile, StandardCharsets.UTF_8), mapType);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new LAttributeModifier());
    }
}
