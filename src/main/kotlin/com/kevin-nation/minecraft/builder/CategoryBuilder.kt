package com.`kevin-nation`.minecraft.builder

import com.`kevin-nation`.minecraft.ModItems
import de.maxanier.guideapi.category.CategoryItemStack
import de.maxanier.guideapi.entry.EntryItemStack
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class CategoryBuilder(
    private val categoryItemStack: CategoryItemStack,
) {

    fun entry(title: String, resourceName: String, entryBuilder: EntryBuilder.() -> Unit) =
        entry(title, itemFromResourceName(resourceName), entryBuilder)

    fun entry(title: String, item: Item, entryBuilder: EntryBuilder.() -> Unit) {
        categoryItemStack.addEntry(title, EntryItemStack(title.component, ItemStack(item)).apply {
            entryBuilder(EntryBuilder(pageList))
        })
    }
}