package com.mosaic_modding.mosaic_cosmetics;

import net.fabricmc.loader.api.FabricLoader;

public class FabricService implements ModService {

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
