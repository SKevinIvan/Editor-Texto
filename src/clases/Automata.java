/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 * Clase que analiza automatas
 *
 * @author hummingbird
 */
public class Automata {

    //Incio de declaración de variables
    private String cadena;
    private char[] vectorAlfabeto;
    private int[][] matrizTransicion;
    private String resAutomata;
    
    private int estado;
    private int transaccionFinal;

    static void main(String[] args) {
        
    }
    //Fin de declaración de variables
    /**
     * Get the value of transaccionFinal
     *
     * @return the value of transaccionFinal
     */
    public int getTransaccionFinal() {
        return transaccionFinal;
    }

    /**
     * Set the value of transaccionFinal
     *
     * @param transaccionFinal new value of transaccionFinal
     */
    public void setTransaccionFinal(int transaccionFinal) {
        this.transaccionFinal = transaccionFinal;
    }

    /**
     * Get the value of estado
     *
     * @return the value of estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Set the value of estado
     *
     * @param estado new value of estado
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * Construtor por defecto
     */
    public Automata() {
        this.matrizTransicion = null;
        this.cadena = null;
        this.vectorAlfabeto = null;
    }

    /**
     * Constructor de la clase que inicializa las variables con lo recibido
     *
     * @param cadena la cadena de tipo String
     * @param vectorAlfabeto el vector del Alfabeto de tipo char []
     * @param matrizTransicion la matriz de transicion de tipo int [][]
     */
    public Automata(String cadena, char[] vectorAlfabeto, int[][] matrizTransicion) {
        this.cadena = cadena;
        this.vectorAlfabeto = vectorAlfabeto;
        this.matrizTransicion = matrizTransicion;
    }

    /**
     * Get the value of resAutomata
     *
     * @return the value of resAutomata
     */
    public String getResAutomata() {
        return resAutomata;
    }

    /**
     * Set the value of resAutomata
     *
     * @param resAutomata new value of resAutomata
     */
    public void setResAutomata(String resAutomata) {
        this.resAutomata = resAutomata;
    }

    /**
     * Get the value of cadena
     *
     * @return the value of cadena
     */
    public String getCadena() {
        return cadena;
    }

    /**
     * Set the value of cadena
     *
     * @param cadena new value of cadena
     */
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    /**
     * Get the value of matrizTransicion
     *
     * @return the value of matrizTransicion
     */
    public int[][] getMatrizTransicion() {
        return matrizTransicion;
    }

    /**
     * Set the value of matrizTransicion
     *
     * @param matrizTransicion new value of matrizTransicion
     */
    public void setMatrizTransicion(int[][] matrizTransicion) {
        this.matrizTransicion = matrizTransicion;
    }

    /**
     * Get the value of vectorAlfabeto
     *
     * @return the value of vectorAlfabeto
     */
    public char[] getVectorAlfabeto() {
        return vectorAlfabeto;
    }

    /**
     * Set the value of vectorAlfabeto
     *
     * @param vectorAlfabeto new value of vectorAlfabeto
     */
    public void setVectorAlfabeto(char[] vectorAlfabeto) {
        this.vectorAlfabeto = vectorAlfabeto;
    }

