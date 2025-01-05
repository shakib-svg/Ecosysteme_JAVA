package com.example.projectfx.species;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import com.example.projectfx.resources.Fruits;
import javafx.scene.image.ImageView;

/**
 * The {@code Canari} class represents a canary species in the ecosystem.
 * Canaries are herbivorous and characterized by their grouping instincts,
 * quick movement, and ability to detect predators.
 * <p>
 * This class extends the {@code Herbivore} class and provides specific behaviors for canaries,
 * including eating fruits, reproduction, and predator detection.
 */
public class Canari extends Herbivore {

    /**
     * The feather color of the canary.
     */
    private String featherColor;

    /**
     * Tracks the total number of canaries in the ecosystem.
     */
    private static int nbCanaries = 0;

    /**
     * Constructs a new {@code Canari} species with the specified attributes.
     *
     * @param name         The name of the canary.
     * @param energy       The initial energy level of the canary.
     * @param positionn    The position of the canary in the ecosystem.
     * @param environment  The environment the canary belongs to.
     * @param vitesse      The speed of the canary.
     * @param featherColor The feather color of the canary.
     * @param imageView    The graphical representation of the canary.
     */
    public Canari(String name, int energy, Positionn positionn, Environment environment, int vitesse, String featherColor, ImageView imageView) {
        super(name, energy, positionn, environment, true, vitesse, imageView); // Canaries typically have a grouping instinct
        this.featherColor = featherColor;
        nbCanaries++;
    }

    /**
     * Retrieves the feather color of the canary.
     *
     * @return The feather color of the canary.
     */
    public String getFeatherColor() {
        return featherColor;
    }

    /**
     * Sets the feather color of the canary.
     *
     * @param featherColor The new feather color.
     */
    public void setFeatherColor(String featherColor) {
        this.featherColor = featherColor;
    }

    /**
     * Retrieves the total number of canaries in the ecosystem.
     *
     * @return The total number of canaries.
     */
    public static int getNbCanaries() {
        return nbCanaries;
    }

    /**
     * Sets the total number of canaries in the ecosystem.
     *
     * @param nbCanaries The new total number of canaries.
     */
    public static void setNbCanaries(int nbCanaries) {
        Canari.nbCanaries = nbCanaries;
    }

    /**
     * Moves the canary in a specified direction.
     * Movement consumes energy.
     *
     * @param direction The direction in which the canary moves (e.g., "north", "east").
     */
    @Override
    public void move(String direction) {
        if (isSleeping()) {
            System.out.println(name + " is sleeping and cannot move.");
        } else {
            System.out.println(name + " (Canari) flutters quickly towards " + direction + " at speed " + vitesse + " m/s.");
            reduceEnergy(5);
        }
    }

    /**
     * Handles the eating behavior of the canary.
     * Canaries consume fruits to restore energy.
     *
     * @param food The food to be consumed, which must be an instance of {@code Fruits}.
     * @return {@code true} if the food was successfully consumed, {@code false} otherwise.
     */
    @Override
    public boolean eat(Object food) {
        if (food instanceof Fruits) {
            Fruits fruits = (Fruits) food;
            System.out.println(name + " consomme des fruits.");
            fruits.consume(20);
            energy += 20;
            return true;
        } else {
            System.out.println(name + " ne peut pas manger cet aliment.");
        }
        return false;
    }

    /**
     * Handles the reproduction behavior of the canary.
     * Reproduction increases the total number of canaries.
     */
    @Override
    public void reproduce() {
        System.out.println(name + " (Canari) is reproducing.");
        if (canReproduce(nbCanaries)) {
            nbCanaries++;
            System.out.println(name + " has successfully reproduced. Total canaries: " + nbCanaries);
        } else {
            System.out.println(name + " cannot reproduce due to insufficient numbers.");
        }
    }

    /**
     * Detects predators in the vicinity of the canary.
     * If a predator is detected, the canary attempts to flee.
     */
    @Override
    public void detecterPredateur() {
        System.out.println(name + " (Canari) is scanning the area for predators.");
        if (Math.random() < 0.6) { // 60% chance to detect a predator
            System.out.println(name + " (Canari) detects a predator and flies away!");
            flee();
        } else {
            System.out.println(name + " (Canari) does not detect any predators for now.");
        }
    }

    /**
     * Makes the canary flee from a detected predator.
     * Fleeing consumes significant energy.
     */
    @Override
    public void flee() {
        System.out.println(name + " (Canari) flutters away to escape danger!");
        reduceEnergy(10);
    }

    /**
     * Puts the canary to sleep, restoring energy.
     */
    @Override
    public void sleep() {
        if (!isSleeping()) {
            sleeping = true;
            energy += 15; // Sleeping restores energy
            System.out.println(name + " (Canari) is now sleeping. Energy: " + energy);
        } else {
            System.out.println(name + " (Canari) is already sleeping.");
        }
    }

    /**
     * Wakes the canary from sleep.
     */
    @Override
    public void wakeUp() {
        if (isSleeping()) {
            sleeping = false;
            System.out.println(name + " (Canari) has woken up.");
        } else {
            System.out.println(name + " (Canari) is already awake.");
        }
    }
}
