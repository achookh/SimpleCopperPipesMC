package net.lunade.copper.datagen.model;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.lunade.copper.registry.SimpleCopperPipesBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

public final class SimpleCopperPipesModelProvider extends FabricModelProvider {

    public SimpleCopperPipesModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) {
    }

    @Override
    public void generateItemModels(ItemModelGenerators generators) {
        generators.itemModelOutput.copy(SimpleCopperPipesBlocks.COPPER_PIPE.asItem(), SimpleCopperPipesBlocks.WAXED_COPPER_PIPE.asItem());
        generators.itemModelOutput.copy(SimpleCopperPipesBlocks.EXPOSED_COPPER_PIPE.asItem(), SimpleCopperPipesBlocks.WAXED_EXPOSED_COPPER_PIPE.asItem());
        generators.itemModelOutput.copy(SimpleCopperPipesBlocks.WEATHERED_COPPER_PIPE.asItem(), SimpleCopperPipesBlocks.WAXED_WEATHERED_COPPER_PIPE.asItem());
        generators.itemModelOutput.copy(SimpleCopperPipesBlocks.OXIDIZED_COPPER_PIPE.asItem(), SimpleCopperPipesBlocks.WAXED_OXIDIZED_COPPER_PIPE.asItem());

        generators.itemModelOutput.copy(SimpleCopperPipesBlocks.COPPER_FITTING.asItem(), SimpleCopperPipesBlocks.WAXED_COPPER_FITTING.asItem());
        generators.itemModelOutput.copy(SimpleCopperPipesBlocks.EXPOSED_COPPER_PIPE.asItem(), SimpleCopperPipesBlocks.WAXED_EXPOSED_COPPER_FITTING.asItem());
        generators.itemModelOutput.copy(SimpleCopperPipesBlocks.WEATHERED_COPPER_FITTING.asItem(), SimpleCopperPipesBlocks.WAXED_WEATHERED_COPPER_FITTING.asItem());
        generators.itemModelOutput.copy(SimpleCopperPipesBlocks.OXIDIZED_COPPER_FITTING.asItem(), SimpleCopperPipesBlocks.WAXED_OXIDIZED_COPPER_FITTING.asItem());
    }
}
