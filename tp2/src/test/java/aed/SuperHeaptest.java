package aed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class SuperHeaptest {
    private Heap<Integer> heap;
    @Test
    void borraruno(){
        Superheap superheap = new Superheap();

        Traslado t1 = new Traslado(1, 101, 201, 100, 10);
        Traslado t2 = new Traslado(2, 102, 202, 300, 20);
        Traslado t3 = new Traslado(3, 103, 203, 200, 30);
        Traslado t4 = new Traslado(4, 101, 201, 120, 40);

        superheap.superencolar(t1);
        superheap.superencolar(t2);
        superheap.superencolar(t3);
        superheap.superencolar(t4);
       

        superheap.desencolarRedito();
        assertEquals(3,superheap.antiguedadHeap.tamaño());
        assertEquals(3,superheap.reditoHeap.tamaño());
        assertEquals(40, superheap.antiguedadHeap.get(0).getTraslado().timestamp);
        assertEquals(200, superheap.reditoHeap.get(0).getTraslado().gananciaNeta);
        assertEquals(30, superheap.antiguedadHeap.get(1).getTraslado().timestamp);
        assertEquals(120, superheap.reditoHeap.get(1).getTraslado().gananciaNeta);
        assertEquals(10, superheap.antiguedadHeap.get(2).getTraslado().timestamp);
        assertEquals(100, superheap.reditoHeap.get(2).getTraslado().gananciaNeta);
        assertEquals(200,superheap.desencolarRedito().gananciaNeta);
        assertEquals(40, superheap.antiguedadHeap.get(0).getTraslado().timestamp);
        assertEquals(120, superheap.reditoHeap.get(0).getTraslado().gananciaNeta);

    }
    @Test
        void elemntos(){
        Superheap superheap = new Superheap();
        Traslado t1 = new Traslado(1, 101, 201, 10, 10);
        Traslado t2 = new Traslado(2, 102, 202, 30, 0);
        Traslado t3 = new Traslado(2, 102, 202, 40, 5);
        superheap.superencolar(t1);
        superheap.superencolar(t2);
        superheap.superencolar(t3);
        superheap.superencolar(t3);

        }

}