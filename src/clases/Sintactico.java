/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import estructurasDatos.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import planetprogrammig.EditorPlanetProgramming;

/**
 *
 * @author por_s
 */
public class Sintactico {

    private String pathTablaExcel;
    private String pathGramatica;
    private ArrayList<Lexema> lexemas;

    private PilaD pilaSintactico;
    private ColaD colaSalida;
    private LDL listaGramatica;
    private int numError;

    private String[][] tablaPredictiva;
    private int[] objT;
    private String[] objNT;

    private ArrayList<String> impresionPilaSintactico;
    private ArrayList<String> impresionColaSalida;

    public Sintactico() {

    }

    public Sintactico(String pathTablaExcel, String pathGramatica, ArrayList<Lexema> lexemas) {
        this.pathTablaExcel = pathTablaExcel;
        this.pathGramatica = pathGramatica;
        this.lexemas = lexemas;
    }

    public String[] analisisSintactico() {
        String salida[] = new String[2];
        salida[0] = "******Pila del sintactico*****************\n";
        salida[1] = "******Cola de salida*****************\n";
        impresionColaSalida = new ArrayList<>();
        impresionPilaSintactico = new ArrayList<>();
        //Extrae el lexico
        extraeLexico();
        //Estrae la gramatica
        extraerGramatica();
        //Extrae la tabla predictiva, extrae los objetos Terminales, extrae los objetos No terminales
        extraerTablaPredictiva();
        //Realiza el analisis sintactico
        //Crear pilaSintactico 
        this.pilaSintactico = new PilaD();
        this.pilaSintactico.inserta(new Nodo("$", -1), null);

        //Terminar la cola del lexico
        this.colaSalida.inserta(new Nodo("$", -1), null);

        String resultado = procesoAnalisis();

        //Obtiene los datos de la pila del sintactico y la cola de la salida
        for (int i = 0; i < impresionPilaSintactico.size(); i++) {
            salida[0] += impresionPilaSintactico.get(i) + "\n";
        }

        for (int i = 0; i < impresionColaSalida.size(); i++) {
            salida[1] += impresionColaSalida.get(i) + "\n";
        }
        salida[0] += resultado;
        //Destruye el analisis
        this.colaSalida = null;
        this.lexemas = null;
        this.listaGramatica = null;
        this.objNT = null;
        this.objT = null;
        this.pathGramatica = null;
        this.pathTablaExcel = null;
        this.pilaSintactico = null;
        this.tablaPredictiva = null;

        return salida;
    }

