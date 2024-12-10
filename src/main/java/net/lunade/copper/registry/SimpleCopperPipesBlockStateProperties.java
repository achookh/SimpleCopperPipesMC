package net.lunade.copper.registry;

import net.lunade.copper.block.properties.PipeFluid;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class SimpleCopperPipesBlockStateProperties {
	public static final BooleanProperty FRONT_CONNECTED = BooleanProperty.create("front_connected");
	public static final BooleanProperty BACK_CONNECTED = BooleanProperty.create("back_connected");
	public static final BooleanProperty SMOOTH = BooleanProperty.create("smooth");
	public static final EnumProperty<PipeFluid> FLUID = EnumProperty.create("fluid", PipeFluid.class);
	public static final BooleanProperty HAS_ELECTRICITY = BooleanProperty.create("has_electricity");

	public static void init() {

	}
}
