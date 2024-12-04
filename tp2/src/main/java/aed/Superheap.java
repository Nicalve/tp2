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
    }//? O(1)

    public void superencolar(Traslado traslado) {

        Handle mihandle = new Handle(traslado); 
        Handle otroHandle = new Handle(traslado);

        mihandle.setContraparte(otroHandle);
        otroHandle.setContraparte(mihandle);

        int pos = reditoHeap.encolar(mihandle);


        mihandle.setPosPropioHeap(pos);
        otroHandle.setPosOtroHeap(pos);
        //? Todas las asignaciones son O(1)
        //actualizarPosEnElOtro(this.reditoHeap,this.antiguedadHeap); //? O(n)

        int posotro = antiguedadHeap.encolar(otroHandle); //? O(log(n))

        otroHandle.setPosPropioHeap(posotro);
        mihandle.setPosOtroHeap(posotro);
        //actualizarPosEnElOtro(this.antiguedadHeap,this.reditoHeap); //? O(n)
    } //? la funcion termina teniendo complejidad O(2n)+O(2log(n)) == O(n)


    public Traslado desencolarRedito(){

        Handle desencolado = (Handle) reditoHeap.desencolarRaiz(); //? O(log(n))

       // actualizarPosEnElOtro(this.reditoHeap,this.antiguedadHeap); //? O(n)
        antiguedadHeap.desencolar(desencolado.getPosOtroHeap());//? O(log(n))
       // actualizarPosEnElOtro(this.antiguedadHeap, this.reditoHeap);//? O(n)

        return desencolado.getTraslado();

    } //? 


    public Traslado desencolarAntiguedad(){
        
        Handle desencolado = (Handle) antiguedadHeap.desencolarRaiz();

        //actualizarPosEnElOtro(this.antiguedadHeap,this.reditoHeap);
        reditoHeap.desencolar(desencolado.getPosOtroHeap());
        //actualizarPosEnElOtro(this.reditoHeap, this.antiguedadHeap);

        return desencolado.getTraslado();

    }
    
}
