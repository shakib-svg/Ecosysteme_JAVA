package com.example.projectfx.species;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import com.example.projectfx.resources.Grass;
import javafx.scene.image.ImageView;

/**
 * The {@code Rabbit} class represents a herbivorous species in the ecosystem.
 * Rabbits are characterized by their speed, grouping instincts, and rapid reproduction.
 * <p>
 * This class extends the {@code Herbivore} class and provides specific behaviors for rabbits,
 * including movement, eating grass, reproduction, and detecting predators.
 */
public class Rabbit extends Herbivore {

    /**
     * The speed of the rabbit, measured in meters per second (m/s).
     */
    private double speed;

    /**
     * Tracks the total number of rabbits in the ecosystem.
     */
    private static int nbrabbit = 0;

    /**
     * Constructs a new {@code Rabbit} species with the specified attributes.
     *
     * @param name        The name of the rabbit.
     * @param energy      The initial energy level of the rabbit.
     * @param positionn   The position of the rabbit in the ecosystem.
     * @param environment The environment the rabbit belongs to.
     * @param speed       The speed of the rabbit in m/s.
     * @param vitesse     The general movement speed of the rabbit.
     * @param imageView   The graphical representation of the rabbit.
     */
    public Rabbit(String name, int energy, Positionn positionn, Environment environment, double speed, int vitesse, ImageView imageView) {
        super(name, energy, positionn, environment, true, vitesse, imageView); // Rabbits typically have grouping instinct
        this.speed = speed;
        nbrabbit++;
    }

    /**
     * Retrieves the speed of the rabbit.
     *
     * @return The speed of the rabbit in m/s.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of the rabbit.
     *
     * @param speed The new speed value in m/s.
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Moves the rabbit in a specified direction.
     * Movement consumes energy.
     *
     * @param direction The direction in which the rabbit moves (e.g., "north").
     */
    @Override
    public void move(String direction) {
        System.out.println(name + " saute rapidement vers " + direction + " à une vitesse de " + speed + " m/s.");
        consumeEnergy(5);
    }

    /**
     * Handles the eating behavior of the rabbit.
     * Rabbits consume grass to restore energy.
     *
     * @param food The food to be consumed, which must be an instance of {@code Grass}.
     * @return {@code true} if the food was successfully consumed, {@code false} otherwise.
     */
    @Override
    public boolean eat(Object food) {
        if (food instanceof Grass) {
            Grass grass = (Grass) food;
            System.out.println(name + " consomme du grass.");
            grass.consume(20);
            energy += 20;
            return true;
        } else {
            System.out.println(name + " ne peut pas manger cet aliment.");
        }
        return false;
    }

    /**
     * Handles the reproduction behavior of the rabbit.
     * Reproduction increases the total number of rabbits in the ecosystem.
     */
    @Override
    public void reproduce() {
        System.out.println(name + " se reproduit rapidement et donne naissance à de nouveaux lapereaux.");
        if (canReproduce(nbrabbit)) {
            nbrabbit++;
            System.out.println(name + " (rabbits) has reproduced. Total rabbits: " + nbrabbit);
        } else {
            System.out.println(name + " (rabbits) cannot reproduce due to insufficient numbers.");
        }
    }

    /**
     * Retrieves the total number of rabbits in the ecosystem.
     *
     * @return The total number of rabbits.
     */
    public static int getNbrabbit() {
        return nbrabbit;
    }

    /**
     * Sets the total number of rabbits in the ecosystem.
     *
     * @param nbrabbit The new total number of rabbits.
     */
    public static void setNbrabbit(int nbrabbit) {
        Rabbit.nbrabbit = nbrabbit;
    }

    /**
     * Detects predators in the vicinity of the rabbit.
     * If a predator is detected, the rabbit attempts to flee.
     */
    @Override
    public void detecterPredateur() {
        System.out.println(name + " cherche des prédateurs.");
        if (Math.random() < 0.7) { // 70% chance to detect a predator
            System.out.println(name + " détecte un prédateur et s'enfuit !");
            flee();
        } else {
            System.out.println(name + " ne détecte aucun prédateur pour l'instant.");
        }
    }

    /**
     * Puts the rabbit to sleep, restoring energy.
     */
    @Override
    public void sleep() {
        if (!isSleeping()) {
            sleeping = true;
            energy += 20; // Sleeping restores energy
            System.out.println(name + " (rabbit) is now sleeping. Energy: " + energy);
        } else {
            System.out.println(name + " (rabbit) is already sleeping.");
        }
    }

    /**
     * Wakes the rabbit from sleep.
     */
    @Override
    public void wakeUp() {
        if (isSleeping()) {
            sleeping = false;
            System.out.println(name + " (rabbit) has woken up.");
        } else {
            System.out.println(name + " (rabbit) is already awake.");
        }
    }

    /**
     * Checks if the rabbit is currently sleeping.
     *
     * @return {@code true} if the rabbit is sleeping, {@code false} otherwise.
     */
    @Override
    public boolean isSleeping() {
        return sleeping;
    }
}
