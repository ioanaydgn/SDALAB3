abstract class Nava {
    String nume;
    String pavilion;

    public Nava(String nume, String pavilion) {
        this.nume = nume;
        this.pavilion = pavilion;
    }

    public abstract void utilizare();

    @Override
    public String toString() {
        return "Nava - nume=" + nume + ", pavilion=" + pavilion;
    }

    public String getNume() {
        return nume;
    }
}

