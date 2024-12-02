package aed;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class HeapTest {
    private Heap<Integer> heap;

    @BeforeEach
    void setUp() {
        Comparator<Integer> maxComparator = (a, b) -> a.compareTo(b);
        heap = new Heap<>(maxComparator);
    }

    @Test
    void testConstructorVacio() {
        assertEquals(0, heap.tamaño());
    }

    @Test
    void testConstructorConSecuencia() {
        Integer[] secuencia = {4, 2, 8, 5, 1};

        Heap<Integer> queue = new Heap<>((a, b) -> a.compareTo(b));
        for (int elemento : secuencia){
            queue.encolar(elemento);
        }

        assertEquals(5, queue.tamaño());
    }

    @Test
    void testEncolar() {
        heap.encolar(1);
        heap.encolar(2);
        heap.encolar(3);
        heap.encolar(4);
        heap.encolar(5);
        heap.encolar(6);
        
    
        assertEquals(6, heap.tamaño());
    }

    @Test
    void testConsultarRaiz() {
        heap.encolar(5);
        heap.encolar(8);
        heap.encolar(3);

        assertEquals(8, heap.consultarRaiz());
    }
    @Test
    void testEncolaryDesencolar() {
        heap.encolar(5);
        heap.encolar(8);
        heap.encolar(3);

        assertEquals(8, heap.consultarRaiz());
        heap.desencolarRaiz();
        assertEquals(5, heap.consultarRaiz());
        heap.desencolarRaiz();
        assertEquals(3, heap.consultarRaiz());
    }
    @Test
    void EncolarYDesencolarMuchos(){
        Integer[] secuencia = {4, 2, 8, 5, 1};
        Heap<Integer> queue = new Heap<>((a, b) -> a.compareTo(b));
        for (int elemento : secuencia){
            queue.encolar(elemento);
        }
        assertEquals(5, queue.tamaño());
        for(int i = 2; i < 5; i++){
            queue.encolar(i*3);
        }
        queue.encolar(7);
        queue.encolar(3);
        assertEquals(10, queue.tamaño());
  
        assertEquals(12, queue.consultarRaiz());
    
        assertEquals(12, queue.desencolarRaiz());
      
        assertEquals(9, queue.desencolarRaiz());
    
        assertEquals(8, queue.desencolarRaiz());
        assertEquals(7, queue.tamaño());
        assertEquals(7, queue.consultarRaiz());

        while(queue.tamaño() > 0){
            int raiz = queue.consultarRaiz();
            assertEquals(raiz, queue.desencolarRaiz());
        }

 
    }

    @Test
    void desencolarCualquiera(){
        Integer[] secuencia = {16,35,19,9,11,93,82,42,25,23,68,68};
        Heap<Integer> queue = new Heap<>((a, b) -> a.compareTo(b));
        for (int elemento : secuencia){
            queue.encolar(elemento);
        }

        assertEquals(68, queue.desencolar((queue.tamaño()-1)/2));
        assertEquals(68,queue.desencolar(1));

        assertEquals(82, queue.desencolar(2));
        assertEquals(23, queue.desencolar(4));
        
    }


    @Nested
    class DesencolarRaizTests {

        @Test
        void cuandoLaColaTieneUnElemento() {
            heap.encolar(5);

            assertEquals(5, heap.desencolarRaiz());
            assertEquals(0, heap.tamaño());
        }

        @Test
        void cuandoLaColaTieneVariosElementos() {
            heap.encolar(5);
            heap.encolar(8);
            heap.encolar(3);

            assertEquals(8, heap.desencolarRaiz());
            assertEquals(2, heap.tamaño());
        }
    }
}