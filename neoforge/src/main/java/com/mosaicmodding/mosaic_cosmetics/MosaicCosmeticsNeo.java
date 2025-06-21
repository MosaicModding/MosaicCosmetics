package com.mosaicmodding.mosaic_cosmetics;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(MosaicCosmetics.MOD_ID)
public class MosaicCosmeticsNeo {
    
    public MosaicCosmeticsNeo(IEventBus bus, ModContainer modContainer) {
        MosaicCosmetics.init();
        NeoforgeConfig.init(modContainer);
    }
}