package top.uunk.mod.registry.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.piston.PistonHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import top.uunk.mod.registry.ModBlocks;

import java.util.List;
import java.util.Map;

public class GreaseItem extends Item {
    public GreaseItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        if (!level.isClientSide()) {
            if (state.getBlock() == Blocks.STICKY_PISTON && state.getBlock() != Blocks.PISTON_HEAD) {
                BlockState newState = Blocks.PISTON
                        .defaultBlockState()
                        .setValue(PistonBaseBlock.FACING, state.getValue(PistonBaseBlock.FACING))
                        .setValue(PistonBaseBlock.EXTENDED, false);
                level.destroyBlock(pos, false);
                level.setBlock(pos, newState, 3);
                level.playSound(null, pos,
                        SoundEvents.SLIME_SQUISH, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.uunk.grease.tooltip"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.uunk.hasto.shift_down"));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
