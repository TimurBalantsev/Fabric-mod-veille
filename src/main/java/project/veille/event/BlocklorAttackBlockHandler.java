package project.veille.event;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import project.veille.entity.custom.BlocklorEntity;

import static project.veille.entity.ModEntities.BLOCKLOR;

public class BlocklorAttackBlockHandler implements AttackBlockCallback {

    // 1 = 100%
    // On check si tu frape de la pierre si oui je spawn mon blocklor si il y a assez despace pour le spawn
    double spawnProbability = 1;



    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction) {
        if (!world.isClient && isStoneBlock(world.getBlockState(pos)) && Math.random() < spawnProbability) {
            BlocklorEntity blocklor = BLOCKLOR.create(world);
            assert blocklor != null;
            if ((world.getBlockState(pos.east()).isAir() || world.getBlockState(pos.south()).isAir() || world.getBlockState(pos.west()).isAir() || world.getBlockState(pos.north()).isAir()) && (world.getBlockState(pos.east().up(3)).isAir() || world.getBlockState(pos.south().up(3)).isAir() || world.getBlockState(pos.west().up(3)).isAir() || world.getBlockState(pos.north().up(3)).isAir()) &&  world.getBlockState(pos.up()).isAir() && world.getBlockState(pos.up(3)).isAir()) {
                // on set la position du blocklor au alentour du block que tu vien de frapper
                // si il y a de la place pour le spawn (2 block d'air au dessus et un block d'air a coter)
                blocklor.updatePosition(pos.getX() + Math.random() * 3 - 1, pos.getY() + 1, pos.getZ() + Math.random() * 3 - 1);
                world.spawnEntity(blocklor);
            }
        }
        return ActionResult.PASS;
    }

    public boolean isStoneBlock(BlockState state){
        return state.isOf(Blocks.STONE) || state.isOf(Blocks.DIORITE)|| state.isOf(Blocks.COAL_ORE)|| state.isOf(Blocks.DIAMOND_ORE)|| state.isOf(Blocks.IRON_ORE);
    }
}