    private String procesoAnalisis() {
        String error = "";
        String nI = procesoInicial();
        this.pilaSintactico.inserta(new Nodo(nI, 0), null);
        impresionPilaSintactico.add(imprimirPila());
        impresionColaSalida.add(imprimirCola());
        while (pilaSintactico.getTope() != null || colaSalida.getF() != null) {
            if (pilaSintactico.getTope().getS().equals(colaSalida.getF().getS())) {
                agregaNodos();
                impresionPilaSintactico.add(imprimirPila());
                impresionColaSalida.add(imprimirCola());
            } else {
                if (pilaSintactico.getTope().getS().startsWith("<")) {
                    String num = buscaEnTablaPredictiva(colaSalida.getF().getS(), pilaSintactico.getTope().getS());
                    if (!num.isEmpty()) {

                        try {
                            int num1 = Integer.parseInt(num);
                            PilaD p = obtenerPilaGramatica(num1);
                            if (p == null) {
                                error = "¿Pila vacia?";
                                Errores e = new Errores();
                                e.setIdError("E19");
                                e.setDescripcion(error);
                                e.setLineaCodigo((int) colaSalida.getF().getObj());
                                e.setTipo("ERROR DESCONOCIDO");
                                EditorPlanetProgramming.lstError.add(e);
                                break;
                            } else {
                                reemplazaNodoSintactico(p);
                                impresionPilaSintactico.add(imprimirPila());
                                impresionColaSalida.add(imprimirCola());
                            }
                        } catch (Exception ex) {
                            error = "Error desconocido ";
                            Errores e = new Errores();
                            e.setIdError("E18");
                            e.setDescripcion(error);
                            e.setLineaCodigo((int) colaSalida.getF().getObj());
                            e.setTipo("ERROR DESCONOCIDO");
                            EditorPlanetProgramming.lstError.add(e);
                            break;
                        }

                    } else {
                        String s = buscaLoQueSeEsperaba(pilaSintactico.getTope().getS());
                        s = buscaLoQueSeEspera(s);
                        if (s == "") {
                            s = buscaLoQueSeEspera(colaSalida.getF().getS());
                            error = "Error sintactico al recibir " + s;
                            numError = (int) colaSalida.getF().getObj();
                            Errores e = new Errores();
                            e.setIdError("E18");
                            e.setDescripcion(error);
                            e.setLineaCodigo((int) colaSalida.getF().getObj());
                            e.setTipo("ERROR SINTACTICO");
                            EditorPlanetProgramming.lstError.add(e);
                        } else {

                            error = "Error sintactico... se esperaba un " + s;
                            numError = (int) colaSalida.getF().getObj();
                            Errores e = new Errores();
                            e.setIdError("E17");
                            e.setDescripcion("Se esperaba un " + s);
                            e.setLineaCodigo((int) colaSalida.getF().getObj());
                            e.setTipo("ERROR SINTACTICO");
                            EditorPlanetProgramming.lstError.add(e);
                        }
                        break;
                    }
                } else if (pilaSintactico.getTope().getS().startsWith("λ")) {
                    agregaNodoPorVacio();
                    impresionPilaSintactico.add(imprimirPila());
                    impresionColaSalida.add(imprimirCola());
                } else {
                    String s = buscaLoQueSeEspera(pilaSintactico.getTope().getS());
                    error = "Error sintactico... se esperaba " + s + " en la linea " + colaSalida.getF().getObj();
                    Errores e = new Errores();
                    e.setIdError("E16");
                    e.setDescripcion("Se esperaba " + s);
                    e.setLineaCodigo((int) colaSalida.getF().getObj());
                    e.setTipo("ERROR SINTACTICO");
                    EditorPlanetProgramming.lstError.add(e);
                    numError = (int) colaSalida.getF().getObj();
                    break;
                }

            }
        }
        if (pilaSintactico.getTope() == null && colaSalida.getA() == null) {
            error = "Sintacticamente correcto";
        }
        return error;
    }

    private void agregaNodos() {
        pilaSintactico.elimina(null);
        colaSalida.elimina(null);
    }

    private void agregaNodoPorVacio() {
        pilaSintactico.elimina(null);
    }

    private void reemplazaNodoSintactico(PilaD pila) {
        pilaSintactico.elimina(null);
        while (pila.getTope() != null) {
            pilaSintactico.inserta(pila.elimina(null), null);
        }
    }

    public PilaD getPilaSintactico() {
        return pilaSintactico;
    }

    public void setPilaSintactico(PilaD pilaSintactico) {
        this.pilaSintactico = pilaSintactico;
    }

    public String[][] getTablaPredictiva() {
        return tablaPredictiva;
    }

    public void setTablaPredictiva(String[][] tablaPredictiva) {
        this.tablaPredictiva = tablaPredictiva;
    }

    public int[] getObjT() {
        return objT;
    }

    public void setObjT(int[] objT) {
        this.objT = objT;
    }

    public String[] getObjNT() {
        return objNT;
    }

    public void setObjNT(String[] objNT) {
        this.objNT = objNT;
    }

    public String getPathTablaExcel() {
        return pathTablaExcel;
    }

    public void setPathTablaExcel(String pathTablaExcel) {
        this.pathTablaExcel = pathTablaExcel;
    }

    public ColaD getColaSalida() {
        return colaSalida;
    }

    public void setColaSalida(ColaD colaSalida) {
        this.colaSalida = colaSalida;
    }

    public LDL getListaGramatica() {
        return listaGramatica;
    }

    public void setListaGramatica(LDL listaGramatica) {
        this.listaGramatica = listaGramatica;
    }

    public String getPathGramatica() {
        return pathGramatica;
    }

    public void setPathGramatica(String pathGramatica) {
        this.pathGramatica = pathGramatica;
    }

    public ArrayList<Lexema> getLexemas() {
        return lexemas;
    }

    public void setLexemas(ArrayList<Lexema> lexemas) {
        this.lexemas = lexemas;
    }

    public int getNumError() {
        return numError;
    }

    public void setNumError(int numError) {
        this.numError = numError;
    }

