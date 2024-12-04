package aed;

public class Handle implements Comparable<Handle> {
    private final Traslado traslado;
    private int posOtroHeap;
    private int posPropioHeap;
    private Handle contraparte; //referencia al handle en el otro heap

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

    public Handle getContraparte() {
        return this.contraparte;
    }

    public void setContraparte(Handle contraparte) {
        this.contraparte = contraparte;
    }
    
    public void actualizar() {
        this.contraparte.setPosOtroHeap(this.posPropioHeap);
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