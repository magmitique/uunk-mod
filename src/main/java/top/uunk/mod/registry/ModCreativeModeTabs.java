package top.uunk.mod.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import top.uunk.mod.Uunk;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Uunk.MOD_ID);

    public static final Supplier<CreativeModeTab> UUNK_ITEMS_TAB = CREATIVE_MODE_TAB.register("uunk_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LEAD_INGOT.get()))
                    .title(Component.translatable("creativetab.uunk.uunk_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAW_LEAD);
                        output.accept(ModItems.LEAD_INGOT);
                        output.accept(ModItems.CHISEL);
                        output.accept(ModItems.FOOD_CAN);
                        output.accept(ModItems.GREASE);
                    }).build());

    public static final Supplier<CreativeModeTab> UUNK_BLOCKS_TAB = CREATIVE_MODE_TAB.register("uunk_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.LEAD_BLOCK.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Uunk.MOD_ID, "uunk_items_tab"))
                    .title(Component.translatable("creativetab.uunk.uunk_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.LEAD_ORE);
                        output.accept(ModBlocks.LEAD_DEEPSLATE_ORE);
                        output.accept(ModBlocks.LEAD_BLOCK);
                        output.accept(ModBlocks.CRUSHER);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