    /**
     * Método que extrae el lexico y lo convierte en una cola dinamica
     *
     * @param lexico
     */
    private void extraeLexico() {
        this.colaSalida = new ColaD();
        for (int i = 0; i < this.lexemas.size(); i++) {
            if (!(this.lexemas.get(i).getNumToken() == 82 || this.lexemas.get(i).getNumToken() == 83)) {
                this.colaSalida.inserta(new Nodo(String.valueOf(lexemas.get(i).getNumToken()), lexemas.get(i).getRenglon()), null);
            }
        }

    }

    /**
     * Método que extrae la gramatica a partir de un archivo txt
     *
     */
    private void extraerGramatica() {
        Date d = new Date();
        Informacion info = new Informacion();
        info.setAccion("Extracción de la gramatica ");
        info.setFechaTransaccion(d.toString());
        info.setIdInfo("1012");
        info.setNoTransaccion("URL: " + getPathGramatica());

        String s = ManipulaArchivos.cargarArchivo(getPathGramatica());
        StringTokenizer st = new StringTokenizer(s, " \n\t\r", true);
        String cadena;
        ArrayList<String> palabras = new ArrayList<>();
        while (st.hasMoreElements()) {
            cadena = st.nextToken();
            if (cadena.equals(" ") || cadena.equals("\n") || cadena.equals("\t") || cadena.equals("\r")) {

            } else {
                palabras.add(cadena);
            }
        }
        LDL lisaGramatica = new LDL();
        int cont = 1;
        int i = 0;
        do {
            if ((i + 1) < palabras.size()) {
                if (palabras.get(i + 1).equals("→")) {
                    Nodo n = new Nodo(palabras.get(i), cont);
                    i = i + 2;

                    PilaD pilaG = new PilaD();
                    while (i < palabras.size()) {
                        pilaG.inserta(new Nodo(palabras.get(i), -1), null);
                        i++;
                        if ((i + 1) < palabras.size()) {
                            if (palabras.get(i + 1).equals("→")) {
                                i--;
                                break;
                            }
                        }
                    }

                    n.setP(pilaG);
                    lisaGramatica.inserta(n, null);
                    cont++;
                }
            }
            i++;
        } while (i < palabras.size());
        setListaGramatica(lisaGramatica);
        info.setEstado("Aceptada");
        EditorPlanetProgramming.lstInfo.add(info);
//     IMPRIME LA GRAMATICA
//        String s2 = "Gramatica \n";
//        Nodo aux = lisaGramatica.getR();
//        while (aux != null) {
//            String s3 = "";
//            while (aux.getP().getTope() != null) {
//                s3 += " " + aux.getP().elimina(null).getS();
//            }
//
//            PilaD pilaAux1 = aux.getP();
//            PilaD pilaAux2 = new PilaD();
//            while (pilaAux1.getTope() != null) {
//                Nodo aux1 = pilaAux1.elimina(null);
//
//                pilaAux2.inserta(aux1, null);
//            }
//            while (pilaAux2.getTope() != null) {
//                Nodo aux1 = pilaAux2.elimina(null);
//                s3 += aux.getS() + " ";
//                pilaAux1.inserta(aux1, null);
//            }
//
//            s2 += aux.getObj() + " " + aux.getS() + " " + s3 + "\n";
//            aux = aux.getSig();
//        }
//        System.out.println(s2);
    }

