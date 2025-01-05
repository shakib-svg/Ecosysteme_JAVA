package test;

import com.example.projectfx.resources.Grass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassTest {

    @Test
    void testConsumption() {
        Grass grass = new Grass(50, 0.2);
        grass.consume(10);
        assertEquals(40, grass.getQuantity(), "Grass quantity should reduce by 10 after consumption.");
    }

    @Test
    void testRegeneration() {
        Grass grass = new Grass(50, 0.2);
        grass.regenerate();
        assertEquals(60, grass.getQuantity(), "Grass quantity should increase by 10% of the current quantity.");
    }
}
