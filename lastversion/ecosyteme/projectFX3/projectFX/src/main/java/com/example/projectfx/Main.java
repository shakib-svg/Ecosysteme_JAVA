package com.example.projectfx;

import com.example.projectfx.ecosystem.*;
import com.example.projectfx.resources.*;
import com.example.projectfx.species.*;
import javafx.scene.image.ImageView;

public class Main {
    public static void main(String[] args) {
        // Initialize environments
        Air airEnvironment = new Air(20.0); // High wind speed
        Forest forestEnvironment = new Forest(0.9); // Dense forest
        Ocean oceanEnvironment = new Ocean(35.0); // Standard salinity

        // Initialize resources
        Grass grass = new Grass(100, new Positionn(5, 10), 0.2,null);
        Fruits fruits = new Fruits(50, new Positionn(15, 20), 0.15,null);
        Algae algae = new Algae(200, new Positionn(25, 30), 0.3,null);

        // Add resources to respective environments
        forestEnvironment.ajouterResource(grass);
        forestEnvironment.ajouterResource(fruits);
        oceanEnvironment.ajouterResource(algae);

        // Initialize species
        Rabbit rabbit1 = new Rabbit("Fluffy", 50, new Positionn(3, 8), forestEnvironment, 2.0, 10,null);
        Rabbit rabbit2 = new Rabbit("Hopper", 40, new Positionn(4, 9), forestEnvironment, 2.5, 12,null);
        Wolf wolf = new Wolf("Alpha", 70, new Positionn(6, 7), forestEnvironment, true, 15, null);
        Fish fish1 = new Fish("Nemo", 40, new Positionn(20, 25), oceanEnvironment, 3.0, 5, null);
        Species fish2 = new Fish("Dory", 50, new Positionn(22, 27), oceanEnvironment, 3.5, 6, null);
        Shark shark = new Shark("Bruce", 100, new Positionn(24, 28), oceanEnvironment, 50, 5.0, 8, null);
        Species crow = new Crow("Blackwing", 60, new Positionn(10, 15), airEnvironment, 12, "Black", null);
        Canari canari = new Canari("YellowBeak", 30, new Positionn(8, 12), airEnvironment, 8, "Yellow", null);

        // Add species to respective environments
        forestEnvironment.ajouterEspece(rabbit1);
        forestEnvironment.ajouterEspece(rabbit2);
        forestEnvironment.ajouterEspece(wolf);
        oceanEnvironment.ajouterEspece(fish1);
        oceanEnvironment.ajouterEspece(fish2);
        oceanEnvironment.ajouterEspece(shark);
        airEnvironment.ajouterEspece(crow);
        airEnvironment.ajouterEspece(canari);

        // Create the ecosystem
        Ecosystem ecosystem = new Ecosystem();
        ecosystem.ajouterEnvironnement(airEnvironment);
        ecosystem.ajouterEnvironnement(forestEnvironment);
        ecosystem.ajouterEnvironnement(oceanEnvironment);

        // Display initial ecosystem state
        System.out.println("\n--- Initial State of the Ecosystem ---\n");
        ecosystem.afficherEcosysteme();

        // Simulate ecosystem dynamics with specific actions
        System.out.println("\n--- Starting Simulation ---\n");
        for (int i = 0; i < 6; i++) { // Run 6 simulation cycles
            System.out.println("\nCycle " + (i + 1) + ":");

            // Specific actions for demonstration
            switch (i) {
                case 0:
                    rabbit1.eat(grass);
                    wolf.hunt(rabbit1);
                    fish1.eat(algae);
                    break;
                case 1:
                    rabbit2.sleep();
                    shark.hunt(fish1);
                    crow.eat(fruits);
                    canari.detecterPredateur();
                    break;
                case 2:
                    wolf.move("north");
                    canari.move("south");
                    fish2.sleep();
                    crow.move("east");
                    rabbit1.flee();
                    break;
                case 3:
                    rabbit1.reproduce();
                    shark.sleep();
                    crow.reproduce();
                    wolf.hunt(rabbit2);
                    fish2.eat(algae);
                    crow.move("west");
                    break;
                case 4:
                    rabbit2.wakeUp();
                    fish1.move("east");
                    wolf.hunt(rabbit2);
                    shark.move("west");
                    canari.sleep();
                    crow.eat(fruits);
                    canari.detecterPredateur();
                    break;
                case 5:
                    wolf.hunt(rabbit1);
                    fish1.move("north");
                    shark.hunt(fish2);
                    rabbit2.flee();
                    canari.reproduce();
                    crow.sleep();
                    break;
                default:
                    System.out.println("No specific actions for this cycle.");
                    break;
            }

            // Additional dynamic interactions
            if (i % 2 == 0) {
                shark.hunt(fish2);
                wolf.hunt(rabbit1);
                canari.move("random");
            } else {
                crow.eat(fruits);
                fish1.eat(algae);
                rabbit2.detecterPredateur();
            }

            ecosystem.lancerSimulation();
            ecosystem.afficherEcosysteme();
        }

        System.out.println("\n--- Simulation Complete ---\n");
    }
}

