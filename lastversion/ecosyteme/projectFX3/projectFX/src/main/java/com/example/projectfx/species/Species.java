package com.example.projectfx.species;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import com.example.projectfx.interfaces.Movable;
import com.example.projectfx.interfaces.Reproducible;
import com.example.projectfx.interfaces.Sleepable;
import javafx.scene.image.ImageView;

/**
 * The {@code Species} class represents an abstract base for all species in the ecosystem.
 * It implements the {@code Movable}, {@code Reproducible}, and {@code Sleepable} interfaces,
 * providing common behaviors such as movement, reproduction, and sleep management.
 * <p>
 * Concrete subclasses must define specific behaviors such as movement and reproduction logic.
 */
public abstract class Species implements Movable, Reproducible, Sleepable {
    /**
     * The name of the species.
     */
    protected String name;

    /**
     * The current energy level of the species.
     */
    protected int energy;

    /**
     * The current position of the species in the ecosystem.
     */
    protected Positionn positionn;

    /**
     * The environment the species belongs to.
     */
    protected Environment environment;

    /**
     * The speed of the species, influencing its movement capabilities.
     */
    protected int vitesse;

    /**
     * The sleep state of the species.
     */
    protected boolean sleeping;

    /**
     * The graphical representation of the species.
     */
    protected ImageView imageView;

    /**
     * Constructs a new {@code Species} with the specified attributes.
     *
     * @param name        The name of the species.
     * @param energy      The initial energy level of the species.
     * @param positionn   The position of the species in the ecosystem.
     * @param environment The environment the species belongs to.
     * @param vitesse     The speed of the species.
     * @param imageView   The graphical representation of the species.
     */
    public Species(String name, int energy, Positionn positionn, Environment environment, int vitesse, ImageView imageView) {
        this.name = name;
        this.energy = energy;
        this.positionn = positionn;
        this.environment = environment;
        this.vitesse = vitesse;
        this.sleeping = false;
        this.imageView = imageView;
    }

    /**
     * Abstract method for defining the species' eating behavior.
     *
     * @param food The food object the species attempts to eat.
     * @return {@code true} if the food was successfully consumed, {@code false} otherwise.
     */
    public abstract boolean eat(Object food);

    // Getters and Setters

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = Math.max(0, energy); // Ensure energy is not negative
    }

    public Positionn getPosition() {
        return positionn;
    }

    public void setPosition(Positionn positionn) {
        this.positionn = positionn;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    // Abstract Methods
    public abstract void move(String direction);

    public abstract void reproduce();

    // Behavior Methods

    @Override
    public boolean canReproduce(int a) {
        return a >= 2;
    }

    public boolean checkDeath() {
        if (energy <= 0) {
            System.out.println(name + " has died due to lack of energy.");
            environment.retirerEspece(this);
            return true;
        }
        return false;
    }

    public void reduceEnergy(int amount) {
        energy -= amount;
        System.out.println(name + "'s energy reduced by " + amount + ". Current energy: " + energy);
        checkDeath();
    }

    public void consumeEnergy(int amount) {
        energy = Math.max(0, energy - amount);
        System.out.println(name + " has consumed " + amount + " energy. Remaining energy: " + energy);
    }

    public boolean isAlive() {
        return energy > 0;
    }

    @Override
    public void sleep() {
        if (!sleeping) {
            sleeping = true;
            energy += 20; // Regenerate energy during sleep
            System.out.println(name + " is now sleeping. Energy: " + energy);
        } else {
            System.out.println(name + " is already sleeping.");
        }
    }

    @Override
    public void wakeUp() {
        if (sleeping) {
            sleeping = false;
            System.out.println(name + " has woken up.");
        } else {
            System.out.println(name + " is already awake.");
        }
    }

    @Override
    public boolean isSleeping() {
        return sleeping;
    }

    public void handlePredatorAttack(Species predator) {
        if (sleeping) {
            System.out.println(name + " is sleeping and attacked by " + predator.getName() + "!");
            if (energy > predator.getEnergy()) {
                wakeUp();
                move("away");
                System.out.println(name + " woke up and escaped from " + predator.getName() + "!");
            } else {
                System.out.println(name + " couldn't escape and was eaten by " + predator.getName() + "...");
                environment.retirerEspece(this);
            }
        } else {
            System.out.println(name + " is awake and can defend itself!");
        }
    }

    @Override
    public String toString() {
        return name + " (Energy: " + energy + ", Position: " + positionn + ")";
    }
}
