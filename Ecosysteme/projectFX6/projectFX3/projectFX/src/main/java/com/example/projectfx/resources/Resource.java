package com.example.projectfx.resources;

import com.example.projectfx.ecosystem.Positionn;
import com.example.projectfx.interfaces.Consumable;
import javafx.scene.image.ImageView;

/**
 * The {@code Resource} class represents an abstract base for various types of resources
 * within the ecosystem. Resources are consumable entities with a defined type, quantity,
 * position, and graphical representation.
 * <p>
 * This class implements the {@code Consumable} interface, requiring concrete subclasses
 * to define how resources are consumed and regenerated.
 */
public abstract class Resource implements Consumable {
    /**
     * The type of resource (e.g., "Water", "Food").
     */
    protected String type;

    /**
     * The current quantity of the resource.
     */
    protected int quantity;

    /**
     * The position of the resource in the ecosystem.
     */
    protected Positionn positionn;

    /**
     * The graphical representation of the resource.
     */
    protected ImageView imageView;

    /**
     * Constructs a new {@code Resource} with the specified type, position, quantity,
     * and graphical representation.
     *
     * @param type       The type of the resource (e.g., "Water", "Food").
     * @param positionn  The position of the resource in the ecosystem.
     * @param quantity   The initial quantity of the resource.
     * @param imageview  The graphical representation of the resource.
     */
    public Resource(String type, Positionn positionn, int quantity, ImageView imageview) {
        this.type = type;
        this.positionn = positionn;
        this.quantity = quantity;
        this.imageView = imageview;
    }

    /**
     * Retrieves the type of the resource.
     *
     * @return The type of the resource.
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves the current quantity of the resource.
     *
     * @return The quantity of the resource.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the resource.
     *
     * @param quantity The new quantity of the resource.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Retrieves the position of the resource in the ecosystem.
     *
     * @return The position of the resource.
     */
    public Positionn getPosition() {
        return positionn;
    }

    /**
     * Sets the position of the resource in the ecosystem.
     *
     * @param positionn The new position of the resource.
     */
    public void setPosition(Positionn positionn) {
        this.positionn = positionn;
    }

    /**
     * Retrieves the graphical representation of the resource.
     *
     * @return The {@code ImageView} representing the resource.
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Sets the graphical representation of the resource.
     *
     * @param imageview The new {@code ImageView} for the resource.
     */
    public void setImageView(ImageView imageview) {
        this.imageView = imageview;
    }

    /**
     * Regenerates the resource based on its specific regeneration behavior.
     * Concrete subclasses must implement this method to define how the resource regenerates.
     */
    public abstract void regenerate();
}
