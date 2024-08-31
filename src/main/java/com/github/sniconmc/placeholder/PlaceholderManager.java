package com.github.sniconmc.placeholder;

import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code PlaceholderManager} class is responsible for managing placeholders specific to individual players.
 * <p>
 * Placeholders are represented as key-value pairs, where the key is a string representing the placeholder's name,
 * and the value is the associated string value. These placeholders are stored in a nested map structure:
 * <ul>
 *   <li>The outer map is keyed by the {@code Player} object.</li>
 *   <li>The inner map is keyed by the placeholder name (a {@code String}), with its corresponding value (also a {@code String}).</li>
 * </ul>
 * This class provides functionality to add, update, retrieve, and remove placeholders for players.
 * It ensures that players can have multiple placeholders without overwriting existing data unintentionally.
 */

public class PlaceholderManager {

    /**
     * A static map that holds placeholders for each player. The outer map is keyed by {@code Player},
     * and the inner map is keyed by the placeholder name (a {@code String}) with its corresponding value.
     * <p>
     * This map is initialized as an empty {@code HashMap} and is shared across all instances of the class.
     * </p>
     */

    private static final Map<Player, Map<String, String>> playerPlaceholders = new HashMap<>();

    /**
     * Adds a set of placeholders to the specified player without overwriting existing ones.
     * <p>
     * This method merges the provided placeholders with the existing ones for the player.
     * If a placeholder already exists in the player's map, its value is updated with the new value provided.
     * If a placeholder does not exist, it is added to the player's map.
     * </p>
     *
     * @param player the {@code Player} object to whom the placeholders should be added.
     *               This parameter must not be {@code null}.
     * @param listOfPlaceholders a {@code Map<String, String>} containing placeholder-value pairs to be associated with the player.
     *                           This map must not be {@code null} but can be empty.
     * @throws NullPointerException if either {@code player} or {@code listOfPlaceholders} is {@code null}.
     *                              Ensure that both parameters are not {@code null} before calling this method.
     */

    public static void addPlaceholdersToPlayer(@NotNull Player player, @NotNull Map<String, String> listOfPlaceholders) {
        // Retrieve the existing placeholder map for the player, or create a new one if it doesn't exist
        Map<String, String> existingPlaceholders = playerPlaceholders.getOrDefault(player, new HashMap<>());

        // Add all the new placeholders to the existing map
        existingPlaceholders.putAll(listOfPlaceholders);

        // Put the updated map back into the playerPlaceholders map
        playerPlaceholders.put(player, existingPlaceholders);
    }

    /**
     * Sets or updates a single placeholder for the specified player.
     * <p>
     * If the player already has a map of placeholders, this method updates the specified placeholder with the new value.
     * If the player does not have any placeholders yet, a new map is created, and the placeholder is added to it.
     * </p>
     *
     * @param player the {@code Player} object for whom the placeholder is being set.
     *               This parameter must not be {@code null}.
     * @param placeholder the placeholder key, represented as a {@code String}.
     *                    This parameter must not be {@code null}.
     * @param value the value associated with the placeholder, represented as a {@code String}.
     *              This parameter must not be {@code null}.
     * @throws NullPointerException if {@code player}, {@code placeholder}, or {@code value} is {@code null}.
     *                              Ensure that all parameters are not {@code null} before calling this method.
     */

    public static void setPlaceholderToPlayer(@NotNull Player player, @NotNull String placeholder, @NotNull String value) {
        // Retrieve the existing placeholder map for the player, or create a new one if it doesn't exist
        Map<String, String> placeholderMap = playerPlaceholders.getOrDefault(player, new HashMap<>());

        // Update the placeholder value
        placeholderMap.put(placeholder, value);

        // Put the updated map back into the playerPlaceholders map
        playerPlaceholders.put(player, placeholderMap);
    }

