import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Arrays;


public class Flota {
    private Nava[] nave;
    private int nrNave;

    public Flota() {
        nave = new Nava[10];
        nrNave = 0;
    }

    public Flota(String numeFisier) {
        this();
        try {
            Scanner scanner = new Scanner(new File(numeFisier));
            int numarNave = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < numarNave; i++) {
                String linie = scanner.nextLine();
                String[] campuri = linie.split(", ");
                if (campuri.length < 3) {
                    continue;
                }
                String nume = campuri[0];
                String pavilion = campuri[1];
                String tipNava = campuri[2];

                Nava nava = null;

                if ("NavaCroaziera".equals(tipNava)) {
                    int nrPasageri = Integer.parseInt(campuri[3]);
                    nava = new NavaCroaziera(nume, pavilion, nrPasageri);
                }
                if ("Cargo".equals(tipNava)) {
                    int capacitate = Integer.parseInt(campuri[3]);
                    nava = new Cargo(nume, pavilion, capacitate);
                }
                if ("Feribot".equals(tipNava)) {
                    int nrPasageri = Integer.parseInt(campuri[3]);
                    int capacitate = Integer.parseInt(campuri[4]);
                    nava = new Feribot(nume, pavilion, nrPasageri, capacitate);
                }

                if (nava != null) {
                    adaugaNava(nava);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit: "+e.getMessage());
        }
    }

    public void adaugaNava(Nava x) {
        if (nrNave == nave.length) {
            nave = Arrays.copyOf(nave, 2 * nave.length);
        }
        nave[nrNave] = x;
        nrNave++;
    }

    public Nava[] getNave() {
        return nave;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Flota:\n");
        for (int i = 0; i < nrNave; i++) {
            sb.append(i + 1).append(". ").append(nave[i]).append("\n");
        }
        return sb.toString();
    }

    public String toStringFlota(boolean dupaNume) {
        StringBuilder sb = new StringBuilder("Flota:\n");

        Nava[] sortedNave = Arrays.copyOf(nave, nrNave);
        Arrays.sort(sortedNave, new Comparator<Nava>() {
            @Override
            public int compare(Nava nava1, Nava nava2) {
                if (!dupaNume) {
                    int pavilionCompare = nava1.pavilion.compareTo(nava2.pavilion);
                    if (pavilionCompare != 0) {
                        return pavilionCompare;
                    }
                }
                return nava1.nume.compareTo(nava2.nume);
            }
        });

        for (int i=0; i < sortedNave.length; i++) {
            sb.append(i + 1).append(". ").append(sortedNave[i]).append("\n");
        }
        return sb.toString();
    }

    public void utilizare() {
        System.out.println("Utilizare flota:");
        for (int i = 0; i < nrNave; i++) {
            System.out.print(nave[i].getNume() + " - ");
            nave[i].utilizare();
        }
    }

    public static void main(String[] args) {
        Flota flota1 = new Flota();
        NavaCroaziera sv = new NavaCroaziera("Suceava", "RO", 1000);
        System.out.println(sv + "\nUtilizare:");
        sv.utilizare();
        System.out.println();
        flota1.adaugaNava(sv);
        flota1.adaugaNava(new NavaCroaziera("Victoria", "RO", 5000));
        System.out.println(flota1);

        Flota flota2 = new Flota("flota.txt");
        System.out.println(flota2);
        flota2.utilizare();

        System.out.println("\nSorted by name:");
        System.out.println(flota2.toStringFlota(true));

        System.out.println("\nSorted by pavilion:");
        System.out.println(flota2.toStringFlota(false));
    }
}
