package aed;

public class Handle implements Comparable<Handle> {
    private final Traslado traslado;
    private int posOtroHeap;
    private int posPropioHeap;

    public Handle(Traslado traslado) {
        this.traslado = traslado; //? Complejidad O(1);
        this.posPropioHeap = -1; //? Complejidad O(1);
        this.posOtroHeap = -1; //? Complejidad O(1);
    }

    public Traslado getTraslado() {
        return this.traslado; //? Complejidad O(1);
    }

    public int getPosPropioHeap() {
        return this.posPropioHeap; //? Complejidad O(1);
    }

    public int getPosOtroHeap() {
        return this.posOtroHeap; //? Complejidad O(1);
    }

    public void setPosPropioHeap(int pos) {
        this.posPropioHeap = pos; //? Complejidad O(1);
    }

    public void setPosOtroHeap(int pos) {
        this.posOtroHeap = pos; //? Complejidad O(1);
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