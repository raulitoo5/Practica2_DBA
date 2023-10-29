/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;
import static java.lang.Math.sqrt;
/**
 *
 * @author yo
 */
public class Agente {
    private int filIni;
    private int colIni;
    private int posX;
    private int posY;
    private int[][] memoria;    // Esta variable será la emmoria para saber por donde ha pasado el Agente
    private boolean objetivo = false;   // Indica si hemos encontrado el objetivo
    private String rutaMapa="/Users/yo/Desktop/InformaticaUgr/Cuarto/DBA/Practicas/Practica2/src/practica2/mapas/mapWithComplexObstacle1.txt";
    private Mapa mapa = new Mapa(rutaMapa);
    boolean derecha, izquierda;
    int posObjetivo [];
    
    
    // Constructor del agente
    Agente(){
        
        // Creamos una posición aleatoria
        int fil = (int) (Math.random()*mapa.getFilas());
        int col= (int) (Math.random()*mapa.getColumnas());
        
        // Si la posición es valida se asigna. Será válida en caso de que el valor del mapa sea 0
        if(mapa.getValorMapa(fil, col) == 0){
            filIni = fil;
            posX = filIni;
            colIni = col;
            posY = colIni;
        // Si no es válida, es decir, que sea un obstáculo se vuelve a crear una posición random en bucle hasta que sea una válida
        }else{
            boolean posicionValida = false;
            
            while(!posicionValida){
                int fil2 = (int) (Math.random()*mapa.getFilas());
                int col2= (int) (Math.random() * mapa.getColumnas());
                
                if(mapa.getValorMapa(fil, col) == 0){
                    filIni = fil;
                    posX = filIni;
                    colIni = col;
                    posY = colIni;
                    
                    posicionValida=true;
                }
            }
        }
        
        //Inicializamos el vector de la posición del agente
        posObjetivo = new int [2];
    }

    public boolean getObjetivo(){
        return objetivo;
    }
    
    // Colocamos el objetivo en una posicón aleatoria que sea válida
    public void colocarObjetivo(){
        
        // Creamos una posición aleatoria
        int fil = (int) (Math.random()*mapa.getFilas());
        int col= (int) (Math.random() * mapa.getColumnas());
        
        // Si la posición es valida se asigna. Será válida en caso de que el valor del mapa sea 0
        if(mapa.getValorMapa(fil, col) == 0){
            posObjetivo[0] = fil;
            posObjetivo[1] = col;
            
            mapa.colocarObjetivo(posObjetivo[0], posObjetivo[1]);
        // Si no es válida, es decir, que sea un obstáculo se vuelve a crear una posición random en bucle hasta que sea una válida
        }else{
            boolean posicionValida = false;
            
            while(!posicionValida){
                int fil2 = (int) (Math.random()*mapa.getFilas());
                int col2= (int) (Math.random() * mapa.getColumnas());
                
                if(mapa.getValorMapa(fil, col) == 0){
                    posObjetivo[0] = fil;
                    posObjetivo[1] = col;

                    mapa.colocarObjetivo(posObjetivo[0], posObjetivo[1]);
                    
                    posicionValida=true;
                }
            }
        }
    }
    
    // Función que señala que se ha encontrado el objetivo
    public boolean objetivoEncontrado(){
        if(mapa.getValorMapa(posX, posY) == 5){
            objetivo = true;
            System.out.println("El objetivo se ha encontrado");
        }
        
        return objetivo;
    }
    
    public int getMemoria(int i, int j){
        return memoria[i][j];
    }
    
    public int getPos(int i, int j){
        return getMemoria(i,j);
    }
    
    // Este método es para cambiar la posición interna del agente
    public void cambiarPosAgente(int i, int j){
        posX = i;
        posY = j;
    }
    
    public int getX(){
        return posX;
    }
    
    public int getY(){
        return posY;
    }
    
