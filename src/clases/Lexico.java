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
            {"Caracter desconocido", "84"},
            {"Nombre de proyecto no valido", "85"},
            {"Nombre de paquete no valido", "86"},
            {"Nombre de clase no valido", "87"},
            {"Nombre de funcion o procedimiento no valido", "88"},
            {"Nombre de variable no valida", "89"},
            {"Nombre de constante no valida", "90"},
            {"Nombre de variable de objeto no valida", "91"},
            {"Numero entero o byte incorrectos", "92"},
            {"Numero de flotante o doble o largo o corto incorrectos", "93"},
            {"Cadena no valida", "94"},
            {"Caracter no valido", "95"},
            {"Nombre de libreria no valida", "96"},
            {"Comentario de linea no valida", "97"},
            {"Comentario de bloque no valida", "98"},};

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
            {"Nombre de proyecto", "70"},
            {"Nombre de paquete", "71"},
            {"Nombre de clase", "72"},
            {"Nombre de funcion o procedimiento", "73"},
            {"Nombre de variable", "74"},
            {"Nombre de constante", "75"},
            {"Nombre de variable de objeto", "76"},
            {"Numero entero o byte", "77"},
            {"Numero flotante o doble o largo o corto", "78"},
            {"Cadena", "79"},
            {"Caracter", "80"},
            {"Nombre de libreria", "81"},
            {"Comentario de linea", "82"},
            {"Comentario de bloque", "83"}
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
            {"publico", "Modificador de acceso: publico", "1", "public"},
            {"privado", "Modificador de acceso: privado", "2", "private"},
            {"protegido", "Modificador de acceso: protegido", "3", "proteted"},
            {"clase", "Palabra reservada: clase", "4", "class"},
            {"paquete", "Palabra reservada: paquete", "5", "package"},
            {"importar", "Palabra reservada: importar", "6", "import"},
            {"estatico", "Palabra reservada: estatico", "7", "static"},
            {"final", "Palabra reservada: final", "8", "final"},
            {"super", "Palabra reservada: super", "9", "super"},
            {"retorna", "Palabra reservada: retorno", "10", "return"},
            {"procedimiento", "Palabra reservada: procedimiento", "11", "procedure"},
            {"funcion", "Palabra reservada: funcion", "12", "funtion"},
            {"entrada", "Palabra reservada: entrada", "13", "in"},
            {"salida", "Palabra reservada: salida", "14", "out"},
            {"sistema", "Palabra reservada: sistema", "15", "system"},
            {"linea", "Palabra reservada: linea", "16", "line"},
            {"abstracta", "Palabra reservada: abstracta", "17", "abstracta"},
            {"extender", "Palabra reservada: extender", "18", "extends"},
            {"implementar", "Palabra reservada: implementar", "19", "implements"},
            {"interfaz", "Palabra reservada: interfaz", "20", "interface"},
            {"principal", "Palabra reservada: principal", "21", "main"},
            {"nuevo", "Palabra reservada: nuevo", "22", "new"},
            {"este", "Palabra reservada: este", "23", "this"},
            {"nulo", "Palabra reservada: nulo", "24", "null"},
            {"leer", "Palabra reservada: leer", "25", "read"},
            {"escribir", "Palabra reservada: imprimir", "26", "write"},
            {"defecto", "Palabra reservada: defecto", "27", "default"},
            {"terminar", "Palabra reservada: terminar", "28", "break"},
            {"salir", "Palabra reservada: salir", "29", "exit"},
            {"caso", "Palabra reservada: caso", "30", "switch"},
            {"alternativa", "Palabra reservada: alternativa", "31", "case"},
            {"si", "Estructura de seleccion: si", "32", "if"},
            {"sino", "Estructura de seleccion: sino", "33", "else"},
            {"sinoSi", "Estructura de seleccion: sinoSi", "34", "elseIf"},
            {"mientras", "Cliclo: mientras", "35", "while"},
            {"hacer", "Ciclo: harcerMientras", "36", "do"},
            {"para", "Ciclo: para", "37", "for"},
            {"verdadero", "Palabra reservada: verdadero", "38", "true"},
            {"falso", "Palabra reservada: falso", "39", "false"},
            {"constructor", "Constructor", "40", "constructor"},
            {"intentar", "Intentar", "41", "try"},
            {"captar", "Captar", "42", "catch"},
            {"finalmente", "Finalmente", "43", "finally"},
            {"entero", "Tipo de dato: entero", "44", "int"},
            {"flotante", "Tipo de dato: flotante", "44", "float"},
            {"doble", "Tipo de dato: double", "44", "double"},
            {"cadena", "Tipo de dato: cadena", "44", "string"},
            {"largo", "Tipo de dato: largo", "44", "long"},
            {"objeto", "Tipo de dato: objeto", "44", "object"},
            {"caracter", "Tipo de dato: caracter", "45", "char"},
            {"corto", "Tipo de dato: corto", "45", "short"},
            {"byte", "Tipo de dato: byte", "45", "byte"},
            {"booleano", "Tipo de dato: booleano", "46", "boolean"},
              {"+", "Operador aritmetico suma", "52", "+"},
            {"-", "Operador aritmetico resta", "48", "-"},
            {"*", "Operador aritmetico producto", "49", "*"},
            {"/", "Operador artimetico cociente", "49", "/"},
            {"%", "Operador artimetico residuo de cociente", "49", "%"},
            {"^", "Operador aritmetico potencia", "49", "^"},
            {"<", "Opererador relacional: menor que", "50", "<"},
            {">", "Operador relacional: mayor que", "50", ">"},
            {">=", "Operador relacional: mayor igual que", "50", ">="},
            {"<=", "Operador relacional: menor igual que", "50", "<="},
            {"==", "Operador relacional: comparacion igual", "51", "=="},
            {"!=", "Operador relaciona: diferente que", "51", "!="},
            {"+=", "Asignacion matementica: añade a ", "53", "+="},
            {"-=", "Asiganacion matematica: resta a", "53", "-="},
            {"/=", "Asiganción matemetica: divide a", "53", "/="},
            {"*=", "Asiganción matematica: multiplica a", "53", "*="},
            {"=", "Asignacion: Igual", "54", "="},
            {"&&", "Operador lógico: Y ", "55", "&&"},
            {"||", "Operador lógico: O", "55", "||"},
            {"!", "Operador lógico: No", "56", "!"},
            {",", "Caracter especial: Coma", "57", ","},
            {";", "Caracter especial: Punto y coma", "58", ";"},
            {".", "Caracter especial: Punto", "59", "."},
            {":", "Caracter especial: Dos puntos", "60", ":"},
            {"{", "Caracter especial: Llave que abre", "61", "{"},
            {"}", "Caracter especial: Llave que cierra", "62", "}"},
            {"[", "Caracter especial: Corchete que abre", "63", "["},
            {"]", "Caracter especial: Corchete que cierra", "64", "]"},
            {"(", "Caracter especial: Parentesis que abre", "65", "("},
            {")", "Caracter especial: Parenctesis que cierra", "66", ")"},
            {"\\", "Caracter especial: Diagonal invertida", "67", "\\"},
            {"++", "Asignación matematica: incrementa uno", "68", "++"},
            {"--", "Asignación matematica: decrementa uno", "69", "--"}};
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
        //Corrgige el punto decimal si es un numero
        for (int i = 0; i < getListaLexico().size(); i++) {

            if (getListaLexico().get(i).getNumToken() == 77) {
                if (i + 1 < getListaLexico().size()) {
                    if ((getListaLexico().get(i + 1).getNumToken() == 59) && (getListaLexico().get(i).getRenglon() == getListaLexico().get(i + 1).getRenglon())) {
                        if (i + 2 < getListaLexico().size()) {
                            if ((getListaLexico().get(i + 2).getNumToken() == 77)
                                    && (getListaLexico().get(i).getRenglon() == getListaLexico().get(i + 2).getRenglon())) {
                                Lexema num2 = getListaLexico().remove(i + 2);
                                Lexema pun = getListaLexico().remove(i + 1);

                                String s = getListaLexico().get(i).getLexema() + pun.getLexema() + num2.getLexema();
                                getListaLexico().get(i).setLexema(s);
                                getListaLexico().get(i).setNumToken(78);
                                getListaLexico().get(i).setNombreToken("Numero flotante o doble o largo o corto");
                            }
                        }
                    }
                }

            }
        }
        //Quita los comentarios para el sintactico y agrega los identificadores a al tabla de tokens
        return getListaLexico();
    }

    /**
     * Método para separar el código
     *
     * @return
     */
    private ArrayList<String> separaCodigo() {

        ArrayList<String> palabras = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(getTextoCodigo(), "+ -=*&| {}().[]^/%;:,<>\n\t\r!\" ", true);
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
                        if (aux.equals("+")) {
                            cadena += aux;
                        } else if (aux.equals("=")) {
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
                        auxRe = true;
                        contRenglon2 = contRenglon;
                        aux = st.nextToken();

                        if (aux.equals("*")) {
                            cadena += aux;
                            aux = "";
                            while (st.hasMoreElements()) {
                                aux = st.nextToken();
                                if (aux.equals("*")) {

                                    cadena += aux;
                                    aux = "";
                                    if (st.hasMoreElements()) {
                                        aux = st.nextToken();
                                        if (aux.equals("/")) {
                                            cadena += aux;
                                            aux = "";
                                            break;
                                        } else {
                                            if (aux.equals("\n")) {
                                                contRenglon++;
                                            }
                                        }

                                    } else {
                                        break;
                                    }

                                } else if (aux.equals("\n")) {
                                    contRenglon++;
                                    cadena += aux;
                                }
                            }
                        } else if (aux.equals("/")) {
                            cadena += aux;
                            aux = "";
                            while (st.hasMoreElements()) {
                                aux = st.nextToken();
                                if (aux.equals("\n")) {
                                    contRenglon++;
                                    break;
                                } else {
                                    cadena += aux;
                                }
                            }

                        } else if (aux.equals("=")) {
                            auxRe = false;
                            cadena += aux;
                        } else if (aux.equals(" ")) {
                            auxRe = false;
                        } else if (aux.equals("\n")) {
                            contRenglon++;
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
                        } else if (aux.equals(" ")) {
                            cadena += " ";
                        } else {
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

                if (lstPalabras.get(i).equals(getConjuntoTokensFijos()[token][0]) || lstPalabras.get(i).equals(getConjuntoTokensFijos()[token][3])) {

                    cadenaAnalizada.setLexema(lstPalabras.get(i));
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
            cadenaAnalizada.setNumToken(84);
            cadenaAnalizada.setNombreToken("Caracter desconocido");
        } else {
            switch (a.getTransaccionFinal()) {
                case 1:
                case 2:
                    if (a.getResAutomata().equals("Cadena valida")) {
                        cadenaAnalizada.setNumToken(79);
                        cadenaAnalizada.setNombreToken("Cadena");
                    } else {
                        cadenaAnalizada.setNumToken(94);
                        cadenaAnalizada.setNombreToken("Cadena no valida");
                    }
                    break;
                case 3:
                case 4:
                case 5:
                    if (a.getResAutomata().equals("Cadena valida")) {
                        cadenaAnalizada.setNumToken(70);
                        cadenaAnalizada.setNombreToken("Nombre de proyecto");
                    } else {
                        cadenaAnalizada.setNumToken(85);
                        cadenaAnalizada.setNombreToken("Nombre de proyecto no valido");
                    }
                    break;
                case 6:
                case 7:
                case 8:
                    if (a.getResAutomata().equals("Cadena valida")) {
                        cadenaAnalizada.setNumToken(71);
                        cadenaAnalizada.setNombreToken("Nombre de paquete");
                    } else {
                        cadenaAnalizada.setNumToken(86);
                        cadenaAnalizada.setNombreToken("Nombre de paquete no valido");
                    }
                    break;
                case 9:
                case 10:
                case 11:
                    if (a.getResAutomata().equals("Cadena valida")) {
                        cadenaAnalizada.setNumToken(72);
                        cadenaAnalizada.setNombreToken("Nombre de clase");
                    } else {
                        cadenaAnalizada.setNumToken(87);
                        cadenaAnalizada.setNombreToken("Nombre de clase no valido");
                    }
                    break;
                case 12:
                case 13:
                    if (a.getResAutomata().equals("Cadena valida")) {
                        cadenaAnalizada.setNumToken(73);
                        cadenaAnalizada.setNombreToken("Nombre de funcion o procedimiento");
                    } else {
                        cadenaAnalizada.setNumToken(88);
                        cadenaAnalizada.setNombreToken("Nombre de funcion o procedimiento no valido");
                    }
                    break;
                case 14:
                case 15:
                    if (a.getResAutomata().equals("Cadena valida")) {
                        cadenaAnalizada.setNumToken(74);
                        cadenaAnalizada.setNombreToken("Nombre de variable");
                    } else {
                        cadenaAnalizada.setNumToken(89);
                        cadenaAnalizada.setNombreToken("Nombre de variable no valida");
                    }
                    break;
                case 16:
                case 17:
                    if (a.getResAutomata().equals("Cadena valida")) {
                        cadenaAnalizada.setNumToken(75);
                        cadenaAnalizada.setNombreToken("Nombre de constante");
                    } else {
                        cadenaAnalizada.setNumToken(90);
                        cadenaAnalizada.setNombreToken("Nombre de constante no valida");
                    }
                    break;
                case 18:
                case 19:
                    if (a.getResAutomata().equals("Cadena valida")) {
                        cadenaAnalizada.setNumToken(76);
                        cadenaAnalizada.setNombreToken("Nombre de variable de objeto");
                    } else {
                        cadenaAnalizada.setNumToken(91);
                        cadenaAnalizada.setNombreToken("Nombre de variable de objeto no valida");
                    }
                    break;
                case 20:
                case 23:
                    if (a.getResAutomata().equals("Cadena valida")) {
                        cadenaAnalizada.setNumToken(77);
                        cadenaAnalizada.setNombreToken("Numero entero o byte");
                    } else {
                        cadenaAnalizada.setNumToken(92);
                        cadenaAnalizada.setNombreToken("Numero entero o byte incorrectos");
                    }
                    break;
                case 21:
                case 22:
                    if (a.getResAutomata().equals("Cadena valida")) {
                        cadenaAnalizada.setNumToken(78);
                        cadenaAnalizada.setNombreToken("Numero flotante o doble o largo o corto");
                    } else {
                        cadenaAnalizada.setNumToken(93);
                        cadenaAnalizada.setNombreToken("Numero de flotante o doble o largo o corto incorrectos");
                    }
                    break;
                case 25:
                case 24:
                    if (a.getResAutomata().equals("Cadena valida")) {
                        cadenaAnalizada.setNumToken(81);
                        cadenaAnalizada.setNombreToken("Nombre de libreria");
                    } else {
                        cadenaAnalizada.setNumToken(96);
                        cadenaAnalizada.setNombreToken("Nombre de libreria no valida");
                    }
                    break;
                case 26:
                case 27:
                case 28:
                    if (a.getResAutomata().equals("Cadena valida")) {
                        cadenaAnalizada.setNumToken(80);
                        cadenaAnalizada.setNombreToken("Caracter");
                    } else {
                        cadenaAnalizada.setNumToken(95);
                        cadenaAnalizada.setNombreToken("Caracter no valido");
                    }
                    break;
                case 29:
                case 30:
                    if (a.getResAutomata().equals("Cadena valida")) {
                        cadenaAnalizada.setNumToken(82);
                        cadenaAnalizada.setNombreToken("Comentario de linea");
                    } else {
                        cadenaAnalizada.setNumToken(96);
                        cadenaAnalizada.setNombreToken("Comentario de linea no valido");
                    }
                    break;
                case 31:
                case 32:
                case 33:
                    if (a.getResAutomata().equals("Cadena valida")) {
                        cadenaAnalizada.setNumToken(83);
                        cadenaAnalizada.setNombreToken("Comentario de bloque");
                    } else {
                        cadenaAnalizada.setNumToken(97);
                        cadenaAnalizada.setNombreToken("Comentario de bloque no valido");
                    }
                    break;
                default:
                    cadenaAnalizada.setNumToken(84);
                    cadenaAnalizada.setNombreToken("Caracter desconocido");
                    break;
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
