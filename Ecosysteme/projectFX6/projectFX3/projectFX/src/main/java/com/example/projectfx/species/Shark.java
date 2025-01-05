package com.example.projectfx.species;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import javafx.scene.image.ImageView;

/**
 * The {@code Shark} class represents a carnivorous species in the ecosystem.
 * Sharks are characterized by their powerful attacks, swift swimming ability, and hunting behavior.
 * <p>
 * This class extends the {@code Carnivore} class and provides specific behaviors for sharks,
 * including hunting, attacking, movement, reproduction, and sleeping.
 */
public class Shark extends Carnivore {

    /**
     * The strength of the shark's attack, influencing the damage it can inflict on prey.
     */
    private int attackStrength;

    /**
     * The swimming speed of the shark, measured in meters per second (m/s).
     */
    private double swimSpeed;

    /**
     * Tracks the total number of sharks in the ecosystem.
     */
    private static int nbshark = 0;

    /**
     * Constructs a new {@code Shark} species with the specified attributes.
     *
     * @param name           The name of the shark.
     * @param energy         The initial energy level of the shark.
     * @param positionn      The position of the shark in the ecosystem.
     * @param environment    The environment the shark belongs to.
     * @param attackStrength The attack strength of the shark.
     * @param swimSpeed      The swimming speed of the shark in m/s.
     * @param vitesse        The general movement speed of the shark.
     * @param imageView      The graphical representation of the shark.
     */
    public Shark(String name, int energy, Positionn positionn, Environment environment, int attackStrength, double swimSpeed, int vitesse, ImageView imageView) {
        super(name, energy, positionn, environment, attackStrength, vitesse, imageView);
        this.attackStrength = attackStrength;
        this.swimSpeed = swimSpeed;
        nbshark++;
    }

    /**
     * Retrieves the swimming speed of the shark.
     *
     * @return The swimming speed of the shark in m/s.
     */
    public double getSwimSpeed() {
        return swimSpeed;
    }

    /**
     * Sets the swimming speed of the shark.
     *
     * @param swimSpeed The new swimming speed value in m/s.
     */
    public void setSwimSpeed(double swimSpeed) {
        this.swimSpeed = swimSpeed;
    }

    /**
     * Retrieves the attack strength of the shark.
     *
     * @return The attack strength of the shark.
     */
    public int getAttackStrength() {
        return attackStrength;
    }

    /**
     * Sets the attack strength of the shark.
     *
     * @param attackStrength The new attack strength value.
     */
    public void setAttackStrength(int attackStrength) {
        this.attackStrength = attackStrength;
    }

    /**
     * Retrieves the total number of sharks in the ecosystem.
     *
     * @return The total number of sharks.
     */
    public static int getNbshark() {
        return nbshark;
    }

    /**
     * Sets the total number of sharks in the ecosystem.
     *
     * @param nbshark The new total number of sharks.
     */
    public static void setNbshark(int nbshark) {
        Shark.nbshark = nbshark;
    }

    /**
     * Handles the eating behavior of the shark.
     * Currently, sharks do not consume any specific food in this implementation.
     *
     * @param food The food object to attempt eating.
     * @return {@code false} as eating is not implemented in this context.
     */
    @Override
    public boolean eat(Object food) {
        return false;
    }

    /**
     * Moves the shark in a specified direction.
     * Movement consumes energy.
     *
     * @param direction The direction in which the shark swims (e.g., "north").
     */
    @Override
    public void move(String direction) {
        System.out.println(name + " nage rapidement vers " + direction + " à une vitesse de " + swimSpeed + " m/s.");
        consumeEnergy(10);
    }

    /**
     * Handles the reproduction behavior of the shark.
     * Reproduction increases the total number of sharks in the ecosystem.
     */
    @Override
    public void reproduce() {
        System.out.println(name + " se reproduit en pondant des œufs.");
        if (canReproduce(nbshark)) {
            nbshark++;
            System.out.println(name + " (sharks) has reproduced. Total sharks: " + nbshark);
        } else {
            System.out.println(name + " (sharks) cannot reproduce due to insufficient numbers.");
        }
    }

    /**
     * Handles the hunting behavior of the shark.
     * Sharks can hunt prey within a specified range.
     *
     * @param prey The prey species the shark is hunting.
     * @return {@code true} if the prey was successfully hunted, {@code false} otherwise.
     */
    @Override
    public boolean hunt(Species prey) {
        System.out.println(name + " chasse " + prey.getName() + " dans l'océan.");
        if (this.environment.equals(prey.getEnvironment()) &&
                this.positionn.calculateDistance(prey.getPosition()) <= 5) { // Sharks have a larger hunting range
            attack(prey);
            return true;
        } else {
            System.out.println(prey.getName() + " est hors de portée.");
        }
        return false;
    }

    /**
     * Handles the attack behavior of the shark.
     * Sharks attack prey with their attack strength, reducing the prey's energy.
     *
     * @param prey The prey species being attacked.
     */
    @Override
    public void attack(Species prey) {
        System.out.println(name + " attaque violemment " + prey.getName() + " avec une force de " + attackStrength + ".");
        if (prey.getEnergy() <= attackStrength) {
            eat(prey);
        } else {
            System.out.println(prey.getName() + " survit à l'attaque mais perd de l'énergie.");
            prey.setEnergy(prey.getEnergy() - attackStrength);
        }
    }

    /**
     * Puts the shark to sleep, restoring energy.
     */
    @Override
    public void sleep() {
        if (!sleeping) {
            sleeping = true;
            energy += 10; // Sharks regenerate energy moderately while sleeping
            System.out.println(name + " (Shark) is now sleeping while floating. Energy: " + energy);
        } else {
            System.out.println(name + " (Shark) is already sleeping.");
        }
    }

    /**
     * Wakes the shark from sleep.
     */
    @Override
    public void wakeUp() {
        if (sleeping) {
            sleeping = false;
            System.out.println(name + " (Shark) has woken up and is now actively swimming.");
        } else {
            System.out.println(name + " (Shark) is already awake.");
        }
    }

    /**
     * Checks if the shark is currently sleeping.
     *
     * @return {@code true} if the shark is sleeping, {@code false} otherwise.
     */
    @Override
    public boolean isSleeping() {
        return sleeping;
    }
}
