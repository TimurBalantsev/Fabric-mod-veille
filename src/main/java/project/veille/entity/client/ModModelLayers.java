package project.veille.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import project.veille.Veille;

public class ModModelLayers {
    public static final EntityModelLayer TREE_DEMON =
            new EntityModelLayer(new Identifier(Veille.MOD_ID,"tree_demon"),"main");
}
