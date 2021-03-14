package com.`kevin-nation`.minecraft

import com.kevin.KevinNationMenuScreen
import net.minecraft.client.gui.screen.MainMenuScreen
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod("kevin-nation")
class KevinNation {

    init {
        MinecraftForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    fun setMainMenuScreen(e: GuiOpenEvent) {
        if (e.gui is MainMenuScreen) {
            e.gui = KevinNationMenuScreen()
        }
    }
}