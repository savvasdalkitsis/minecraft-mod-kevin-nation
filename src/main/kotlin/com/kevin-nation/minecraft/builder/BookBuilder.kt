package com.`kevin-nation`.minecraft.builder

import de.maxanier.guideapi.api.impl.Book
import de.maxanier.guideapi.api.impl.BookBinder
import de.maxanier.guideapi.api.impl.abstraction.CategoryAbstract
import de.maxanier.guideapi.category.CategoryItemStack
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items.*
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.registries.ForgeRegistries
import java.awt.Color

class BookBuilder(
    resourceId: String,
    author: String,
    color: Color? = null,
    displayName: String,
    header: String,
    title: String,
    spawnWithBook: Boolean,
    private val contents: BookBuilder.() -> Unit
) {
    private lateinit var categories: MutableList<CategoryAbstract>

    private val book: Book =
        BookBinder(ResourceLocation(resourceId))
            .setAuthor(author.component)
            .apply {
                if (color != null)
                    setColor(color)
            }
            .setItemName(displayName.component)
            .setHeader(header.component)
            .setGuideTitle(title.component)
            .apply {
                if (spawnWithBook)
                    setSpawnWithBook()
            }
            .setContentProvider {
                this.categories = it
                contents(this)
            }
            .build()

    fun category(title: String, itemResourceId: String, categoryBuilder: CategoryBuilder.() -> Unit) =
        category(title, itemFromResourceName(itemResourceId), categoryBuilder)

    fun category(title: String, item: Item, categoryBuilder: CategoryBuilder.() -> Unit) {
        categories.add(CategoryItemStack(title.component, ItemStack(item)).apply {
            withKeyBase(title)
            categoryBuilder(CategoryBuilder(this))
        })
    }

    companion object {
        fun buildBook(
            resourceId: String,
            author: String,
            color: Color? = null,
            displayName: String,
            header: String,
            title: String,
            spawnWithBook: Boolean,
            contents: BookBuilder.() -> Unit
        ) = BookBuilder(resourceId, author, color, displayName, header, title, spawnWithBook, contents).book
    }
}

val String.component get() = StringTextComponent(this)
fun itemFromResourceName(resourceName: String) = ForgeRegistries.ITEMS.getValue(ResourceLocation(resourceName)) ?: BARRIER
