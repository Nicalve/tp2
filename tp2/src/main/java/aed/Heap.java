package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T extends Comparable<T>>{
    private final ArrayList<T> elementos; 
    private final Comparator<T> comparador;
    

    public Heap(Comparator<T> comparador) {
        
        this.elementos = new ArrayList<>();
        this.comparador = comparador;
    }

    public int encolar(T valor) {

        elementos.add(valor); //? O(1);
        return siftUp(elementos.size() - 1); //? O(log(n)), ver sfitUp;

    }

    public T consultarRaiz() {

        return elementos.get(0); //? O(1);
    }

    public T desencolarRaiz() {
        T max = elementos.get(0); //? O(1);
        T ult = elementos.remove(elementos.size() - 1); //? O(1); 

        if (!elementos.isEmpty()) { 

            elementos.set(0, ult); //? O(1)
            siftDown(0); //? O(log(n)), ver sfitDown;
        }
        return max; //? Luego, la complejidad es O(log(n));
    }

    public T desencolar(int indice){

        T desencolado = this.elementos.get(indice); //? O(1);
        T ult = this.elementos.get(this.elementos.size()-1); //? O(1);

        this.elementos.set(indice, ult); //? O(1);
        this.elementos.remove(this.elementos.size()-1); //? .remove tiene O(n) generalmente, pero al ser el ultimo elemento de la lista pasa a ser O(1);
        siftDown(indice); //? O(log(n)), ver sfitDown;
        
        //? Luego, la complejidad es O(log(n));
        return desencolado; 
    }

    public int tamaño() {

        return elementos.size();
    }

    private int swap(int i, int j) {
        T tempI = elementos.get(i);
        T tempJ = elementos.get(j);

        //? Heap esta hecho de manera generica, por lo que necesitamos hacer casting en caso de que sean Handles para poder utilizar las funciones de la clase
        if(tempI.getClass().equals(Handle.class) && tempJ.getClass().equals(Handle.class)){

            Handle handleI = (Handle) tempI;
            Handle handleJ = (Handle) tempJ;

            //? Las funciones de Handle tienen O(1), ver en Handle.java;
            handleI.setPosPropioHeap(j);
            handleJ.setPosPropioHeap(i);
            handleI.actualizar();
            handleJ.actualizar();

            //? Complejidad O(1);
            elementos.set(i, elementos.get(j));
            elementos.set(j, tempI);
            
            return j; //? Sumando las complejidades qeuda O(1);
        }

        //? Mismo caso que cuando se usa el casting para Handle;
        elementos.set(i, elementos.get(j));
        elementos.set(j, tempI);

        return j;
    }

    private int siftUp(int indice) {

        int ultimapos = indice;

        while (indice > 0) {

        int padre = (indice - 1) / 2; 
           
        if (comparador.compare(elementos.get(indice), elementos.get(padre)) > 0) {

                ultimapos = swap(indice, padre); //? Swap tiene complejidad O(1);
                indice = padre;
            } else {

                break; 
            }
        } //? Se realiza un Swap tantas veces como el indice padre del que llega por parametro sea mayor a 2 ( (n-1)/2 > 0). Dicha operacion se puede hacer (n%2 == 0) veces, luego la complejdiad es O(log(n));
        return ultimapos;
    }
    
    private int siftDown(int indice) {
        //? Las asignaciones son todas O(1);
        int ultimapos = indice;
        int hijoIzq = 2 * indice + 1; 
        int hijoDer = 2 * indice + 2; 
        int mayor = indice;
        int comparadorHijos = 0;

        //? En todos los if se valida que HijoIzq y HijoDer sean menor que la longitud del ArrayList elementos. Por como se obtienen los hijos (recursivamente), como mucho se pueden obtener log(n) indices, siendo n el size de elementos.


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

        //? Dado que siftDown es una funcion recursiva que puede llamarse a si misma log(elementos.size) veces, y dentro de cada if, aparte de la recursion, se llama a swap que tiene O(1) La complejidad final de siftDown es O(log(elementos.size()) ≈ O(log(n)); 

        return ultimapos;
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
    public T get(int i){ //? Complejidad O(1);

        return this.elementos.get(i);
    }
}