    /**
     * Método que extrae la tabla de Excel en donde se encuentra la tabla
     * predictiva y la convierte en la matriz predictiva y obtiene los objetos
     * Terminales y no terminales
     *
     */
    private void extraerTablaPredictiva() {

        String ttablaPredictiva[][];
        ArrayList<String> datos = new ArrayList<>();                //Matriz predictiva
        int numF, numC = 0, conArray = 0;

        Date date = new Date();
        Informacion info = new Informacion();
        info.setAccion("Extracción de la tabla de la tabla predictiva");
        info.setFechaTransaccion(date.toString());
        info.setIdInfo("5012");
        info.setNoTransaccion("URL: " + getPathTablaExcel());

        try {
            FileInputStream file = new FileInputStream(new File(getPathTablaExcel()));
            XSSFWorkbook book = new XSSFWorkbook(file);
            XSSFSheet sheet = book.getSheetAt(0);

            int numFilas = sheet.getLastRowNum();
            numF = numFilas;
            //Fila 1 Alfabeto
            for (int i = 0; i < numFilas; i++) {
                Row fial = sheet.getRow(i);
                int numCOLS = fial.getLastCellNum();
                numC = numCOLS;
                for (int j = 0; j < numCOLS; j++) {
                    Cell celda = fial.getCell(j);
                    try {
                        //System.out.println(celda.getCellType().toString());
                        if (celda.getCellType().toString().equals("STRING")) {

                            datos.add(String.valueOf(celda.getStringCellValue()).trim());
                        } else if (celda.getCellType().toString().equals("NUMERIC")) {
                            double c = celda.getNumericCellValue();
                            int d = (int) c;
                            datos.add(String.valueOf(d));
                        } else {
                            datos.add(" ");
                        }
                    } catch (Exception e) {
                        System.out.println("Error " + e.toString());
                    }

                }
            }
            ttablaPredictiva = new String[numF][numC];
            for (int i = 0; i < numF; i++) {
                for (int j = 0; j < numC; j++) {
                    ttablaPredictiva[i][j] = datos.get(conArray);
                    conArray++;
                }
            }

            setTablaPredictiva(ttablaPredictiva);
            info.setEstado("Aceptada");
        } catch (IOException e) {
            System.out.println("" + e);
            info.setEstado("Rechazada");
        } finally {
            EditorPlanetProgramming.lstInfo.add(info);
        }
//        String s = "Tabla\n";
//        for (int i = 0; i < getTablaPredictiva().length; i++) {
//            for (int j = 0; j < getTablaPredictiva()[i].length; j++) {
//                s += getTablaPredictiva()[i][j] + "\t";
//            }
//            s += "\n";
//        }
//        System.out.println(s);
    }

    /**
     * Metodo que busca en la tabla predictiva dado un objeto terminal y no
     * terminal Busca primero que el objeto no terminal este en la tabla,
     * despues revisa que exista una concidencia entre el objeto no terminal y
     * regresa el numero
     *
     * @param objT Objeto terminal, el token, de tipo String
     * @param objNT Objeto no terminal, de tipo String
     * @return
     */
    private String buscaEnTablaPredictiva(String objT, String objNT) {
        String num = "";
        for (int i = 0; i < getTablaPredictiva().length; i++) {
            if (getTablaPredictiva()[i][0].equals(objNT)) {
                for (int j = 0; j < getTablaPredictiva()[i].length; j++) {
                    if (getTablaPredictiva()[0][j].equals(objT)) {
                        num = getTablaPredictiva()[i][j].trim();
                    }
                }
            }
        }
        return num;
    }

    /**
     * Metodo que inicia la pila sintactica con la gramatica
     *
     * @return
     */
    private PilaD procesoInicialX() {
        PilaD inicioPila = null;
        LDL auxLista = listaGramatica;
        Nodo n = auxLista.getR();
        while (n != null) {
            if (n.getObj().equals(Integer.parseInt("1"))) {
                inicioPila = n.getP();
                break;
            }
            n = n.getSig();
        }
        return inicioPila;
    }

    /**
     * Metodo que inicia la pila sintactica con la gramatica
     *
     * @return
     */
    private String procesoInicial() {
        LDL auxLista = listaGramatica;
        Nodo n = auxLista.getR();
        String s = "";
        while (n != null) {
            if (n.getObj().equals(Integer.parseInt("1"))) {
                s = n.getS();
                break;
            }
            n = n.getSig();
        }
        return s;
    }

    /**
     * Metodo que extrae la pila de la gramatica segun el numero de la lista
     *
     * @param num
     * @return
     */
    private PilaD obtenerPilaGramatica(int num) {

        LDL auxLista = listaGramatica;
        PilaD pilaAux1 = new PilaD();
        PilaD pilaAux2 = new PilaD();
        PilaD pilaAux3 = new PilaD();
        Nodo n = auxLista.getR();

        while (n != null) {
            if (n.getObj().equals(num)) {
                pilaAux1 = n.getP();
                break;
            }
            n = n.getSig();
        }
        if (pilaAux1 == null) {

        } else {

            while (pilaAux1.getTope() != null) {
                Nodo aux = pilaAux1.elimina(null);
                pilaAux2.inserta(aux, null);
            }
            while (pilaAux2.getTope() != null) {
                Nodo aux = pilaAux2.elimina(null);
                pilaAux1.inserta(new Nodo(aux.getS(), -1), null);
                pilaAux3.inserta(new Nodo(aux.getS(), -1), null);
            }

            Nodo n1 = auxLista.getR();

            while (n1 != null) {
                if (n1.getObj().equals(num)) {
                    n1.setP(pilaAux3);
                    break;
                }
                n1 = n1.getSig();
            }
        }

        return pilaAux1;
    }

