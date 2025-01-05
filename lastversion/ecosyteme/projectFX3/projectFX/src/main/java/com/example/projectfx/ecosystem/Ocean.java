package com.example.projectfx.ecosystem;

/**
 * The {@code Ocean} class represents an ocean environment within the ecosystem.
 * It extends the {@code Environment} class and introduces specific attributes
 * and behaviors unique to oceans, such as salinity levels.
 */
public class Ocean extends Environment {
    /**
     * The salinity level of the ocean, measured in parts per thousand (ppt).
     */
    private double salinity;

    /**
     * Constructs an {@code Ocean} environment with the specified salinity level.
     *
     * @param salinity the initial salinity level of the ocean in ppt.
     */
    public Ocean(double salinity) {
        this.salinity = salinity;
    }

    /**
     * Applies specific rules for the ocean environment.
     * For example, salinity levels may decrease based on the number of marine herbivores present.
     */
    @Override
    public void appliquerReglesEnvironment() {
        System.out.println("Appliquer les règles spécifiques à l'océan.");

        // Adjust salinity based on the number of marine herbivores
        if (!speciesList.isEmpty()) {
            long marineHerbivoresCount = speciesList.stream()
                    .filter(species -> species.getClass().getSimpleName().equals("MarineHerbivore"))
                    .count();
            this.salinity -= marineHerbivoresCount * 0.1; // Example adjustment
            System.out.println("Niveau de salinité ajusté : " + salinity);
        }
    }

    /**
     * Updates the current season in the ocean environment and applies
     * additional effects specific to the ocean during each season.
     *
     * @param newSeason the new season to set.
     */
    @Override
    public void mettreAJourSaison(String newSeason) {
        super.mettreAJourSaison(newSeason);

        // Additional seasonal effects specific to oceans
        switch (currentSeason) {
            case "Spring":
                System.out.println("Les algues prolifèrent grâce aux conditions favorables.");
                break;
            case "Summer":
                System.out.println("Les températures élevées augmentent la productivité marine.");
                break;
            case "Autumn":
                System.out.println("Les tempêtes automnales affectent les ressources marines.");
                break;
            case "Winter":
                System.out.println("La température de l'eau baisse, ralentissant la reproduction.");
                break;
            default:
                System.out.println("Effets saisonniers inconnus.");
                break;
        }
    }

    /**
     * Retrieves the current salinity level of the ocean.
     *
     * @return the salinity level in ppt.
     */
    public double getSalinity() {
        return salinity;
    }

    /**
     * Updates the salinity level of the ocean.
     *
     * @param salinity the new salinity level in ppt.
     */
    public void setSalinity(double salinity) {
        this.salinity = salinity;
    }
}
