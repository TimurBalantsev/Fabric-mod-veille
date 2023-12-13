package project.veille.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrossItem extends Item {
    public CrossItem(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {

        //ajoute le nom de ton entity ici
        if (!user.getWorld().isClient &&  entity.getType().getName().getString().contains("Tree Demon")) {
            entity.kill();
            stack.decrement(1);
            user.sendMessage(Text.literal("The demon has been exorcised"));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Used to banish demons from this realm"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
