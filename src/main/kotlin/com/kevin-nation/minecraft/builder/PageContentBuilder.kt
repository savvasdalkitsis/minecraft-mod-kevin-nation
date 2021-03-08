package com.`kevin-nation`.minecraft.builder

import net.minecraft.util.text.StringTextComponent
import net.minecraft.util.text.TextFormatting

class PageContentBuilder {
    private val content = "".component

    fun paragraph(text: String, vararg styles: TextFormatting) {
        text("$text\n\n\n", *styles)
    }

    fun text(text: String, vararg styles: TextFormatting) {
        append(text, *styles)
    }

    private fun append(text: String, vararg styles: TextFormatting) {
        content.append(text.trimMargin().component.apply { if (styles.isNotEmpty())
            mergeStyle(*styles)
        })
    }

    fun buildPageContent(): StringTextComponent = content
}