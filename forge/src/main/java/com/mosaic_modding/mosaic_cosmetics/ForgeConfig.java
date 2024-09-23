package com.mosaic_modding.mosaic_cosmetics;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;

public class ForgeConfig {

    public static void init() {
        Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
        MosaicCosmetics.configAccess = specPair.getLeft();
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.CLIENT, specPair.getRight());
    }

    public static class Config implements ModConfig {
        private final ForgeConfigSpec.ConfigValue<Boolean> renderContributorCape;

        public Config(ForgeConfigSpec.Builder builder) {
            renderContributorCape = builder.define("render_contributor_cape", true);
        }

        @Override
        public boolean renderContributorCape() {
            return renderContributorCape.get();
        }
    }
}
