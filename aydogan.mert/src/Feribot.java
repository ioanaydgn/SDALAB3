public class Feribot extends Nava {
    private int nrAuto;
    private int nrPasageri;

    public Feribot(String nume, String pavilion, int nrAuto, int nrPasageri) {
        super(nume, pavilion);
        this.nrAuto = nrAuto;
        this.nrPasageri = nrPasageri;
    }

    @Override
    public void utilizare() {
        System.out.println("Transport persoane si masini");
    }

    @Override
    public int capacitate() {
        return nrAuto;
    }

    @Override
    public String toString() {
        return "Feribot - nume=" + getNume() + ", pavilion=" + pavilion + ", nrAuto=" + nrAuto + ", nrPasageri=" + nrPasageri;
    }
}
