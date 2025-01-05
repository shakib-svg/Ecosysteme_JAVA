package com.example.projectfx.resources;

import com.example.projectfx.ecosystem.Positionn;
import javafx.scene.image.ImageView;

/**
 * The {@code Grass} class represents a specific type of resource in the ecosystem.
 * It extends the {@code Resource} class and includes additional attributes and behaviors
 * specific to grass, such as its growth rate.
 */
public class Grass extends Resource {
    /**
     * The growth rate of the grass, represented as a percentage (e.g., 0.2 for 20% growth per cycle).
     */
    private double growthRate;

    /**
     * Constructs a new {@code Grass} resource with the specified quantity, position, growth rate,
     * and graphical representation.
     *
     * @param quantity   The initial quantity of the grass.
     * @param positionn  The position of the grass in the ecosystem.
     * @param growthRate The growth rate of the grass (e.g., 0.2 for 20% growth per cycle).
     * @param imageView  The graphical representation of the grass.
     */
    public Grass(int quantity, Positionn positionn, double growthRate, ImageView imageView) {
        super("Grass", positionn, quantity, imageView);
        this.growthRate = growthRate;
    }

    /**
     * Retrieves the growth rate of the grass.
     *
     * @return The growth rate as a double value.
     */
    public double getGrowthRate() {
        return growthRate;
    }

    /**
     * Sets the growth rate of the grass.
     *
     * @param growthRate The new growth rate as a double value.
     */
    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }

    /**
     * Regenerates the grass based on its growth rate.
     * The quantity of grass increases by a percentage of the current quantity, determined by the growth rate.
     */
    @Override
    public void regenerate() {
        int regeneratedAmount = (int) (quantity * growthRate);
        quantity += regeneratedAmount;
        System.out.println(type + " régénérée de " + regeneratedAmount + " unités. Quantité totale : " + quantity);
    }

    /**
     * Consumes a specified amount of grass, reducing its quantity.
     * If the specified amount exceeds the current quantity, all grass is consumed.
     *
     * @param amount The amount of grass to consume.
     */
    @Override
    public void consume(int amount) {
        if (amount > quantity) {
            System.out.println("Pas assez d'herbe à consommer. Tout est consommé.");
            quantity = 0;
        } else {
            quantity -= amount;
            System.out.println(amount + " unités de " + type + " consommées. Quantité restante : " + quantity);
        }
    }

    /**
     * Checks if the grass has been fully consumed.
     *
     * @return {@code true} if the quantity is zero, {@code false} otherwise.
     */
    @Override
    public boolean isConsumed() {
        return quantity <= 0;
    }
}
