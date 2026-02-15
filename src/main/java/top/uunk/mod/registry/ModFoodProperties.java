package top.uunk.mod.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties FOOD_CAN = new FoodProperties.Builder()
            .nutrition(12)
            .saturationModifier(1.25f)
            .fast()
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 400), 0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 400), 1f)
            .build();
}
