package test;

import com.example.projectfx.ecosystem.Environment;
import com.example.projectfx.ecosystem.Positionn;
import com.example.projectfx.resources.Grass;
import com.example.projectfx.species.Rabbit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RabbitTest {

    @Test
    void testEatingGrass() {
        Environment mockEnvironment = null; // Mock environment for testing
        Positionn position = new Positionn(3, 3);
        Rabbit rabbit = new Rabbit("TestRabbit", 30, position, mockEnvironment, 2.0, 12);
        Grass grass = new Grass(50, 0.2);

        rabbit.eat(grass);
        assertEquals(50, rabbit.getEnergy(), "Energy should increase by 15 after eating grass.");
        assertEquals(30, grass.getQuantity(), "Grass quantity should decrease by 10.");
    }

    @Test
    void testFlee() {
        Environment mockEnvironment = null; // Mock environment for testing
        Positionn position = new Positionn(3, 3);
        Rabbit rabbit = new Rabbit("TestRabbit", 30, position, mockEnvironment, 2.0, 12);

        rabbit.flee();
        assertEquals(20, rabbit.getEnergy(), "Energy should decrease by 10 after fleeing.");
    }
}

