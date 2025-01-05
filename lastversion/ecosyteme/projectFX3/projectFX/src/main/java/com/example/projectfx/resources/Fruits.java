package com.example.projectfx.resources;

import com.example.projectfx.ecosystem.Positionn;
import javafx.scene.image.ImageView;

/**
 * The {@code Fruits} class represents a specific type of resource in the ecosystem.
 * It extends the {@code Resource} class and includes additional attributes and behaviors
 * specific to fruits, such as their ripening rate.
 */
public class Fruits extends Resource {
    /**
     * The ripening rate of the fruits, represented as a percentage (e.g., 0.15 for 15% growth per cycle).
     */
    private double ripeningRate;

    /**
     * Constructs a new {@code Fruits} resource with the specified quantity, position, ripening rate,
     * and graphical representation.
     *
     * @param quantity      The initial quantity of the fruits.
     * @param positionn     The position of the fruits in the ecosystem.
     * @param ripeningRate  The ripening rate of the fruits (e.g., 0.15 for 15% growth per cycle).
     * @param imageView     The graphical representation of the fruits.
     */
    public Fruits(int quantity, Positionn positionn, double ripeningRate, ImageView imageView) {
        super("Fruits", positionn, quantity, imageView);
        this.ripeningRate = ripeningRate;
    }

    /**
     * Retrieves the ripening rate of the fruits.
     *
     * @return The ripening rate as a double value.
     */
    public double getRipeningRate() {
        return ripeningRate;
    }

    /**
     * Sets the ripening rate of the fruits.
     *
     * @param ripeningRate The new ripening rate as a double value.
     */
    public void setRipeningRate(double ripeningRate) {
        this.ripeningRate = ripeningRate;
    }

    /**
     * Regenerates the fruits based on their ripening rate.
     * The quantity of fruits increases by a percentage of the current quantity, determined by the ripening rate.
     */
    @Override
    public void regenerate() {
        int regeneratedAmount = (int) (quantity * ripeningRate);
        quantity += regeneratedAmount;
        System.out.println(type + " régénérés de " + regeneratedAmount + " unités. Quantité totale : " + quantity);
    }

    /**
     * Consumes a specified amount of fruits, reducing their quantity.
     * If the specified amount exceeds the current quantity, all fruits are consumed.
     *
     * @param amount The amount of fruits to consume.
     */
    @Override
    public void consume(int amount) {
        if (amount > quantity) {
            System.out.println("Pas assez de fruits à consommer. Tout est consommé.");
            quantity = 0;
        } else {
            quantity -= amount;
            System.out.println(amount + " unités de " + type + " consommées. Quantité restante : " + quantity);
        }
    }

    /**
     * Checks if the fruits have been fully consumed.
     *
     * @return {@code true} if the quantity is zero, {@code false} otherwise.
     */
    @Override
    public boolean isConsumed() {
        return quantity <= 0;
    }
}
