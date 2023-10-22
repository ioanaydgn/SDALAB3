import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

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

            while (scanner.hasNextLine()) {
                String linie = scanner.nextLine();
                String[] campuri = linie.split(", ");

                if (campuri.length < 4) {
                    System.out.println("*** Eroare. Nr. parametri < 4 la linia \"" + linie + "\"");
                    continue;
                }

                String nume = campuri[0];
                String pavilion = campuri[1];
                String tipNava = campuri[2];

                Nava nava = null;

                try {
                    if ("NavaCroaziera".equals(tipNava)) {
                        int nrPasageri = Integer.parseInt(campuri[3]);
                        nava = new NavaCroaziera(nume, pavilion, nrPasageri);
                    } else if ("Cargo".equals(tipNava)) {
                        int capacitate = Integer.parseInt(campuri[3]);
                        nava = new Cargo(nume, pavilion, capacitate);
                    } else if ("Feribot".equals(tipNava)) {
                        int nrPasageri = Integer.parseInt(campuri[3]);
                        int capacitate = Integer.parseInt(campuri[4]);
                        nava = new Feribot(nume, pavilion, nrPasageri, capacitate);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("*** Eroare. Numar eronat la linia \"" + linie + "\"");
                    continue;
                }

                if (nava != null) {
                    adaugaNava(nava);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit: " + e.getMessage());
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

    public void afiseazaFlota() {
        System.out.println("Flota:");
        for (int i = 0; i < nrNave; i++) {
            System.out.println((i + 1) + ". " + nave[i]);
        }
    }

    public void sorteazaFlota(int tipSortare) {


        Nava[] sortedNave = Arrays.copyOf(nave, nrNave);

        Arrays.sort(sortedNave, new Comparator<Nava>() {
            @Override
            public int compare(Nava nava1, Nava nava2) {
                switch (tipSortare) {
                    case 0:
                        return nava1.pavilion.compareTo(nava2.pavilion);
                    case 1:
                        return nava1.nume.compareTo(nava2.nume);
                    case 2:
                        return Integer.compare(nava1.capacitate(), nava2.capacitate());
                    case 3:
                        int pavilionCompare = nava1.pavilion.compareTo(nava2.pavilion);
                        if (pavilionCompare != 0) {
                            return pavilionCompare;
                        }
                        return nava1.nume.compareTo(nava2.nume);
                    case 4:
                        int pavilionCompare2 = nava1.pavilion.compareTo(nava2.pavilion);
                        if (pavilionCompare2 != 0) {
                            return pavilionCompare2;
                        }
                        int tipCompare = nava1.getClass().getSimpleName().compareTo(nava2.getClass().getSimpleName());
                        if (tipCompare != 0) {
                            return tipCompare;
                        }
                        return Integer.compare(nava1.capacitate(), nava2.capacitate());
                    default:
                        return 0;
                }
            }
        });

        afiseazaFlota(sortedNave);
    }

    public void afiseazaFlota(Nava[] navaList) {
        System.out.println("Flota:");

        for (int i = 0; i < navaList.length; i++) {
            System.out.println((i + 1) + ". " + navaList[i]);
        }
    }

    private void utilizare() {
        System.out.println("Utilizare flota:");
        for (int i = 0; i < nrNave; i++) {
            System.out.print(nave[i].getNume() + " - ");
            nave[i].utilizare();
        }
    }

    public void toString(int tipSortare) {
        switch (tipSortare) {
            case 0:
                System.out.println("\nSortare dupa Pavilion");
                break;
            case 1:
                System.out.println("\nSortare dupa Nume");
                break;
            case 2:
                System.out.println("\nSortare dupa Tip Si Capacitate");
                break;
            case 3:
                System.out.println("\nSortare dupa Pavilion Si Nume");
                break;
            case 4:
                System.out.println("\nSortare dupa Pavilion Si Tip Si Capacitate");
                break;
            default:
                System.out.println("Sortare invalida");
                return;
        }
        sorteazaFlota(tipSortare);
    }


        public static void main(String[] args) {
        Flota flota1 = new Flota();
        NavaCroaziera sv = new NavaCroaziera("Suceava", "RO", 1000);
        System.out.println(sv + "\nUtilizare:");
        sv.utilizare();
        System.out.println();
        flota1.adaugaNava(sv);
        flota1.adaugaNava(new NavaCroaziera("Victoria", "RO", 5000));


        Flota flota2 = new Flota("flota.txt");
        flota2.afiseazaFlota();
        flota2.utilizare();

        flota2.toString(0);
        flota2.toString(1);
        flota2.toString(2);
        flota2.toString(3);
        flota2.toString(4);
    }
}