    /**
     * Retrieves the value of a specific placeholder for the specified player.
     * <p>
     * This method looks up the player's map of placeholders and returns the value associated with the given key.
     * If the player does not have any placeholders, or if the specified placeholder key is not found, {@code null} is returned.
     * </p>
     *
     * @param player the {@code Player} object whose placeholder value is to be retrieved.
     *               This parameter must not be {@code null}.
     * @param placeholder the key of the placeholder whose value is to be retrieved, represented as a {@code String}.
     *                    This parameter must not be {@code null}.
     * @return the value associated with the specified placeholder key, or {@code null} if the placeholder is not found.
     * @throws NullPointerException if {@code player} or {@code placeholder} is {@code null}.
     *                              Ensure that both parameters are not {@code null} before calling this method.
     */

    public static String getPlaceholderForPlayer(@NotNull Player player, @NotNull String placeholder) {
        // Retrieve the existing placeholder map for the player, or create a new one if it doesn't exist
        Map<String, String> placeholderMap = playerPlaceholders.getOrDefault(player, new HashMap<>());

        // Return the value
        return placeholderMap.get(placeholder);
    }

    /**
     * Retrieves the entire map of player placeholders.
     * <p>
     * This method returns the full map that associates each player with their respective map of placeholders.
     * The returned map is a reference to the internal map used by the class, meaning changes to this map will affect
     * the internal data.
     * </p>
     *
     * @return a {@code Map<Player, Map<String, String>>} where each key is a {@code Player} and the value is another map
     *         containing placeholder-value pairs for that player.
     *         The map is never {@code null}, but it may be empty if no placeholders have been set.
     */

    public static Map<Player, Map<String, String>> getPlayerPlaceholders() {
        return playerPlaceholders;
    }

    /**
     * Removes a specific placeholder from the specified player's placeholder map.
     * <p>
     * This method attempts to remove a placeholder identified by the provided key from the player's
     * map of placeholders. If the player does not exist in the main map {@code playerPlaceholders},
     * or if the player's map does not contain the specified placeholder key, the method will not
     * perform any removal operation and will exit silently.
     * </p>
     * <p>
     * After the removal of the placeholder:
     * <ul>
     *   <li>If the player's map of placeholders becomes empty, the player entry is removed from the
     *   main {@code playerPlaceholders} map entirely. This helps to prevent unnecessary storage of
     *   players with no associated placeholders.</li>
     *   <li>If the placeholder key exists and is successfully removed, the player's map of
     *   placeholders is updated in the {@code playerPlaceholders} map.</li>
     *   <li>If the placeholder key does not exist in the player's map, no changes are made.</li>
     * </ul>
     *
     *
     * @param player the {@code Player} object from whom the placeholder should be removed.
     *               If the player is not found in the main map, no action is taken.
     * @param placeholder the placeholder key that needs to be removed from the player's map.
     *                    If this key is not found in the player's map, no action is taken.
     *
     * @throws NullPointerException if the {@code player} or {@code placeholder} is {@code null}.
     *                              It's important to ensure that neither the player nor the
     *                              placeholder key is {@code null} before calling this method.
     *
     * @see #addPlaceholdersToPlayer(Player, Map)
     * @see #setPlaceholderToPlayer(Player, String, String)
     * @see #getPlaceholderForPlayer(Player, String)
     */

    public static void removePlaceholderForPlayer(@NotNull Player player, @NotNull String placeholder) {
        // Retrieve the existing placeholder map for the player
        Map<String, String> existingPlaceholders = playerPlaceholders.get(player);

        // If the map exists and contains the placeholder, remove it
        if (existingPlaceholders != null && existingPlaceholders.containsKey(placeholder)) {
            existingPlaceholders.remove(placeholder);

            // If the map is now empty, remove the player from the main map
            if (existingPlaceholders.isEmpty()) {
                playerPlaceholders.remove(player);
            } else {
                // Put the updated map back into the playerPlaceholders map
                playerPlaceholders.put(player, existingPlaceholders);
            }
        }
    }
}
