package com.example.projectfx.ecosystem;

import com.example.projectfx.species.Species;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Ecosystem} class represents an entire ecosystem, containing multiple environments and species.
 * It manages the simulation cycles, updates, and interactions within the ecosystem.
 */
public class Ecosystem {
    /**
     * The list of environments in the ecosystem.
     */
    private List<Environment> listOfEnvironments = new ArrayList<>();

    /**
     * The list of species in the ecosystem.
     */
    private List<Species> listOfSpecies = new ArrayList<>();

    /**
     * Starts the simulation by applying environment-specific rules
     * and updating the cycle for the entire ecosystem.
     */
    public void lancerSimulation() {
        System.out.println("Simulation démarrée.");
        for (Environment environment : listOfEnvironments) {
            environment.appliquerReglesEnvironment();
        }
        mettreAJourCycle();
    }

    /**
     * Updates the ecosystem for one cycle. This includes:
     * <ul>
     *     <li>Removing species with no energy left.</li>
     *     <li>Allowing species to move and reproduce.</li>
     *     <li>Updating all environments to the new season.</li>
     * </ul>
     */
    public void mettreAJourCycle() {
        System.out.println("\n=== Mise à jour du cycle ===");

        // Loop through species and update their status
        for (int i = 0; i < listOfSpecies.size(); i++) {
            Species species = listOfSpecies.get(i);

            // Remove species with no energy left
            if (species.getEnergy() <= 0) {
                System.out.println(species.getName() + " est mort.");
                listOfSpecies.remove(i);
                i--; // Adjust index after removal
            } else {
                species.move("dans une direction aléatoire");
                species.reproduce();
            }
        }

        // Update all environments
        for (Environment environment : listOfEnvironments) {
            environment.mettreAJourSaison("Spring");
        }
    }

    /**
     * Displays the current state of the ecosystem, including:
     * <ul>
     *     <li>The type of each environment.</li>
     *     <li>The species present in each environment and their energy levels.</li>
     * </ul>
     */
    public void afficherEcosysteme() {
        System.out.println("\n=== État de l'écosystème ===");
        for (Environment environment : listOfEnvironments) {
            System.out.println("Environnement : " + environment.getClass().getSimpleName());
            System.out.println("Espèces présentes :");
            for (Species species : environment.speciesList) {
                System.out.println("- " + species.getName() + " (Énergie : " + species.getEnergy() + ")");
            }
            System.out.println();
        }
    }

    /**
     * Adds a new environment to the ecosystem.
     *
     * @param environment the environment to add.
     */
    public void ajouterEnvironnement(Environment environment) {
        listOfEnvironments.add(environment);
        System.out.println("Environnement ajouté : " + environment.getClass().getSimpleName());
    }

    /**
     * Adds a new species to the ecosystem and associates it with its environment.
     *
     * @param species the species to add.
     */
    public void ajouterEspece(Species species) {
        listOfSpecies.add(species);
        species.getEnvironment().ajouterEspece(species);
        System.out.println("Espèce ajoutée : " + species.getName());
    }

    /**
     * Removes a species from the ecosystem.
     *
     * @param species the species to remove.
     */
    public void removeEspece(Species species) {
        listOfSpecies.remove(species);
        System.out.println("Espèce supprimée : " + species.getName());
    }
}
