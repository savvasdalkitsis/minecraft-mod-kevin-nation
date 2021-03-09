package com.`kevin-nation`.minecraft.builder

import de.maxanier.guideapi.category.CategoryItemStack
import de.maxanier.guideapi.entry.EntryItemStack
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class CategoryBuilder(
    private val categoryItemStack: CategoryItemStack,
) {

    fun entry(resourceName: String, title: String, entryBuilder: EntryBuilder.() -> Unit) =
        entry(itemFromResourceName(resourceName), title, entryBuilder)

    fun entry(item: Item, title: String, entryBuilder: EntryBuilder.() -> Unit) {
        categoryItemStack.addEntry(title, EntryItemStack(title.component, ItemStack(item)).apply {
            entryBuilder(EntryBuilder(pageList))
        })
    }
}