    /**
     * Método que imprime la pila en su estado actual
     *
     * @return una cadena
     */
    public String imprimirPila() {
        String s = "";

        PilaD pilaAux1 = pilaSintactico;
        PilaD pilaAux2 = new PilaD();
        while (pilaAux1.getTope() != null) {
            Nodo aux = pilaAux1.elimina(null);

            pilaAux2.inserta(aux, null);
        }
        while (pilaAux2.getTope() != null) {
            Nodo aux = pilaAux2.elimina(null);
            s += aux.getS() + " ";
            pilaAux1.inserta(aux, null);
        }

        return s;
    }

    /**
     * Método que imprime la cola en su estado actual
     *
     * @return una cadena
     */
    public String imprimirCola() {
        String s = "";
        ColaD colaAux1 = colaSalida;
        ColaD colaAux2 = new ColaD();
        while (colaAux1.getA() != null) {
            Nodo n = colaAux1.elimina(null);
            s += n.getS() + " ";
            colaAux2.inserta(n, null);
        }
        while (colaAux2.getA() != null) {
            colaAux1.inserta(colaAux2.elimina(null), null);
        }

        return s;
    }

    private String buscaLoQueSeEsperaba(String objNT) {
        String num = "";
        int n = -1;
        for (int i = 0; i < getTablaPredictiva().length; i++) {
            if (getTablaPredictiva()[i][0].equals(objNT)) {
                n = i;
                break;
            }
        }
        if (n != -1) {
            for (int j = 0; j < getTablaPredictiva()[n].length; j++) {
                if (!(j == 0)) {
                    if (!getTablaPredictiva()[n][j].trim().equals("")) {
                        if (num.trim().equals("")) {
                            num += getTablaPredictiva()[0][j];
                        } else {
                            num += " o " + getTablaPredictiva()[0][j];
                        }

                    }
                }
            }
        }
        char[] signo = num.toCharArray();
        num = "";
        for (int i = 0; i < signo.length; i++) {
            num += signo[i];
            if (i + 1 < signo.length) {
                if (signo[i + 1] == 'o') {
                    if (signo[i + 3] == '$') {
                        break;
                    }
                }
            }

        }
        return num;
    }

    private String buscaLoQueSeEspera(String s) {
        String espera = "";
        StringTokenizer st = new StringTokenizer(s, " ", false);
        ArrayList<String> ar = new ArrayList<>();
        while (st.hasMoreElements()) {
            String pa = st.nextToken();
            if (pa.equals("o")) {

            } else {
                ar.add(pa);
            }
        }

        Lexico ls = new Lexico();
        String tokensAutomatas[][] = ls.getConjuntoTokensAutomatas();
        String tokensFijos[][] = ls.getConjuntoTokensFijos();

        for (int i = 0; i < ar.size(); i++) {
            String tokenSalida = ar.get(i);
            int token = 0;
            String tokenS = "";
            boolean automata = false;
            boolean fijos = false;
            while (token < tokensAutomatas.length) {

                if (tokenSalida.equals(tokensAutomatas[token][1])) {

                    tokenS = tokensAutomatas[token][0];
                    automata = true;

                }
                token++;
            }

            if (automata) {
                espera += tokenS + " o ";
            } else {
                token = 0;
                while (token < tokensFijos.length) {

                    if (tokenSalida.equals(tokensFijos[token][2])) {

                        tokenS = tokensFijos[token][1] + "(" + tokensFijos[token][0] + ")";
                        fijos = true;

                    }
                    token++;
                }
                if (fijos) {
                    espera += tokenS + " o ";
                }
            }

        }
        if (espera.endsWith(" o ")) {

            espera = espera.substring(0, espera.length() - 3);

        }

        return espera;
    }

    /**
     * Get the value of conjuntoErrores
     *
     * @return the value of conjuntoErrores
     */
    public String[][] getConjuntoErrores() {
        String listaErrores[][] = {
            {"Caracter desconocido", "84", "E16"}};

        return listaErrores;
    }
}
