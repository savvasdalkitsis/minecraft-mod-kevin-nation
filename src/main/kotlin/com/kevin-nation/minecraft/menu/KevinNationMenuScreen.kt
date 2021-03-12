package com.kevin

import com.`kevin-nation`.minecraft.data.guiTexture
import com.`kevin-nation`.minecraft.menu.KevinNationButton
import com.mojang.blaze3d.matrix.MatrixStack
import net.minecraft.client.gui.screen.MainMenuScreen
import net.minecraft.client.gui.widget.Widget
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TranslationTextComponent


class KevinNationMenuScreen : MainMenuScreen() {

    private val background = ResourceLocation(guiTexture("background"))
    private val titleImage = ResourceLocation(guiTexture("title"))
    private val buttonKeysToRemove = listOf(
        "menu.singleplayer",
        "menu.multiplayer",
        "menu.online",
    )

    override fun init() {
        super.init()
        buttons.filter {
            it.key in buttonKeysToRemove
        }.forEach {
            buttons.remove(it)
            it.width = 0
            it.setHeight(0)
            it.x = 0
            it.y = 0
        }
        buttons.find { it.key == "fml.menu.mods" }?.apply {
            x += 2 + width / 2
        }
        minecraft?.isConnectedToRealms = false
        addButton(KevinNationButton(this, minecraft!!).widget)
    }

    override fun render(matrixStack: MatrixStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
        minecraft?.textureManager?.bindTexture(background)
        blit(matrixStack, 0, 0, width, height, 0f, 0f, 16, 128, 16, 128)
        minecraft?.textureManager?.bindTexture(titleImage)
        val w = 180
        val r = 4.12
        val h = (w / r).toInt()
        blit(matrixStack, (width - w)/2, 20, 0f, 0f, w, h, w, h)
        for (widget in buttons) {
            widget.setAlpha(1f)
            widget.render(matrixStack, mouseX, mouseY, partialTicks)
        }
    }

    private val Widget.key get() = (this.message as? TranslationTextComponent)?.key
}
