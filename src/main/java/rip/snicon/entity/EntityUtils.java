package rip.snicon.entity;

import net.minestom.server.entity.EntityType;

/**
 * Utility class for handling operations related to {@link EntityType}.
 *
 * <p>This class provides static methods to convert between entity types and their corresponding
 * namespace IDs. It also includes default handling for unknown or null values.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * EntityType entityType = EntityUtils.getEntityTypeFromNamespace("minecraft:villager");
 * String namespace = EntityUtils.getNamespaceFromEntityType(EntityType.ZOMBIE);
 * }</pre>
 *
 * @see EntityType
 */

public class EntityUtils {

    /**
     * Retrieves the {@link EntityType} corresponding to the given namespace ID.
     *
     * <p>This method attempts to convert a namespace ID string to its corresponding
     * {@link EntityType} using {@link EntityType#fromNamespaceId(String)}. If the namespace ID does not match
     * any known entity type, the method returns a default of {@link EntityType#VILLAGER}.</p>
     *
     * @param namespaceId the namespace ID of the entity type, e.g., "minecraft:villager"
     * @return the corresponding {@link EntityType}, or {@link EntityType#VILLAGER} if the namespace ID is unknown
     */

    public static EntityType getEntityTypeFromNamespace(String namespaceId) {
        // Use EntityType.fromNamespaceId to get the corresponding EntityType enum
        EntityType entityType = EntityType.fromNamespaceId(namespaceId);

        // Set default entityType (Chosen at random really)
        if (entityType == null) {
            return EntityType.VILLAGER;
        }
        return entityType;
    }

    /**
     * Retrieves the namespace ID corresponding to the given {@link EntityType}.
     *
     * <p>This method converts an {@link EntityType} to its associated namespace ID string.
     * If the entity type is null, it returns the namespace ID for a villager ("minecraft:villager").</p>
     *
     * @param entityType the {@link EntityType} to convert to a namespace ID
     * @return the corresponding namespace ID string, or "minecraft:villager" if the entity type is null
     */

    public static String getNamespaceFromEntityType(EntityType entityType) {
        if (entityType == null) {
            return "minecraft:villager";
        }
        return entityType.namespace().toString();
    }
}
