package com.mosaic_modding.mosaic_cosmetics;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Definitions {
    public static final Set<UUID> DEV_UUIDS = Set.of(
            UUID.fromString("6c175d10-198a-49f9-8e2b-c74f1f0178f3"), //Sully
            UUID.fromString("fddaefa0-31d2-4acf-9cd2-4711d0e5e5d5"), //Uraneptus
            UUID.fromString("3fd1d511-62d6-4e18-a28d-3e3d4fd93620"), //Keke
            UUID.fromString("1fb0ecbd-2000-4f00-aa65-002c1ccedef7") //Stelle
    );
    public static final Set<Map<String, UUID>> CONTRIBUTORS = Set.of(
            Map.of("sullysmod", UUID.fromString("a69fc0f5-028a-4bee-9102-26ff3764017e")) //Sully alt
    );
}
