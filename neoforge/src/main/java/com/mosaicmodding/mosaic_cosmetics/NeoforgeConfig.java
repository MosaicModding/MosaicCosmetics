package com.mosaicmodding.mosaic_cosmetics;

import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class NeoforgeConfig {

    public static void init(ModContainer modContainer) {
        Pair<Config, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Config::new);
        MosaicCosmetics.configAccess = specPair.getLeft();
        modContainer.registerConfig(net.neoforged.fml.config.ModConfig.Type.CLIENT, specPair.getRight());
    }

    public static class Config implements ModConfig {
        private final ModConfigSpec.ConfigValue<Boolean> renderContributorCape;

        public Config(ModConfigSpec.Builder builder) {
            renderContributorCape = builder.define("render_contributor_cape", true);
        }

        @Override
        public boolean renderContributorCape() {
            return renderContributorCape.get();
        }
    }
}
