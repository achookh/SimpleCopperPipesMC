package net.lunade.copper.config.gui;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lunade.copper.CopperPipeMain;
import net.lunade.copper.config.SimpleCopperPipesConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public final class SimpleCopperPipesConfigGui {
    private SimpleCopperPipesConfigGui() {}

    static Screen buildScreen(Screen parent) {
        var configBuilder = ConfigBuilder.create().setParentScreen(parent).setTitle(text("component.title"));
        var entryBuilder = configBuilder.entryBuilder();

        configBuilder.setSavingRunnable(SimpleCopperPipesConfig.INSTANCE::save);

        var main = configBuilder.getOrCreateCategory(text("main"));
        setupEntries(main, entryBuilder);

        return configBuilder.build();
    }

    private static void setupEntries(ConfigCategory category, ConfigEntryBuilder entryBuilder) {
        var config = SimpleCopperPipesConfig.get();
        var defaultConfig = SimpleCopperPipesConfig.INSTANCE.defaultInstance();

        category.addEntry(entryBuilder.startBooleanToggle(text("openable_fittings"), config.openableFittings)
                .setDefaultValue(defaultConfig.openableFittings)
                .setSaveConsumer(newValue -> config.openableFittings = newValue)
                .setTooltip(tooltip("openable_fittings"))
                .setYesNoTextSupplier(bool -> text(bool.toString()))
                .build()
        );

        category.addEntry(entryBuilder.startBooleanToggle(text("dispensing"), config.dispensing)
                .setDefaultValue(defaultConfig.dispensing)
                .setSaveConsumer(newValue -> config.dispensing = newValue)
                .setTooltip(tooltip("dispensing"))
                .setYesNoTextSupplier(bool -> text(bool.toString()))
                .build()
        );

        category.addEntry(entryBuilder.startBooleanToggle(text("special_effect_dispensing"), config.specialEffectDispensing)
                .setDefaultValue(defaultConfig.specialEffectDispensing)
                .setSaveConsumer(newValue -> config.specialEffectDispensing = newValue)
                .setTooltip(tooltip("special_effect_dispensing"))
                .setYesNoTextSupplier(bool -> text(bool.toString()))
                .build()
        );
    }

    private static Component text(String key) {
        return Component.translatable("option." + CopperPipeMain.NAMESPACE + "." + key);
    }

    private static Component tooltip(String key) {
        return Component.translatable("tooltip." + CopperPipeMain.NAMESPACE + "." + key);
    }
}