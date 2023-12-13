package project.veille;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import project.veille.entity.ModEntities;
import project.veille.entity.client.ModModelLayers;
import project.veille.entity.client.TreeDemonModel;
import project.veille.entity.client.TreeDemonRenderer;

public class VeilleClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.TREE_DEMON, TreeDemonRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.TREE_DEMON, TreeDemonModel::getTexturedModelData);
    }
}
