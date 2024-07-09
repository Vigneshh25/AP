package roomallotment.model;

/**
 * Created by Vignesh.V on 09/07/24.
 */
public class Bench {
    private Slot A;
    private Slot B;

    public Bench(Slot a, Slot b) {
        A = a;
        B = b;
    }

    public Slot getA() {
        return A;
    }

    public void setA(Slot a) {
        A = a;
    }

    public Slot getB() {
        return B;
    }

    public void setB(Slot b) {
        B = b;
    }
}
