package project.veille.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import project.veille.Veille;
import project.veille.entity.custom.TreeDemonEntity;

public class TreeDemonRenderer extends MobEntityRenderer<TreeDemonEntity,TreeDemonModel<TreeDemonEntity>> {
    private static final Identifier TEXTURE = new Identifier(Veille.MOD_ID,"textures/entity/tree_demon.png");
    public TreeDemonRenderer(EntityRendererFactory.Context context) {
        //shadow is the float
        super(context, new TreeDemonModel<>(context.getPart(ModModelLayers.TREE_DEMON)),0.4f);
    }

    @Override
    public Identifier getTexture(TreeDemonEntity entity) {
        return TEXTURE;
    }

}
