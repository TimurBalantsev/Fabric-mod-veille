package project.veille.item;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import project.veille.Veille;

public class ModItems {

    public static final Item CROSS = registerItem("cross", new Item(new FabricItemSettings()));

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