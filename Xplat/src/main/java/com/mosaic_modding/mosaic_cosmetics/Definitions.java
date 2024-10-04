package com.mosaic_modding.mosaic_cosmetics;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Definitions {
    public static final Set<String> DEV_UUIDS = Set.of(
            "6c175d10-198a-49f9-8e2b-c74f1f0178f3", //Sully
            "fddaefa0-31d2-4acf-9cd2-4711d0e5e5d5", //Uraneptus
            "3fd1d511-62d6-4e18-a28d-3e3d4fd93620", //Keke
            "1fb0ecbd-2000-4f00-aa65-002c1ccedef7" //Stelle
    );
    public static final Set<Map<List<String>, String>> CONTRIBUTORS = Set.of(
            Map.of(List.of("sullysmod"), "a69fc0f5-028a-4bee-9102-26ff3764017e"), //Sully alt
            Map.of(List.of("sullysmod", "painters_inc"), "456e2ae6-74b3-474a-9560-92b782bce747"), //Graus
            Map.of(List.of("sullysmod"), "2d173722-de6b-4bb8-b21b-b2843cfe395d"), //Ninni
            Map.of(List.of("sullysmod"), "7d43a0a0-f228-4382-9d0e-24b96f92ce39"), //Shable
            Map.of(List.of("sullysmod"), "b2e40db2-5aa8-435a-9c30-8f79efffaba6"), //Angery
            Map.of(List.of("sullysmod", "painters_inc"), "82d459b2-d085-4991-997f-6f210228e527"), //Farcr
            Map.of(List.of("sullysmod", "painters_inc"), "852646ab-620d-48f7-974c-6451100f70f0"), //ibrokemyribcage
            Map.of(List.of("sullysmod"), "4d659277-fbcc-4fd8-9e80-c8e4c73d10b5 "), //sullys cousin
            Map.of(List.of("sullysmod"), "ce9dd341-b1c2-44d9-a014-71e11d163b01"), //LudoCrypt
            Map.of(List.of("sullysmod"), "7a422124-11eb-4064-94a7-7741ad45e8f3"), //Samir
            Map.of(List.of("painters_inc"), "50dead25-46c1-4dd4-8f63-f8403716fcb9"), //8onfire
            Map.of(List.of("painters_inc"), "1bf63d89-bfde-462e-9cd0-3a6801a27813"), //Merp
            Map.of(List.of("painters_inc"), "9778ff53-d83d-4233-8fa6-8aab7b89c4c0"), //Artbeet
            Map.of(List.of("painters_inc"), "3ff497ed-71f0-453a-a869-4fc17be298ff") //Yapetto
    );

    /*sullysmod
    static final Set<UUID> CONTRIBUTORS = Set.of(
            UUID.fromString("a69fc0f5-028a-4bee-9102-26ff3764017e"), //Sully alt
            UUID.fromString("456e2ae6-74b3-474a-9560-92b782bce747"), //Graus
            UUID.fromString("2d173722-de6b-4bb8-b21b-b2843cfe395d"), //Ninni
            UUID.fromString("7d43a0a0-f228-4382-9d0e-24b96f92ce39"), //Shable
            UUID.fromString("b2e40db2-5aa8-435a-9c30-8f79efffaba6"), //Angery
            UUID.fromString("4378df24-8433-4b5c-b865-bf635b003ebb"), //Farcr
            UUID.fromString("852646ab-620d-48f7-974c-6451100f70f0"), //ibrokemyribcage
            UUID.fromString("4d659277-fbcc-4fd8-9e80-c8e4c73d10b5"), //sunken past artist
            UUID.fromString("ce9dd341-b1c2-44d9-a014-71e11d163b01"), //LudoCrypt
            UUID.fromString("7a422124-11eb-4064-94a7-7741ad45e8f3") //Samir
    );

     */
}
