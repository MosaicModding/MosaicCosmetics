package com.mosaicmodding.mosaic_cosmetics;

import net.fabricmc.api.ModInitializer;

public class MosaicCosmeticsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        MosaicCosmetics.init();
        ModFiberConfig.setup();
    }
}
