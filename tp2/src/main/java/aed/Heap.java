package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T extends Comparable<T>>{
    private final ArrayList<T> elementos; 
    private final Comparator<T> comparador;
    

    public Heap(Comparator<T> comparador) {
        
        super();
        this.elementos = new ArrayList<>();
        this.comparador = comparador;
    }
    public Heap(T[] secuencia, Comparator<T> comparador) {

        this.elementos = new ArrayList<>();
        this.comparador = comparador;
        for (T elemento : secuencia) {

            this.elementos.add(elemento);
            heapify(); 
        }
    }

    public int encolar(T valor) {

        elementos.add(valor); 
        return siftUp(elementos.size() - 1);

    }

    public T consultarRaiz() {

        return elementos.get(0); 
    }

    public T desencolarRaiz() {
        T max = elementos.get(0); 
        T ult = elementos.remove(elementos.size() - 1); 

        if (!elementos.isEmpty()) { 

            elementos.set(0, ult); 
            siftDown(0); 
        }
        return max; 
    }

    public T desencolar(int indice){

        T desencolado = this.elementos.get(indice);
        T ult = this.elementos.get(this.elementos.size()-1);

        this.elementos.set(indice, ult);
        this.elementos.remove(this.elementos.size()-1);
        siftDown(indice);
        
        return desencolado; 
    }

    public int tamaño() {

        return elementos.size();
    }

    private int swap(int i, int j) {
        T tempI = elementos.get(i);
        T tempJ = elementos.get(j);

        if(tempI.getClass().equals(Handle.class) && tempJ.getClass().equals(Handle.class)){

            Handle handleI = (Handle) tempI;
            Handle handleJ = (Handle) tempJ;

            handleI.setPosPropioHeap(j);
            handleJ.setPosPropioHeap(i);

            elementos.set(i, elementos.get(j));
            elementos.set(j, tempI);

            return j;
        }

        elementos.set(i, elementos.get(j));
        elementos.set(j, tempI);
        return j;
    }

    private int siftUp(int indice) {

        int ultimapos = indice;

        while (indice > 0) {

        int padre = (indice - 1) / 2; 
           
        if (comparador.compare(elementos.get(indice), elementos.get(padre)) > 0) {
            ultimapos = swap(indice, padre);    
            indice = padre;

            } else {
                break; 
            }
        }
        return ultimapos;
    }
    
    private int siftDown(int indice) {
        int ultimapos = indice;
        int hijoIzq = 2 * indice + 1; 
        int hijoDer = 2 * indice + 2; 
        int mayor = indice;
        int comparadorHijos = 0;

        if(hijoDer < elementos.size() && hijoIzq < elementos.size()){

            comparadorHijos = comparador.compare(elementos.get(hijoIzq), elementos.get(hijoDer));

        }

        if (hijoIzq < elementos.size() && comparador.compare(elementos.get(hijoIzq), elementos.get(mayor)) > 0 && comparadorHijos >= 0){

            ultimapos = swap(mayor, hijoIzq);
            siftDown(hijoIzq);
        }

        if(hijoDer < elementos.size() && comparador.compare(elementos.get(hijoDer),elementos.get(mayor))>0){

            ultimapos = swap(mayor, hijoDer);
            siftDown(hijoDer);
        }

        if(mayor != indice){

            ultimapos = swap(indice,mayor);
            siftDown(mayor);
        }

        return ultimapos;
    }

    private void heapify() {

        for (int j = 0; j < this.elementos.size(); j++) {

            siftDown(j);
        }
    }

    @Override
    public String toString() {

        StringBuilder resultado = new StringBuilder("{");
        if (!elementos.isEmpty()) {

            resultado.append(elementos.get(0));
            for (int i = 1; i < elementos.size(); i++) {

                resultado.append(", ").append(elementos.get(i));
            }
        }
        resultado.append("}");
        return resultado.toString();
    }
    public T get(int i){

        return this.elementos.get(i);
    }
}
