package com.github.sniconmc.utils.text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for handling text components and MiniMessage serialization/deserialization.
 *
 * <p>This class provides methods for converting between lists of MiniMessage-formatted strings
 * and Adventure {@link Component} objects. This is particularly useful in Minecraft server
 * development, where text is often formatted in a custom way.</p>
 *
 * @see Component
 * @see MiniMessage
 * @see TextComponent
 * @see <a href="https://docs.adventure.kyori.net/">Adventure Documentation</a>
 *
 * @author znopp
 * @author Wi1helm
 */
public class TextUtils {


    /**
     * Converts a list of MiniMessage-formatted strings to a single {@link Component}.
     *
     * <p>This method processes each string in the provided list, deserializing it from
     * MiniMessage format to an Adventure {@link Component}. All components are then combined
     * into a single component.</p>
     *
     * <p>The method applies a default format of reset, non-italic, and gray color to each text component.</p>
     *
     * @param minimessages A list of strings formatted using MiniMessage syntax.
     * @return A {@link Component} representing the combined text.
     *
     * @author znopp
     */

    public static Component convertStringListToComponent(List<String> minimessages) {
        Component comp = Component.empty();
        MiniMessage miniMessage = MiniMessage.miniMessage();

        for (String text : minimessages) {

            // Deserialize the MiniMessage formatted text
            String newText = "<reset><italic:false><#818181>" + text + "<reset>";
            Component textComponent = miniMessage.deserialize(newText);
            comp = comp.append(textComponent);
        }
        return comp;
    }

    /**
     * Converts an Adventure {@link Component} to a list of MiniMessage-formatted strings.
     *
     * <p>This method serializes the provided {@link Component} to a string using MiniMessage
     * syntax and adds it to a list. This is useful for converting complex text components
     * back into a format that can be easily stored or transmitted as plain text.</p>
     *
     * @param component The {@link Component} to convert to a string.
     * @return A list containing the MiniMessage representation of the component.
     *
     * @author znopp
     * @author Wi1helm
     */

    public static List<String> convertComponentToStringList(Component component) {
        List<String> strings = new ArrayList<>();
        strings.add(MiniMessage.miniMessage().serialize(component));
        return strings;
    }


    /**
     * Converts a list of lists of MiniMessage-formatted strings to a list of {@link Component}.
     *
     * <p>This method processes each list in the provided list, deserializing it from
     * MiniMessage format to an Adventure {@link Component}. All components are then combined
     * into a single component and added into the list of components. This method is primary used for lore</p>
     *
     * <p>The method applies a default format of reset, non-italic, and gray color to each text component.</p>
     *
     * @param list A list of strings formatted using MiniMessage syntax.
     * @return A {@link Component} representing the combined text.
     *
     * @author znopp
     */

    public static List<Component> convertStringListListToListComponent(List<List<String>> list) {
        List<Component> components = new ArrayList<>();

        for (List<String> strings : list) {
            components.add(convertStringListToComponent(strings));
        }
        return components;
    }

    /**
     * Converts a list of {@link Component} objects into a list of lists of strings.
     *
     * <p>This method iterates through a list of {@link Component} objects and converts each
     * component into a list of strings using the {@link #convertComponentToStringList(Component)} method.
     * The resulting list contains a list of strings for each component.</p>
     *
     * <p>The conversion is useful for cases where you need to represent complex text components
     * as simple strings for easier processing, display, or storage.</p>
     *
     * @param components the list of {@link Component} objects to be converted
     * @return a list of lists of strings, where each inner list represents the string representation of a {@link Component}
     */

    public static List<List<String>> convertComponentListToListStringComponent(List<Component> components) {
        List<List<String>> list = new ArrayList<>();

        for (Component component : components) {
            list.add(convertComponentToStringList(component));
        }

        return list;
    }
    
    public static Component convertStringToComponent(String text) {
        return MiniMessage.miniMessage().deserialize(text);
    }
    
    public static String convertComponentToString(Component component) {
        return MiniMessage.miniMessage().serialize(component);
    }
}
