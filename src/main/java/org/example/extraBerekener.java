package org.example;

public class extraBerekener {
    public static boolean checkPostcode(String postcode) {
        // Check voor aardbevinggebied
        if (postcode.equals("9679") || postcode.equals("9681") || postcode.equals("9682")) {
            return false;
        }
        return true;
    }

    public static double renteVastePeriode(int rentevastePeriode) {
        // Kijk wat de rentevasteperiode is
        double rentePercentage = switch (rentevastePeriode) {
            case 1 -> 0.02;
            case 5 -> 0.03;
            case 10 -> 0.035;
            case 20 -> 0.045;
            case 30 -> 0.05;
            default -> throw new IllegalArgumentException("Ongeldige rentevaste periode");
        };
        return rentePercentage;
    }

    public static double maxTelenenBedrag(double maandinkomen, boolean heeftStudieschuld) {
        // Bereken het maximale te lenen bedrag
        double maxTeLenen = maandinkomen * 12 * 4.25;

        // Check of er een studieschuld is en pas het maximale te lenen bedrag aan
        if (heeftStudieschuld == true) {
            maxTeLenen = maxTeLenen * 0.75;
        }
        return maxTeLenen;
    }
}