    public Mapa getMapa(){
        return mapa;
    }
//    
//    public void moverAgenteAbajo(){
//        if(mapa.getValorMapa(getX()+1,getY()) == 0){
//            posX=posX+1;
//            mapa.marcarCasilla(posX, posY);
//            // Si no hay nada ni muro ni camino, ni un camino que ya ha pasado gira a la derecha
//        } else if(mapa.getValorMapa(getX()+1,getY())!= 0 && mapa.getValorMapa(getX()+1,getY())!= 2 && mapa.getValorMapa(getX()+1,getY())!= -1){
//            // Si recto no queda mapa giramos hacia la derecha
//            posY=posY+1;
//            mapa.marcarCasilla(posX, posY);
//        } 
//    }
    
    public void marcarCasilla(int i, int j){
        mapa.marcarCasilla(i, j);
    }

    
    public void giroDerecha(){
        // Si la posición que vamos a mirar no está fuera del rango
        if(posX < mapa.getFilas() && posY +1 < mapa.getColumnas()){

            // Si a la derecha del muñeco es 0 se mueve sino no
            if(mapa.getValorMapa(posX, posY+1) == 0 || mapa.getValorMapa(posX, posY+1) == 5 || mapa.getValorMapa(posX, posY+1)==2){
                posY=posY+1;
                mapa.marcarCasilla(posX, posY);
            }
        }
    }
    
    public void giroIzquierda(){
        // Si la posición que vamos a mirar no está fuera del rango
        if (posX < mapa.getFilas() && posY-1 < mapa.getColumnas()) {
  
            // Si a la izquierda del muñeco es 0 se mueve sino no
            if (mapa.getValorMapa(posX, posY - 1) == 0 || mapa.getValorMapa(posX, posY-1) == 2 || mapa.getValorMapa(posX, posY-1) == 5) {
                posY = posY - 1;
                mapa.marcarCasilla(posX, posY);
        }
    }
}
   
    
    public void moverAbajo(){
        // Si la posición que vamos a mirar no está fuera del rango        
        if(posX+1 < mapa.getFilas() && posY <= mapa.getColumnas()){
            // Si abajo del muñeco es 0 se mueve sino no
            if(mapa.getValorMapa(posX+1, posY) == 0 || mapa.getValorMapa(posX+1, posY) == 5 || mapa.getValorMapa(posX+1, posY) == 2){
                posX=posX+1;
                mapa.marcarCasilla(posX, posY);
            }
        }
    }
    
