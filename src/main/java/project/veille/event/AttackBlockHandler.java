package project.veille.event;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import project.veille.entity.custom.TreeDemonEntity;

import static project.veille.entity.ModEntities.TREE_DEMON;

public class AttackBlockHandler implements AttackBlockCallback {

    double spawnProbability = 1;

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction) {

        double random = Math.random();


        if (!world.isClient && isBlockWood(world.getBlockState(pos)) && random < spawnProbability) {
            TreeDemonEntity treeDemonEntity = new TreeDemonEntity(TREE_DEMON, world);
            treeDemonEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
            world.spawnEntity(treeDemonEntity);
        }
        return ActionResult.PASS;
    }

    public boolean isBlockWood(BlockState state){
        return state.isOf(Blocks.ACACIA_LOG) || state.isOf(Blocks.BIRCH_LOG)|| state.isOf(Blocks.CHERRY_LOG)|| state.isOf(Blocks.JUNGLE_LOG)|| state.isOf(Blocks.OAK_LOG);
    }
}
