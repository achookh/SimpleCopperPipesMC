package net.lunade.copper.mixin.datafix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import java.util.function.BiFunction;
import net.lunade.copper.SimpleCopperPipesConstants;
import net.minecraft.util.datafix.DataFixers;
import net.minecraft.util.datafix.fixes.AddNewChoices;
import net.minecraft.util.datafix.fixes.References;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(DataFixers.class)
public class DataFixersMixin {

	@WrapOperation(
		method = "addFixers",
		at = @At(
			value = "INVOKE",
			target = "Lcom/mojang/datafixers/DataFixerBuilder;addSchema(ILjava/util/function/BiFunction;)Lcom/mojang/datafixers/schemas/Schema;",
			ordinal = 0,
			remap = false
		),
		slice = @Slice(
			from = @At(
				value = "CONSTANT",
				args = "intValue=3438"
			)
		)
	)
	private static Schema simpleCopperPipes$addFixers3807(DataFixerBuilder builder, int version, BiFunction<Integer, Schema, Schema> factory, Operation<Schema> original) {
		Schema schema = original.call(builder, version, factory);
		builder.addFixer(new AddNewChoices(schema, SimpleCopperPipesConstants.id("copper_pipe").toString(), References.BLOCK_ENTITY));
		builder.addFixer(new AddNewChoices(schema, SimpleCopperPipesConstants.id("copper_fitting").toString(), References.BLOCK_ENTITY));
		return schema;
	}

}
