package com.example.projectfx.resources;

import com.example.projectfx.ecosystem.Positionn;
import javafx.scene.image.ImageView;

/**
 * The {@code Algae} class represents a specific type of resource in the ecosystem.
 * It extends the {@code Resource} class and includes additional attributes and behaviors
 * specific to algae, such as reproduction rates.
 */
public class Algae extends Resource {
    /**
     * The reproduction rate of the algae, represented as a percentage (e.g., 0.3 for 30% growth per cycle).
     */
    private double reproductionRate;

    /**
     * Constructs a new {@code Algae} resource with the specified quantity, position, reproduction rate,
     * and graphical representation.
     *
     * @param quantity         The initial quantity of the algae.
     * @param positionn        The position of the algae in the ecosystem.
     * @param reproductionRate The reproduction rate of the algae (e.g., 0.3 for 30% growth per cycle).
     * @param imageView        The graphical representation of the algae.
     */
    public Algae(int quantity, Positionn positionn, double reproductionRate, ImageView imageView) {
        super("Algae", positionn, quantity, imageView);
        this.reproductionRate = reproductionRate;
    }

    /**
     * Retrieves the reproduction rate of the algae.
     *
     * @return The reproduction rate as a double value.
     */
    public double getReproductionRate() {
        return reproductionRate;
    }

    /**
     * Sets the reproduction rate of the algae.
     *
     * @param reproductionRate The new reproduction rate as a double value.
     */
    public void setReproductionRate(double reproductionRate) {
        this.reproductionRate = reproductionRate;
    }

    /**
     * Regenerates the algae based on its reproduction rate.
     * The quantity of algae increases by a percentage of the current quantity, determined by the reproduction rate.
     */
    @Override
    public void regenerate() {
        int regeneratedAmount = (int) (quantity * reproductionRate);
        quantity += regeneratedAmount;
        System.out.println(type + " régénérées de " + regeneratedAmount + " unités. Quantité totale : " + quantity);
    }

    /**
     * Consumes a specified amount of algae, reducing its quantity.
     * If the specified amount exceeds the current quantity, all algae are consumed.
     *
     * @param amount The amount of algae to consume.
     */
    @Override
    public void consume(int amount) {
        if (amount > quantity) {
            System.out.println("Pas assez d'algues à consommer. Tout est consommé.");
            quantity = 0;
        } else {
            quantity -= amount;
            System.out.println(amount + " unités de " + type + " consommées. Quantité restante : " + quantity);
        }
    }

    /**
     * Checks if the algae have been fully consumed.
     *
     * @return {@code true} if the quantity is zero, {@code false} otherwise.
     */
    @Override
    public boolean isConsumed() {
        return quantity <= 0;
    }
}
