package com.example.projectfx.species;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import javafx.scene.image.ImageView;

/**
 * The {@code Human} class represents a human species in the ecosystem.
 * Humans are characterized by their ability to defend against attacks, interact with other species,
 * and their strategic behavior in the ecosystem.
 * <p>
 * This class extends the {@code Species} class and provides specific behaviors for humans,
 * including movement, reproduction, defense, and interaction with other species.
 */
public class Human extends Species {

    /**
     * The defense power of the human, determining their ability to defend against attacks.
     */
    private int defensePower;

    /**
     * Tracks the total number of humans in the ecosystem.
     */
    private static int nbhuman = 0;

    /**
     * Constructs a new {@code Human} species with the specified attributes.
     *
     * @param name          The name of the human.
     * @param energy        The initial energy level of the human.
     * @param positionn     The position of the human in the ecosystem.
     * @param environment   The environment the human belongs to.
     * @param defensePower  The defense power of the human.
     * @param vitesse       The movement speed of the human.
     * @param imageView     The graphical representation of the human.
     */
    public Human(String name, int energy, Positionn positionn, Environment environment, int defensePower, int vitesse, ImageView imageView) {
        super(name, energy, positionn, environment, vitesse, imageView);
        this.defensePower = defensePower;
        nbhuman++;
    }

    /**
     * Retrieves the total number of humans in the ecosystem.
     *
     * @return The total number of humans.
     */
    public static int getNbhuman() {
        return nbhuman;
    }

    /**
     * Sets the total number of humans in the ecosystem.
     *
     * @param nbhuman The new total number of humans.
     */
    public static void setNbhuman(int nbhuman) {
        Human.nbhuman = nbhuman;
    }

    /**
     * Retrieves the defense power of the human.
     *
     * @return The defense power of the human.
     */
    public int getDefensePower() {
        return defensePower;
    }

    /**
     * Sets the defense power of the human.
     *
     * @param defensePower The new defense power value.
     */
    public void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }

    /**
     * Handles the eating behavior of the human.
     * Currently, humans do not consume any specific food in this implementation.
     *
     * @param food The food object to attempt eating.
     * @return {@code false} as humans do not eat in this context.
     */
    @Override
    public boolean eat(Object food) {
        return false;
    }

    /**
     * Moves the human in a specified direction.
     * Movement consumes energy.
     *
     * @param direction The direction in which the human moves (e.g., "north").
     */
    @Override
    public void move(String direction) {
        System.out.println(name + " marche vers " + direction + ".");
        consumeEnergy(5);
    }

    /**
     * Handles the reproduction behavior of the human.
     * Reproduction increases the total number of humans in the ecosystem.
     */
    @Override
    public void reproduce() {
        System.out.println(name + " participe à l'extension de la communauté humaine.");
        if (canReproduce(nbhuman)) {
            nbhuman++;
            System.out.println(name + " (humans) has reproduced. Total humans: " + nbhuman);
        } else {
            System.out.println(name + " (humans) cannot reproduce due to insufficient numbers.");
        }
    }

    /**
     * Allows the human to defend against an attack.
     *
     * @param attackPower The power of the attack to defend against.
     * @return {@code true} if the defense was successful, {@code false} otherwise.
     */
    public boolean defend(int attackPower) {
        System.out.println(name + " tente de se défendre contre une attaque.");
        if (defensePower >= attackPower) {
            System.out.println(name + " a réussi à se défendre avec succès !");
            return true;
        } else {
            System.out.println(name + " a échoué à se défendre.");
            return false;
        }
    }

    /**
     * Allows the human to interact with another species.
     *
     * @param otherSpecies The other species to interact with.
     */
    public void interactWith(Species otherSpecies) {
        System.out.println(name + " interagit avec " + otherSpecies.getName() + ".");
        if (otherSpecies instanceof Herbivore) {
            System.out.println(name + " nourrit " + otherSpecies.getName() + ".");
            otherSpecies.setEnergy(otherSpecies.getEnergy() + 10);
        } else if (otherSpecies instanceof Carnivore) {
            System.out.println(name + " garde ses distances avec " + otherSpecies.getName() + ".");
        } else {
            System.out.println(name + " observe " + otherSpecies.getName() + ".");
        }
    }

    /**
     * Puts the human to sleep, restoring energy.
     */
    @Override
    public void sleep() {
        if (!sleeping) {
            sleeping = true;
            energy += 10; // Humans regenerate moderate energy while sleeping
            System.out.println(name + " (Human) is now sleeping. Energy: " + energy);
        } else {
            System.out.println(name + " (Human) is already sleeping.");
        }
    }

    /**
     * Wakes the human from sleep.
     */
    @Override
    public void wakeUp() {
        if (sleeping) {
            sleeping = false;
            System.out.println(name + " (Human) has woken up.");
        } else {
            System.out.println(name + " (Human) is already awake.");
        }
    }

    /**
     * Checks if the human is currently sleeping.
     *
     * @return {@code true} if the human is sleeping, {@code false} otherwise.
     */
    @Override
    public boolean isSleeping() {
        return sleeping;
    }
}
