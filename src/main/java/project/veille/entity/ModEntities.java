package project.veille.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import project.veille.Veille;
import project.veille.entity.custom.BlocklorEntity;
import project.veille.entity.custom.TreeDemonEntity;

public class ModEntities {
    public static final EntityType<TreeDemonEntity> TREE_DEMON = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Veille.MOD_ID, "tree_demon"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TreeDemonEntity::new).dimensions(EntityDimensions.fixed(1f, 1f)).build()
    );
    public static final EntityType<BlocklorEntity> BLOCKLOR = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Veille.MOD_ID, "blocklor"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BlocklorEntity::new).dimensions(EntityDimensions.fixed(2f, 3f)).build()
    );
}
