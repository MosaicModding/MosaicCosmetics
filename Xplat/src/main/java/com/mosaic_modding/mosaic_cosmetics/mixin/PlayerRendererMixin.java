package com.mosaic_modding.mosaic_cosmetics.mixin;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mosaic_modding.mosaic_cosmetics.Definitions;
import com.mosaic_modding.mosaic_cosmetics.MosaicCosmetics;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.UUID;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {

    @Inject(method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("TAIL"))
    public void mosaic_moddingAddCapes(AbstractClientPlayer player, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, CallbackInfo ci) {
        UUID uuid = player.getGameProfile().getId();

        if ((mosaicCosmetics$contributorCheck(uuid) || Definitions.DEV_UUIDS.contains(uuid) || MosaicCosmetics.ACCESS.isDevEnvironment()) && MosaicCosmetics.configAccess.renderContributorCape()) {
            ResourceLocation cape = MosaicCosmetics.modPrefix("textures/entity/dev_cape.png");
            PlayerInfo info = player.playerInfo;
            if (info != null) {
                if (player.isCapeLoaded()) {
                    Map<MinecraftProfileTexture.Type, ResourceLocation> playerTextures = info.textureLocations;
                    playerTextures.put(MinecraftProfileTexture.Type.CAPE, cape);
                    playerTextures.put(MinecraftProfileTexture.Type.ELYTRA, cape);
                }
            }
        }
    }

    @Unique
    public boolean mosaicCosmetics$contributorCheck(UUID uuid) {
        for (Map<String, UUID> df : Definitions.CONTRIBUTORS) {
            for (UUID uuid1 : df.values()) {
                if (uuid1.equals(uuid)) {
                    for (String modId : df.keySet()) {
                        if (MosaicCosmetics.ACCESS.isModLoaded(modId)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }
}
