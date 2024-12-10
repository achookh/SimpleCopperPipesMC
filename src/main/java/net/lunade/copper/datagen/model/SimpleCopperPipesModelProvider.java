package net.lunade.copper.datagen.model;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.lunade.copper.SimpleCopperPipesConstants;
import net.lunade.copper.block.CopperPipe;
import net.lunade.copper.registry.SimpleCopperPipesBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import java.util.Optional;

public final class SimpleCopperPipesModelProvider extends FabricModelProvider {
	private static final ModelTemplate PIPE_MODEL = new ModelTemplate(
		Optional.of(SimpleCopperPipesConstants.id("block/template_pipe")),
		Optional.empty(),
		TextureSlot.SIDE,
		TextureSlot.FRONT
	);
	private static final ModelTemplate PIPE_MODEL_BACK = new ModelTemplate(
		Optional.of(SimpleCopperPipesConstants.id("block/template_pipe_back_extension")),
		Optional.empty(),
		TextureSlot.SIDE,
		TextureSlot.FRONT
	);
	private static final ModelTemplate PIPE_MODEL_BACK_SMOOTH = new ModelTemplate(
		Optional.of(SimpleCopperPipesConstants.id("block/template_pipe_back_smooth")),
		Optional.empty(),
		TextureSlot.SIDE
	);
	private static final ModelTemplate PIPE_MODEL_DOUBLE_EXTENSION = new ModelTemplate(
		Optional.of(SimpleCopperPipesConstants.id("block/template_pipe_double_extension")),
		Optional.empty(),
		TextureSlot.SIDE
	);
	private static final ModelTemplate PIPE_MODEL_FRONT_EXTENSION = new ModelTemplate(
		Optional.of(SimpleCopperPipesConstants.id("block/template_pipe_front_extension")),
		Optional.empty(),
		TextureSlot.SIDE,
		TextureSlot.FRONT
	);
	private static final ModelTemplate PIPE_MODEL_SMOOTH = new ModelTemplate(
		Optional.of(SimpleCopperPipesConstants.id("block/template_pipe_smooth")),
		Optional.empty(),
		TextureSlot.SIDE,
		TextureSlot.FRONT
	);
	private static final ModelTemplate FITTING_MODEL = new ModelTemplate(
		Optional.of(SimpleCopperPipesConstants.id("block/template_fitting")),
		Optional.empty(),
		TextureSlot.TEXTURE
	);
	public SimpleCopperPipesModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators generators) {
		createPipe(generators, SimpleCopperPipesBlocks.COPPER_PIPE, SimpleCopperPipesBlocks.COPPER_PIPE);
		createPipe(generators, SimpleCopperPipesBlocks.EXPOSED_COPPER_PIPE, SimpleCopperPipesBlocks.EXPOSED_COPPER_PIPE);
		createPipe(generators, SimpleCopperPipesBlocks.WEATHERED_COPPER_PIPE, SimpleCopperPipesBlocks.WEATHERED_COPPER_PIPE);
		createPipe(generators, SimpleCopperPipesBlocks.OXIDIZED_COPPER_PIPE, SimpleCopperPipesBlocks.OXIDIZED_COPPER_PIPE);

		createPipe(generators, SimpleCopperPipesBlocks.COPPER_PIPE, SimpleCopperPipesBlocks.WAXED_COPPER_PIPE);
		createPipe(generators, SimpleCopperPipesBlocks.EXPOSED_COPPER_PIPE, SimpleCopperPipesBlocks.WAXED_EXPOSED_COPPER_PIPE);
		createPipe(generators, SimpleCopperPipesBlocks.WEATHERED_COPPER_PIPE, SimpleCopperPipesBlocks.WAXED_WEATHERED_COPPER_PIPE);
		createPipe(generators, SimpleCopperPipesBlocks.OXIDIZED_COPPER_PIPE, SimpleCopperPipesBlocks.WAXED_OXIDIZED_COPPER_PIPE);

		createFitting(generators, SimpleCopperPipesBlocks.COPPER_FITTING, SimpleCopperPipesBlocks.COPPER_FITTING);
		createFitting(generators, SimpleCopperPipesBlocks.EXPOSED_COPPER_FITTING, SimpleCopperPipesBlocks.EXPOSED_COPPER_FITTING);
		createFitting(generators, SimpleCopperPipesBlocks.WEATHERED_COPPER_FITTING, SimpleCopperPipesBlocks.WEATHERED_COPPER_FITTING);
		createFitting(generators, SimpleCopperPipesBlocks.OXIDIZED_COPPER_FITTING, SimpleCopperPipesBlocks.OXIDIZED_COPPER_FITTING);

		createFitting(generators, SimpleCopperPipesBlocks.COPPER_FITTING, SimpleCopperPipesBlocks.WAXED_COPPER_FITTING);
		createFitting(generators, SimpleCopperPipesBlocks.EXPOSED_COPPER_FITTING, SimpleCopperPipesBlocks.WAXED_EXPOSED_COPPER_FITTING);
		createFitting(generators, SimpleCopperPipesBlocks.WEATHERED_COPPER_FITTING, SimpleCopperPipesBlocks.WAXED_WEATHERED_COPPER_FITTING);
		createFitting(generators, SimpleCopperPipesBlocks.OXIDIZED_COPPER_FITTING, SimpleCopperPipesBlocks.WAXED_OXIDIZED_COPPER_FITTING);
	}

	@Override
	public void generateItemModels(@NotNull ItemModelGenerators generators) {

	}

	public static void createPipe(@NotNull BlockModelGenerators generators, Block pipeBlock, Block outputPipeBlock) {
		if (pipeBlock == outputPipeBlock) {
			TextureMapping pipeTextureMapping = new TextureMapping();
			pipeTextureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(pipeBlock));
			pipeTextureMapping.put(TextureSlot.FRONT, TextureMapping.getBlockTexture(pipeBlock, "_front"));

			PIPE_MODEL.create(pipeBlock, pipeTextureMapping, generators.modelOutput);
			PIPE_MODEL_BACK.createWithSuffix(pipeBlock, "_back", pipeTextureMapping, generators.modelOutput);
			PIPE_MODEL_BACK_SMOOTH.createWithSuffix(pipeBlock, "_back_smooth", pipeTextureMapping, generators.modelOutput);
			PIPE_MODEL_DOUBLE_EXTENSION.createWithSuffix(pipeBlock, "_double_extension", pipeTextureMapping, generators.modelOutput);
			PIPE_MODEL_FRONT_EXTENSION.createWithSuffix(pipeBlock, "_front_extension", pipeTextureMapping, generators.modelOutput);
			PIPE_MODEL_SMOOTH.createWithSuffix(pipeBlock, "_smooth", pipeTextureMapping, generators.modelOutput);
		}

		ResourceLocation model = ModelLocationUtils.getModelLocation(pipeBlock);
		ResourceLocation frontExtensionModel = ModelLocationUtils.getModelLocation(pipeBlock, "_front_extension");
		ResourceLocation doubleExtensionModel = ModelLocationUtils.getModelLocation(pipeBlock, "_double_extension");
		ResourceLocation backExtensionModel = ModelLocationUtils.getModelLocation(pipeBlock, "_back_extension");
		ResourceLocation smoothModel = ModelLocationUtils.getModelLocation(pipeBlock, "_smooth");
		ResourceLocation backSmoothModel = ModelLocationUtils.getModelLocation(pipeBlock, "_back_smooth");
		generators.registerSimpleItemModel(outputPipeBlock, model);
		generators.blockStateOutput
			.accept(
				MultiVariantGenerator.multiVariant(outputPipeBlock)
					.with(BlockModelGenerators.createFacingDispatch())
					.with(
						PropertyDispatch.properties(CopperPipe.FRONT_CONNECTED, CopperPipe.BACK_CONNECTED, CopperPipe.SMOOTH)
							.select(false, false, false, Variant.variant().with(VariantProperties.MODEL, model))
							.select(true, false, false, Variant.variant().with(VariantProperties.MODEL, frontExtensionModel))
							.select(true, true, false, Variant.variant().with(VariantProperties.MODEL, doubleExtensionModel))
							.select(true, true, true, Variant.variant().with(VariantProperties.MODEL, doubleExtensionModel))
							.select(true, false, true, Variant.variant().with(VariantProperties.MODEL, frontExtensionModel))
							.select(false, false, true, Variant.variant().with(VariantProperties.MODEL, smoothModel))
							.select(false, true, false, Variant.variant().with(VariantProperties.MODEL, backExtensionModel))
							.select(false, true, true, Variant.variant().with(VariantProperties.MODEL, backSmoothModel))
					)
			);
	}

	public static void createFitting(@NotNull BlockModelGenerators generators, Block fittingBlock, Block outputFittingBlock) {
		if (fittingBlock == outputFittingBlock) {
			TextureMapping fittingTextureMapping = new TextureMapping();
			fittingTextureMapping.put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(fittingBlock));

			FITTING_MODEL.create(fittingBlock, fittingTextureMapping, generators.modelOutput);
		}

		ResourceLocation model = ModelLocationUtils.getModelLocation(fittingBlock);
		generators.registerSimpleItemModel(outputFittingBlock, model);
		generators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(outputFittingBlock, model));
	}
}
