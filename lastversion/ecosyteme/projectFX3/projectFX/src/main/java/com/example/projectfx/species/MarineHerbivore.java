package com.example.projectfx.species;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import javafx.scene.image.ImageView;

/**
 * The {@code MarineHerbivore} class represents a type of herbivore species that lives in marine environments.
 * It extends the {@code Herbivore} class and introduces additional behavior specific to marine herbivores,
 * such as the ability to swim.
 * <p>
 * Subclasses must implement the movement and reproduction behaviors.
 */
public abstract class MarineHerbivore extends Herbivore {

    /**
     * Constructs a new {@code MarineHerbivore} species with the specified attributes.
     *
     * @param name        The name of the marine herbivore species.
     * @param energy      The initial energy level of the marine herbivore.
     * @param positionn   The position of the marine herbivore in the ecosystem.
     * @param environment The environment the marine herbivore belongs to.
     * @param vitesse     The speed of the marine herbivore.
     * @param imageView   The graphical representation of the marine herbivore.
     */
    public MarineHerbivore(String name, int energy, Positionn positionn, Environment environment, int vitesse, ImageView imageView) {
        super(name, energy, positionn, environment, false, vitesse, imageView);
    }

    /**
     * Makes the marine herbivore swim in a specified direction.
     * Swimming consumes energy.
     *
     * @param direction The direction in which the marine herbivore swims (e.g., "north", "east").
     */
    public void swim(String direction) {
        System.out.println(name + " nage vers " + direction + ".");
        consumeEnergy(5); // Swimming consumes energy
    }

    /**
     * Abstract method to define the movement behavior of the marine herbivore.
     *
     * @param direction The direction in which the marine herbivore moves.
     */
    @Override
    public abstract void move(String direction);

    /**
     * Abstract method to define the reproduction behavior of the marine herbivore.
     */
    @Override
    public abstract void reproduce();
}
