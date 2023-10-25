package org.example;

import java.util.Scanner;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class App {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static double berekenMaxTeLenenBedrag(double maandinkomen, boolean heeftStudieschuld, int rentevastePeriode, String postcode) {
        if (maandinkomen <= 0 || (rentevastePeriode != 1 && rentevastePeriode != 5 && rentevastePeriode != 10 && rentevastePeriode != 20 && rentevastePeriode != 30)) {
            throw new IllegalArgumentException("Ongeldige invoer");
        }

        if (postcode.equals("9679") || postcode.equals("9681") || postcode.equals("9682")) {
            return 0.0;
        }

        double rentePercentage = switch (rentevastePeriode) {
            case 1 -> 0.02;
            case 5 -> 0.03;
            case 10 -> 0.035;
            case 20 -> 0.045;
            case 30 -> 0.05;
            default -> throw new IllegalArgumentException("Ongeldige rentevaste periode");
        };

        double maxTeLenen = maandinkomen * 12 * 4.25;

        if (heeftStudieschuld == true) {
            maxTeLenen = maxTeLenen * 0.75;
        }

        double renteBedrag = maxTeLenen * (rentePercentage / 12);

        double aflossingsBedrag = maxTeLenen / (rentevastePeriode * 12);

        double totaalBedrag = renteBedrag + aflossingsBedrag;

        double totaalBetaald = totaalBedrag * rentevastePeriode * 12;

        System.out.println("Maximaal te lenen:" + df.format(maxTeLenen));
        System.out.println("Rente bedrag:" + df.format(renteBedrag));
        System.out.println("Aflossingsbedrag:" + df.format(aflossingsBedrag));
        System.out.println("Totaal maandbedrag " + df.format(totaalBedrag));

        return totaalBetaald;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welkom bij de Hypotheekberekeningstool!");
        System.out.print("Voer uw maandinkomen in: ");
        double maandinkomen = scanner.nextDouble();

        System.out.print("Heeft u een partner? (ja/nee): ");
        boolean heeftPartner = scanner.next().equalsIgnoreCase("ja");

        double partnerInkomen = 0.0; // Default partner income is 0.
        if (heeftPartner) {
            System.out.print("Voer het maandinkomen van uw partner in: ");
            partnerInkomen = scanner.nextDouble();
        }

        System.out.print("Heeft u een studieschuld? (ja/nee): ");
        boolean heeftStudieschuld = scanner.next().equalsIgnoreCase("ja");

        System.out.print("Selecteer de rentevaste periode (1/5/10/20/30 jaar): ");
        int rentevastePeriode = scanner.nextInt();

        System.out.print("Voer uw postcode in: ");
        String postcode = scanner.next();

        double maxTeLenenBedrag = berekenMaxTeLenenBedrag(maandinkomen + partnerInkomen, heeftStudieschuld, rentevastePeriode, postcode);

        System.out.println("Totaal betaald na 30 jaar: " + df.format(maxTeLenenBedrag));

        // Add more calculations and output here as needed.

        scanner.close();
    }
}
