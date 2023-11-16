/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica2;

/**
 *
 * @author jmart
 */
public class Entorno {
    
    private int cordx;
    private int cordy;
    private int fillIni;
    private int colIni;
    private String rutaMapa="/Users/yo/Desktop/InformaticaUgr/Cuarto/DBA/Practicas/Practica2/src/practica2/mapas/mapWithComplexObstacle3.txt";
    private Mapa mapa= new Mapa(rutaMapa);
    int posObjetivo [];
    boolean objetivo = false;

    public Entorno() {
       // Creamos una posición aleatoria
        int fil = (int) (Math.random()*getFilas());
        int col= (int) (Math.random()*getColumnas());
        
        // Si la posición es valida se asigna. Será válida en caso de que el valor del mapa sea 0
        if(getValorMapa(fil, col) == 0){
            setFillIni(fil);
            setCordx(col);
            setColIni(col);
            setCordy(col);
        // Si no es válida, es decir, que sea un obstáculo se vuelve a crear una posición random en bucle hasta que sea una válida
        }else{
            boolean posicionValida = false;
            
            while(!posicionValida){
                int fil2 = (int) (Math.random()*getFilas());
                int col2 = (int) (Math.random() * getColumnas());
                
                if(getValorMapa(fil2, col2) == 0){
                    setFillIni(fil2);
                    setCordx(fil2);
                    setColIni(col2);
                    setCordy(col2);
                    
                    posicionValida=true;
                }
            }
        }
        
        //Inicializamos el vector de la posición del agente
        posObjetivo = new int [2];
    }

    Entorno(int posXIni, int posYIni, int posXObjetivo, int posYObjetivo){
        fillIni = posXIni;
        colIni = posYIni;
        cordx = posXIni;
        cordy = posYIni;
   
            
        //Inicializamos el vector de la posición del agente
        posObjetivo = new int [2];

        mapa.colocarObjetivo(posXObjetivo, posYObjetivo);
        posObjetivo[0] = posXObjetivo;
        posObjetivo[1] = posYObjetivo;
    }
    
    
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
    
    
    public boolean objetivoEncontrado(){
        if(mapa.getValorMapa(cordx, cordy) == 5){
            objetivo = true;
            System.out.println("El objetivo se ha encontrado");
        }
        
        return objetivo;
    }
    
    public boolean getObjetivo(){
        return objetivo;
    }
    
    
    public void setFillIni(int fillIni) {
        this.fillIni = fillIni;
    }

    public void setColIni(int colIni) {
        this.colIni = colIni;
    }


    public void setCoordenadas(int x,int y){
        cordx=x;
        cordy=y;
    }
    
    public void setCordx(int cordx) {
        this.cordx = cordx;
    }

    public void setCordy(int cordy) {
        this.cordy = cordy;
    }
    
    // Devuelve la coordenada X del objetivo
    public int getPosObjetivoX(){
        return posObjetivo[0];
    }
    
    // Devuleve la coordenada Y del objetivo
    public int getPosObjetivoY(){
        return posObjetivo[1];
    }
    
    public int getPosX(){
        return cordx;
    }
    
    public int getPosY(){
        return cordy;
    }
    
    // Devuleve un valor de una posición del mapa
    public int getValorMapa(int x,int y){
        return mapa.getValorMapa(x, y);
    }
    
    // Devuelve las filas del mapa
    public int getFilas(){
        return mapa.getFilas();
    }
    
    // Devuleve las columnas del mapa
    public int getColumnas(){
        return mapa.getColumnas();
    }
    
    // Marca una casilla del mapa
    public void marcarCasilla(int x, int y){
        mapa.marcarCasilla(x, y);
    }
    
    // Devuelve el mapa
    public int[][] getMapa(){
        return mapa.getMapa();
    }  
    
    public void giroDerecha(){
        // Si la posición que vamos a mirar no está fuera del rango
        if(cordx < mapa.getFilas() && cordy +1 < mapa.getColumnas()){

            // Si a la derecha del muñeco es 0 se mueve sino no
            if(getValorMapa(cordx, cordy+1) == 0 || getValorMapa(cordx, cordy+1) == 5 || mapa.getValorMapa(cordx, cordy+1)==2){
                cordy=cordy+1;
                mapa.marcarCasilla(cordx, cordy);
            }
        }
    }
    
    public void giroIzquierda(){
        // Si la posición que vamos a mirar no está fuera del rango
        if (cordx < getFilas() && cordy-1 < getColumnas()) {
  
            // Si a la izquierda del muñeco es 0 se mueve sino no
            if (getValorMapa(cordx, cordy - 1) == 0 || getValorMapa(cordx, cordy-1) == 2 || getValorMapa(cordx, cordy-1) == 5) {
                cordy = cordy - 1;
                mapa.marcarCasilla(cordx, cordy);
        }
    }
  }
    
    public void moverAbajo(){
        // Si la posición que vamos a mirar no está fuera del rango        
        if(cordx+1 < getFilas() && cordy <= getColumnas()){
            // Si abajo del muñeco es 0 se mueve sino no
            if(getValorMapa(cordx+1, cordy) == 0 || getValorMapa(cordx+1, cordy) == 5 || mapa.getValorMapa(cordx+1, cordy) == 2){
                cordx=cordx+1;
                mapa.marcarCasilla(cordx, cordy);
            }
        }
    }
    
    public void moverArriba(){   
        // Si la posición que vamos a mirar no está fuera del rango
        if((cordx-1 >=0) && cordy < getColumnas()){
            // Si arriba del muñeco es 0 se mueve sino no
            if(getValorMapa(cordx-1, cordy) >= 0){
                cordx=cordx-1;
                mapa.marcarCasilla(cordx, cordy);
            }
        }
    }
    
    public void mostrarMapa(){
        mapa.mostrarMapa();
    }    
}
