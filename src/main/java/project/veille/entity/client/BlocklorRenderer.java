package project.veille.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import project.veille.Veille;
import project.veille.entity.custom.BlocklorEntity;

public class BlocklorRenderer extends MobEntityRenderer<BlocklorEntity, BlocklorModel<BlocklorEntity>> {

    private static final Identifier TEXTURE = new Identifier(Veille.MOD_ID, "textures/entity/brocklor.png");
    public BlocklorRenderer(EntityRendererFactory.Context context) {
        super(context, new BlocklorModel<>(context.getPart(ModModelLayers.BLOCKLOR)), 0.5f);
    }

    @Override
    public Identifier getTexture(BlocklorEntity entity) {
        return TEXTURE;
    }

}