    public void moverArriba(){   
        // Si la posición que vamos a mirar no está fuera del rango
        if((posX-1 >=0) && posY < mapa.getColumnas()){
            // Si arriba del muñeco es 0 se mueve sino no
            if(mapa.getValorMapa(posX-1, posY) >= 0){
                posX=posX-1;
                mapa.marcarCasilla(posX, posY);
            }
        }
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
        if((posX+1 < mapa.getFilas() && posY < mapa.getColumnas()) && mapa.getValorMapa(posX+1, posY) == 0 || mapa.getValorMapa(posX+1, posY) == 5){
            distanciaAbajo = distanciaEuclidea(posX+1,posY,posObjetivo[0], posObjetivo[1]);
            // Si no se puede mover a esa casilla porque es un obstáculo
        } else if((posX+1 < mapa.getFilas() && posY < mapa.getColumnas()) && mapa.getValorMapa(posX+1, posY) == -1)
            distanciaAbajo += 20;
            // Si no se puede mover porque se sale del mapa si hace ese movimiento
        else if((posX+1 == mapa.getFilas() && posY < mapa.getColumnas()))
            distanciaAbajo+=20;
        // Si ya ha pasado dos veces por esa casilla que no pase más
        else if((posX+1 < mapa.getFilas() && posY < mapa.getColumnas()) && (mapa.getValorMapa(posX+1, posY) == 7))
            distanciaAbajo+=20;

        
        // Si se puede mover hacia la derecha calcula la distancia euclidea de ese movimiento
        if((posX < mapa.getFilas() && posY+1 < mapa.getColumnas()) && (mapa.getValorMapa(posX, posY+1) == 0 || mapa.getValorMapa(posX, posY+1) == 5)){
            distanciaDerecha = distanciaEuclidea(posX,posY+1,posObjetivo[0],posObjetivo[1]);
            // Si no se puede mover a esa casilla porque es un obstáculo
        } else if((posX < mapa.getFilas() && posY+1 < mapa.getColumnas()) && (mapa.getValorMapa(posX, posY+1) == -1))
                distanciaDerecha+=20;
        // Si no se puede mover porque si hace ese movimiento se sale del mapa
        else if((posX < mapa.getFilas() && posY+1 == mapa.getColumnas()) )
            distanciaDerecha+=20;
        // Si ya se ha movido dos veces en esa casilla
        else if((posX < mapa.getFilas() && posY+1 < mapa.getColumnas()) && (mapa.getValorMapa(posX, posY+1) == 7))
            distanciaDerecha+=20;
        
        
        
        //Movimiento izquierda
        if((posX < mapa.getFilas() && posY-1 < mapa.getColumnas()) && (mapa.getValorMapa(posX, posY-1) == 0 || mapa.getValorMapa(posX, posY-1) == 5)){
            distanciaIzquierda = distanciaEuclidea(posX,posY-1,posObjetivo[0],posObjetivo[1]);
            // Si la casilla es un obstáculo
        } else if((posX < mapa.getFilas() && posY-1 < mapa.getColumnas()) && (mapa.getValorMapa(posX, posY-1) == -1))
            distanciaIzquierda+=20;
        // Si hace este movimiento se sale de la casilla
        else if((posX < mapa.getFilas() && posY-1 == mapa.getColumnas()) )
            distanciaIzquierda+=20;
        //si ya ha pasado dos vece por esa casilla
        else if((posX < mapa.getFilas() && posY-1 < mapa.getColumnas()) && (mapa.getValorMapa(posX, posY-1) == 7))
            distanciaIzquierda+=20;


        
        //Movimiento arriba
        if((posX-1 < mapa.getFilas() && posY < mapa.getColumnas()) && (mapa.getValorMapa(posX-1, posY) == 0 || mapa.getValorMapa(posX-1, posY) == 5)){
            distanciaArriba = distanciaEuclidea(posX-1,posY,posObjetivo[0],posObjetivo[1]);
            // Si la casilla es un obstáculo
        } else if((posX-1 < mapa.getFilas() && posY < mapa.getColumnas()) && (mapa.getValorMapa(posX-1, posY) == -1))
            distanciaArriba+=20;
         // Si hace este movimiento se sale de la casilla
        else if((posX-1 == mapa.getFilas() && posY < mapa.getColumnas()) )
            distanciaArriba+=20;
        //si ya ha pasado dos vece por esa casilla
        else if((posX-1 < mapa.getFilas() && posY < mapa.getColumnas()) && (mapa.getValorMapa(posX-1, posY) == 7))
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
        
        if(mapa.getValorMapa(posX, posY) == 5){
            objetivoEncontrado();
        }
//        
//         //Primero hacia abajo si es posible
//            if(mapa.getValorMapa(posX+1, posY) == 0 || mapa.getValorMapa(posX+1, posY) == 5){
//              moverAbajo();
//              contadorGiro=1;
//              
//                //System.out.println("jfsj" + mapa.getValorMapa(posX+1, posY));
//                System.out.println("jdjfsa" + mapa.getValorMapa(posX, posY+1));
//                System.out.println("contador: " + contadorGiro);
//            }
//          // Si no se mueve abajo se mueve a la derecha
//            else if((mapa.getValorMapa(posX, posY+1) == 0 || mapa.getValorMapa(posX, posY+1) == 5)&&(contadorGiro==1)){
//                System.out.println("entra giro");
////              giroDerecha();
//                System.out.println("sla egiro");
//                contadorGiro=0;
//            }
//            else if(mapa.getValorMapa(posX-1, posY) == 0 || mapa.getValorMapa(posX-1, posY) == 5){
//              moverArriba();
//                // Este if sirve para que si ya no puede mover más hacia arriba sea cuando gire
//                if(mapa.getValorMapa(posX-1, posY) == -3 || mapa.getValorMapa(posX-1, posY) == -1){
//                    System.out.println("gira cuandomueve arrbia");
//                    contadorGiro=1;
//                }
//            }
//            else if((mapa.getValorMapa(posX, posY-1) == 0 || mapa.getValorMapa(posX, posY-1) == 5) && contadorGiro == 1){
//              giroIzquierda();
//              contadorGiro=0;
//            }
        }   
    }

