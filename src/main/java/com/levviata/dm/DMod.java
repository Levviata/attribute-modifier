package com.levviata.dm;

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
public class DMod {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    private static Map<String, ItemValues> itemValuesMap;

    private File configFile;

    /**
     * <a href="https://cleanroommc.com/wiki/forge-mod-development/event#overview">
     *     Take a look at how many FMLStateEvents you can listen to via the @Mod.EventHandler annotation here
     * </a>
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Hello From {}!", Tags.MOD_NAME);
        try {
            Gson gson = (new GsonBuilder()).setLenient().setPrettyPrinting().create();
            this.configFile = new File("config/weapons_values.json");
            if (!this.configFile.exists()) {
                this.configFile.createNewFile();
                itemValuesMap = new HashMap<>();
                itemValuesMap.put("minecraft:diamond_sword", new ItemValues(20.0F, 3.0F));
                FileUtils.writeStringToFile(this.configFile, gson.toJson(itemValuesMap), StandardCharsets.UTF_8);
            } else {
                Type mapType = (new TypeToken<HashMap<String, ItemValues>>() {

                }).getType();
                itemValuesMap = gson.fromJson(FileUtils.readFileToString(this.configFile, StandardCharsets.UTF_8), mapType);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new DamageAttributeModifier());
    }
}
