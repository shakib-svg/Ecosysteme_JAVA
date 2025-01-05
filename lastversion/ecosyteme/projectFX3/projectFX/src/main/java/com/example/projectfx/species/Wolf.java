package com.example.projectfx.species;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import javafx.scene.image.ImageView;

/**
 * The {@code Wolf} class represents a carnivorous species in the ecosystem.
 * Wolves are characterized by their pack instinct, hunting behavior, and ability to efficiently regenerate energy while sleeping.
 * <p>
 * This class extends the {@code Carnivore} class and provides specific behaviors for wolves,
 * including pack-based hunting, movement, reproduction, and sleeping.
 */
public class Wolf extends Carnivore {

    /**
     * Indicates whether the wolf has a pack instinct, influencing its hunting behavior.
     */
    private boolean packInstinct;

    /**
     * Tracks the total number of wolves in the ecosystem.
     */
    private static int nbwolf = 0;

    /**
     * Constructs a new {@code Wolf} species with the specified attributes.
     *
     * @param name         The name of the wolf.
     * @param energy       The initial energy level of the wolf.
     * @param positionn    The position of the wolf in the ecosystem.
     * @param environment  The environment the wolf belongs to.
     * @param packInstinct Whether the wolf has a pack instinct.
     * @param vitesse      The general movement speed of the wolf.
     * @param imageView    The graphical representation of the wolf.
     */
    public Wolf(String name, int energy, Positionn positionn, Environment environment, boolean packInstinct, int vitesse, ImageView imageView) {
        super(name, energy, positionn, environment, 30, vitesse, imageView); // Default attack strength: 30
        this.packInstinct = packInstinct;
        nbwolf++;
    }

    /**
     * Checks if the wolf has a pack instinct.
     *
     * @return {@code true} if the wolf has a pack instinct, {@code false} otherwise.
     */
    public boolean hasPackInstinct() {
        return packInstinct;
    }

    /**
     * Sets whether the wolf has a pack instinct.
     *
     * @param packInstinct {@code true} to enable pack instinct, {@code false} otherwise.
     */
    public void setPackInstinct(boolean packInstinct) {
        this.packInstinct = packInstinct;
    }

    /**
     * Retrieves the total number of wolves in the ecosystem.
     *
     * @return The total number of wolves.
     */
    public static int getNbwolf() {
        return nbwolf;
    }

    /**
     * Sets the total number of wolves in the ecosystem.
     *
     * @param nbwolf The new total number of wolves.
     */
    public static void setNbwolf(int nbwolf) {
        Wolf.nbwolf = nbwolf;
    }

    /**
     * Handles the eating behavior of the wolf.
     * Currently, wolves do not consume any specific food in this implementation.
     *
     * @param food The food object to attempt eating.
     * @return {@code false} as eating is not implemented in this context.
     */
    @Override
    public boolean eat(Object food) {
        return false;
    }

    /**
     * Moves the wolf in a specified direction.
     * Movement consumes energy.
     *
     * @param direction The direction in which the wolf moves (e.g., "north").
     */
    @Override
    public void move(String direction) {
        System.out.println(name + " se déplace en meute vers " + direction + ".");
        consumeEnergy(8); // Moving consumes 8 energy units
    }

    /**
     * Handles the reproduction behavior of the wolf.
     * Reproduction increases the total number of wolves in the ecosystem.
     */
    @Override
    public void reproduce() {
        System.out.println(name + " se reproduit en meute, augmentant la taille du groupe.");
        if (canReproduce(nbwolf)) {
            nbwolf++;
            System.out.println(name + " (wolfs) has reproduced. Total wolves: " + nbwolf);
        } else {
            System.out.println(name + " (wolfs) cannot reproduce due to insufficient numbers.");
        }
    }

    /**
     * Handles the hunting behavior of the wolf.
     * Hunting behavior varies based on whether the wolf hunts alone or with its pack.
     *
     * @param prey The prey species the wolf is hunting.
     * @return {@code true} if the prey was successfully hunted, {@code false} otherwise.
     */
    @Override
    public boolean hunt(Species prey) {
        if (packInstinct) {
            System.out.println(name + " chasse " + prey.getName() + " avec l'aide de sa meute.");
        } else {
            System.out.println(name + " chasse " + prey.getName() + " en solitaire.");
        }

        boolean success = super.hunt(prey); // General hunting logic from Carnivore
        if (success) {
            System.out.println(name + " a capturé " + prey.getName() + " l'énergie est : " + this.getEnergy());
        } else {
            System.out.println(name + " n'a pas pu capturer " + prey.getName());
        }

        return success;
    }

    /**
     * Puts the wolf to sleep, restoring energy.
     */
    @Override
    public void sleep() {
        if (!sleeping) {
            sleeping = true;
            energy += 20; // Wolves regenerate energy efficiently while sleeping
            System.out.println(name + " (Wolf) is now sleeping. Energy: " + energy);
        } else {
            System.out.println(name + " (Wolf) is already sleeping.");
        }
    }

    /**
     * Wakes the wolf from sleep.
     */
    @Override
    public void wakeUp() {
        if (sleeping) {
            sleeping = false;
            System.out.println(name + " (Wolf) has woken up.");
        } else {
            System.out.println(name + " (Wolf) is already awake.");
        }
    }

    /**
     * Checks if the wolf is currently sleeping.
     *
     * @return {@code true} if the wolf is sleeping, {@code false} otherwise.
     */
    @Override
    public boolean isSleeping() {
        return sleeping;
    }
}
