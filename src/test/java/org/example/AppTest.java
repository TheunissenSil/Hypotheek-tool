package org.example;

import static org.junit.Assert.*;
import org.junit.Test;

public class AppTest {

    @Test
    public void testMaxHypotheekMetPartnerEnStudieschuld() {
        double maxHypotheek = App.berekenMaxTeLenenBedrag(5000, true, 30,  "1234");
        assertEquals(478125.00, maxHypotheek, 0.01);
    }

    @Test
    public void testMaxHypotheekZonderPartnerEnGeenStudieschuld() {
        double maxHypotheek = App.berekenMaxTeLenenBedrag(5000, false, 30,  "1234");
        assertEquals(637500.00, maxHypotheek, 0.01);
    }

    @Test
    public void testMaxHypotheekMetOnjuisteRentevastePeriode() {
        try {
            App.berekenMaxTeLenenBedrag(4000, false, 18,  "1234");
            fail("Moet een IllegalArgumentException genereren voor ongeldige rentevaste periode");
        } catch (IllegalArgumentException e) {
            // Verwachtte uitzondering
        }
    }

    @Test
    public void testMaxHypotheekMetAardbevingsgebied() {
        double maxHypotheek = App.berekenMaxTeLenenBedrag(5500, false, 30,  "9679");
        assertEquals(0.0, maxHypotheek, 0.01);
    }

    @Test
    public void testMaxHypotheekMetVerkeerdeInvoer() {
        try {
            App.berekenMaxTeLenenBedrag(-1000, false, 30,  "1234");
            fail("Moet een IllegalArgumentException genereren voor ongeldig maandinkomen");
        } catch (IllegalArgumentException e) {
            // Verwachtte uitzondering
        }
    }
}
