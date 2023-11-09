/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;
import static java.lang.Math.sqrt;
import practica2.Entorno;
/**
 *
 * @author yo
 */
public class Agente {  
    
    Entorno entorno;
    
    // Constructor del agente
    Agente(){
        entorno = new Entorno();
    }
    
    // Constructor con parámetros
    Agente(int posXIni, int posYIni, int posXObjetivo, int posYObjetivo){
        entorno = new Entorno(posXIni, posYIni, posXObjetivo, posYObjetivo);
    }

//    public boolean getObjetivo(){
//        return objetivo;
//    }
    
    // Colocamos el objetivo en una posicón aleatoria que sea válida
    public void colocarObjetivo(){
        entorno.colocarObjetivo();
    }
    
    public Entorno getEntorno(){
        return entorno;
    }
    
    // Función que señala que se ha encontrado el objetivo
    public boolean objetivoEncontrado(){
        return entorno.objetivoEncontrado();
    }
    
    public void marcarCasilla(int i, int j){
        entorno.marcarCasilla(i, j);
    }

    
    public void giroDerecha(){
        entorno.giroDerecha();
    }
    
    public void giroIzquierda(){
        entorno.giroIzquierda();
}
   
    
    public void moverAbajo(){
        entorno.moverAbajo();
    }
    
    public void moverArriba(){   
        entorno.moverArriba();
    }
    /*
    x1,y1: corresponden a la posición actual del agente
    x2,y2: corresponden a la posición del objetivo
    
    
    */
    double distanciaEuclidea(int x1, int y1, int x2, int y2){
        double distancia = 0.0;
            distancia = sqrt((Math.pow(x2-x1, 2)) + (Math.pow(y2-y1,2)));
        return distancia;
    }
    
