/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.ahorcado;

import java.io.IOException;

/**
 *
 * @author Hector Llano
 */
public class JuegoAhorcado {
    
    /*Inicializa los arrays palabra y pantalla. El primero de ellos a traves de uso de guiones (_) 
    mientras que el segundo sera cargado con el valor 0*/
    public static void inicializaJuego(char [] palabra,char [][] pantalla){
        for (int i=0;i<palabra.length;i++){
            palabra[i] = '_';
        }
        for (int i=0;i<pantalla.length;i++){
            for (int j=0;j<pantalla[i].length;j++){
                pantalla [i][j] = '0';
            }
        }
    }

    /*Mostrara los arrays palabra y pantalla, observamos que tanto en la inicializacion como en la visualizacion se usan 
    bucles for, en pantalla observamos un bucle for anidado. Al especificar pantalla[i].length estamos controlando el hecho 
    de que cada fila pueda tener un numero diferente de casillas o columnas.*/
    public static void mostrar(char [] palabra,char [][] pantalla){
        System.out.println("Palabra");
        for (int i=0;i<palabra.length;i++){
            System.out.print(palabra[i]);
        }
        System.out.println();
        for (int i=0;i<pantalla.length;i++){
            for (int j=0;j<pantalla[i].length;j++){
                System.out.print(pantalla[i][j]);
            }
        }
        System.out.println();
    }
    
    /*El metodo buscarLetra sera de utilidad a la hora de comprobar si la letra que el usuario indica se encuentra en la
    palabraSecreta. Este metodo pretende ser similar al IndexOf que ya hemos usado en C#*/
    public static int buscarLetra(char [] palabraSecreta,char letra,int comienzo){
        int posicion = -1;
        int i = comienzo;
        while (i<palabraSecreta.length && posicion==-1){
            if (palabraSecreta[i]==letra){
                posicion=i;
            }
            i++;
        }
        return posicion;
    }
    
    /*Metodo que ira modificando la matriz pantalla que se encarga de contener el estado del "ahorcado". A cada 
    nuevo fallo se dibujara una parte del ahorcado*/
    public static void dibujoAhorcado(int fallos, char [][] pantalla){
        switch (fallos){
            case 1 -> pantalla [4][0] = pantalla [4][1] = pantalla [4][2] = '@';
            case 2 -> pantalla [3][1] = pantalla [2][1] = pantalla [1][1] = pantalla [0][1] = '@';
            case 3 -> pantalla [0][2] = pantalla [0][3] = '@';
            case 4 -> pantalla [1][3] = '@';
            case 5 -> pantalla [2][3] = '@';
            case 6 -> pantalla [1][2] ='@';
            case 7 -> pantalla [1][4] = '@';
            case 8 -> pantalla [3][2] = '@';
            case 9 -> pantalla [3][4] = '@';
        }
    }
    
    public static void main(String[] args) throws IOException {
        char [] palabraSecreta = {'a','r','r','a','y'};
        char [] palabra = new char [palabraSecreta.length];
        char [][] pantalla = new char [5][5];
        
        boolean ganador = false;
        int fallos = 0;
        char letra;
        int pos = 0;
        int aciertos = 0;
        
        System.out.println("Empieza el juego!!");
        inicializaJuego(palabra,pantalla);
        mostrar (palabra,pantalla);
        
        while (fallos <= 9 && !ganador){
            System.out.println("Letra:");
            letra = (char) System.in.read();
            if ((pos=buscarLetra(palabraSecreta,letra,0)) != -1){
                palabra[pos] = palabraSecreta[pos];
                aciertos++;
            }
            else{
                fallos++;
                dibujoAhorcado(fallos,pantalla);
            }
            mostrar(palabra,pantalla);
            if (aciertos==palabraSecreta.length){
                ganador=true;
            }
        }
        if (ganador==true){
            System.out.println("Ganaste!!");
        }
        else{
            System.out.println("Ohhhhh!!!!! Has perdido!!!");
        }
    }
}
