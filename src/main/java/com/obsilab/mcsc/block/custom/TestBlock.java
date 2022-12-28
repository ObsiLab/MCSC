package com.obsilab.mcsc.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class TestBlock extends Block {
    public TestBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos,
                                 Player player, InteractionHand hand, BlockHitResult blockHitResult) {

        /*
        //! Use is called 4 times when right clicking a block? :
        // On the server:
        //     1 for the main hand and 1 for the off hand
        // On the client:
        //    1 for the main hand and 1 for the off hand
        // => is then called 4 times
        // need to verify if(level.isClientSide() && hand==InteractionHand.MAIN_HAND) or other
        */

        if(!level.isClientSide() && hand==InteractionHand.MAIN_HAND) {
            player.sendSystemMessage(Component.literal("TestBlock used"));
            return InteractionResult.SUCCESS; // skips checking the rest of use(), like the off-hand checking, etc
        }

        return super.use(state, level, blockPos, player, hand, blockHitResult);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        /*
        if(entity.isAlive()) {
            entity.hurt(null, 1);
        }
        */
        if(entity instanceof LivingEntity livingEntity) {
            livingEntity.hurt(DamageSource.GENERIC, 1);
            livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 40));
        }

        super.stepOn(level, blockPos, blockState, entity);
    }
}
