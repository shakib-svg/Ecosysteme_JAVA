package com.example.projectfx.ecosystem;

import com.example.projectfx.resources.Resource;
import com.example.projectfx.species.Species;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Environment} class represents a generic environment
 * within an ecosystem, managing resources, species, and seasonal effects.
 * This is an abstract class, requiring specific rules for the environment
 * to be implemented in subclasses.
 */
public abstract class Environment {
    // Attributes
    /**
     * List of resources available in the environment.
     */
    protected List<Resource> resources = new ArrayList<>();

    /**
     * List of species present in the environment.
     */
    protected List<Species> speciesList = new ArrayList<>();

    /**
     * The current season in the environment. Default is "Spring".
     */
    protected String currentSeason = "Spring";

    /**
     * Retrieves the list of species in the environment.
     *
     * @return the list of species.
     */
    public List<Species> getSpeciesList() {
        return speciesList;
    }

    /**
     * Retrieves the list of resources in the environment.
     *
     * @return the list of resources.
     */
    public List<Resource> getResources() {
        return resources;
    }

    /**
     * Abstract method to apply specific rules for the environment.
     * Subclasses must implement this method to define custom behavior.
     */
    public abstract void appliquerReglesEnvironment();

    /**
     * Updates the current season in the environment and applies
     * seasonal effects on the resources.
     *
     * @param newSeason the new season to set.
     */
    public void mettreAJourSaison(String newSeason) {
        this.currentSeason = newSeason;
        System.out.println("Saison actuelle : " + currentSeason);

        // Seasonal effects on resources
        switch (currentSeason) {
            case "Spring":
                regenererRessources(10); // Spring: Increase resources
                break;
            case "Summer":
                regenererRessources(5); // Summer: Moderate regeneration
                break;
            case "Autumn":
                diminuerRessources(5); // Autumn: Moderate decrease
                break;
            case "Winter":
                diminuerRessources(10); // Winter: Significant decrease
                break;
            default:
                System.out.println("Saison inconnue, aucun effet appliqué.");
        }
    }

    /**
     * Adds a species to the environment.
     *
     * @param species the species to add.
     */
    public void ajouterEspece(Species species) {
        speciesList.add(species);
        System.out.println("Espèce ajoutée : " + species.getName());
    }

    /**
     * Removes a species from the environment.
     *
     * @param species the species to remove.
     */
    public void retirerEspece(Species species) {
        if (speciesList.remove(species)) {
            System.out.println("Species removed: " + species.getName());
        } else {
            System.out.println("Species not found: " + species.getName());
        }
    }

    /**
     * Adds a resource to the environment.
     *
     * @param resource the resource to add.
     */
    public void ajouterResource(Resource resource) {
        resources.add(resource);
        System.out.println("Ressource ajoutée : " + resource.getType());
    }

    /**
     * Regenerates resources in the environment by a specified amount.
     *
     * @param amount the amount to regenerate.
     */
    protected void regenererRessources(int amount) {
        for (Resource resource : resources) {
            resource.setQuantity(resource.getQuantity() + amount);
            System.out.println(resource.getType() + " régénéré(e) de " + amount + " unités.");
        }
    }

    /**
     * Decreases resources in the environment by a specified amount.
     * Resource quantities cannot go below zero.
     *
     * @param amount the amount to decrease.
     */
    protected void diminuerRessources(int amount) {
        for (Resource resource : resources) {
            resource.setQuantity(Math.max(0, resource.getQuantity() - amount)); // Avoid negative quantities
            System.out.println(resource.getType() + " diminué(e) de " + amount + " unités.");
        }
    }
}
