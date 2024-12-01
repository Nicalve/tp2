package aed;

import java.util.Comparator;

public class Superheap {
    public  Heap<Handle> reditoHeap;
    public Heap<Handle> antiguedadHeap;
    private class reditoComparator implements Comparator<Handle>{
    @Override
        public int compare(Handle h1, Handle h2){
            return Integer.compare(h1.getTraslado().gananciaNeta, h2.getTraslado().gananciaNeta);
        }
    }
    private class antiguedadComparator implements Comparator<Handle>{
        @Override
        public int compare(Handle h1, Handle h2){
            return Integer.compare(h1.getTraslado().timestamp, h2.getTraslado().timestamp);
        }
    }

    public Superheap() {
        reditoComparator reditocomparador = new reditoComparator();
        antiguedadComparator antiguedadcomparador = new antiguedadComparator();
        this.reditoHeap = new Heap<>(reditocomparador);
        
        this.antiguedadHeap = new Heap<>( antiguedadcomparador );
    }

    public void superencolar(Traslado traslado) {
        Handle mihandle = new Handle(traslado); 
        Handle otroHandle = new Handle(traslado);
        int pos = reditoHeap.encolar(mihandle);
        mihandle.setPosPropioHeap(pos);
        otroHandle.setPosOtroHeap(pos);
        actualizarPosEnElOtro(reditoHeap, antiguedadHeap);
        int posotro = antiguedadHeap.encolar(otroHandle);
        otroHandle.setPosPropioHeap(posotro);
        mihandle.setPosOtroHeap(posotro);
       actualizarPosEnElOtro(antiguedadHeap, reditoHeap);
    }


    public Traslado desencolarRedito(){
        Handle desencolado = (Handle) reditoHeap.desencolarRaiz();
        actualizarPosEnElOtro(reditoHeap,antiguedadHeap);
       antiguedadHeap.desencolar(desencolado.getPosOtroHeap());
        actualizarPosEnElOtro(antiguedadHeap, reditoHeap);
            return desencolado.getTraslado();

    }


    public Traslado desencolarAntiguedad(){
        Handle desencolado = (Handle) antiguedadHeap.desencolarRaiz();
        actualizarPosEnElOtro(antiguedadHeap,reditoHeap);
        reditoHeap.desencolar(desencolado.getPosOtroHeap());
        actualizarPosEnElOtro(reditoHeap, antiguedadHeap);
            return desencolado.getTraslado();

    }
    public void actualizarPosEnElOtro(Heap<Handle> h1, Heap<Handle> h2){
        for(int i = 0; i<h1.tamaño();i++){
            Handle actual = (Handle)h1.get(i);
            int posenelotro = actual.getPosOtroHeap();
            actual.setPosPropioHeap(i);
            if (posenelotro< h2.tamaño() && posenelotro > -1){
            Handle otro = (Handle)h2.get(posenelotro);
            otro.setPosOtroHeap(i);
            }
        }
    }
}
