package com.example.projectfx.interfaces;

/**
 * The {@code Sleepable} interface represents entities capable of transitioning
 * between sleeping and waking states. It defines methods to manage the sleep state
 * and check if the entity is currently sleeping.
 * <p>
 * Classes implementing this interface should provide logic for managing the
 * entity's state and handling actions triggered by sleep or wake-up transitions.
 */
public interface Sleepable {

    /**
     * Puts the entity into a sleeping state.
     * Implementing classes should define any state changes or behaviors
     * associated with the entity entering sleep.
     */
    void sleep();

    /**
     * Wakes the entity up from its sleeping state.
     * Implementing classes should define any state changes or behaviors
     * associated with the entity transitioning to an awake state.
     */
    void wakeUp();

    /**
     * Checks if the entity is currently in a sleeping state.
     *
     * @return {@code true} if the entity is sleeping, {@code false} otherwise.
     */
    boolean isSleeping();
}
