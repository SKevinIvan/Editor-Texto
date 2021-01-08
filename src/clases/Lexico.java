package clases;

import java.util.StringTokenizer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Lexico {

    //Inicio de declaracion de atributos
    private String textoCodigo;
    private String separadores;
    private String pathTablaExcel;
    private int[][] matrizTransaccionGeneral;
    private char[] vecAlfabeto;
    private String[][] conjuntoTokensFijos;
    private String[][] conjuntoTokensAutomatas;
    private String[][] conjuntoErrores;
    private ArrayList<Lexema> listaLexico;
    private int[] numRenglon;
    //Fin de declaración de atributos

    public Lexico() {
    }

    /**
     * Constructor de clase
     *
     * @param textoCodigo - String: Texto a analizar
     * @param separadores - String: Separadores naturales
     * @param pathTablaExcel - String: Direccion en donde se obtiene la tabla,
     * fila 1- primera linea Alfabeto, columna 1- Estados del 0 al ...
     */
    public Lexico(String textoCodigo, String separadores, String pathTablaExcel) {
        this.textoCodigo = textoCodigo;
        this.separadores = separadores;
        this.pathTablaExcel = pathTablaExcel;
        extraerTabla();
        this.listaLexico = new ArrayList<>();
    }

    /**
     * Constructor de clase
     *
     * @param textoCodigo - String: Texto a analizar
     * @param separadores - String: Separadores
     * @param matrizTransaccionGeneral - int[][] Matriz de transaccion general
     * @param vecAlfabeto -char [] Alfabeto
     */
    public Lexico(String textoCodigo, String separadores, int[][] matrizTransaccionGeneral, char[] vecAlfabeto) {
        this.textoCodigo = textoCodigo;
        this.separadores = separadores;
        this.matrizTransaccionGeneral = matrizTransaccionGeneral;
        this.vecAlfabeto = vecAlfabeto;
        this.listaLexico = new ArrayList<>();
    }

    /**
     * Get the value of pathTablaExcel
     *
     * @return the value of pathTablaExcel
     */
    public String getPathTablaExcel() {
        return pathTablaExcel;
    }

    /**
     * Set the value of pathTablaExcel
     *
     * @param pathTablaExcel new value of pathTablaExcel
     */
    public void setPathTablaExcel(String pathTablaExcel) {
        this.pathTablaExcel = pathTablaExcel;
    }

    /**
     * Get the value of conjuntoErrores
     *
     * @return the value of conjuntoErrores
     */
    public String[][] getConjuntoErrores() {
        String listaErrores[][] = {
            {"Caracter desconocido", "80"},
            {"Nombre de proyecto no valido", "81"},
            {"Nombre de paquete no valido", "82"},
            {"Nombre de clase no valido", "83"},
            {"Nombre de funcion o procedimiento no valido", "84"},
            {"Nombre de variable no valida", "85"},
            {"Nombre de constante no valida", "86"},
            {"Nombre de variable de objeto no valida", "87"},
            {"Numero entero o byte incorrectos", "88"},
            {"Numero de flotante o doble o largo o corto incorrectos", "89"},
            {"Cadena no valida", "90"},
            {"Caracter no valido", "91"},
            {"Nombre de libreria no valida", "92"},
            {"Comentario de linea no valida", "93"},
            {"Comentario de bloque no valida", "94"},};

        this.conjuntoErrores = listaErrores;
        return conjuntoErrores;
    }

    /**
     * Set the value of conjuntoErrores
     *
     * @param conjuntoErrores new value of conjuntoErrores
     */
    public void setConjuntoErrores(String[][] conjuntoErrores) {
        this.conjuntoErrores = conjuntoErrores;
    }

    /**
     * Get the value of conjuntoTokensAutomatas
     *
     * @return the value of conjuntoTokensAutomatas
     */
    public String[][] getConjuntoTokensAutomatas() {
        String[][] listaTokensAutomatas = {
            {"Nombre de proyecto", "66"},
            {"Nombre de paquete", "67"},
            {"Nombre de clase", "68"},
            {"Nombre de funcion o procedimiento", "69"},
            {"Nombre de variable", "70"},
            {"Nombre de constante", "71"},
            {"Nombre de variable de objeto", "72"},
            {"Numero entero o byte", "73"},
            {"Numero flotante o doble o largo o corto", "74"},
            {"Cadena", "75"},
            {"Caracter", "76"},
            {"Nombre de libreria", "77"},
            {"Comentario de linea", "78"},
            {"Comentario de bloque", "79"}
        };
        this.conjuntoTokensAutomatas = listaTokensAutomatas;
        return listaTokensAutomatas;
    }

    /**
     * Set the value of conjuntoTokensAutomatas
     *
     * @param conjuntoTokensAutomatas new value of conjuntoTokensAutomatas
     */
    public void setConjuntoTokensAutomatas(String[][] conjuntoTokensAutomatas) {
        this.conjuntoTokensAutomatas = conjuntoTokensAutomatas;
    }

    /**
     * Get the value of conjuntoTokensFijos
     *
     * @return the value of conjuntoTokensFijos
     */
    public String[][] getConjuntoTokensFijos() {
        String[][] listaTokensFijos = {
            {"publico", "Modificador de acceso: publico", "1"},
            {"privado", "Modificador de acceso: privado", "2"},
            {"protegido", "Modificador de acceso: protegido", "3"},
            {"clase", "Palabra reservada: clase", "4"},
            {"paquete", "Palabra reservada: paquete", "5"},
            {"importar", "Palabra reservada: importar", "6"},
            {"estatico", "Palabra reservada: estatico", "7"},
            {"final", "Palabra reservada: final", "8"},
            {"super", "Palabra reservada: super", "9"},
            {"retorno", "Palabra reservada: retorno", "10"},
            {"procedimiento", "Palabra reservada: procedimiento", "11"},
            {"funcion", "Palabra reservada: funcion", "12"},
            {"entrada", "Palabra reservada: entrada", "13"},
            {"salida", "Palabra reservada: salida", "14"},
            {"sistema", "Palabra reservada: sistema", "15"},
            {"linea", "Palabra reservada: linea", "16"},
            {"abstracta", "Palabra reservada: abstracta", "17"},
            {"extender", "Palabra reservada: extender", "18"},
            {"implementar", "Palabra reservada: implementar", "19"},
            {"interfaz", "Palabra reservada: interfaz", "20"},
            {"obtener", "Palabra reservada: obtener", "21"},
            {"asignar", "Palabra reservada: asignar", "22"},
            {"principal", "Palabra reservada: principal", "23"},
            {"nuevo", "Palabra reservada: nuevo", "24"},
            {"este", "Palabra reservada: este", "25"},
            {"nulo", "Palabra reservada: nulo", "26"},
            {"leer", "Palabra reservada: leer", "27"},
            {"imprimir", "Palabra reservada: imprimir", "28"},
            {"defecto", "Palabra reservada: defecto", "29"},
            {"terminar", "Palabra reservada: terminar", "30"},
            {"salir", "Palabra reservada: salir", "31"},
            {"caso", "Palabra reservada: caso", "32"},
            {"alternativa", "Palabra reservada: alternativa", "33"},
            {"si", "Estructura de seleccion: si", "34"},
            {"sino", "Estructura de seleccion: sino", "35"},
            {"sinoSi", "Estructura de seleccion: sinoSi", "36"},
            {"mientras", "Cliclo: mientras", "37"},
            {"hacerMientras", "Ciclo: harcerMientras", "38"},
            {"para", "Ciclo: para", "39"},
            {"verdadero", "Palabra reservada: verdadero", "40"},
            {"falso", "Palabra reservada: falso", "41"},
            {"entero", "Tipo de dato: entero", "42"},
            {"flotante", "Tipo de dato: flotante", "42"},
            {"doble", "Tipo de dato: double", "42"},
            {"cadena", "Tipo de dato: cadena", "42"},
            {"booleano", "Tipo de dato: booleano", "42"},
            {"largo", "Tipo de dato: largo", "42"},
            {"objeto", "Tipo de dato: objeto", "42"},
            {"caracter", "Tipo de dato: caracter", "43"},
            {"corto", "Tipo de dato: corto", "43"},
            {"byte", "Tipo de dato: byte", "43"},
            {"+", "Operador artimetico suma o concatenación", "44"},
            {"-", "Operador aritmetico resta", "45"},
            {"*", "Operador aritmetico producto", "45"},
            {"/", "Operador artimetico cociente", "45"},
            {"%", "Operador artimetico residuo de cociente", "45"},
            {"^", "Operador aritmetico potencia", "45"},
            {"<", "Opererador relacional: menor que", "46"},
            {">", "Operador relacional: mayor que", "46"},
            {">=", "Operador relacional: mayor igual que", "46"},
            {"<=", "Operador relacional: menor igual que", "46"},
            {"==", "Operador relacional: comparacion igual", "46"},
            {"!=", "Operador relaciona: diferente que", "46"},
            {"<>", "Operador relacional: diferente que", "46"},
            {"+=", "Asignacion matementica: añade a ", "47"},
            {"-=", "Asiganacion matematica: resta a", "47"},
            {"/=", "Asiganción matemetica: divide a", "47"},
            {"*=", "Asiganción matematica: multiplica a", "47"},
            {"=", "Asignacion: Igual", "95"},
            {"&&", "Operador lógico: Y ", "48"},
            {"||", "Operador lógico: O", "48"},
            {"!", "Operador lógico: No", "49"},
            {",", "Caracter especial: Coma", "50"},
            {".", "Caracter especial: Punto", "51"},
            {";", "Caracter especial: Punto y coma", "52"},
            {":", "Caracter especial: Dos puntos", "53"},
            {"{", "Caracter especial: Llave que abre", "54"},
            {"}", "Caracter especial: Llave que cierra", "55"},
            {"[", "Caracter especial: Corchete que abre", "56"},
            {"]", "Caracter especial: Corchete que cierra", "57"},
            {"(", "Caracter especial: Parentesis que abre", "58"},
            {")", "Caracter especial: Parenctesis que cierra", "59"},
            {"\\", "Caracter especial: Diagonal invertida", "60"},
            {"//", "Comentario de linea", "61"},
            {"/*", "Comentatio de bloque que abre", "62"},
            {"*/", "Comentario de bloque que cierra", "63"},
            {"++", "Asignación matematica: incrementa uno", "64"},
            {"--", "Asignación matematica: decrementa uno", "65"},
            {"constructor", "Constructor", "96"}};
        this.conjuntoTokensFijos = listaTokensFijos;
        return listaTokensFijos;
    }

    /**
     * Set the value of conjuntoTokensFijos
     *
     * @param conjuntoTokensFijos new value of conjuntoTokensFijos
     */
    public void setConjuntoTokensFijos(String[][] conjuntoTokensFijos) {
        this.conjuntoTokensFijos = conjuntoTokensFijos;
    }

    /**
     * Get the value of listaLexico
     *
     * @return the value of listaLexico
     */
    public ArrayList<Lexema> getListaLexico() {
        return listaLexico;
    }

    /**
     * Set the value of listaLexico
     *
     * @param listaLexico new value of listaLexico
     */
    public void setListaLexico(ArrayList<Lexema> listaLexico) {
        this.listaLexico = listaLexico;
    }

    /**
     * Get the value of vecAlfabeto
     *
     * @return the value of vecAlfabeto
     */
    public char[] getVecAlfabeto() {
        return vecAlfabeto;
    }

    /**
     * Set the value of vecAlfabeto
     *
     * @param vecAlfabeto new value of vecAlfabeto
     */
    public void setVecAlfabeto(char[] vecAlfabeto) {
        this.vecAlfabeto = vecAlfabeto;
    }

    /**
     * Get the value of separadores
     *
     * @return the value of separadores
     */
    public String getSeparadores() {
        return separadores;
    }

    /**
     * Set the value of separadores
     *
     * @param separadores new value of separadores
     */
    public void setSeparadores(String separadores) {
        this.separadores = separadores;
    }

    /**
     * Get the value of matrizTransaccionGeneral
     *
     * @return the value of matrizTransaccionGeneral
     */
    public int[][] getMatrizTransaccionGeneral() {
        return matrizTransaccionGeneral;
    }

    /**
     * Set the value of matrizTransaccionGeneral
     *
     * @param matrizTransaccionGeneral new value of matrizTransaccionGeneral
     */
    public void setMatrizTransaccionGeneral(int[][] matrizTransaccionGeneral) {
        this.matrizTransaccionGeneral = matrizTransaccionGeneral;
    }

    /**
     * Get the value of textoCodigo
     *
     * @return the value of textoCodigo
     */
    public String getTextoCodigo() {
        return textoCodigo;
    }

    /**
     * Set the value of textoCodigo
     *
     * @param textoCodigo new value of textoCodigo
     */
    public void setTextoCodigo(String textoCodigo) {
        this.textoCodigo = textoCodigo;
    }

    /**
     * Método que realiza el analisis léxico del código Proceso: Toma el texto
     * completo del código, lo envia a otro método en donde lo separa, se crea
     * un vector en donde se conservan los numeros del renglon, se busca en la
     * tabla de tokens fijos, si la encuentra, la etiqueta, sino, se envia a la
     * busqueda de los tokens de los automatas, con la referencia de la tabla de
     * transacción del automata general, si el automata es correcto entonces lo
     * etiqueta con el nombre del automata sino se le coloca el error, se toman
     * como referencia la tabla de errores despues de que se han recorrido todos
     * los lexemas se recorren una vez más para colocar el numero del renglon.
     *
     * @return una lista de Lexemas, como analisis lexico contienen toda la
     * información para saber si es lexicamente correcto o no
     */
    public ArrayList<Lexema> analisisLexico() {

        ArrayList<String> lexemas = separaCodigo();
        buscarTokensFijos(lexemas);
        for (int i = 0; i < getListaLexico().size(); i++) {
            getListaLexico().get(i).setRenglon(numRenglon[i]);
        }

        return getListaLexico();
    }

    /**
     * Método para separar el código
     *
     * @return
     */
    private ArrayList<String> separaCodigo() {

        ArrayList<String> palabras = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(getTextoCodigo(), "+ -=*&| {}()[]^/%;:,<>\n\t\r!\" ", true);
        String cadena;
        int contRenglon = 1; //incrementa las lineas de codigo
        int contRenglon2 = 1; //incrementa las lineas de codigo
        boolean aux1;
        boolean auxRe;

        while (st.hasMoreElements()) {
            String aux = "";
            aux1 = false;
            auxRe = false;
            cadena = st.nextToken();
            if (cadena.equals(" ") || cadena.equals("\n") || cadena.equals("\t") || cadena.equals("\r")) {

                if (cadena.equals("\n")) {
                    contRenglon++;
                }
            } else {

                if (cadena.equals("+")) {

                    if (st.hasMoreElements()) {
                        aux = st.nextToken();
                        if (aux.equals("=")) {
                            cadena += aux;
                        } else if (aux.equals("+")) {
                            cadena += aux;
                        } else if (aux.equals(" ")) {

                        } else {
                            aux1 = true;
                        }
                    }
                } else if (cadena.equals("-")) {

                    if (st.hasMoreElements()) {
                        aux = st.nextToken();
                        boolean bh = false;
                        try {

                            int aux32 = Integer.parseInt(aux);
                            bh = true;
                        } catch (Exception e) {

                        }
                        if (aux.equals("=")) {
                            cadena += aux;
                        } else if (aux.equals("-")) {
                            cadena += aux;
                        } else if (bh) {
                            cadena += aux;
                        } else if (aux.equals(" ")) {

                        } else {
                            aux1 = true;
                        }
                    }
                } else if (cadena.equals("*")) {

                    if (st.hasMoreElements()) {
                        aux = st.nextToken();
                        if (aux.equals("=")) {
                            cadena += aux;
                        } else if (aux.equals("/")) {
                            cadena += aux;
                        } else if (aux.equals(" ")) {

                        } else {
                            aux1 = true;
                        }
                    }
                } else if (cadena.equals("=")) {

                    if (st.hasMoreElements()) {
                        aux = st.nextToken();
                        if (aux.equals("=")) {
                            cadena += aux;
                        } else if (aux.equals(" ")) {

                        } else {
                            aux1 = true;
                        }
                    }
                } else if (cadena.equals("&")) {
                    if (st.hasMoreElements()) {
                        aux = st.nextToken();
                        if (aux.equals("&")) {
                            cadena += aux;
                        } else if (aux.equals(" ")) {

                        } else {
                            cadena += aux;
                        }

                    }
                } else if (cadena.equals("|")) {

                    if (st.hasMoreElements()) {
                        aux = st.nextToken();
                        if (aux.equals("|")) {
                            cadena += aux;
                        } else if (aux.equals(" ")) {

                        } else {
                            aux1 = true;
                        }
                    }
                } else if (cadena.equals("!")) {

                    if (st.hasMoreElements()) {
                        aux = st.nextToken();
                        if (aux.equals("=")) {
                            cadena += aux;
                        } else if (aux.equals(" ")) {

                        } else {
                            aux1 = true;
                        }
                    }
                } else if (cadena.equals("/")) {

                    if (st.hasMoreElements()) {
                        aux = st.nextToken();
                        if (aux.equals("*")) {
                            auxRe = true;
                            cadena += aux;
                            contRenglon++;
                            contRenglon2 = contRenglon;

                            while (st.hasMoreElements()) {

                                cadena += aux;
                                aux = st.nextToken();
                                if (aux.equals("*")) {

                                    String aux2 = st.nextToken();
                                    aux += aux2;
                                    if (aux2.equals("/")) {

                                        cadena += aux;

                                        break;
                                    } else {
                                        if (aux.equals("\n")) {
                                            contRenglon++;
                                        }
                                    }
                                } else {
                                    if (aux.equals("\n")) {
                                        contRenglon++;
                                    }
                                }
                            }
                        } else if (aux.equals("/")) {

                            while (st.hasMoreElements()) {
                                cadena += aux;
                                aux = st.nextToken();
                                if (aux.equals("\n")) {
                                    break;
                                }
                            }

                        } else if (aux.equals("/=")) {
                            cadena += aux;
                        } else if (aux.equals(" ")) {

                        } else {
                            aux1 = true;
                        }

                    }
                } else if (cadena.equals("<")) {

                    if (st.hasMoreElements()) {
                        aux = st.nextToken();
                        if (aux.equals("=")) {
                            cadena += aux;
                        } else if (aux.equals(">")) {
                            cadena += aux;
                        } else if (aux.equals(" ")) {

                        } else {
                            aux1 = true;
                        }
                    }
                } else if (cadena.equals(">")) {

                    if (st.hasMoreElements()) {
                        aux = st.nextToken();
                        if (aux.equals("=")) {
                            cadena += aux;
                        } else if (aux.equals(" ")) {

                        } else {
                            aux1 = true;
                        }

                    }
                } else if (cadena.equals("\"")) {
                    auxRe = true;
                    contRenglon2 = contRenglon;
                    while (st.hasMoreElements()) {
                        aux = st.nextToken();
                        if (aux.contains("\"")) {
                            cadena += aux;
                            break;
                        } else if (aux.equals("\n")) {
                            cadena += "\n";
                            contRenglon++;
                        } else if(aux.equals(" ")){
                            cadena += " ";
                        }else{
                            cadena += aux;
                        }
                    }

                }
                palabras.add(cadena);

                if (auxRe) {
                    if (aux1) {
                        palabras.add(aux);
                        agregaRenglon(contRenglon2);
                    }
                    agregaRenglon(contRenglon2);

                } else {
                    if (aux1) {
                        palabras.add(aux);
                        agregaRenglon(contRenglon);
                    }
                    agregaRenglon(contRenglon);
                }

            }
        }

        return palabras;
    }

    /**
     * Agregar valores al vector de renglón
     *
     * @param valor es el contador /n
     */
    private void agregaRenglon(int valor) {
        if (numRenglon == null) {
            numRenglon = new int[1];
            numRenglon[0] = valor;
        } else {
            int nvoRenglon[] = new int[numRenglon.length + 1];

            System.arraycopy(numRenglon, 0, nvoRenglon, 0, numRenglon.length);
            nvoRenglon[numRenglon.length] = valor;
            numRenglon = null;
            numRenglon = nvoRenglon;
        }

    }

    /**
     * Busca en tokens fijos la palabra y la prepara la busqueda para los
     * automatas
     *
     * @param lstPalabras
     */
    private void buscarTokensFijos(ArrayList<String> lstPalabras) {

        for (int i = 0; i < lstPalabras.size(); i++) {

            Lexema cadenaAnalizada = new Lexema();

            int token = 0;
            boolean automata = true;
            while (token < getConjuntoTokensFijos().length) {

                if (lstPalabras.get(i).equals(getConjuntoTokensFijos()[token][0])) {

                    cadenaAnalizada.setLexema(getConjuntoTokensFijos()[token][0]);
                    cadenaAnalizada.setNombreToken(getConjuntoTokensFijos()[token][1]);
                    cadenaAnalizada.setNumToken(Integer.parseInt(getConjuntoTokensFijos()[token][2]));

                    automata = false;
                    break;

                }
                token++;
            }
            if (automata) {

                char[] automataInverion = lstPalabras.get(i).toCharArray();
                String palabraConvertida = "";
                for (int j = 0; j < automataInverion.length; j++) {

                    if (Character.isLetterOrDigit(automataInverion[j])) {
                        if (Character.isLetter(automataInverion[j])) {
                            if (Character.isUpperCase(automataInverion[j])) {

                                palabraConvertida += "A";
                            }
                            if (Character.isLowerCase(automataInverion[j])) {
                                palabraConvertida += "L";
                            }
                        } else {
                            if (Character.isDigit(automataInverion[j])) {
                                palabraConvertida += "D";
                            }
                        }
                    } else if (automataInverion[j] == '"') {
                        palabraConvertida += "\"";
                    } else if (automataInverion[j] == '$') {
                        palabraConvertida += "$";
                    } else if (automataInverion[j] == '&') {
                        palabraConvertida += "&";
                    } else if (automataInverion[j] == '_') {
                        palabraConvertida += "_";
                    } else if (automataInverion[j] == '~') {
                        palabraConvertida += "~";
                    } else if (automataInverion[j] == '/') {
                        palabraConvertida += "/";
                    } else if (automataInverion[j] == '@') {
                        palabraConvertida += "@";
                    } else if (automataInverion[j] == '-') {
                        palabraConvertida += "-";
                    } else if (automataInverion[j] == '#') {
                        palabraConvertida += "#";
                    } else if (automataInverion[j] == '\'') {
                        palabraConvertida += "'";
                    } else if (automataInverion[j] == '*') {
                        palabraConvertida += "*";
                    } else if (automataInverion[j] == '(') {
                        palabraConvertida += "(";
                    } else if (automataInverion[j] == ')') {
                        palabraConvertida += ")";
                    } else if (automataInverion[j] == '.') {
                        palabraConvertida += ".";
                    } else {
                        palabraConvertida += "C";
                    }

                }

                cadenaAnalizada = buscaAutomata(palabraConvertida, lstPalabras.get(i));
            }

            getListaLexico().add(cadenaAnalizada);
        }
    }

    private Lexema buscaAutomata(String palabra, String lexema) {

        Lexema cadenaAnalizada = new Lexema();
        cadenaAnalizada.setLexema(lexema);

        Automata a = new Automata();
        a.setMatrizTransicion(getMatrizTransaccionGeneral());
        a.setVectorAlfabeto(getVecAlfabeto());
        a.setCadena(palabra);
        a.analisisAutomata();

        if (a.getEstado() == -4) {
            cadenaAnalizada.setNumToken(78);
            cadenaAnalizada.setNombreToken("Caracter desconocido");
        } else {
            if (a.getTransaccionFinal() == 1 || a.getTransaccionFinal() == 2) {
                if (a.getResAutomata().equals("Cadena valida")) {
                    cadenaAnalizada.setNumToken(75);
                    cadenaAnalizada.setNombreToken("Cadena");
                } else {
                    cadenaAnalizada.setNumToken(90);
                    cadenaAnalizada.setNombreToken("Cadena no valida");
                }
            } else if (a.getTransaccionFinal() == 3 || a.getTransaccionFinal() == 4 || a.getTransaccionFinal() == 5) {
                if (a.getResAutomata().equals("Cadena valida")) {
                    cadenaAnalizada.setNumToken(66);
                    cadenaAnalizada.setNombreToken("Nombre de proyecto");
                } else {
                    cadenaAnalizada.setNumToken(81);
                    cadenaAnalizada.setNombreToken("Nombre de proyecto no valido");
                }
            } else if (a.getTransaccionFinal() == 6 || a.getTransaccionFinal() == 7 || a.getTransaccionFinal() == 8) {
                if (a.getResAutomata().equals("Cadena valida")) {
                    cadenaAnalizada.setNumToken(67);
                    cadenaAnalizada.setNombreToken("Nombre de paquete");
                } else {
                    cadenaAnalizada.setNumToken(83);
                    cadenaAnalizada.setNombreToken("Nombre de paquete no valido");
                }
            } else if (a.getTransaccionFinal() == 9 || a.getTransaccionFinal() == 10 || a.getTransaccionFinal() == 11) {
                if (a.getResAutomata().equals("Cadena valida")) {
                    cadenaAnalizada.setNumToken(68);
                    cadenaAnalizada.setNombreToken("Nombre de clase");
                } else {
                    cadenaAnalizada.setNumToken(84);
                    cadenaAnalizada.setNombreToken("Nombre de clase no valido");
                }
            } else if (a.getTransaccionFinal() == 12 || a.getTransaccionFinal() == 13) {
                if (a.getResAutomata().equals("Cadena valida")) {
                    cadenaAnalizada.setNumToken(69);
                    cadenaAnalizada.setNombreToken("Nombre de funcion o procedimiento");
                } else {
                    cadenaAnalizada.setNumToken(87);
                    cadenaAnalizada.setNombreToken("Nombre de funcion o procedimiento no valido");
                }
            } else if (a.getTransaccionFinal() == 14 || a.getTransaccionFinal() == 15) {
                if (a.getResAutomata().equals("Cadena valida")) {
                    cadenaAnalizada.setNumToken(70);
                    cadenaAnalizada.setNombreToken("Nombre de variable");
                } else {
                    cadenaAnalizada.setNumToken(86);
                    cadenaAnalizada.setNombreToken("Nombre de variable no valida");
                }
            } else if (a.getTransaccionFinal() == 16 || a.getTransaccionFinal() == 17) {
                if (a.getResAutomata().equals("Cadena valida")) {
                    cadenaAnalizada.setNumToken(71);
                    cadenaAnalizada.setNombreToken("Nombre de constante");
                } else {
                    cadenaAnalizada.setNumToken(87);
                    cadenaAnalizada.setNombreToken("Nombre de constante no valida");
                }
            } else if (a.getTransaccionFinal() == 18 || a.getTransaccionFinal() == 19) {
                if (a.getResAutomata().equals("Cadena valida")) {
                    cadenaAnalizada.setNumToken(72);
                    cadenaAnalizada.setNombreToken("Nombre de variable de objeto");
                } else {
                    cadenaAnalizada.setNumToken(88);
                    cadenaAnalizada.setNombreToken("Nombre de variable de objeto no valida");
                }
            } else if (a.getTransaccionFinal() == 20 || a.getTransaccionFinal() == 23) {
                if (a.getResAutomata().equals("Cadena valida")) {
                    cadenaAnalizada.setNumToken(73);
                    cadenaAnalizada.setNombreToken("Numero entero o byte");
                } else {
                    cadenaAnalizada.setNumToken(89);
                    cadenaAnalizada.setNombreToken("Numero entero o byte incorrectos");
                }
            } else if (a.getTransaccionFinal() == 21 || a.getTransaccionFinal() == 22) {

                if (a.getResAutomata().equals("Cadena valida")) {
                    cadenaAnalizada.setNumToken(74);
                    cadenaAnalizada.setNombreToken("Numero flotante o doble o largo o corto");
                } else {
                    cadenaAnalizada.setNumToken(90);
                    cadenaAnalizada.setNombreToken("Numero de flotante o doble o largo o corto incorrectos");
                }
            } else if (a.getTransaccionFinal() == 25 || a.getTransaccionFinal() == 24) {
                if (a.getResAutomata().equals("Cadena valida")) {
                    cadenaAnalizada.setNumToken(77);
                    cadenaAnalizada.setNombreToken("Nombre de libreria");
                } else {
                    cadenaAnalizada.setNumToken(92);
                    cadenaAnalizada.setNombreToken("Nombre de libreria no valida");
                }
            } else if (a.getTransaccionFinal() == 26 || a.getTransaccionFinal() == 27 || a.getTransaccionFinal() == 28) {
                if (a.getResAutomata().equals("Cadena valida")) {
                    cadenaAnalizada.setNumToken(76);
                    cadenaAnalizada.setNombreToken("Caracter");
                } else {
                    cadenaAnalizada.setNumToken(91);
                    cadenaAnalizada.setNombreToken("Caracter no valido");
                }
            } else if (a.getTransaccionFinal() == 29 || a.getTransaccionFinal() == 30) {
                if (a.getResAutomata().equals("Cadena valida")) {
                    cadenaAnalizada.setNumToken(78);
                    cadenaAnalizada.setNombreToken("Comentario de linea");
                } else {
                    cadenaAnalizada.setNumToken(93);
                    cadenaAnalizada.setNombreToken("Comentario de linea no valido");
                }

            } else if (a.getTransaccionFinal() == 31 || a.getTransaccionFinal() == 32 || a.getTransaccionFinal() == 33) {
                if (a.getResAutomata().equals("Cadena valida")) {
                    cadenaAnalizada.setNumToken(79);
                    cadenaAnalizada.setNombreToken("Comentario de bloque");
                } else {
                    cadenaAnalizada.setNumToken(94);
                    cadenaAnalizada.setNombreToken("Comentario de bloque no valido");
                }

            } else {
                cadenaAnalizada.setNumToken(80);
                cadenaAnalizada.setNombreToken("Caracter desconocido");
            }

        }

        return cadenaAnalizada;
    }

    /**
     * Método que extrae la tabla de Excel en donde se encuentra la tabla de
     * transaccion y la convierte en la matriz de transaccion y obtiene el
     * vector del alfabeto
     */
    private void extraerTabla() {

        int tablaTransiccion[][];
        char vectorAlfabeto[];

        ArrayList<Integer> datos = new ArrayList<>();  //Matriz de transaccion
        ArrayList<Character> alfabeto = new ArrayList<>(); //Vector alfabeto
        int numF, numC = 0, conArray = 0;  //Matriz de transaccion
        int conVector = 0;             //Array del Vector alfabeto

        try {
            FileInputStream file = new FileInputStream(new File(getPathTablaExcel()));
            XSSFWorkbook book = new XSSFWorkbook(file);
            XSSFSheet sheet = book.getSheetAt(0);

            int numFilas = sheet.getLastRowNum();
            numF = numFilas;
            //Fila 1 Alfabeto
            for (int i = 0; i < numFilas + 1; i++) {
                Row fial = sheet.getRow(i);
                int numCOLS = fial.getLastCellNum();
                numC = numCOLS - 1;
                for (int j = 1; j < numCOLS; j++) {
                    Cell celda = fial.getCell(j);
                    if (i == 0) {
                        if (celda.getCellType().toString().equals("STRING")) {

                            char valorCelda = celda.getStringCellValue().charAt(0);
                            alfabeto.add(valorCelda);
                        }
                    } else {
                        if (celda.getCellType().toString().equals("NUMERIC")) {
                            datos.add((int) (celda.getNumericCellValue()));
                        } else {
                            datos.add(-1);
                        }
                    }
                }
            }
            vectorAlfabeto = new char[numC];
            for (int i = 0; i < numC; i++) {
                vectorAlfabeto[i] = alfabeto.get(conVector);
                conVector++;
            }

            tablaTransiccion = new int[numF][numC];
            for (int i = 0; i < numF; i++) {

                for (int j = 0; j < numC; j++) {
                    tablaTransiccion[i][j] = datos.get(conArray);
                    conArray++;
                }
            }
            setVecAlfabeto(vectorAlfabeto);
            setMatrizTransaccionGeneral(tablaTransiccion);
        } catch (IOException e) {
            System.out.println("" + e);
        }

    }

}
