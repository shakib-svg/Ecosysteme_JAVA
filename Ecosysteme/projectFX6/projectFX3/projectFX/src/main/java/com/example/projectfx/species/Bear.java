package com.example.projectfx.species;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import com.example.projectfx.interfaces.Consumable;
import javafx.scene.image.ImageView;

/**
 * The {@code Bear} class represents a specific type of omnivorous species in the ecosystem.
 * Bears are characterized by their strength, speed, and unique behaviors such as hibernation
 * and consuming both resources and prey.
 * <p>
 * This class extends the {@code Omnivore} class and provides implementations for movement,
 * reproduction, and eating behaviors specific to bears.
 */
public class Bear extends Omnivore {

    /**
     * The strength of the bear, influencing its attack power.
     */
    private double strength;

    /**
     * The speed of the bear, measured in meters per second (m/s).
     */
    private double speed;

    /**
     * Tracks the total number of bears in the ecosystem.
     */
    private static int nbbears = 0;

    /**
     * Constructs a new {@code Bear} with the specified attributes.
     *
     * @param name        The name of the bear.
     * @param energy      The initial energy level of the bear.
     * @param position    The position of the bear in the ecosystem.
     * @param environment The environment the bear belongs to.
     * @param strength    The strength of the bear, influencing attack power.
     * @param speed       The speed of the bear, measured in m/s.
     * @param vitesse     The speed attribute for general movement.
     * @param imageView   The graphical representation of the bear.
     */
    public Bear(String name, int energy, Positionn position, Environment environment, double strength, double speed, int vitesse, ImageView imageView) {
        super(name, energy, position, environment, vitesse, imageView);
        this.strength = strength;
        this.speed = speed;
        nbbears++;
    }

    /**
     * Retrieves the strength of the bear.
     *
     * @return The strength of the bear.
     */
    public double getStrength() {
        return strength;
    }

    /**
     * Sets the strength of the bear.
     *
     * @param strength The new strength value.
     */
    public void setStrength(double strength) {
        this.strength = strength;
    }

    /**
     * Retrieves the speed of the bear.
     *
     * @return The speed of the bear in m/s.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of the bear.
     *
     * @param speed The new speed value in m/s.
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Moves the bear in a specified direction.
     * Movement consumes energy.
     *
     * @param direction The direction in which the bear moves (e.g., "north", "east").
     */
    @Override
    public void move(String direction) {
        System.out.println(name + " se déplace lentement mais avec force vers " + direction + " à une vitesse de " + speed + " m/s.");
        consumeEnergy(10);
    }

    /**
     * Handles the reproduction behavior of the bear.
     * Reproduction is dependent on the total number of bears in the ecosystem.
     */
    @Override
    public void reproduce() {
        System.out.println(name + " participe à l'extension de la communauté bears.");
        if (canReproduce(nbbears)) {
            nbbears++;
            System.out.println(name + " (bears) has reproduced. Total bears: " + nbbears);
        } else {
            System.out.println(name + " (bears) cannot reproduce due to insufficient numbers.");
        }
    }

    /**
     * Handles the eating behavior of the bear.
     * Bears can consume both resources and other species.
     *
     * @param food The food to be consumed, which can be a {@code Species} or {@code Consumable}.
     * @return {@code true} if the food was successfully consumed, {@code false} otherwise.
     */
    @Override
    public boolean eat(Object food) {
        if (food instanceof Species) {
            Species prey = (Species) food;
            System.out.println(name + " attaque et mange " + prey.getName() + ".");
            if (prey.getEnergy() <= strength) {
                System.out.println(prey.getName() + " est complètement mangé par " + name + ".");
                energy += prey.getEnergy();
                prey.setEnergy(0);
                return true;
            } else {
                System.out.println(prey.getName() + " perd de l'énergie après l'attaque.");
                prey.setEnergy(prey.getEnergy() - (int) strength);
                energy += (int) strength;
                return false;
            }
        } else if (food instanceof Consumable) {
            Consumable resource = (Consumable) food;
            System.out.println(name + " consomme une ressource.");
            resource.consume(20);
            energy += 20;
            return true;
        } else {
            System.out.println(name + " ne peut pas manger cet aliment.");
        }
        return false;
    }

    /**
     * Handles the bear's hibernation behavior.
     * During hibernation, the bear regenerates energy.
     */
    @Override
    public void sleep() {
        if (!sleeping) {
            sleeping = true;
            energy += 30; // Regenerate energy during hibernation
            System.out.println(name + " (Bear) is now sleeping (hibernating). Energy: " + energy);
        } else {
            System.out.println(name + " (Bear) is already sleeping.");
        }
    }

    /**
     * Wakes the bear from hibernation.
     */
    @Override
    public void wakeUp() {
        if (sleeping) {
            sleeping = false;
            System.out.println(name + " (Bear) has woken up from hibernation.");
        } else {
            System.out.println(name + " (Bear) is already awake.");
        }
    }

    /**
     * Checks if the bear is currently sleeping or hibernating.
     *
     * @return {@code true} if the bear is sleeping, {@code false} otherwise.
     */
    @Override
    public boolean isSleeping() {
        return sleeping;
    }
}
