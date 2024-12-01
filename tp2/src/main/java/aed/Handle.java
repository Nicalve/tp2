package aed;

public class Handle implements Comparable<Handle> {
    private Traslado traslado;
    private int posOtroHeap;
    private int posPropioHeap;

    public Handle(Traslado traslado) {
        this.traslado = traslado;
        this.posPropioHeap = -1;
        this.posOtroHeap = -1;
    }

    public Traslado getTraslado() {
        return this.traslado;
    }

    public int getPosPropioHeap() {
        return this.posPropioHeap;
    }

    public int getPosOtroHeap() {
        return this.posOtroHeap;
    }

    public void setPosPropioHeap(int pos) {
        this.posPropioHeap = pos;
    }

    public void setPosOtroHeap(int pos) {
        this.posOtroHeap = pos;
    }


    @Override
    public int compareTo(Handle other) {
        return Integer.compare(this.traslado.gananciaNeta, other.traslado.gananciaNeta);
    }
    

    @Override
    public String toString() {
        return "Handle {Traslado: " + traslado + ", posPropioHeap: " + posPropioHeap + ", posOtroHeap: " + posOtroHeap + "}";
    }
}
