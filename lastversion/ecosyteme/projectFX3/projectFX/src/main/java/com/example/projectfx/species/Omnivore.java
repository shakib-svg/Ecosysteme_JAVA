package com.example.projectfx.species;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import com.example.projectfx.interfaces.Consumable;
import javafx.scene.image.ImageView;

/**
 * The {@code Omnivore} class represents a type of species that can consume both resources
 * and other species in the ecosystem. It extends the {@code Species} class and implements
 * specific eating behavior for omnivorous entities.
 * <p>
 * Subclasses must define their movement and reproduction behaviors.
 */
public abstract class Omnivore extends Species {

    /**
     * Constructs a new {@code Omnivore} species with the specified attributes.
     *
     * @param name        The name of the omnivore species.
     * @param energy      The initial energy level of the omnivore.
     * @param position    The position of the omnivore in the ecosystem.
     * @param environment The environment the omnivore belongs to.
     * @param vitesse     The speed of the omnivore.
     * @param imageView   The graphical representation of the omnivore.
     */
    public Omnivore(String name, int energy, Positionn position, Environment environment, int vitesse, ImageView imageView) {
        super(name, energy, position, environment, vitesse, imageView);
    }

    /**
     * Defines the omnivore's eating behavior.
     * <ul>
     *     <li>If the food is another species, the omnivore may consume the prey entirely or partially based on the prey's energy level.</li>
     *     <li>If the food is a resource, the omnivore consumes a fixed amount (e.g., 10 units).</li>
     * </ul>
     *
     * @param food The food to be consumed, which could be a {@code Species} or a {@code Consumable} resource.
     * @return {@code true} if the food was successfully consumed, {@code false} otherwise.
     */
    @Override
    public boolean eat(Object food) {
        if (food instanceof Species) {
            Species prey = (Species) food;
            System.out.println(name + " attaque et mange " + prey.getName() + ".");
            if (prey.getEnergy() <= 20) { // Example: Omnivore consumes prey if energy <= 20
                System.out.println(prey.getName() + " est complètement mangé par " + name + ".");
                energy += prey.getEnergy();
                prey.setEnergy(0);
                prey.checkDeath();
                return true;
            } else {
                System.out.println(prey.getName() + " perd de l'énergie après l'attaque.");
                prey.setEnergy(prey.getEnergy() - 20);
                energy += 20;
                return false;
            }
        } else if (food instanceof Consumable) {
            Consumable resource = (Consumable) food;
            System.out.println(name + " consomme des ressources.");
            resource.consume(10); // Example: Omnivore consumes 10 units
            energy += 10;
            return true;
        } else {
            System.out.println(name + " ne peut pas manger cet aliment.");
        }
        return false;
    }

    /**
     * Abstract method to define the omnivore's movement behavior.
     *
     * @param direction The direction in which the omnivore moves (e.g., "north", "south").
     */
    @Override
    public abstract void move(String direction);

    /**
     * Abstract method to define the omnivore's reproduction behavior.
     */
    @Override
    public abstract void reproduce();
}
