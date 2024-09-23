package com.mosaic_modding.mosaic_cosmetics;

import net.minecraft.resources.ResourceLocation;

import java.util.ServiceLoader;

public class MosaicCosmetics {
    public static final String MOD_ID = "mosaic_cosmetics";
    public static final ModService ACCESS = load(ModService.class);
    public static ModConfig configAccess;

    public static ResourceLocation modPrefix(String id) {
        return new ResourceLocation(MOD_ID, id);
    }

    public static void init() {

    }

    public static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }
}