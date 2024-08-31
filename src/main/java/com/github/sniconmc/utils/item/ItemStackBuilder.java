package com.github.sniconmc.utils.item;

import com.github.sniconmc.utils.skin.SkinUtils;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemComponent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.component.CustomData;
import net.minestom.server.item.component.DyedItemColor;
import com.github.sniconmc.utils.Main;
import com.github.sniconmc.utils.text.ColorUtils;
import net.minestom.server.item.component.HeadProfile;

import java.util.ArrayList;
import java.util.List;

/**

 Builder class for creating {@link ItemStack} objects with various properties.


 <p>This class provides methods to set different attributes of an item stack,

 such as material, count, name, lore, and custom data. It uses a fluent interface
 for easy creation of complex item stacks.</p>

 @see net.minestom.server.item.ItemStack
 @see net.minestom.server.item.ItemComponent

 @author znopp
 @author Wi1helm
 */


public class ItemStackBuilder {

    private Material material = Material.AIR;

    private Integer count = 1;
    private Integer maxStackSize = 64;

    private Component name = Component.empty();
    private List<Component> lore = new ArrayList<>();

    private Boolean hasGlint = false;
    private String dyeColor = "";
    private Boolean showToolTips = false;

    private Player player;
    private String skullOwner = "";
    private String uuid = "";
    private String texture = "";

    private Boolean isVanillaItem = false;
    private String function = "";
    private String page = "";

    /**

     Sets the material of the item stack.

     @param material The Material to set for the item stack
     @return This builder for chaining
     */

    public ItemStackBuilder material(Material material) {
        if (material == null) {
            Main.logger.error("Material cannot be null");
        }
        this.material = material;
        return this;
    }

    /**

     Sets the count (amount) of the item stack.

     @param count The number of items in the stack
     @return This builder for chaining
     */

    public ItemStackBuilder count(Integer count) {
        if (count == null) {
            Main.logger.error("Count cannot be null");
        }
        this.count = count;
        return this;
    }

    /**

     Sets the max stack size of the item stack.

     @param maxStackSize The maximum size of a stack
     @return This builder for chaining
     */

    public ItemStackBuilder maxStackSize(Integer maxStackSize) {
        if (maxStackSize == null) {
            Main.logger.error("MaxStackSize cannot be null");
        }
        this.maxStackSize = maxStackSize;
        return this;
    }

    /**

     Sets the name of the item stack.

     @param name The name in question
     @return This builder for chaining
     */

    public ItemStackBuilder name(Component name) {
        if (name == null) {
            Main.logger.error("Name cannot be null");
        }
        this.name = name;
        return this;
    }

    /**

     Sets the lore of the item stack.

     @param lore The list of components comprising the lore
     @return This builder for chaining
     */

    public ItemStackBuilder lore(List<Component> lore) {
        if (lore == null) {
            Main.logger.error("Lore cannot be null");
        }
        this.lore = lore;
        return this;
    }

    /**

     Sets the glint state of the item stack.

     @param glint The variable deciding the glint state
     @return This builder for chaining
     */

    public ItemStackBuilder glint(Boolean glint) {
        if (glint == null) {
            Main.logger.error("Glint cannot be null");
        }
        this.hasGlint = glint;
        return this;
    }

    /**

     Sets the dye color of the item stack, even if not visible for that specific item.

     @param dyeColor A string that later gets converted to the proper RGB value
     @return This builder for chaining
     */

    public ItemStackBuilder dyeColor(String dyeColor) {
        if (dyeColor == null) {
            Main.logger.error("DyeColor cannot be null");
        }
        this.dyeColor = dyeColor;
        return this;
    }

    /**

     Sets the tool tip state of the item stack.

     @param showToolTips The variable deciding the visibility of tool tips
     @return This builder for chaining
     */

    public ItemStackBuilder showToolTips(Boolean showToolTips) {
        if (showToolTips == null) {
            Main.logger.error("ShowToolTips cannot be null");
        }
        this.showToolTips = showToolTips;
        return this;
    }

    /**

     Sets the skin of the skull itemstack.

     @param username The variable deciding the username of the player, or "this",
     meaning to use the player class instead.
     @param player The player class which skin will be used if username is "this"
     @return This builder for chaining
     */

    public ItemStackBuilder playerSkull(String username, Player player) {
        if (username == null) {
            Main.logger.error("PlayerSkull cannot be null");
            return this;
        }
        if (username.equals("this") && player != null) {
            this.player = player;
        }
        this.skullOwner = username;
        return this;
    }

    /**

     Sets the player skin of an item using a UUID, which is only applicable if the item is a player head.

     @param uuid The variable representing the player of which to get the skin from
     @return This builder for chaining
     */

    public ItemStackBuilder uuid(String uuid) {
        if (uuid == null) {
            Main.logger.error("UUID cannot be null");
        }
        this.uuid = uuid;
        return this;
    }

    /**

     Sets the player skin of an item using a texture string, which is only applicable if the item is a player head.

     @param texture The variable representing the skin used for the head
     @return This builder for chaining
     */

    public ItemStackBuilder texture(String texture) {
        if (texture == null) {
            Main.logger.error("Texture cannot be null");
        }
        this.texture = texture;
        return this;
    }

    /**

     Sets custom data used for checking if the item stack is a vanilla item or
     used for GUI purposes.
     In short, whether the player should be able to use this item.

     @param isVanillaItem The variable setting the vanilla-ness of the item
     @return This builder for chaining
     */

    public ItemStackBuilder isVanillaItem(Boolean isVanillaItem) {
        if (isVanillaItem == null) {
            Main.logger.error("IsVanillaItem cannot be null");
        }
        this.isVanillaItem = isVanillaItem;
        return this;
    }

    /**

     Sets a function to be executed on an interaction event.

     @param function The function to execute
     @return This builder for chaining
     */

    public ItemStackBuilder function(String function) {
        if (function == null) {
            Main.logger.error("Function cannot be null");
        }
        this.function = function;
        return this;
    }

    /**

     Sets the page value used to navigate GUIs.

     @param page The page the player gets displayed
     @return This builder for chaining
     */

    public ItemStackBuilder page(String page) {
        if (page == null) {
            Main.logger.error("Page cannot be null");
        }
        this.page = page;
        return this;
    }

    /**

     Builds and returns the final ItemStack with all the configured properties.

     @return The constructed ItemStack object
     */

    public ItemStack build() {
        ItemStack.Builder itemstack = ItemStack.builder(material)
                .amount(count)
                .maxStackSize(maxStackSize)
                .set(ItemComponent.ITEM_NAME, name)
                .set(ItemComponent.LORE, lore)
                .glowing(hasGlint)
                .set(ItemComponent.DYED_COLOR, new DyedItemColor(ColorUtils.StringToRgb(dyeColor), false));

                if (showToolTips) {
                    itemstack.set(ItemComponent.HIDE_TOOLTIP);
                }
        itemstack
                .set(ItemComponent.CUSTOM_DATA, new CustomData(CompoundBinaryTag.builder().putBoolean("isVanillaItem" ,isVanillaItem).build()))
                .set(ItemComponent.CUSTOM_DATA, new CustomData(CompoundBinaryTag.builder().putString("function" ,function).build()))
                .set(ItemComponent.CUSTOM_DATA, new CustomData(CompoundBinaryTag.builder().putString("page" ,page).build()));

                if (material == Material.PLAYER_HEAD) {
                    itemstack.set(ItemComponent.PROFILE, new HeadProfile(SkinUtils.getSkin(player, skullOwner, uuid, texture, "" )));
                }

        return itemstack.build();

    }
}