    /**
     * Metodo que recorre a la cadena
     *
     * @return el resultado de tipo String
     */
    public String analisisAutomata() {

        String resultado = "";

        int trans = 0, i = 0;
        char[] vectorPalabra = getCadena().toCharArray();
        boolean charInv = false, estInv = false;
        ArrayList<Character> charInvs = new ArrayList<>();
        resultado += "Entrada\t Salida\n";
        do {

            //Imprime el estado
            resultado += trans + "\t";
            //Inicio de la impresión de la impresión los caracteres faltantes
            for (int k = i; k < vectorPalabra.length; k++) {
                resultado += vectorPalabra[k];
            }
            resultado += "\n";
            //Fin de la impresión de los caracteres faltantes
            //Revisa si el estado es valido en el caracter
            if (vectorPalabra.length == 0 && estadoAceptacion(trans) == -3) {
                
                break;
            } else if (vectorPalabra.length == 0 && estadoAceptacion(trans) == -2) {
               
                break;
            }
            if (analisisEstado(vectorPalabra[i], trans) == -1) {
             
                estInv = true;
            } else if (analisisEstado(vectorPalabra[i], trans) != -4) {
                trans = analisisEstado(vectorPalabra[i], trans);
                
            } else {
                charInv = true;
                //Imprime los caracteres no permitidos
                if (charInvs.isEmpty()) {
                    charInvs.add(vectorPalabra[i]);
                } else {
                    boolean repe = false;
                    for (int j = 0; j < charInvs.size(); j++) {
                        if (charInvs.get(j) == vectorPalabra[i]) {
                            repe = true;
                        }
                    }
                    if (!repe) {
                        charInvs.add(vectorPalabra[i]);
                        
                    }
                }
            }

            i++;
            
        } while (i < vectorPalabra.length);

        //Transaccion es el estado en donde se quedo
        //Aux es el estado donde el anterior
        //Imprime el ultimo estado
        resultado += trans + "\n";
        setTransaccionFinal(trans);
        //Imprime el resultado
        if (estInv) {
            setResAutomata("Estado no valido -> Cadena no valida");
            setEstado(-1);
        } else if (charInv) {
            setEstado(-4);
            String caracteresInvalidos = "";
            for (int j = 0; j < charInvs.size(); j++) {
                caracteresInvalidos += charInvs.get(j) + " ";
            }
            setResAutomata("Carácter(es) no esperado -> Cadena no valida\n" + caracteresInvalidos);
        } else if (estadoAceptacion(trans) == -3) {
            setEstado(-3);
            setResAutomata("Cadena valida");
        } else if (estadoAceptacion(trans) == -2) {
            setEstado(-2);
            setResAutomata("Cadena no valida");
        }
        
        return resultado;
    }

    /**
     * Metodo que valida el caracter
     *
     * @param vectorPalabra es el caracter a buscar
     * @param trans numero de transacción/estado
     * @return trans -1 si no hay un estado, -4 no es un elemento del alfabeto y
     * sino entonces retorna el valor del siguiente estado
     */
    private int analisisEstado(char vectorPalabra, int trans) {
        for (int j = 0; j < getVectorAlfabeto().length; j++) {
            if (vectorPalabra == getVectorAlfabeto()[j]) {
                if (getMatrizTransicion()[trans][j] == -1) {
                    return -1;
                } else {
                    return getMatrizTransicion()[trans][j];
                }
            }
            if (j == getVectorAlfabeto().length - 1) {
                trans = -4;
            }
        }
        return trans;
    }

    /**
     * Metodo que checa si es un estado de aceptacion
     *
     * @param trans Numero de transicion de tipo entero
     * @return trans -2 si no es un estado, -3 si es un estado de aceptación
     */
    private int estadoAceptacion(int trans) {

        return getMatrizTransicion()[trans][getMatrizTransicion()[trans].length - 1];
    }

    /**
     * Metodo que despliega la matriz de transicion
     *
     */
    public void desMatrizTrasicion() {
        System.out.println("**** Matriz de transisción ***** ");
        for (int[] matrizTransicion1 : getMatrizTransicion()) {
            for (int j = 0; j < matrizTransicion1.length; j++) {
                System.out.print(matrizTransicion1[j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * Metodo que depliega el vector
     *
     */
    public void desVectorAlfabeto() {
        System.out.println("**** Vector alfabeto **** ");
        for (int i = 0; i < getVectorAlfabeto().length; i++) {
            System.out.print(getVectorAlfabeto()[i] + "\t");
        }
        System.out.println("");
    }

    /**
     * Metodo que desplega la cadena
     *
     */
    public void desCadena() {
        System.out.println("**** Cadena a validar **** ");
        System.out.println(getCadena());
    }

}
