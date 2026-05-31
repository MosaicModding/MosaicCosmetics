package com.mosaicmodding.mosaic_cosmetics;

import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class CapeHandler {
    private static final ResourceLocation DEV_CAPE = MosaicCosmetics.modPrefix("textures/entity/dev_cape.png");
    private static final ResourceLocation CONTRIBUTOR_CAPE = MosaicCosmetics.modPrefix("textures/entity/contributor_cape.png");

    public static void addCapes(UUID uuid, CallbackInfoReturnable<CompletableFuture<PlayerSkin>> cir) {
        if (MosaicCosmetics.configAccess.renderContributorCape()) {
            CompletableFuture<PlayerSkin> playerSkinFuture = cir.getReturnValue();
            if (Definitions.DEV_UUIDS.contains(uuid.toString()) || MosaicCosmetics.ACCESS.isDevEnvironment()) {
                cir.setReturnValue(playerSkinFuture.thenApply(s -> mosaicCosmetics$setTexture(s, DEV_CAPE)));
            }
            if (mosaicCosmetics$contributorCheck(uuid.toString())) {
                cir.setReturnValue(playerSkinFuture.thenApply(s -> mosaicCosmetics$setTexture(s, CONTRIBUTOR_CAPE)));
            }
        }
    }

    private static @NotNull PlayerSkin mosaicCosmetics$setTexture(PlayerSkin playerSkin, ResourceLocation texture) {
        if (playerSkin.capeTexture() == null)
            return new PlayerSkin(playerSkin.texture(), playerSkin.textureUrl(), texture, texture, playerSkin.model(), playerSkin.secure());
        return playerSkin;
    }

    private static boolean mosaicCosmetics$contributorCheck(String uuid) {
        for (Map<List<String>, String> df : Definitions.CONTRIBUTORS) {
            for (String uuid1 : df.values()) {
                if (uuid1.equals(uuid)) {
                    for (List<String> modIds : df.keySet()) {
                        for (String id : modIds) {
                            if (MosaicCosmetics.ACCESS.isModLoaded(id)) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }
}
