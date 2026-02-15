package top.uunk.mod.registry.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import top.uunk.mod.registry.ModItems;

import java.util.concurrent.ThreadLocalRandom;

public class CrusherBlock extends Block {
    public CrusherBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        level.playSound(player, pos, SoundEvents.DECORATED_POT_INSERT, SoundSource.BLOCKS, 1f, 1f);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if(entity instanceof ItemEntity itemEntity) {
            if(itemEntity.getItem().getItem() == ModItems.CHISEL.get()) {
                itemEntity.setItem(new ItemStack(ModItems.LEAD_INGOT.get(), itemEntity.getItem().getCount()));
            }
        }

        if (entity instanceof ItemEntity itemEntity) {
            if (itemEntity.getItem().getItem() == Items.DIAMOND) {
                int count = itemEntity.getItem().getCount();
                int totalCoal = 0;
                for (int i = 0; i < count; i++) {
                    totalCoal += ThreadLocalRandom.current().nextInt(0, 4);
                }
                itemEntity.setItem(new ItemStack(Items.COAL, totalCoal));
            }
        }


        super.stepOn(level, pos, state, entity);
    }
}
