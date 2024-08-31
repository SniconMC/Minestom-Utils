package com.github.sniconmc.item;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.item.ItemComponent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.component.CustomData;
import net.minestom.server.item.component.HeadProfile;
import com.github.sniconmc.text.TextUtils;
import java.util.List;
import java.util.Objects;


/**
 * The {@code ItemStackDestroyer} class provides methods for extracting various properties
 * from an {@link ItemStack} object and storing them as data. This class uses a fluent interface
 * to facilitate the retrieval of complex item stack attributes, such as material, count, name,
 * lore, custom data, and other components.
 *
 * <p>This class is particularly useful for destructuring an {@code ItemStack} into its
 * constituent data parts for further processing, logging, or display purposes.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * ItemStackDestroyer destroyer = new ItemStackDestroyer().destroy(itemStack);
 * Material material = destroyer.getMaterial();
 * List<String> lore = destroyer.getLoreStringList();
 * }</pre>
 *
 * @see net.minestom.server.item.ItemStack
 * @see net.minestom.server.item.ItemComponent
 * @see net.kyori.adventure.text.Component
 * @see net.kyori.adventure.nbt.CompoundBinaryTag
 * @see net.minestom.server.entity.PlayerSkin
 *
 * @author znopp
 * @author Wi1helm
 */

public class ItemStackDestroyer {

    private Material material;
    private String materialName;

    private Integer count;
    private Integer maxStackSize;

    private List<String> nameStringList;
    private Component nameComponent;
    private List<List<String>> loreStringList;
    private List<Component> loreComponent;

    private Boolean glint;
    private String dyeColor;
    private Boolean showToolTips;
    private PlayerSkin playerSkin;

    private Boolean isVanillaItem;
    private String function;
    private String page;

    /**
     * Extracts and stores various properties from the given {@link ItemStack}.
     *
     * <p>This method will retrieve and store the material, count, name, lore, custom data,
     * and other properties of the {@code ItemStack} for later retrieval via the corresponding
     * getter methods in this class.</p>
     *
     * @param itemStack the {@code ItemStack} from which to extract data
     * @return this {@code ItemStackDestroyer} instance for method chaining
     */

    public ItemStackDestroyer destroy(ItemStack itemStack) {
        // Get material and materialName string
        this.material = itemStack.material();
        this.materialName = itemStack.material().name();
        // Get count and maxStackSize
        this.count = itemStack.amount();
        this.maxStackSize = itemStack.maxStackSize();

        // Get name as both String list and component
        if (itemStack.get(ItemComponent.ITEM_NAME) != null){
            this.nameComponent = itemStack.get(ItemComponent.ITEM_NAME);
            this.nameStringList = TextUtils.convertComponentToString(itemStack.get(ItemComponent.ITEM_NAME));
        }

        // Get lore as both list of Components and List of List of Strings
        if (itemStack.get(ItemComponent.LORE) != null) {
            this.loreComponent = itemStack.get(ItemComponent.LORE);
            this.loreStringList = TextUtils.convertListComponentToListStringComponent(Objects.requireNonNull(itemStack.get(ItemComponent.LORE)));
        }
        // Get if it has a glint
        this.glint = Boolean.TRUE.equals(itemStack.get(ItemComponent.ENCHANTMENT_GLINT_OVERRIDE));

        // Get dye color string
        if (itemStack.get(ItemComponent.DYED_COLOR) != null) {
            this.dyeColor = Objects.requireNonNull(itemStack.get(ItemComponent.DYED_COLOR)).color().toString();
        }
        // Get if showToolTips. ! there due to asking if it has hide_tooltip returning true if show == false
        this.showToolTips = !itemStack.has(ItemComponent.HIDE_TOOLTIP);

        // Extract and set the skin if material is PLAYER_HEAD
        if (itemStack.material() == Material.PLAYER_HEAD) {
            HeadProfile profile = itemStack.get(ItemComponent.PROFILE);
            if (profile != null) {
                playerSkin = profile.skin();

            }
        }

        // Get CustomData
        CustomData data = itemStack.get(ItemComponent.CUSTOM_DATA);
        if (data != null) {
            CompoundBinaryTag customData = data.nbt();
            // Get values
            this.isVanillaItem = customData.getBoolean("isVanillaItem");
            this.function = customData.getString("function");
            this.page = customData.getString("page");
        }

        return this;
    }

    // Getters

    /**
     * Returns the material of the item.
     *
     * @return the {@link Material} of the item
     */

    public Material getMaterial() {
        return material;
    }

    /**
     * Returns the name of the material as a string.
     *
     * @return the material name as a {@link String}
     */

    public String getMaterialName() {
        return materialName;
    }

    /**
     * Returns the count of items in the stack.
     *
     * @return the item count as an {@link Integer}
     */

    public Integer getCount() {
        return count;
    }

    /**
     * Returns the maximum stack size for the item.
     *
     * @return the maximum stack size as an {@link Integer}
     */

    public Integer getMaxStackSize() {
        return maxStackSize;
    }

    /**
     * Returns the name of the item as a list of strings.
     *
     * @return the item name as a {@link List} of {@link String}
     */

    public List<String> getNameStringList() {
        return nameStringList;
    }

    /**
     * Returns the name of the item as a component.
     *
     * @return the item name as a {@link Component}
     */

    public Component getNameComponent() {
        return nameComponent;
    }

    /**
     * Returns the lore of the item as a list of lists of strings.
     *
     * @return the item lore as a {@link List} of {@link List} of {@link String}
     */

    public List<List<String>> getLoreStringList() {
        return loreStringList;
    }

    /**
     * Returns the lore of the item as a list of components.
     *
     * @return the item lore as a {@link List} of {@link Component}
     */

    public List<Component> getLoreComponent() {
        return loreComponent;
    }

    /**
     * Returns whether the item has a glint effect.
     *
     * @return {@code true} if the item has a glint effect, otherwise {@code false}
     */

    public Boolean getGlint() {
        return glint;
    }

    /**
     * Returns the dye color of the item as a string.
     *
     * @return the dye color as a {@link String}
     */

    public String getDyeColor() {
        return dyeColor;
    }

    /**
     * Returns whether the item has tooltips visible.
     *
     * @return {@code true} if tooltips are visible, otherwise {@code false}
     */

    public Boolean getShowToolTips() {
        return showToolTips;
    }

    /**
     * Returns the player skin associated with the item if it's a player head.
     *
     * @return the {@link PlayerSkin} of the item, or {@code null} if not applicable
     */

    public PlayerSkin getPlayerSkin() {
        return playerSkin;
    }

    /**
     * Returns whether the item is a vanilla item. In short, whether the is intended to be used by a player.
     *
     * @return {@code true} if the item is a vanilla item, otherwise {@code false}
     */

    public Boolean getVanillaItem() {
        return isVanillaItem;
    }

    /**
     * Returns the function associated with the item, if any.
     *
     * @return the function as a {@link String}
     */

    public String getFunction() {
        return function;
    }

    /**
     * Returns the page associated with the item, if any.
     *
     * @return the page as a {@link String}
     */

    public String getPage() {
        return page;
    }
}
