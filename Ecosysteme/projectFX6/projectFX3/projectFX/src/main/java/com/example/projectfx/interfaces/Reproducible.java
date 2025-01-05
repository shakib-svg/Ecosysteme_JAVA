package com.example.projectfx.interfaces;

/**
 * Interface representing entities capable of reproduction.
 */
public interface Reproducible {
    /**
     * Triggers the reproduction process for the entity.
     */
    void reproduce();

    /**
     * Checks if the entity meets the conditions to reproduce.
     *
     * @return True if reproduction conditions are met, false otherwise.
     */
    boolean canReproduce(int a);
}