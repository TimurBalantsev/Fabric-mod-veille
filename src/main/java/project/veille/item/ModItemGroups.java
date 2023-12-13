package project.veille.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import project.veille.Veille;

public class ModItemGroups {
    public static final ItemGroup VEILLE_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(Veille.MOD_ID, "veille"),
            FabricItemGroup
                    .builder()
                    .displayName(Text.translatable("itemgroup.veille"))
                    .icon(() -> new ItemStack(ModItems.CROSS)).entries((displayContext, entries) -> {
                        entries.add(ModItems.CROSS);
                        entries.add(ModItems.TREE_DEMON_EGG);
                    })
                    .build()
    );

    public static void registerItemGroups() {
        Veille.LOGGER.info("Registering item groups for" + Veille.MOD_ID);
    }
}
