package com.example.projectfx.species;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import com.example.projectfx.resources.Algae;
import javafx.scene.image.ImageView;

/**
 * The {@code Fish} class represents a marine herbivorous species in the ecosystem.
 * Fish are characterized by their ability to consume algae, swim swiftly, and detect predators.
 * <p>
 * This class extends the {@code MarineHerbivore} class and provides specific behaviors for fish,
 * including eating, movement, reproduction, and sleeping.
 */
public class Fish extends MarineHerbivore {

    /**
     * The speed of the fish while swimming, measured in meters per second (m/s).
     */
    private double swimSpeed;

    /**
     * Tracks the total number of fish in the ecosystem.
     */
    private static int nbfish = 0;

    /**
     * Constructs a new {@code Fish} species with the specified attributes.
     *
     * @param name        The name of the fish.
     * @param energy      The initial energy level of the fish.
     * @param positionn   The position of the fish in the ecosystem.
     * @param environment The environment the fish belongs to.
     * @param swimSpeed   The swimming speed of the fish in m/s.
     * @param vitesse     The general movement speed of the fish.
     * @param imageView   The graphical representation of the fish.
     */
    public Fish(String name, int energy, Positionn positionn, Environment environment, double swimSpeed, int vitesse, ImageView imageView) {
        super(name, energy, positionn, environment, vitesse, imageView);
        this.swimSpeed = swimSpeed;
        nbfish++;
    }

    /**
     * Retrieves the total number of fish in the ecosystem.
     *
     * @return The total number of fish.
     */
    public static int getNbfish() {
        return nbfish;
    }

    /**
     * Sets the total number of fish in the ecosystem.
     *
     * @param nbfish The new total number of fish.
     */
    public static void setNbfish(int nbfish) {
        Fish.nbfish = nbfish;
    }

    /**
     * Retrieves the swimming speed of the fish.
     *
     * @return The swimming speed of the fish in m/s.
     */
    public double getSwimSpeed() {
        return swimSpeed;
    }

    /**
     * Sets the swimming speed of the fish.
     *
     * @param swimSpeed The new swimming speed in m/s.
     */
    public void setSwimSpeed(double swimSpeed) {
        this.swimSpeed = swimSpeed;
    }

    /**
     * Handles the eating behavior of the fish.
     * Fish consume algae to restore energy.
     *
     * @param food The food to be consumed, which must be an instance of {@code Algae}.
     * @return {@code true} if the food was successfully consumed, {@code false} otherwise.
     */
    @Override
    public boolean eat(Object food) {
        if (food instanceof Algae) {
            Algae algae = (Algae) food;
            System.out.println(name + " consomme des algues.");
            algae.consume(20);
            energy += 20;
            return true;
        } else {
            System.out.println(name + " ne peut pas manger cet aliment.");
        }
        return false;
    }

    /**
     * Moves the fish in a specified direction.
     * Movement consumes energy.
     *
     * @param direction The direction in which the fish swims (e.g., "north", "away from danger").
     */
    @Override
    public void move(String direction) {
        System.out.println(name + " nage rapidement vers " + direction + " à une vitesse de " + swimSpeed + " m/s.");
        consumeEnergy(5);
    }

    /**
     * Handles the reproduction behavior of the fish.
     * Reproduction increases the total number of fish in the ecosystem.
     */
    @Override
    public void reproduce() {
        System.out.println(name + " pond des œufs dans l'océan.");
        if (canReproduce(nbfish)) {
            nbfish++;
            System.out.println(name + " (fishes) has reproduced. Total fishes: " + nbfish);
        } else {
            System.out.println(name + " (fishes) cannot reproduce due to insufficient numbers.");
        }
    }

    /**
     * Detects predators in the vicinity of the fish.
     * If a predator is detected, the fish attempts to swim away.
     */
    @Override
    public void detecterPredateur() {
        System.out.println(name + " cherche des prédateurs dans l'eau.");
        if (Math.random() < 0.6) { // 60% chance to detect a predator
            System.out.println(name + " détecte un prédateur et tente de fuir !");
            swim("loin du danger");
        } else {
            System.out.println(name + " ne détecte aucun prédateur pour l'instant.");
        }
    }

    /**
     * Puts the fish to sleep, restoring energy.
     */
    @Override
    public void sleep() {
        if (!sleeping) {
            sleeping = true;
            energy += 5; // Fish regenerate less energy while sleeping
            System.out.println(name + " (Fish) is now sleeping. Energy: " + energy);
        } else {
            System.out.println(name + " (Fish) is already sleeping.");
        }
    }

    /**
     * Wakes the fish from sleep.
     */
    @Override
    public void wakeUp() {
        if (sleeping) {
            sleeping = false;
            System.out.println(name + " (Fish) has woken up.");
        } else {
            System.out.println(name + " (Fish) is already awake.");
        }
    }

    /**
     * Checks if the fish is currently sleeping.
     *
     * @return {@code true} if the fish is sleeping, {@code false} otherwise.
     */
    @Override
    public boolean isSleeping() {
        return sleeping;
    }
}
