package project.veille;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import project.veille.entity.ModEntities;
import project.veille.entity.client.*;

public class VeilleClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.TREE_DEMON, TreeDemonRenderer::new);
        EntityRendererRegistry.register(ModEntities.BLOCKLOR, BlocklorRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.TREE_DEMON, TreeDemonModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BLOCKLOR, BlocklorModel::getTexturedModelData);
    }
}
