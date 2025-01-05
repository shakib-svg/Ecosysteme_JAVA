package com.example.projectfx.ecosystem;

import java.util.List;
import java.util.Random;

/**
 * The {@code Positionn} class represents a two-dimensional position in an ecosystem.
 * It provides methods for managing coordinates, calculating distances, and ensuring
 * valid positioning within a specified range and minimum distance constraints.
 */
public class Positionn {
    /**
     * The X-coordinate of the position.
     */
    private int x;

    /**
     * The Y-coordinate of the position.
     */
    private int y;

    /**
     * Constructs a {@code Positionn} with specified coordinates.
     *
     * @param x the X-coordinate of the position.
     * @param y the Y-coordinate of the position.
     */
    public Positionn(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a {@code Positionn} with random coordinates within a specified range,
     * ensuring that the new position does not overlap with existing positions by a minimum distance.
     *
     * @param rangeMinX          the minimum X-coordinate.
     * @param rangeMaxX          the maximum X-coordinate.
     * @param rangeMinY          the minimum Y-coordinate.
     * @param rangeMaxY          the maximum Y-coordinate.
     * @param existingPositions  a list of existing positions to avoid overlap.
     * @param minDistance        the minimum allowed distance from other positions.
     */
    public Positionn(int rangeMinX, int rangeMaxX, int rangeMinY, int rangeMaxY,
                     List<Positionn> existingPositions, int minDistance) {
        Random random = new Random();
        boolean isValidPosition;

        // Ensure a valid random position that doesn't overlap
        do {
            this.x = random.nextInt(rangeMaxX - rangeMinX + 1) + rangeMinX;
            this.y = random.nextInt(rangeMaxY - rangeMinY + 1) + rangeMinY;
            isValidPosition = true;

            // Check against existing positions to avoid overlap
            for (Positionn existingPos : existingPositions) {
                if (calculateDistance(existingPos) < minDistance) {
                    isValidPosition = false;
                    break;
                }
            }
        } while (!isValidPosition);
    }

    /**
     * Retrieves the X-coordinate of the position.
     *
     * @return the X-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X-coordinate of the position.
     *
     * @param x the new X-coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retrieves the Y-coordinate of the position.
     *
     * @return the Y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y-coordinate of the position.
     *
     * @param y the new Y-coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Calculates the distance between this position and another position.
     *
     * @param other the other position.
     * @return the distance between the two positions.
     */
    public double calculateDistance(Positionn other) {
        int deltaX = this.x - other.x;
        int deltaY = this.y - other.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Checks if this position is too close to another position based on a minimum distance.
     *
     * @param other       the other position.
     * @param minDistance the minimum allowed distance.
     * @return {@code true} if the positions are too close, {@code false} otherwise.
     */
    public boolean isTooClose(Positionn other, int minDistance) {
        return calculateDistance(other) < minDistance;
    }

    /**
     * Moves the position by a given offset.
     *
     * @param deltaX the offset for the X-coordinate.
     * @param deltaY the offset for the Y-coordinate.
     */
    public void moveBy(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
        System.out.println("Position mise à jour : (" + this.x + ", " + this.y + ")");
    }

    /**
     * Returns a string representation of the position in the format "(x, y)".
     *
     * @return a string representation of the position.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
