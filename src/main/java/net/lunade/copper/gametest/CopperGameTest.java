package net.lunade.copper.gametest;

import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.frozenblock.lib.gametest.api.TrackedPosition;
import net.frozenblock.lib.storage.api.MoveDirection;
import net.lunade.copper.block_entity.CopperPipeEntity;
import net.lunade.copper.blocks.CopperFitting;
import net.lunade.copper.blocks.CopperPipe;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class CopperGameTest implements FabricGameTest {

    private static final String AXE_INTERACTION = "copper_pipe:axe_interaction";
    private static final String COMPOSTER_TRANSFER = "copper_pipe:composter_transfer";
    private static final String DIRECT_PIPE_TRANSFER = "copper_pipe:direct_pipe_transfer";
    private static final String STORAGE_UNIFICATION = "copper_pipe:storage_unification";

    @GameTest(template = AXE_INTERACTION)
    public void axeInteraction(GameTestHelper helper) {
        ServerPlayer player = helper.makeMockServerPlayerInLevel();
        player.setPos(helper.absoluteVec(new Vec3(9.5, 3.0, 0.5)));
        ItemStack stack = new ItemStack(Items.DIAMOND_AXE);

        // variable names reflect the end result after axe interaction
        TrackedPosition<Vec3> copperPipe = TrackedPosition.createRelative(helper, CopperPipe.EXPOSED_PIPE, new Vec3(9.5, 2.5, 0.5));
        TrackedPosition<Vec3> exposedPipe = TrackedPosition.createRelative(helper, CopperPipe.WEATHERED_PIPE, new Vec3(9.5, 2.5, 1.5));
        TrackedPosition<Vec3> weatheredPipe = TrackedPosition.createRelative(helper, CopperPipe.OXIDIZED_PIPE, new Vec3(9.5, 2.5, 2.5));
        TrackedPosition<Vec3> copperPipe2 = TrackedPosition.createRelative(helper, CopperPipe.WAXED_COPPER_PIPE, new Vec3(8.5, 2.5, 0.5));
        TrackedPosition<Vec3> exposedPipe2 = TrackedPosition.createRelative(helper, CopperPipe.WAXED_EXPOSED_PIPE, new Vec3(8.5, 2.5, 1.5));
        TrackedPosition<Vec3> weatheredPipe2 = TrackedPosition.createRelative(helper, CopperPipe.WAXED_WEATHERED_PIPE, new Vec3(8.5, 2.5, 2.5));
        TrackedPosition<Vec3> oxidizedPipe = TrackedPosition.createRelative(helper, CopperPipe.WAXED_OXIDIZED_PIPE, new Vec3(8.5, 2.5, 3.5));

        TrackedPosition<Vec3> copperFitting = TrackedPosition.createRelative(helper, CopperFitting.EXPOSED_FITTING, new Vec3(9.5, 2.5, 7.5));
        TrackedPosition<Vec3> exposedFitting = TrackedPosition.createRelative(helper, CopperFitting.WEATHERED_FITTING, new Vec3(9.5, 2.5, 8.5));
        TrackedPosition<Vec3> weatheredFitting = TrackedPosition.createRelative(helper, CopperFitting.OXIDIZED_FITTING, new Vec3(9.5, 2.5, 9.5));
        TrackedPosition<Vec3> copperFitting2 = TrackedPosition.createRelative(helper, CopperFitting.WAXED_COPPER_FITTING, new Vec3(8.5, 2.5, 6.5));
        TrackedPosition<Vec3> exposedFitting2 = TrackedPosition.createRelative(helper, CopperFitting.WAXED_EXPOSED_FITTING, new Vec3(8.5, 2.5, 7.5));
        TrackedPosition<Vec3> weatheredFitting2 = TrackedPosition.createRelative(helper, CopperFitting.WAXED_WEATHERED_FITTING, new Vec3(8.5, 2.5, 8.5));
        TrackedPosition<Vec3> oxidizedFitting = TrackedPosition.createRelative(helper, CopperFitting.WAXED_OXIDIZED_FITTING, new Vec3(8.5, 2.5, 9.5));
        List<TrackedPosition<Vec3>> copperLocations = List.of(
                copperPipe, exposedPipe, weatheredPipe, copperPipe2, exposedPipe2, weatheredPipe2, oxidizedPipe,
                copperFitting, exposedFitting, weatheredFitting, copperFitting2, exposedFitting2, weatheredFitting2, oxidizedFitting
        );
        helper.runAfterDelay(1L, () -> {
            for (TrackedPosition<Vec3> location : copperLocations) {
                Vec3 absolute = location.absolute();

                player.setPos(absolute.add(0, 0.5, 0));
                player.lookAt(EntityAnchorArgument.Anchor.EYES, absolute);
                BlockHitResult hitResult = (BlockHitResult) player.pick(2, 1.0F, false);
                UseOnContext context = new UseOnContext(player, InteractionHand.MAIN_HAND, hitResult);
                stack.useOn(context); // simulates a player using an axe on the pipe
            }
        });
        helper.runAfterDelay(2L, () -> {
            // remove fake player
            helper.getLevel().getServer().getPlayerList().remove(player);
            player.remove(Entity.RemovalReason.DISCARDED);

            // check if all pipes and fittings have been interacted with
            copperPipe.assertBlockPresent(helper, CopperPipe.COPPER_PIPE);
            exposedPipe.assertBlockPresent(helper, CopperPipe.EXPOSED_PIPE);
            weatheredPipe.assertBlockPresent(helper, CopperPipe.WEATHERED_PIPE);
            copperPipe2.assertBlockPresent(helper, CopperPipe.COPPER_PIPE);
            exposedPipe2.assertBlockPresent(helper, CopperPipe.EXPOSED_PIPE);
            weatheredPipe2.assertBlockPresent(helper, CopperPipe.WEATHERED_PIPE);
            oxidizedPipe.assertBlockPresent(helper, CopperPipe.OXIDIZED_PIPE);

            copperFitting.assertBlockPresent(helper, CopperFitting.COPPER_FITTING);
            exposedFitting.assertBlockPresent(helper, CopperFitting.EXPOSED_FITTING);
            weatheredFitting.assertBlockPresent(helper, CopperFitting.WEATHERED_FITTING);
            copperFitting2.assertBlockPresent(helper, CopperFitting.COPPER_FITTING);
            exposedFitting2.assertBlockPresent(helper, CopperFitting.EXPOSED_FITTING);
            weatheredFitting2.assertBlockPresent(helper, CopperFitting.WEATHERED_FITTING);
            oxidizedFitting.assertBlockPresent(helper, CopperFitting.OXIDIZED_FITTING);

            helper.succeed();
        });
    }

    @GameTest(template = COMPOSTER_TRANSFER)
    public void composterTransfer(GameTestHelper helper) {
        ItemVariant inputResource = ItemVariant.of(Items.CORNFLOWER);
        ItemVariant outputResource = ItemVariant.of(Items.BONE_MEAL);
        TrackedPosition<BlockPos> source = TrackedPosition.createRelative(helper, CopperPipe.WAXED_COPPER_PIPE, new BlockPos(9, 4, 4));
        TrackedPosition<BlockPos> output = TrackedPosition.createRelative(helper, Blocks.CHEST, new BlockPos(7, 2, 4));

        moveResources(helper, source, inputResource, 32, MoveDirection.IN, false);
        helper.runAfterDelay(75L, () -> {
            long amountInChest = moveResources(helper, output, outputResource, 32, MoveDirection.OUT, true);
            if (amountInChest > 0) helper.succeed(); else helper.fail("No items in chest");
        });
    }

    @GameTest(template = DIRECT_PIPE_TRANSFER)
    public void directPipeTransfer(GameTestHelper helper) {
        ItemVariant resource = ItemVariant.of(Blocks.MANGROVE_LOG);
        TrackedPosition<BlockPos> sourceChest = TrackedPosition.createRelative(helper, Blocks.CHEST, new BlockPos(9, 2, 4));
        TrackedPosition<BlockPos> targetChest = TrackedPosition.createRelative(helper, Blocks.CHEST, new BlockPos(0, 2, 4));

        moveResources(helper, sourceChest, resource, 32, MoveDirection.IN, false);
        helper.onEachTick(() -> {
            long amountInChest = moveResources(helper, targetChest, resource, 32, MoveDirection.OUT, true);
            if (amountInChest == 32) helper.succeed();
        });
    }

    @GameTest(template = STORAGE_UNIFICATION, timeoutTicks = 700)
    public void storageUnification(GameTestHelper helper) {
        ItemVariant resource = ItemVariant.of(Blocks.MANGROVE_LOG);
        for (int z = 0; z < 10; z +=  2) {
            TrackedPosition<BlockPos> chestPos = TrackedPosition.createRelative(helper, Blocks.CHEST, new BlockPos(9, 2, z));
            moveResources(helper, chestPos, resource, 128, MoveDirection.IN, false);
        }
        // unified chest
        TrackedPosition<BlockPos> chestPos = TrackedPosition.createRelative(helper, Blocks.CHEST, new BlockPos(9, 4, 4));
        helper.onEachTick(() -> {
            long amountInChest = moveResources(helper, chestPos, resource, 640, MoveDirection.OUT, true);
            if (amountInChest == 640) helper.succeed();
        });
    }

    private static long moveResources(GameTestHelper helper, TrackedPosition<BlockPos> inventoryPos, ItemVariant resource, long maxAmount, MoveDirection direction, boolean simulate) {
        Storage<ItemVariant> inventory = CopperPipeEntity.getStorageAt(helper.getLevel(), inventoryPos.absolute(), null);
        helper.assertTrue(inventory != null, "Inventory not found at " + inventoryPos.relative());

        Transaction transaction = Transaction.openOuter();
        long amountMoved = simulate
                ? direction.simulateMoveResources(inventory, resource, maxAmount, transaction)
                : direction.moveResources(inventory, resource, maxAmount, transaction);
        transaction.commit();
        return amountMoved;
    }

}