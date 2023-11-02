package org.example;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class ExtraBerekenerTest {
    // Unit tests
    @Test
    public void testCheckPostcodeWithValidPostcode() {
        assertTrue(extraBerekener.checkPostcode("1234"));
    }

    @Test
    public void testCheckPostcodeWithInvalidPostcode() {
        assertFalse(extraBerekener.checkPostcode("9679"));
        assertFalse(extraBerekener.checkPostcode("9681"));
        assertFalse(extraBerekener.checkPostcode("9682"));
    }

    @Test
    public void testRenteVastePeriode() {
        assertEquals(0.02, extraBerekener.renteVastePeriode(1), 0.001);
        assertEquals(0.03, extraBerekener.renteVastePeriode(5), 0.001);
        assertEquals(0.035, extraBerekener.renteVastePeriode(10), 0.001);
        assertEquals(0.045, extraBerekener.renteVastePeriode(20), 0.001);
        assertEquals(0.05, extraBerekener.renteVastePeriode(30), 0.001);
    }

    @Test
    public void testRenteVastePeriodeWithInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> extraBerekener.renteVastePeriode(15));
    }

    @Test
    public void testMaxTelenenBedragWithoutStudieschuld() {
        double expected = 12 * 4.25 * 1000.0; // Assuming a monthly income of 1000.0
        assertEquals(expected, extraBerekener.maxTelenenBedrag(1000.0, false), 0.001);
    }

    @Test
    public void testMaxTelenenBedragWithStudieschuld() {
        double expected = 12 * 4.25 * 1000.0 * 0.75; // Assuming a monthly income of 1000.0 and hasStudieschuld = true
        assertEquals(expected, extraBerekener.maxTelenenBedrag(1000.0, true), 0.001);
    }
}