package com.example.projectfx.interfaces;

/**
 * Interface representing entities that can be consumed.
 */
public interface Consumable {
    /**
     * Consumes the specified quantity of the entity.
     *
     * @param amount The quantity to be consumed.
     */
    void consume(int amount);

    /**
     * Checks if the entity is fully consumed (i.e., no quantity left).
     *
     * @return True if the entity is fully consumed, false otherwise.
     */
    boolean isConsumed();
}