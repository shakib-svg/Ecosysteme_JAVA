package com.example.projectfx.ecosystem;

/**
 * The {@code Air} class represents an air-based environment within the ecosystem.
 * It extends the {@code Environment} class and introduces specific rules
 * and attributes relevant to the air environment, such as wind speed.
 */
public class Air extends Environment {
    /**
     * The wind speed in the air environment, measured in kilometers per hour (km/h).
     */
    private double windSpeed;

    /**
     * Constructs an {@code Air} environment with a specified wind speed.
     *
     * @param windSpeed the initial wind speed in km/h.
     */
    public Air(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Applies specific rules for the air environment.
     * For example, it adjusts the wind speed based on the number of flying species.
     */
    @Override
    public void appliquerReglesEnvironment() {
        System.out.println("Appliquer les règles spécifiques à l'air.");

        // Adjust wind speed based on the number of flying species
        if (!speciesList.isEmpty()) {
            long flyingSpeciesCount = speciesList.stream()
                    .filter(species -> species.getClass().getSimpleName().equals("FlyingSpecies")) // Example filter
                    .count();
            this.windSpeed += flyingSpeciesCount * 0.5; // Adjust wind speed
            System.out.println("Vitesse du vent ajustée : " + windSpeed + " km/h.");
        }
    }

    /**
     * Updates the current season in the air environment and applies
     * additional seasonal effects specific to the air environment.
     *
     * @param newSeason the new season to set.
     */
    @Override
    public void mettreAJourSaison(String newSeason) {
        super.mettreAJourSaison(newSeason);

        // Additional seasonal effects specific to the air environment
        switch (currentSeason) {
            case "Spring":
                System.out.println("Les oiseaux migrateurs reviennent dans l'environnement.");
                break;
            case "Summer":
                System.out.println("Les courants thermiques augmentent, facilitant le vol.");
                break;
            case "Autumn":
                System.out.println("Les vents forts perturbent les espèces volantes.");
                break;
            case "Winter":
                System.out.println("Les tempêtes hivernales affectent les déplacements aériens.");
                break;
            default:
                System.out.println("Effets saisonniers inconnus.");
                break;
        }
    }

    /**
     * Retrieves the current wind speed in the air environment.
     *
     * @return the wind speed in km/h.
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Updates the wind speed in the air environment.
     *
     * @param windSpeed the new wind speed in km/h.
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
