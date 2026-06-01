package com.mosaicmodding.mosaic_cosmetics.mixin;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mosaicmodding.mosaic_cosmetics.Definitions;
import com.mosaicmodding.mosaic_cosmetics.MosaicCosmetics;
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

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
    @Unique
    private static final ResourceLocation DEV_CAPE = MosaicCosmetics.modPrefix("textures/entity/dev_cape.png");
    @Unique
    private static final ResourceLocation CONTRIBUTOR_CAPE = MosaicCosmetics.modPrefix("textures/entity/contributor_cape.png");

    @Inject(method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("TAIL"))
    public void mosaicCosmetics$addCapes(AbstractClientPlayer player, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, CallbackInfo ci) {
        if (!MosaicCosmetics.configAccess.renderContributorCape()) return;

        String uuid = player.getGameProfile().getId().toString();
        PlayerInfo info = player.playerInfo;

        if (info == null) return;
        if (!player.isCapeLoaded()) return;

        if (Definitions.DEV_UUIDS.contains(uuid)) {
            mosaicCosmetics$setTexture(info, uuid, DEV_CAPE);
        } else if (mosaicCosmetics$contributorCheck(uuid)) {
            mosaicCosmetics$setTexture(info, uuid, CONTRIBUTOR_CAPE);
        }
    }

    @Unique
    private static void mosaicCosmetics$setTexture(PlayerInfo info, String uuid, ResourceLocation rl) {
        Map<MinecraftProfileTexture.Type, ResourceLocation> playerTextures = info.textureLocations;
        playerTextures.put(MinecraftProfileTexture.Type.CAPE, rl);
        playerTextures.put(MinecraftProfileTexture.Type.ELYTRA, rl);
    }

    @Unique
    public boolean mosaicCosmetics$contributorCheck(String uuid) {
        if (Definitions.CONTRIBUTORS.containsKey(uuid)) {
            String[] mods = Definitions.CONTRIBUTORS.get(uuid);
            for (String id : mods) {
                if (MosaicCosmetics.ACCESS.isModLoaded(id)) {
                    return true;
                }
            }
        }
        return false;
    }
}
