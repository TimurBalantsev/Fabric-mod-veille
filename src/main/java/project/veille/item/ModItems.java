package project.veille.item;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import project.veille.Veille;
import project.veille.entity.ModEntities;
import project.veille.item.custom.CrossItem;

public class ModItems {

    public static final Item CROSS = registerItem("cross", new CrossItem(new FabricItemSettings()));

    public static Item TREE_DEMON_EGG = registerItem("tree_demon_egg", new SpawnEggItem(ModEntities.TREE_DEMON, 0xffd700,0xffd700, new FabricItemSettings()));

    // enregistrement de l'oeuf de spawn du blocklor. les couleurs sont gris foncé et gris
    public static Item BLOCKLOR_EGG = registerItem("blocklor_egg", new SpawnEggItem(ModEntities.BLOCKLOR, 0x4d4d4d,0x4d4d4d, new FabricItemSettings()));
    public static void addItemsToCombatTabItemGroup(FabricItemGroupEntries entries){
        entries.add(CROSS);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Veille.MOD_ID, name), item);
    }



    public static void registerModItems() {
        Veille.LOGGER.info("Registering mod items for " + Veille.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatTabItemGroup);
    }
}