package com.mosaicmodding.mosaic_cosmetics;

import io.github.fablabsmc.fablabs.api.fiber.v1.builder.ConfigTreeBuilder;
import io.github.fablabsmc.fablabs.api.fiber.v1.exception.ValueDeserializationException;
import io.github.fablabsmc.fablabs.api.fiber.v1.serialization.FiberSerialization;
import io.github.fablabsmc.fablabs.api.fiber.v1.serialization.JanksonValueSerializer;
import io.github.fablabsmc.fablabs.api.fiber.v1.tree.ConfigTree;
import io.github.fablabsmc.fablabs.api.fiber.v1.tree.PropertyMirror;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.*;

import static io.github.fablabsmc.fablabs.api.fiber.v1.schema.type.derived.ConfigTypes.BOOLEAN;

public class ModFiberConfig {
    private static final Logger LOGGER = LogManager.getLogger(ModFiberConfig.class);

    private static void writeDefaultConfig(ConfigTree config, Path path, JanksonValueSerializer serializer) {
        try (OutputStream s = new BufferedOutputStream(Files.newOutputStream(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW))) {
            FiberSerialization.serialize(config, s, serializer);
        } catch (FileAlreadyExistsException ignored) {} catch (IOException e) {
            LOGGER.error("Error writing default config", e);
        }
    }

    private static void setupConfig(ConfigTree config, Path p, JanksonValueSerializer serializer) {
        writeDefaultConfig(config, p, serializer);

        try (InputStream s = new BufferedInputStream(Files.newInputStream(p, StandardOpenOption.READ, StandardOpenOption.CREATE))) {
            FiberSerialization.deserialize(config, s, serializer);
        } catch (IOException | ValueDeserializationException e) {
            LOGGER.error("Error loading config from {}", p, e);
        }
    }

    public static void setup() {
        try {
            Files.createDirectory(Paths.get("config"));
        } catch (FileAlreadyExistsException ignored) {} catch (IOException e) {
            LOGGER.warn("Failed to make config dir", e);
        }

        JanksonValueSerializer serializer = new JanksonValueSerializer(false);
        ConfigTree client = CLIENT.configure(ConfigTree.builder());
        setupConfig(client, Paths.get("config", MosaicCosmetics.MOD_ID + "-client.json5"), serializer);
        MosaicCosmetics.configAccess = CLIENT;
    }

    private static class Client implements ModConfig {
        private final PropertyMirror<Boolean> renderContributorCape = PropertyMirror.create(BOOLEAN);

        public ConfigTree configure(ConfigTreeBuilder builder) {
            builder.beginValue("renderContributorCape", BOOLEAN, true)
                    .withComment("efef")
                    .finishValue(renderContributorCape::mirror);

            return builder.build();
        }

        @Override
        public boolean renderContributorCape() {
            return renderContributorCape.getValue();
        }
    }

    private static final Client CLIENT = new Client();
}
