package com.example.projectfx.species;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import javafx.scene.image.ImageView;

/**
 * The {@code Herbivore} class represents a type of species in the ecosystem that primarily consumes plants.
 * It extends the {@code Species} class and includes specific attributes and behaviors unique to herbivores,
 * such as grouping instincts and predator detection.
 * <p>
 * Subclasses must implement the movement and reproduction behaviors.
 */
public abstract class Herbivore extends Species {

    /**
     * Indicates whether the herbivore prefers to stay in groups.
     */
    private boolean groupingInstinct;

    /**
     * Constructs a new {@code Herbivore} species with the specified attributes.
     *
     * @param name             The name of the herbivore species.
     * @param energy           The initial energy level of the herbivore.
     * @param positionn        The position of the herbivore in the ecosystem.
     * @param environment      The environment the herbivore belongs to.
     * @param groupingInstinct Indicates whether the herbivore has a grouping instinct.
     * @param vitesse          The speed of the herbivore.
     * @param imageView        The graphical representation of the herbivore.
     */
    public Herbivore(String name, int energy, Positionn positionn, Environment environment, boolean groupingInstinct, int vitesse, ImageView imageView) {
        super(name, energy, positionn, environment, vitesse, imageView);
        this.groupingInstinct = groupingInstinct;
    }

    /**
     * Checks if the herbivore has a grouping instinct.
     *
     * @return {@code true} if the herbivore has a grouping instinct, {@code false} otherwise.
     */
    public boolean hasGroupingInstinct() {
        return groupingInstinct;
    }

    /**
     * Sets whether the herbivore has a grouping instinct.
     *
     * @param groupingInstinct {@code true} to enable grouping instinct, {@code false} otherwise.
     */
    public void setGroupingInstinct(boolean groupingInstinct) {
        this.groupingInstinct = groupingInstinct;
    }

    /**
     * Detects predators in the vicinity of the herbivore.
     * If a predator is detected, the herbivore attempts to flee.
     */
    public void detecterPredateur() {
        System.out.println(name + " cherche des prédateurs.");
        // Simple detection logic: simulate predator detection
        if (Math.random() < 0.5) {
            System.out.println(name + " détecte un prédateur et tente de fuir !");
            flee();
        } else {
            System.out.println(name + " ne détecte aucun prédateur.");
        }
    }

    /**
     * Makes the herbivore flee from a detected predator.
     * Fleeing reduces the herbivore's energy.
     */
    public void flee() {
        System.out.println(name + " fuit rapidement pour échapper au danger.");
        consumeEnergy(10); // Fleeing costs energy
    }

    /**
     * Abstract method to define the movement behavior of the herbivore.
     *
     * @param direction The direction in which the herbivore moves (e.g., "north", "east").
     */
    @Override
    public abstract void move(String direction);

    /**
     * Abstract method to define the reproduction behavior of the herbivore.
     */
    @Override
    public abstract void reproduce();
}
