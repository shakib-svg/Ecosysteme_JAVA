package test;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Forest;
import com.example.projectfx.ecosystem.Positionn;
import com.example.projectfx.resources.Grass;
import com.example.projectfx.species.Rabbit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnvironmentTest {

       @Test
    void testRemoveSpecies() {
        Environment forest = new Forest(0.1);
        Positionn position = new Positionn(5, 5);
        Rabbit rabbit = new Rabbit("TestRabbit", 30, position, forest, 2.0, 12,null);

        forest.ajouterEspece(rabbit);
        forest.retirerEspece(rabbit);
        assertFalse(forest.getSpeciesList().contains(rabbit), "Rabbit should be removed from the environment.");
    }

    @Test
    void testSeasonalChanges() {
        Forest forest = new Forest(0.1);
        Grass grass = new Grass(50, new Positionn(2,2),12,null);

        forest.ajouterResource(grass);
        forest.mettreAJourSaison("Spring");
        assertEquals(60, grass.getQuantity(), "Grass should regenerate by 10 during Spring.");
    }
}
