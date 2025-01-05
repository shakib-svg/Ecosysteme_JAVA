package com.example.projectfx.species;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import javafx.scene.image.ImageView;

/**
 * The {@code Carnivore} class represents a type of species in the ecosystem that primarily hunts and consumes other species.
 * It extends the {@code Species} class and introduces specific attributes and behaviors unique to carnivores,
 * such as attack strength and hunting behavior.
 * <p>
 * Subclasses must implement the movement and reproduction behaviors.
 */
public abstract class Carnivore extends Species {

    /**
     * The attack strength of the carnivore, determining the damage it can inflict on prey.
     */
    private int attackStrength;

    /**
     * Constructs a new {@code Carnivore} species with the specified attributes.
     *
     * @param name           The name of the carnivore species.
     * @param energy         The initial energy level of the carnivore.
     * @param positionn      The position of the carnivore in the ecosystem.
     * @param environment    The environment the carnivore belongs to.
     * @param attackStrength The attack strength of the carnivore.
     * @param vitesse        The speed of the carnivore.
     * @param imageView      The graphical representation of the carnivore.
     */
    public Carnivore(String name, int energy, Positionn positionn, Environment environment, int attackStrength, int vitesse, ImageView imageView) {
        super(name, energy, positionn, environment, vitesse, imageView);
        this.attackStrength = attackStrength;
    }

    /**
     * Retrieves the attack strength of the carnivore.
     *
     * @return The attack strength as an integer.
     */
    public int getAttackStrength() {
        return attackStrength;
    }

    /**
     * Sets the attack strength of the carnivore.
     *
     * @param attackStrength The new attack strength as an integer.
     */
    public void setAttackStrength(int attackStrength) {
        this.attackStrength = attackStrength;
    }

    /**
     * Performs the hunting behavior for the carnivore.
     * The carnivore will attempt to hunt a prey species if it is in the same environment
     * and within a certain distance.
     *
     * @param prey The prey species the carnivore is hunting.
     * @return {@code true} if the hunt was successful, {@code false} otherwise.
     */
    public boolean hunt(Species prey) {
        System.out.println(name + " chasse " + prey.getName() + ".");

        // Check if prey is in the same environment and within hunting range
        if (this.environment.equals(prey.getEnvironment()) &&
                this.positionn.calculateDistance(prey.getPosition()) <= 300) {
            attack(prey);
            return true;
        } else {
            System.out.println(prey.getName() + " est trop loin ou dans un autre environnement.");
        }
        return false;
    }

    /**
     * Attacks the prey species, reducing its energy based on the carnivore's attack strength.
     * If the prey's energy is less than or equal to the attack strength, it is consumed.
     *
     * @param prey The prey species being attacked.
     */
    public void attack(Species prey) {
        System.out.println(name + " attaque " + prey.getName() + " avec une force de " + attackStrength + ".");

        if (prey.getEnergy() <= attackStrength) {
            eat(prey);
        } else {
            System.out.println(prey.getName() + " survit à l'attaque mais perd de l'énergie.");
            prey.setEnergy(prey.getEnergy() - attackStrength);
        }
    }

    /**
     * Consumes the prey species, gaining its energy and removing it from the ecosystem.
     *
     * @param prey The prey species being consumed.
     */
    public void eat(Species prey) {
        System.out.println(name + " mange " + prey.getName() + ".");
        this.energy += prey.getEnergy(); // Gain prey's energy
        prey.setEnergy(0); // Prey is consumed
        prey.checkDeath();
    }

    /**
     * Abstract method to define the movement behavior of the carnivore.
     *
     * @param direction The direction in which the carnivore moves (e.g., "north", "east").
     */
    @Override
    public abstract void move(String direction);

    /**
     * Abstract method to define the reproduction behavior of the carnivore.
     */
    @Override
    public abstract void reproduce();
}
