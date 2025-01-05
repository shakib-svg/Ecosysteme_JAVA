package com.example.projectfx.species;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import com.example.projectfx.resources.Fruits;
import javafx.scene.image.ImageView;

/**
 * The {@code Crow} class represents an omnivorous species in the ecosystem.
 * Crows are characterized by their ability to consume both fruits and other species, their swift movement, and their grouping nature.
 * <p>
 * This class extends the {@code Omnivore} class and provides specific behaviors for crows, including eating, reproducing, and sleeping.
 */
public class Crow extends Omnivore {

    /**
     * Tracks the total number of crows in the ecosystem.
     */
    private static int nbCrows = 0;

    /**
     * The feather color of the crow.
     */
    private String featherColor;

    /**
     * Constructs a new {@code Crow} species with the specified attributes.
     *
     * @param name         The name of the crow.
     * @param energy       The initial energy level of the crow.
     * @param positionn    The position of the crow in the ecosystem.
     * @param environment  The environment the crow belongs to.
     * @param vitesse      The speed of the crow.
     * @param featherColor The feather color of the crow.
     * @param imageView    The graphical representation of the crow.
     */
    public Crow(String name, int energy, Positionn positionn, Environment environment, int vitesse, String featherColor, ImageView imageView) {
        super(name, energy, positionn, environment, vitesse, imageView);
        this.featherColor = featherColor;
        nbCrows++;
    }

    /**
     * Retrieves the feather color of the crow.
     *
     * @return The feather color of the crow.
     */
    public String getFeatherColor() {
        return featherColor;
    }

    /**
     * Sets the feather color of the crow.
     *
     * @param featherColor The new feather color.
     */
    public void setFeatherColor(String featherColor) {
        this.featherColor = featherColor;
    }

    /**
     * Retrieves the total number of crows in the ecosystem.
     *
     * @return The total number of crows.
     */
    public static int getNbCrows() {
        return nbCrows;
    }

    /**
     * Sets the total number of crows in the ecosystem.
     *
     * @param nbCrows The new total number of crows.
     */
    public static void setNbCrows(int nbCrows) {
        Crow.nbCrows = nbCrows;
    }

    /**
     * Moves the crow in a specified direction.
     * Movement consumes energy.
     *
     * @param direction The direction in which the crow moves (e.g., "north", "east").
     */
    @Override
    public void move(String direction) {
        if (isSleeping()) {
            System.out.println(name + " is sleeping and cannot move.");
        } else {
            System.out.println(name + " (Crow) flies swiftly towards " + direction + " at speed " + vitesse + " m/s.");
            reduceEnergy(5);
        }
    }

    /**
     * Handles the eating behavior of the crow.
     * Crows can consume both fruits and other species to restore energy.
     *
     * @param food The food to be consumed, which can be a {@code Fruits} or a {@code Species}.
     * @return {@code true} if the food was successfully consumed, {@code false} otherwise.
     */
    @Override
    public boolean eat(Object food) {
        if (food instanceof Fruits) {
            Fruits fruits = (Fruits) food;
            System.out.println(name + " consomme des fruits.");
            fruits.consume(15);
            energy += 15;
            return true;
        } else if (food instanceof Species) {
            Species prey = (Species) food;
            System.out.println(name + " attaque et mange " + prey.getName() + ".");
            if (prey.getEnergy() <= 30) {
                System.out.println(prey.getName() + " est complètement mangé par " + name + ".");
                energy += prey.getEnergy();
                prey.setEnergy(0);
                return true;
            } else {
                System.out.println(prey.getName() + " perd de l'énergie après l'attaque.");
                prey.setEnergy(prey.getEnergy() - 30);
                energy += 30;
                return false;
            }
        } else {
            System.out.println(name + " ne peut pas manger cet aliment.");
        }
        return false;
    }

    /**
     * Handles the reproduction behavior of the crow.
     * Reproduction increases the total number of crows.
     */
    @Override
    public void reproduce() {
        System.out.println(name + " (Crow) is reproducing.");
        if (canReproduce(nbCrows)) {
            nbCrows++;
            System.out.println(name + " has successfully reproduced. Total crows: " + nbCrows);
        } else {
            System.out.println(name + " cannot reproduce due to insufficient numbers.");
        }
    }

    /**
     * Puts the crow to sleep, restoring energy.
     */
    @Override
    public void sleep() {
        if (!isSleeping()) {
            sleeping = true;
            energy += 20; // Sleeping restores energy
            System.out.println(name + " (Crow) is now sleeping. Energy: " + energy);
        } else {
            System.out.println(name + " (Crow) is already sleeping.");
        }
    }

    /**
     * Wakes the crow from sleep.
     */
    @Override
    public void wakeUp() {
        if (isSleeping()) {
            sleeping = false;
            System.out.println(name + " (Crow) has woken up.");
        } else {
            System.out.println(name + " (Crow) is already awake.");
        }
    }
}
