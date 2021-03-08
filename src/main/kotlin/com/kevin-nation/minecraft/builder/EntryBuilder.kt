package com.`kevin-nation`.minecraft.builder

import de.maxanier.guideapi.api.IPage
import de.maxanier.guideapi.api.util.PageHelper
import de.maxanier.guideapi.page.*
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.tags.ITag
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextProperties

class EntryBuilder(
    private val pageList: MutableList<IPage>,
) {

    fun page(pageContentBuilder: PageContentBuilder.() -> Unit) {
        val builder = PageContentBuilder()
        pageContentBuilder(builder)
        pageList += PageHelper.pagesForLongText(builder.buildPageContent())
    }

    fun pageWithText(text: String) {
        pageList += PageHelper.pagesForLongText(text.trimMargin().component)
    }

    fun pageWithText(resourceName: String, text: String) {
        pageWithText(itemFromResourceName(resourceName), text)
    }

    fun pageWithText(item: Item, text: String) {
        pageList += PageHelper.pagesForLongText(text.trimMargin().component, item)
    }

    fun imagePage(resourceName: String, title: String? = null, textAtTop: Boolean = false) {
        pageList += when (title) {
            null -> PageImage(ResourceLocation(resourceName))
            else -> PageTextImage(title.component, ResourceLocation(resourceName), textAtTop)
        }
    }

    fun recipePage(resourceName: String) {
        pageList += PageJsonRecipe(ResourceLocation(resourceName))
    }

    fun itemStackPage(title: String, itemTag: ITag<Item>) {
        itemStackPage(title, Ingredient.fromTag(itemTag))
    }

    fun itemStackPage(title: String, ingredient: Ingredient) {
        itemStackPage(title, ingredient)
    }

    fun itemStackPage(resourceName: String, contentsProvider: PageContentBuilder.() -> Unit) {
        val builder = PageContentBuilder()
        contentsProvider(builder)
        itemStackPage(ItemStack(itemFromResourceName(resourceName)), builder.buildPageContent());
    }

    fun itemStackPage(resourceName: String, contents: String) {
        itemStackPage(ItemStack(itemFromResourceName(resourceName)), contents);
    }

    fun itemStackPage(items: ItemStack, contents: String) {
        itemStackPage(items, contents.component)
    }

    fun itemStackPage(items: ItemStack, contents: ITextProperties) {
        pageList += PageItemStack(contents, items)
    }

    fun <E: LivingEntity> entityPage(entity: EntityType<E>, title: String? = null, equip: E.() -> Unit = {}) {
        pageList += PageEntity({ w -> entity.create(w)!!.apply { equip(this) } }, title?.component)
    }

    fun brewingRecipePage(input: Ingredient, ingredient: Ingredient, output: ItemStack) {
        pageList += PageBrewingRecipe(input, ingredient, output)
    }
}