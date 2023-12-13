package project.veille;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.veille.entity.ModEntities;
import project.veille.entity.custom.TreeDemonEntity;
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
	}
}