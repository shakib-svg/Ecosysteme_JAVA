package test;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import com.example.projectfx.species.Rabbit;
import com.example.projectfx.species.Species;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeciesTest {

    @Test
    void testEnergyReduction() {
        Environment mockEnvironment = null; // Mock environment for testing
        Positionn position = new Positionn(5, 5);
        Species testSpecies = new Rabbit("TestRabbit", 50, position, mockEnvironment, 1.5, 10);

        testSpecies.reduceEnergy(20);
        assertEquals(30, testSpecies.getEnergy(), "Energy should be reduced to 30.");
    }

    @Test
    void testDeathWhenEnergyZero() {
        Environment mockEnvironment = null; // Mock environment for testing
        Positionn position = new Positionn(5, 5);
        Species testSpecies = new Rabbit("TestRabbit", 10, position, mockEnvironment, 1.5, 10);

        testSpecies.reduceEnergy(10);
        assertFalse(testSpecies.isAlive(), "Species should be dead when energy is zero.");
    }

    @Test
    void testSleeping() {
        Environment mockEnvironment = null; // Mock environment for testing
        Positionn position = new Positionn(5, 5);
        Species testSpecies = new Rabbit("TestRabbit", 50, position, mockEnvironment, 1.5, 10);

        testSpecies.sleep();
        assertTrue(testSpecies.isSleeping(), "Species should be sleeping.");
        assertEquals(70, testSpecies.getEnergy(), "Energy should increase during sleep.");
    }
}
