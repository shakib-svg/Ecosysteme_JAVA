package com.example.projectfx.ecosystem;

/**
 * The {@code Forest} class represents a forest environment within the ecosystem.
 * It extends the {@code Environment} class and introduces specific attributes
 * and behaviors unique to forests, such as tree density.
 */
public class Forest extends Environment {
    /**
     * The density of trees in the forest. This value influences resource availability
     * and changes based on environmental factors or species interactions.
     */
    private double treeDensity;

    /**
     * Constructs a {@code Forest} environment with the specified tree density.
     *
     * @param treeDensity the initial density of trees in the forest.
     */
    public Forest(double treeDensity) {
        this.treeDensity = treeDensity;
    }

    /**
     * Applies specific rules for the forest environment.
     * For example, tree density decreases with the number of herbivores present.
     */
    @Override
    public void appliquerReglesEnvironment() {
        System.out.println("Appliquer les règles spécifiques à la forêt.");

        // Adjust tree density based on the number of herbivores
        if (!speciesList.isEmpty()) {
            double herbivoresCount = speciesList.stream()
                    .filter(species -> species.getClass().getSimpleName().equals("Herbivore"))
                    .count();
            this.treeDensity -= herbivoresCount * 0.01; // Example adjustment
            System.out.println("Densité des arbres ajustée : " + treeDensity);
        }
    }

    /**
     * Updates the current season in the forest environment and applies
     * additional effects specific to the forest during each season.
     *
     * @param newSeason the new season to set.
     */
    @Override
    public void mettreAJourSaison(String newSeason) {
        super.mettreAJourSaison(newSeason);

        // Additional seasonal effects specific to forests
        switch (currentSeason) {
            case "Spring":
                System.out.println("La forêt fleurit, attirant davantage d'herbivores.");
                break;
            case "Summer":
                System.out.println("Les arbres produisent beaucoup de fruits en été.");
                break;
            case "Autumn":
                System.out.println("Les feuilles tombent, limitant les ressources.");
                break;
            case "Winter":
                System.out.println("Les arbres perdent toutes leurs feuilles, ressources réduites.");
                break;
            default:
                System.out.println("Effets saisonniers inconnus.");
                break;
        }
    }

    /**
     * Retrieves the current tree density in the forest.
     *
     * @return the tree density.
     */
    public double getTreeDensity() {
        return treeDensity;
    }

    /**
     * Updates the tree density in the forest.
     *
     * @param treeDensity the new tree density.
     */
    public void setTreeDensity(double treeDensity) {
        this.treeDensity = treeDensity;
    }
}
