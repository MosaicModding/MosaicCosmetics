package com.mosaicmodding.mosaic_cosmetics.mixin;

import com.mojang.authlib.minecraft.MinecraftProfileTextures;
import com.mosaicmodding.mosaic_cosmetics.CapeHandler;
import com.mosaicmodding.mosaic_cosmetics.Definitions;
import com.mosaicmodding.mosaic_cosmetics.MosaicCosmetics;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
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
        CapeHandler.addCapes(uuid, cir);
    }
}
