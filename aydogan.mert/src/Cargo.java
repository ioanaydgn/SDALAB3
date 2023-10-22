public class Cargo extends Nava {
    private int capacitateIncarcare;

    public Cargo(String nume, String pavilion, int capacitateIncarcare) {
        super(nume, pavilion);
        this.capacitateIncarcare = capacitateIncarcare;
    }

    @Override
    public void utilizare() {
        System.out.println("Transport marfuri");
    }

    @Override
    public int capacitate() {
        return capacitateIncarcare;
    }

    @Override
    public String toString() {
        return "Cargo - nume=" + getNume() + ", pavilion=" + pavilion + ", capacitateIncarcare=" + capacitateIncarcare;
    }
}
