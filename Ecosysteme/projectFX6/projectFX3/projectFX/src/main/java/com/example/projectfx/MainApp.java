package com.example.projectfx;

import com.example.projectfx.exceptions.EcosystemException;
import com.example.projectfx.species.*;
import com.example.projectfx.resources.*;
import com.example.projectfx.ecosystem.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.application.Platform;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainApp extends Application {
    private ImageView forestView;
    private Label interactionLabel;
    private Label resourceLabel;
    private Label statisticsLabel;  // New label for displaying statistics

    private final List<Positionn> positions = new ArrayList<>();
    private final int minDistance = 50;
    private Species selectedSpecies = null;
    private Forest forest;
    private final Random random = new Random();

    // Configuration constants
    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 768;
    private static final int MOVEMENT_INTERVAL = 1000; // milliseconds
    private static final int INITIAL_RABBITS = 16;
    private static final int INITIAL_WOLVES = 8;
    private static final int INITIAL_GRASS = 28;
    private static final int INITIAL_FRUITS = 10;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            forest = new Forest(0.1);
            initializeSpecies();
            initializeResource();
            initializeUI(primaryStage);
            startSimulationTimer();
        } catch (Exception e) {
            showError("Error initializing the application", e);
        }
    }

    private void initializeSpecies() {
        // Use constants for initial populations
        for (int i = 0; i < INITIAL_RABBITS; i++) {
            createSpecies(new Rabbit("Rabbit" + (i + 1), 80, createPositonn(minDistance), forest, 2.0, 10, new ImageView()));
        }

        for (int i = 0; i < INITIAL_WOLVES; i++) {
            createSpecies(new Wolf("Wolf" + (i + 1), 100, createPositonn(minDistance), forest, true, 15, new ImageView()));
        }
        for (int i = 0; i < INITIAL_WOLVES; i++) {
            createSpecies(new Human("human" + (i + 1), 100, createPositonn(minDistance), forest,5,5,new ImageView()));
        }
        for (int i = 0; i < INITIAL_WOLVES; i++) {
            createSpecies(new Bear("Bear" + (i + 1), 70, createPositonn(minDistance), forest, 20, 5,15, new ImageView()));
        }
    }

    private void initializeResource() {
        try {
            for (int i = 0; i < INITIAL_RABBITS; i++) {
                createSpecies(new Rabbit("Rabbit" + (i + 1), 80, createPositonn(minDistance), forest, 2.0, 10, new ImageView()));
            }

            for (int i = 0; i < INITIAL_WOLVES; i++) {
                createSpecies(new Wolf("Wolf" + (i + 1), 100, createPositonn(minDistance), forest, true, 15, new ImageView()));
            }
            for (int i = 0; i < INITIAL_WOLVES; i++) {
                createSpecies(new Human("human" + (i + 1), 100, createPositonn(minDistance), forest, 5, 5, new ImageView()));
            }
            for (int i = 0; i < INITIAL_WOLVES; i++) {
                createSpecies(new Bear("Bear" + (i + 1), 70, createPositonn(minDistance), forest, 20, 5, 15, new ImageView()));
            }
        } catch (Exception e) {
            showError("Error initializing species", e);
        }
    }

    private Positionn createPositonn(int minDistance) {
        return new Positionn(0, WINDOW_WIDTH - 1, 0, WINDOW_HEIGHT - 1, positions, minDistance);
    }

    private void createSpecies(Species species) {
        positions.add(species.getPosition());
        forest.ajouterEspece(species);
        species.getImageView().setFitWidth(getFitWidth(species));
        species.getImageView().setPreserveRatio(true);
        setupSpeciesInteraction(species);
    }

    private void createResource(Resource resource) throws EcosystemException {
        try {
            positions.add(resource.getPosition());
            forest.ajouterResource(resource);
            resource.getImageView().setFitWidth(getFitResourceWidth(resource));
            resource.getImageView().setPreserveRatio(true);
            setupResourceInteraction(resource);
        } catch (Exception e) {
            throw new EcosystemException("Error creating resource", e);
        }
    }

    private void setupSpeciesInteraction(Species species) {
        species.getImageView().setOnMouseClicked(event -> {
            selectedSpecies = species;
            updateInteractionLabel(species);
        });
    }

    private void updateInteractionLabel(Species species) {
        String status = "Selected: " + species.getName() +
                "\nEnergy: " + species.getEnergy() +
                "\nPosition: " + species.getPosition();
        interactionLabel.setText(status);
    }

    private void setupResourceInteraction(Resource resource) {
        resource.getImageView().setOnMouseClicked(event -> {
            String status = "Selected: " + resource.getType() +
                    "\nQuantity: " + resource.getQuantity() +
                    "\nPosition: " + resource.getPosition();
            interactionLabel.setText(status);
        });
    }

    private double getFitWidth(Species species) {
        return switch (species) {
            case Rabbit ignored -> 50;
            case Wolf ignored -> 80;
            default -> 100;
        };
    }

    private double getFitResourceWidth(Resource resource) {
        if (resource instanceof Grass) {
            return 100 + ((Grass)resource).getQuantity();
        }
        return resource instanceof Fruits ? 50 : 100;
    }

    private void startSimulationTimer() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(MOVEMENT_INTERVAL),
                e -> {
                    moveAnimalsRandomly();
                    removeDeadAnimals();
                    processInteractions();
                    updateStatistics();
                }
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void moveAnimalsRandomly() {
        Platform.runLater(() -> {
            for (Species animal : new ArrayList<>(forest.getSpeciesList())) {
                if (!animal.checkDeath()) {
                    moveAnimal(animal);
                }
            }
        });
    }

    private void moveAnimal(Species animal) {
        int dx = random.nextInt(61) - 20;
        int dy = random.nextInt(61) - 20;

        int currentX = animal.getPosition().getX();
        int currentY = animal.getPosition().getY();

        // Boundary checking and movement logic
        int newX = Math.min(Math.max(currentX + (currentY > 100 && currentX < 700 ? dx : -dx), 0), WINDOW_WIDTH - 1);
        int newY = Math.min(Math.max(currentY + dy, 0), WINDOW_HEIGHT - 1);

        animal.getPosition().setX(newX);
        animal.getPosition().setY(newY);
        setPositions();
        animal.move(animal.getPosition().toString());
    }
    private void setPositions() {
        forest.getSpeciesList().forEach(species -> {
            species.getImageView().setTranslateX(species.getPosition().getX());
            species.getImageView().setTranslateY(species.getPosition().getY());
        });
        forest.getResources().forEach(resource -> {
            resource.getImageView().setTranslateX(resource.getPosition().getX());
            resource.getImageView().setTranslateY(resource.getPosition().getY());
        });
    }

    private void removeDeadAnimals() {
        Platform.runLater(() -> {
            Iterator<Species> iterator = forest.getSpeciesList().iterator();
            while (iterator.hasNext()) {
                Species animal = iterator.next();
                if (animal.getEnergy() <= 0 || animal.checkDeath() || !animal.isAlive()) {
                    handleDeadAnimal(animal, iterator);
                }
            }
        });
    }

    private void handleDeadAnimal(Species animal, Iterator<Species> iterator) {
        positions.remove(animal.getPosition());
        animal.getImageView().setVisible(false);
        iterator.remove();
        interactionLabel.setText(animal.getName() + " has died.");
        if (selectedSpecies == animal) {
            selectedSpecies = null;
        }
    }

    private void processInteractions() {
        Platform.runLater(() -> {
            processEating();
            processHunting();
        });
    }

    private void processEating() {
        List<Species> species = new ArrayList<>(forest.getSpeciesList());
        List<Resource> resources = new ArrayList<>(forest.getResources());

        for (Species animal : species) {
            if (!animal.checkDeath() && animal instanceof Rabbit) {
                for (Resource resource : resources) {
                    if (resource instanceof Grass &&
                            animal.getPosition().calculateDistance(resource.getPosition()) < 100) {
                        animateEat(animal, resource);
                        break;
                    }
                }
            }
        }
    }
    private void animateEat(Species animal, Resource resource) {

        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), animal.getImageView());
        transition.setToX(resource.getPosition().getX());
        transition.setToY(resource.getPosition().getY());

        transition.setOnFinished(e -> {
            interactionLabel.setText("The "+animal.getName()+" ate the resource");
            positions.remove(animal.getPosition());
            animal.setPosition(resource.getPosition());
            resource.getImageView().setVisible(false);
            forest.getResources().remove(resource);
            setPositions();
            resetSelections();

        });

        if (animal.eat(resource)){
            transition.play();
        }

        setPositions();
    }
    public void resetSelections() {
        selectedSpecies = null;
    }

    private void processHunting() {
        List<Species> species = new ArrayList<>(forest.getSpeciesList());

        for (Species predator : species) {
            if (predator instanceof Wolf && !predator.checkDeath()) {
                for (Species prey : species) {
                    if (prey instanceof Rabbit && !prey.checkDeath() &&
                            predator.getPosition().calculateDistance(prey.getPosition()) < 100) {
                        animateHunt((Carnivore) predator, prey);
                        break;
                    }
                }
            }
        }
    }
    private void animateHunt(Carnivore animal, Species species) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1.5), animal.getImageView());
        transition.setToX(species.getPosition().getX());
        transition.setToY(species.getPosition().getY());

        transition.setOnFinished(e -> {
            interactionLabel.setText("The "+animal.getName()+" ate the "+species.getName()+"!"+ " energie restant : " + animal.getEnergy());
            positions.remove(animal.getPosition());
            animal.setPosition(species.getPosition());
            species.getImageView().setVisible(false);
            forest.getSpeciesList().remove(species);
            setPositions();
            resetSelections();
        });

        if (animal.hunt(species)) {

            transition.play();
        } else {
            interactionLabel.setText("The wolf missed the "+species.getName()+".");
            resetoPosition(animal);
        }
    }
    private void resetoPosition(Species species) {
        species.getImageView().setTranslateX(species.getPosition().getX());
        species.getImageView().setTranslateY(species.getPosition().getY());
    }

    private void updateStatistics() {
        Platform.runLater(() -> {
            long rabbits = forest.getSpeciesList().stream().filter(s -> s instanceof Rabbit).count();
            long wolves = forest.getSpeciesList().stream().filter(s -> s instanceof Wolf).count();
            long grass = forest.getResources().stream().filter(r -> r instanceof Grass).count();

            statisticsLabel.setText(String.format(
                    "Population Statistics:\nRabbits: %d\nWolves: %d\nGrass: %d",
                    rabbits, wolves, grass
            ));
        });
    }

    private void initializeUI(Stage primaryStage) {
        // Create main layout
        BorderPane root = new BorderPane();

        // Initialize forest view
        forestView = new ImageView();
        forestView.setFitWidth(WINDOW_WIDTH);
        forestView.setFitHeight(WINDOW_HEIGHT);
        forestView.setPreserveRatio(false);
        updateForestImage("forest_summer.png");

        // Initialize labels
        interactionLabel = new Label("Click to select something.");
        resourceLabel = new Label("Resource statistics will appear here.");
        statisticsLabel = new Label("Population statistics will appear here.");

        styleLabels();

        // Create UI components
        MenuBar menuBar = createSeasonMenuBar();
        VBox topBox = new VBox(10, menuBar, interactionLabel);
        VBox rightBox = new VBox(10, statisticsLabel, resourceLabel);
        rightBox.setStyle("-fx-padding: 10; -fx-background-color: rgba(255,255,255,0.8);");

        // Create main game pane
        Pane gamePane = createGamePane();

        // Assemble layout
        root.setTop(topBox);
        root.setCenter(gamePane);
        root.setRight(rightBox);

        // Create scene
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        forestView.fitWidthProperty().bind(scene.widthProperty());
        forestView.fitHeightProperty().bind(scene.heightProperty());
        setupKeyBindings(scene);

        // Configure stage
        primaryStage.setTitle("Ecosystem Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initial updates
        updateImages();
        setPositions();
        updateimageview();
    }
    private void updateimageview() {
        for (Species animal : forest.getSpeciesList()) {
            if (animal instanceof Carnivore) {
                animal.getImageView().setOnMouseClicked(e -> {
                    if (selectedSpecies != null) {
                        animateHunt((Carnivore) animal, selectedSpecies);
                    } else {
                        interactionLabel.setText("Selected: " + animal.getName() + " Energy: " + ((Wolf)animal).getEnergy());
                    }
                });
            }
        }
    }
    private void displaySeasonDetails(String season) {
        forest.mettreAJourSaison(season);
        captureResourceDetails(season);
        updateForestImage("forest_" + season.toLowerCase() + ".png");
    }
    private void captureResourceDetails(String season) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        forest.mettreAJourSaison(season);
        System.setOut(originalOut);
        resourceLabel.setText(outputStream.toString());
    }
    private MenuBar createSeasonMenuBar() {
        Menu seasonMenu = new Menu("Seasons");

        MenuItem springItem = createSeasonMenuItem("Spring");
        MenuItem summerItem = createSeasonMenuItem("Summer");
        MenuItem autumnItem = createSeasonMenuItem("Autumn");
        MenuItem winterItem = createSeasonMenuItem("Winter");

        seasonMenu.getItems().addAll(springItem, summerItem, autumnItem, winterItem);

        MenuBar menuBar = new MenuBar(seasonMenu);
        return menuBar;
    }

    private MenuItem createSeasonMenuItem(String season) {
        MenuItem item = new MenuItem(season);
        item.setOnAction(e -> displaySeasonDetails(season));
        return item;
    }
    private void updateForestImage(String imageName) {
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/" + imageName)));
            forestView.setImage(image);
        } catch (Exception e) {
            System.err.println("Failed to load forest image: " + imageName);
            e.printStackTrace();
        }
    }
    private void setupKeyBindings(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case R:
                    if (selectedSpecies instanceof Bear && ((Bear) selectedSpecies).canReproduce(countspecies("Bear"))) {
                        ((Bear) selectedSpecies).reproduce();
                        reproduce("Bear");
                    }
                    break;
                case SPACE:
                    selectedSpecies = null;
                    interactionLabel.setText("Selection cleared.");
                    break;
            }
        });
    }
    private int countspecies(String type) {
        int count = 0;
        for (Species species : forest.getSpeciesList()) {
            if (type.equals("Bear") && species instanceof Bear) {
                count++;
            } else if (type.equals("Wolf") && species instanceof Wolf) {
                count++;
            } else if (type.equals("Rabbit") && species instanceof Rabbit) {
                count++;
            } else if (type.equals("Human") && species instanceof Human) {
                count++;
            }
        }
        return count;
    }
    private void reproduce(String type) {
        Positionn position = createPositonn(minDistance);
        Species obj = null;
        switch (type) {
            case "Bear" -> {
                obj = new Bear("Bear" + (countspecies("Bear") + 1), 80, position, forest, 50, 2.0, 10, new ImageView());
            }
            case "Wolf" -> {
                obj = new Wolf("Wolf" + (countspecies("Wolf") + 1), 50, position, forest, true, 15, new ImageView());
            }
            case "Rabbit" -> {
                obj = new Rabbit("Rabbit" + (countspecies("Rabbit") + 1), 80, position, forest, 2.0, 10, new ImageView());
            }
            case "Human" -> {
                obj = new Human("Human" + (countspecies("Human") + 1), 90, position, forest, 30, 8, new ImageView());
            }
            default -> {
                System.out.println("Unknown species type: " + type);
                return;
            }
        }
        if (obj != null) {
            createSpecies(obj);
            ((Pane) forestView.getParent()).getChildren().add(obj.getImageView());
            updateImages();
            setPositions();
            selectedSpecies=null;
            interactionLabel.setText("New "+type+" created!");
        }
    }
    private void updateImages() {
        forest.getSpeciesList().forEach(species -> {
            try {
                String imagePath = "/images/" + getAnimalImageType(species) + ".png";
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
                species.getImageView().setImage(image);
            } catch (Exception e) {
                try {
                    throw new EcosystemException("Failed to load species image: " + species.getName(), e);
                } catch (EcosystemException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        forest.getResources().forEach(resource -> {
            try {
                String imagePath = "/images/" + getResourceImageType(resource) + ".png";
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
                resource.getImageView().setImage(image);
            } catch (Exception e) {
                try {
                    throw new EcosystemException("Failed to load resource image", e);
                } catch (EcosystemException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    private String getAnimalImageType(Species species) {
        if (species instanceof Rabbit) return "rabbit";
        if (species instanceof Wolf) return "wolf";
        if (species instanceof Bear) return "bear";
        if (species instanceof Human) return "human";
        throw new IllegalArgumentException("Unknown animal type: " + species.getClass().getSimpleName());
    }

    private String getResourceImageType(Resource resource) throws EcosystemException {
        try {
            if (resource instanceof Grass) return "Grass";
            if (resource instanceof Fruits) return "Fruit";
            throw new IllegalArgumentException("Unknown resource type: " + resource.getClass().getSimpleName());
        } catch (Exception e) {
            throw new EcosystemException("Error determining resource image type", e);
        }
    }

    private void styleLabels() {
        String labelStyle = "-fx-font-size: 14px; -fx-padding: 10; -fx-background-color: rgba(255,255,255,0.8); -fx-background-radius: 5;";
        interactionLabel.setStyle(labelStyle);
        resourceLabel.setStyle(labelStyle);
        statisticsLabel.setStyle(labelStyle);
    }

    private Pane createGamePane() {
        Pane pane = new Pane();
        pane.getChildren().add(forestView);
        forest.getSpeciesList().forEach(species -> pane.getChildren().add(species.getImageView()));
        forest.getResources().forEach(resource -> pane.getChildren().add(resource.getImageView()));
        return pane;
    }



    // Additional helper methods for clarity and maintainability
    private void handleSeasonChange(String season) {
        forest.mettreAJourSaison(season);
        updateForestImage("forest_" + season.toLowerCase() + ".png");
        updateSeasonEffects(season);
    }

    private void updateSeasonEffects(String season) {
        // Implement season-specific effects
        switch (season.toLowerCase()) {
            case "winter" -> {
                forest.getSpeciesList().forEach(species -> species.setEnergy((int)(species.getEnergy() * 0.8)));
                forest.getResources().forEach(resource -> resource.setQuantity((int)(resource.getQuantity() * 0.5)));
            }
            case "spring" -> {
                reproduceSpecies("Rabbit", 2);
                forest.getResources().forEach(resource -> resource.setQuantity((int)(resource.getQuantity() * 1.5)));
            }
            case "summer" -> {
                forest.getSpeciesList().forEach(species -> species.setEnergy((int)(species.getEnergy() * 1.2)));
                reproduceSpecies("Wolf", 1);
            }
            case "autumn" -> {
                forest.getResources().forEach(resource -> resource.setQuantity((int)(resource.getQuantity() * 1.2)));
            }
        }
        updateStatistics();
    }

    private void reproduceSpecies(String type, int count) {
        for (int i = 0; i < count; i++) {
            if (random.nextDouble() < 0.5) {  // 50% chance of reproduction
                reproduce(type);
            }
        }
    }
    private void showError(String message, Exception e) {
        System.err.println(message);
        e.printStackTrace();
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(message);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        });
    }



}