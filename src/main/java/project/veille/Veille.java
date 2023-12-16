package project.veille;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.veille.entity.ModEntities;
import project.veille.entity.custom.BlocklorEntity;
import project.veille.entity.custom.TreeDemonEntity;
import project.veille.event.BlocklorAttackBlockHandler;
import project.veille.event.TreeDemonAttackBlockHandler;
import project.veille.item.ModItemGroups;
import project.veille.item.ModItems;

public class Veille implements ModInitializer {
	public static final String MOD_ID = "veille";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		FabricDefaultAttributeRegistry.register(ModEntities.TREE_DEMON, TreeDemonEntity.createTreeDemonAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.BLOCKLOR, BlocklorEntity.createBlocklorAttributes());
		AttackBlockCallback.EVENT.register(new TreeDemonAttackBlockHandler());
		AttackBlockCallback.EVENT.register(new BlocklorAttackBlockHandler());
	}
}