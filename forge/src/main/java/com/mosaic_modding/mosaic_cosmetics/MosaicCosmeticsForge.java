package com.mosaic_modding.mosaic_cosmetics;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MosaicCosmetics.MOD_ID)
public class MosaicCosmeticsForge {
    
    public MosaicCosmeticsForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        MosaicCosmetics.init();

        MinecraftForge.EVENT_BUS.register(this);
    }
}