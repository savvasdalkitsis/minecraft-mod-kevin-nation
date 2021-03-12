package com.`kevin-nation`.minecraft.menu

import com.`kevin-nation`.minecraft.builder.component
import com.`kevin-nation`.minecraft.data.ToggleableResourceLocation
import com.`kevin-nation`.minecraft.data.guiTexture
import com.mojang.blaze3d.matrix.MatrixStack
import net.minecraft.client.Minecraft
import net.minecraft.client.audio.SimpleSound
import net.minecraft.client.gui.screen.ConnectingScreen
import net.minecraft.client.gui.screen.MainMenuScreen
import net.minecraft.client.gui.widget.button.Button
import net.minecraft.client.gui.widget.button.ImageButton
import net.minecraft.client.multiplayer.ServerData
import net.minecraft.client.multiplayer.ServerList
import net.minecraft.util.SoundEvents

class KevinNationButton(
    private val gui: MainMenuScreen,
    private val minecraft: Minecraft,
) : Button.IPressable, Button.ITooltip {

    private val w = 32
    private val server = ServerData("Kevin Nation", "minecraft.kevin-nation.com", false)
    private val soundHandler = minecraft.soundHandler
    private var drawingOverlay = false
    private val soundClick = SimpleSound.master(SoundEvents.UI_BUTTON_CLICK, 1.0f)
    private val soundVillager = SimpleSound.master(SoundEvents.ENTITY_VILLAGER_AMBIENT, 1.0f)
    private var playingSound: SimpleSound? = soundVillager
    private val texture = ToggleableResourceLocation(
        guiTexture("server_button_32"),
        guiTexture("server_button_highlight_32"),
    )

    init {
        ServerList.saveSingleServer(server)
    }

    val widget = object: ImageButton(
        (gui.width - w) / 2,
        gui.height / 2 - w, w, w, 0, 0, 0, texture, w, w,
        this, this, "Kevin Nation".component
    ) {
        override fun renderButton(matrixStack: MatrixStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
            super.renderButton(matrixStack, mouseX, mouseY, partialTicks)
            if (!isHovered()) {
                playingSound = soundVillager
            }
        }
    }

    override fun onPress(p_onPress_1_: Button) {
        minecraft.displayGuiScreen(ConnectingScreen(gui, minecraft, server))
        soundHandler.play(soundClick)
    }

    override fun onTooltip(button: Button, matrixStack: MatrixStack, mouseX: Int, mouseY: Int) {
        if (drawingOverlay)
            return

        playingSound?.let {
            if (!soundHandler.isPlaying(it)) {
                soundHandler.play(it)
                playingSound = null
            }
        }
        drawingOverlay = true
        texture.secondary()
        button.render(matrixStack, mouseX, mouseY, 0f)
        texture.primary()
        drawingOverlay = false
    }

}