    // Esta funcion sirve para ver con que movimiento se acerca mas al objetivo
    /*----------------------------------------------------*/
    //IMPORTANTE: NO HEMOS VALORADO SI LA POSICIÓN A LA QUE MIRAMOS ES == 2. EN CASO DE QUE HAYAMOS
    // DESCUBIERTO GRAN PARTE DEL MAPA Y CASI TODO SEA 2 NO LO HEMOS CONSIDERADO
    public int pensar(){
        // Estas variables indican la distancia euclidea existente si el siguiente movimiento que hacen es ese
        // Inicializamos las variables a valores grandes para que así no coja alguno que no haya podido calcular la distancia euclídea
        double distanciaArriba = 50;    // Distancia euclidea si el siguiente movimiento que hace es ir abajo
        double distanciaAbajo = 50;
        double distanciaDerecha = 50;
        double distanciaIzquierda = 50;
        double distanciaMenor=0;
        
        // Si se puede mover hacia abajo y todavía no hemos calculado la distancia en esa posición
        if((entorno.getPosX()+1 < entorno.getFilas() && entorno.getPosY() < entorno.getColumnas()) && entorno.getValorMapa(entorno.getPosX()+1, entorno.getPosY()) == 0 || entorno.getValorMapa(entorno.getPosX()+1, entorno.getPosY()) == 5){
            distanciaAbajo = distanciaEuclidea(entorno.getPosX()+1,entorno.getPosY(),entorno.getPosObjetivoX(), entorno.getPosObjetivoY());
            // Si no se puede mover a esa casilla porque es un obstáculo
        } else if((entorno.getPosX()+1 < entorno.getFilas() && entorno.getPosY() < entorno.getColumnas()) && entorno.getValorMapa(entorno.getPosX()+1, entorno.getPosY()) == -1)
            distanciaAbajo += 20;
            // Si no se puede mover porque se sale del mapa si hace ese movimiento
        else if((entorno.getPosX()+1 == entorno.getFilas() && entorno.getPosY() < entorno.getColumnas()))
            distanciaAbajo+=20;
        // Si ya ha pasado dos veces por esa casilla que no pase más
        else if((entorno.getPosX()+1 < entorno.getFilas() && entorno.getPosY() < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX()+1, entorno.getPosY()) == 7))
            distanciaAbajo+=20;

        
        // Si se puede mover hacia la derecha calcula la distancia euclidea de ese movimiento
        if((entorno.getPosX() < entorno.getFilas() && entorno.getPosY()+1 < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX(), entorno.getPosY()+1) == 0 || entorno.getValorMapa(entorno.getPosX(), entorno.getPosY()+1) == 5)){
            distanciaDerecha = distanciaEuclidea(entorno.getPosX(),entorno.getPosY()+1,entorno.getPosObjetivoX(),entorno.getPosObjetivoY());
            // Si no se puede mover a esa casilla porque es un obstáculo
        } else if((entorno.getPosX() < entorno.getFilas() && entorno.getPosY()+1 < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX(), entorno.getPosY()+1) == -1))
                distanciaDerecha+=20;
        // Si no se puede mover porque si hace ese movimiento se sale del mapa
        else if((entorno.getPosX() < entorno.getFilas() && entorno.getPosY()+1 == entorno.getColumnas()) )
            distanciaDerecha+=20;
        // Si ya se ha movido dos veces en esa casilla
        else if((entorno.getPosX() < entorno.getFilas() && entorno.getPosY()+1 < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX(), entorno.getPosY()+1) == 7))
            distanciaDerecha+=20;
        
        
        
        //Movimiento izquierda
        if((entorno.getPosX() < entorno.getFilas() && entorno.getPosY()-1 < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX(), entorno.getPosY()-1) == 0 || entorno.getValorMapa(entorno.getPosX(), entorno.getPosY()-1) == 5)){
            distanciaIzquierda = distanciaEuclidea(entorno.getPosX(),entorno.getPosY()-1,entorno.getPosObjetivoX(),entorno.getPosObjetivoY());
            // Si la casilla es un obstáculo
        } else if((entorno.getPosX() < entorno.getFilas() && entorno.getPosY()-1 < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX(), entorno.getPosY()-1) == -1))
            distanciaIzquierda+=20;
        // Si hace este movimiento se sale de la casilla
        else if((entorno.getPosX() < entorno.getFilas() && entorno.getPosY()-1 == entorno.getColumnas()) )
            distanciaIzquierda+=20;
        //si ya ha pasado dos vece por esa casilla
        else if((entorno.getPosX() < entorno.getFilas() && entorno.getPosY()-1 < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX(), entorno.getPosY()-1) == 7))
            distanciaIzquierda+=20;


        
        //Movimiento arriba
        if((entorno.getPosX()-1 < entorno.getFilas() && entorno.getPosY() < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX()-1, entorno.getPosY()) == 0 || entorno.getValorMapa(entorno.getPosX()-1, entorno.getPosY()) == 5)){
            distanciaArriba = distanciaEuclidea(entorno.getPosX()-1,entorno.getPosY(),entorno.getPosObjetivoX(),entorno.getPosObjetivoY());
            // Si la casilla es un obstáculo
        } else if((entorno.getPosX()-1 < entorno.getFilas() && entorno.getPosY() < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX()-1, entorno.getPosY()) == -1))
            distanciaArriba+=20;
         // Si hace este movimiento se sale de la casilla
        else if((entorno.getPosX()-1 == entorno.getFilas() && entorno.getPosY() < entorno.getColumnas()) )
            distanciaArriba+=20;
        //si ya ha pasado dos vece por esa casilla
        else if((entorno.getPosX()-1 < entorno.getFilas() && entorno.getPosY() < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX()-1, entorno.getPosY()) == 7))
            distanciaArriba+=20;

        
        
        
        //Ahora vemos cual de todos tiene la distancia euclidea menor
        distanciaMenor = Math.min(Math.min(Math.min(distanciaAbajo, distanciaDerecha), distanciaIzquierda), distanciaArriba);
        System.out.println("distancia menor: " + distanciaMenor);
        System.out.println("distancia arriba: "+ distanciaArriba);
        System.out.println("distancia abajo: " + distanciaAbajo);
        System.out.println("distancia derecha: " + distanciaDerecha);
        System.out.println("distancia Izquierda: " + distanciaIzquierda);
        
        if(distanciaMenor == distanciaAbajo){
            System.out.println("devuelve abajo");
            return 0;
        } else if(distanciaMenor == distanciaDerecha){
            System.out.println("devuelve derecha");
            return 1;
        } else if(distanciaMenor == distanciaIzquierda){
            System.out.println("devuleve izquierda");
            return 2;
        } else 
            System.out.println("devuleve arriba");
            return 3;
    }

    public void MoverAgente(){
        
        int movimiento = pensar();
        
        switch(movimiento){
            case 0: moverAbajo();break;
            case 1: giroDerecha();break;
            case 2: giroIzquierda();break;
            case 3: moverArriba();break;
        }
        
        if(entorno.getValorMapa(entorno.getPosX(), entorno.getPosY()) == 5){
            objetivoEncontrado();
        }
      }
 }

