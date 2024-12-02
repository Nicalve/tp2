package aed;

import java.util.ArrayList;
import java.util.Comparator;
public class BestEffort {
    private final  Ciudad[] ciudades;
    private final ArrayList<Integer> ciudadesMayorGanancia;
    private final ArrayList<Integer> ciudadesMayorPerdida;
    private int gananciaTotal ;
    private int cantidadDeTrasladosDespachados;
   public Superheap manager;
    private Heap<Integer> superavit;
    private final Comparator<Integer> maxComparator = (a, b) -> b.compareTo(a);
    
    public BestEffort(int cantCiudades, Traslado[] traslados){
        this.ciudades = new Ciudad[cantCiudades]; 
        this.superavit = new Heap<>(this.maxComparator);
        this.manager = new Superheap();
        this.cantidadDeTrasladosDespachados = 0;
        this.gananciaTotal = 0;
        this.ciudadesMayorGanancia = new ArrayList<>();
        this.ciudadesMayorPerdida = new ArrayList<>();

    for (int i = 0 ; i<cantCiudades;i++){ //? este for es O(|ciudades|)
        Ciudad nuevaciudad = new Ciudad(i); 
        this.ciudades[i] = nuevaciudad;
    }
    for(Traslado t :traslados){   //? este for es O(|Traslado|)
        manager.superencolar(t);
    }
   
    }

    public void registrarTraslados(Traslado[] traslados){
        for(Traslado t :traslados){
            manager.superencolar(t);
        }
    }

    public int[] despacharMasRedituables(int n){
        int[] res = new int[n];
        
        for (int i = 0 ; i<n;i++){

            Traslado actual = manager.desencolarRedito(); //? O(2log(T)) == O(log(T)), ver desencolarRedito en Superheap

            res[i]=actual.id;
            this.cantidadDeTrasladosDespachados += 1;
            this.gananciaTotal += actual.gananciaNeta;
            this.ciudades[actual.origen].addEarnings(actual.gananciaNeta);
            this.ciudades[actual.destino].addLosses(actual.gananciaNeta);
            //? todas las asignaciones son O(1)

            if (this.ciudadesMayorGanancia.isEmpty()){
                this.ciudadesMayorGanancia.add(actual.origen); 
            }
            if (this.ciudadesMayorPerdida.isEmpty()){
                this.ciudadesMayorPerdida.add(actual.destino);
            }


            Ciudad mayorGanancia = this.ciudades[this.ciudadesMayorGanancia.get(0)];
            Ciudad mayorPerdida = this.ciudades[this.ciudadesMayorPerdida.get(0)];
            Ciudad origenActual =  this.ciudades[actual.origen];
            Ciudad destinoActual = this.ciudades[actual.destino];
           

        
            if(mayorGanancia.getEarnings() < origenActual.getEarnings()){
                this.ciudadesMayorGanancia.set(0,actual.origen);
            }else if(mayorGanancia.getEarnings() == origenActual.getEarnings()){
                this.ciudadesMayorGanancia.add(actual.origen);
            }

            if(mayorPerdida.getLosses()> destinoActual.getLosses()){
                this.ciudadesMayorPerdida.set(0,actual.destino);
            }else if(mayorPerdida.getLosses() == origenActual.getLosses()){
                this.ciudadesMayorPerdida.add(actual.destino);
            }


            if(this.superavit.tamaño() == 0 ){
                this.superavit.encolar(actual.origen);//? O(log(C))

            }

            int iDraiz = (int) superavit.consultarRaiz();
            Ciudad mayorSuperavit = this.ciudades[iDraiz];
           
            if(origenActual.getSuperavit() >mayorSuperavit.getSuperavit()){
                this.superavit = new Heap<>(this.maxComparator);
                this.superavit.encolar(actual.origen);//? esto es O(log(C)), pero como anteriormente creamos un heap vacio nuevo seria O(1)
            }else if (this.ciudades[actual.origen].getSuperavit() == mayorSuperavit.getSuperavit()){
                this.superavit.encolar(actual.origen);//? O(log(C))
            }
        //? todos los if y elseif son comparaciones (O(1)) y asignaciones (O(1))
        }
        
        return res;
    }//? al final de este for termina siendo O(n(log(T)+log(C)))

    public int[] despacharMasAntiguos(int n){
        int[] res = new int[n];
        for (int i = 0 ; i<n;i++){

            Traslado actual = manager.desencolarAntiguedad();

            res[i]=actual.id;
            this.cantidadDeTrasladosDespachados += 1;
            this.gananciaTotal += actual.gananciaNeta;
            this.ciudades[actual.origen].addEarnings(actual.gananciaNeta);
            this.ciudades[actual.destino].addLosses(actual.gananciaNeta);

            
            if (this.ciudadesMayorGanancia.isEmpty()){
                this.ciudadesMayorGanancia.add(actual.origen);
            }
            

            if (this.ciudadesMayorPerdida.isEmpty()){
                this.ciudadesMayorPerdida.add(actual.destino);
            }


            Ciudad mayorGanancia = this.ciudades[this.ciudadesMayorGanancia.get(0)];
            Ciudad mayorPerdida = this.ciudades[this.ciudadesMayorPerdida.get(0)];
            Ciudad destinoActual = this.ciudades[actual.destino];
            Ciudad origenActual =  this.ciudades[actual.origen];


            if(mayorGanancia.getEarnings()< origenActual.getEarnings()){
                this.ciudadesMayorGanancia.set(0,actual.origen);
            }else if(mayorGanancia.getEarnings() == origenActual.getEarnings()){
                this.ciudadesMayorGanancia.add(actual.origen);
            }


            if(mayorPerdida.getLosses()> destinoActual.getLosses()){
                this.ciudadesMayorPerdida.set(0,actual.destino);
            }else if(mayorPerdida.getLosses() == origenActual.getLosses()){
                this.ciudadesMayorPerdida.add(actual.destino);
            }


            if(this.superavit.tamaño() == 0 ){
                this.superavit.encolar(actual.origen);
            }

            int iDraiz = (int) superavit.consultarRaiz();
            Ciudad mayorSuperavit = this.ciudades[iDraiz];
            
            if(origenActual.getSuperavit() > mayorSuperavit.getSuperavit()){
                this.superavit = new Heap<>(this.maxComparator);
                this.superavit.encolar(actual.origen);
            }else if (origenActual.getSuperavit() == mayorSuperavit.getSuperavit()){
                this.superavit.encolar(actual.origen);
            }
        }
        
        return res;
    }//? la complejidad de esta funcion es exactamente lo mismo que en la de despacharMasRedituables.

    public int ciudadConMayorSuperavit(){ 

        return this.superavit.consultarRaiz(); //? Complejidad O(1)
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        
        return this.ciudadesMayorGanancia; //? O(1);
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        
        return this.ciudadesMayorPerdida; //? O(1);
    }

    public int gananciaPromedioPorTraslado(){
        if (this.cantidadDeTrasladosDespachados == 0){ //? O(1);
            return 0; //? O(1);
        }
        return (Math.round(this.gananciaTotal/this.cantidadDeTrasladosDespachados)); //? O(1) ish;
    }
    
}