package com.`kevin-nation`.minecraft

import com.`kevin-nation`.minecraft.builder.BookBuilder
import com.`kevin-nation`.minecraft.builder.CategoryBuilder
import de.maxanier.guideapi.api.GuideBook
import de.maxanier.guideapi.api.IGuideBook
import de.maxanier.guideapi.api.impl.Book
import net.minecraft.item.Items
import net.minecraft.util.text.TextFormatting.*

@GuideBook
class WelcomeBook : IGuideBook {

    override fun buildBook(): Book = BookBuilder.buildBook(
        resourceId = "kevin-nation:guide-book",
        author = "Joob",
        displayName = "Guide book",
        header = "Welcome to Kevin Nation",
        title = "Kevin Nation Guide Book",
        spawnWithBook = true,
    ) {
        category("Waystones", ModItems.WAYSTONE) {
            entry("What are waystones?", ModItems.WAYSTONE) {
                pageWithText(ModItems.WAYSTONE, """
                    | A Waystone is the way Kevins can travel fast across the Kevin Nation.
                    |
                    | Right clicking on one will activate it. If already active, right clicking will show you all previously activated Waystones and allow you to teleport to them instantly!
                    | 
                    | Note that traveling to a Waystone across dimensions is possible but it will cost you levels so use it wisely!
                """)
            }
            entry("Where can I find Waystones?", Items.FILLED_MAP) {
                pageWithText("""
                    | You can find your first two Waystones in the spawn house and in the Shopping district. Try activating them and teleport between them!
                    |
                    | You can also find Waystones scattered across Kevin Nation. They are usually found on top of abandoned towers along with some treasure so activate as many as you can.
                """)

                imagePage(guiTexture("tower"), "    An abandoned tower")
            }
            entry("Can I have my own?", Items.CRAFTING_TABLE) {
                pageWithText("""
                    | Waystones can be destroyed and picked up like most blocks.
                    | 
                    | If you find Waystones in the wild though, they are most likely being used by other Kevins too so avoid taking those.
                    | 
                    | If you find a Waystone without an autogenerated name (you will know when you see it), please destroy it and place it again giving it a descriptive name. Maybe use the biome it is in and the coordinates (River 999, 555 for example).
                    | 
                    | You can also craft your own Waystones:
                """)
                recipePage(ModItems.WAYSTONE)
                recipePage(ModItems.WARP_STONE)
            }
            entry("Can I only teleport from Waystones?", ModItems.WARP_SCROLL) {
                page {
                    paragraph("There may be times when you want to teleport to a Waystone but you are not standing next to one (after a long and dangerous mining expedition to the Nether for example).")

                    paragraph("Thankfully there are 4 ways to teleport to a Waystone from anywhere in the world:")
                }
                itemStackPage(ModItems.WARP_SCROLL) {
                    paragraph("Warp Scroll", BOLD, BLUE)
                    paragraph("A warp scroll is a one-use item which will teleport you to a Waystone of your choice.")
                }
                recipePage(ModItems.WARP_SCROLL)

                itemStackPage(ModItems.RETURN_SCROLL) {
                    paragraph("Return Scroll", BOLD, BLUE)
                    paragraph("A return scroll works like a Warp Scroll but will return you to your last used Waystone.")
                }
                recipePage(ModItems.RETURN_SCROLL)

                itemStackPage(ModItems.BOUND_SCROLL) {
                    paragraph("Return Scroll (Bound)", BOLD, BLUE)
                    paragraph("A bound return scroll works like a normal Return Scroll but it will return you to a predefined Waystone.")
                }
                recipePage(ModItems.BOUND_SCROLL)

                itemStackPage(ModItems.WARP_STONE) {
                    paragraph("Warp Stone", BOLD, BLUE)
                    paragraph("A warp stone is like a portable Waystone. You can activate it at any point but it has a cooldown during which you won't be able to use it again so use it wisely. It is also an ingredient of the Waystone.")
                }
                recipePage(ModItems.WARP_STONE)
            }
        }
        category("Shops", ModItems.COIN_DOLLAR) {
            entry("Currency", ModItems.COIN_DOLLAR) {
                pageWithText(Items.DIAMOND, """
                    | The currency here at Kevin Nation is Cat Treats (naturally).
                    |
                    | You can use Cat Treats to buy resources from shops other Kevins have set up around the Shopping district.
                    |
                    | You can get Cat Treats by trading diamonds in the Bank building at the shopping center.
                """)
            }
            wip()
        }
        category("Maps", Items.FILLED_MAP) {
            wip()
        }
        category("Claiming Land", ModItems.BANNER_RALLY_GUARDS) {
            wip()
        }
        category("Colonies", ModItems.SUPPLY_CHEST_DEPLOYER) {
            wip()
        }
        category("Graves", ModItems.GRAVESTONE) {
            wip()
        }
        category("Cosmetic Armour", Items.NETHERITE_CHESTPLATE) {
            wip()
        }
        category("Dank Storage", ModItems.DANK_7) {
            wip()
        }
        category("Ender Mail", ModItems.STAMP) {
            wip()
        }
        category("Fairy lights/Additional Lights", ModItems.PAPER_LANTERN) {
            wip()
        }
        category("Miscellaneous", ModItems.GOLDEN_HOPPER) {
            wip()
        }
    }

    private fun CategoryBuilder.wip() = entry("Work In Progress. More Pages coming soon.", ModItems.IRON_SCAFFOLD) {
        itemStackPage(ModItems.IRON_SCAFFOLD, "Please check back soon for more information.")
    }
}