package com.mosaicmodding.mosaic_cosmetics.mixin;

import com.mojang.authlib.minecraft.MinecraftProfileTextures;
import com.mosaicmodding.mosaic_cosmetics.Definitions;
import com.mosaicmodding.mosaic_cosmetics.MosaicCosmetics;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Mixin(SkinManager.class)
public class SkinManagerMixin {

    @Inject(method = "registerTextures", at = @At("RETURN"), cancellable = true)
    public void mosaicCosmetics$addCapes(UUID uuid, MinecraftProfileTextures textures, CallbackInfoReturnable<CompletableFuture<PlayerSkin>> cir) {
        if ((mosaicCosmetics$contributorCheck(uuid.toString()) || Definitions.DEV_UUIDS.contains(uuid.toString())) && MosaicCosmetics.configAccess.renderContributorCape()) {
            ResourceLocation cape = MosaicCosmetics.modPrefix("textures/entity/dev_cape.png");
            CompletableFuture<PlayerSkin> playerSkinFuture = cir.getReturnValue();
            cir.setReturnValue(playerSkinFuture.thenApply(playerSkin -> {
                if (playerSkin.capeTexture() == null)
                    return new PlayerSkin(playerSkin.texture(), playerSkin.textureUrl(), cape, cape, playerSkin.model(), playerSkin.secure());
                return playerSkin;
            }));

        }
    }

    @Unique
    public boolean mosaicCosmetics$contributorCheck(String uuid) {
        if (MosaicCosmetics.ACCESS.isDevEnvironment()) {
            return true;
        }
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
