/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 * Clase para llevar a cabo validaciones simples en cajas de texto en un
 * ambiente de pantallas de java
 *
 * @author kevin2
 */
public class Validaciones {

    /**
     * Método que se coloca en el evento KeyType de una caja de texto con el fin
     * de que al precionar un caracter del teclado se verifique si es un número
     * entero, de no ser así lo elimina
     *
     * @param ke Objeto que refiere a la tecla atrapada por el KeyType de una
     * caja de texto de java
     */
    public static void validaEntero(KeyEvent ke) {
        if ((ke.getKeyChar() < '0' || ke.getKeyChar() > '9')
                && ke.getKeyCode() != 8) {
            ke.setKeyChar((char) 8);
        }
    }

    /**
     * Método que se coloca en el evento KeyType de una caja de texto con el fin
     * de que al precionar un caracter del teclado se verifique si es un número
     * con uso de punto flotante, de no ser así lo elimina
     *
     * @param ke Objeto que refiere a la tecla atrapada por el KeyType de una
     * caja de texto de java
     */
    public static void validaFlotantes(KeyEvent ke) {
        if ((ke.getKeyChar() < '0' || ke.getKeyChar() > '9')
                && ke.getKeyCode() != 8 && ke.getKeyChar() != '.') {
            ke.setKeyChar((char) 8);
        }
    }

    /**
     * Método que se coloca en el evento KeyType de una caja de texto con el fin
     * de que al precionar un caracter del teclado se verifique si es un
     * caracter alfabético, de no ser así lo elimina
     *
     * @param ke Objeto que refiere a la tecla atrapada por el KeyType de una
     * caja de texto de java
     */
    public static void validaAlfabeticos(KeyEvent ke) {
        if ((ke.getKeyChar() < 'a' || ke.getKeyChar() > 'z')
                && (ke.getKeyChar() < 'A' || ke.getKeyChar() > 'Z')
                && ke.getKeyCode() != 8 && ke.getKeyChar() != '.'
                && ke.getKeyChar() != ' ' && ke.getKeyChar() != 'ñ'
                && ke.getKeyChar() != 'Ñ' && ke.getKeyChar() != 'á'
                && ke.getKeyChar() != 'Á' && ke.getKeyChar() != 'é'
                && ke.getKeyChar() != 'É' && ke.getKeyChar() != 'í'
                && ke.getKeyChar() != 'Í' && ke.getKeyChar() != 'ó'
                && ke.getKeyChar() != 'Ó' && ke.getKeyChar() != 'ú'
                && ke.getKeyChar() != 'Ú') {
            ke.setKeyChar((char) 8);
        }
    }

    /**
     * Método que se coloca en el evento KeyType de una caja de texto con el fin
     * de que al precionar un caracter del teclado se verifique si es un
     * alfanumérico, de no ser así lo elimina
     *
     * @param ke Objeto que refiere a la tecla atrapada por el KeyType de una
     * caja de texto de java
     */
    public static void validaAlfanumerico(KeyEvent ke) {
        if ((ke.getKeyChar() < 'a' || ke.getKeyChar() > 'z')
                && (ke.getKeyChar() < 'A' || ke.getKeyChar() > 'Z')
                && (ke.getKeyChar() < '0' || ke.getKeyChar() > '9')
                && ke.getKeyCode() != 8 && ke.getKeyChar() != '.'
                && ke.getKeyChar() != ' ' && ke.getKeyChar() != 'ñ'
                && ke.getKeyChar() != 'Ñ' && ke.getKeyChar() != 'á'
                && ke.getKeyChar() != 'Á' && ke.getKeyChar() != 'é'
                && ke.getKeyChar() != 'É' && ke.getKeyChar() != 'í'
                && ke.getKeyChar() != 'Í' && ke.getKeyChar() != 'ó'
                && ke.getKeyChar() != 'Ó' && ke.getKeyChar() != 'ú'
                && ke.getKeyChar() != 'Ú') {
            ke.setKeyChar((char) 8);
        }
    }

    /**
     * Método que se coloca en el evento KeyPress de una caja de texto con el
     * fin de verificar si el contenido de la misma es entero
     *
     * @param jt Caja de texto a evaluar
     * @return verdadero si el contenido de la caja es un entero y false si el
     * contenido de la misma no es un entero
     */
    public static boolean verificaEntero(JTextField jt) {
        try {
            int x = Integer.parseInt(jt.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Método que se coloca en el evento KeyPress de una caja de texto con el
     * fin de verificar si el contenido de la misma es un número con punto
     * flotante
     *
     * @param jt Caja de texto a evaluar
     * @return verdadero si el contenido de la caja es un un número con punto
     * flotante y false si el contenido de la misma no lo es
     */
    public static boolean verificaDoble(JTextField jt) {
        try {
            double x = Double.parseDouble(jt.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Método que se coloca en el evento KeyPress de una caja de texto con el
     * fin de validar si la tecla presioanada es ENTER siempre y cuando el
     * contenido de la caja de texto sea una cadena y cambia a otro objeto
     *
     * @param jf Nombre del frame donde se esta haciendo la acción
     * @param ke Variable evt del método KeyPress
     * @param obj Objeto al que se desea pasar al momento de dar enter
     */
    public static void enter(JFrame jf, KeyEvent ke, Object obj) {
        if (ke.getKeyChar() == '\n') {
            CtrlInterfaz.cambia(obj);
        }
    }

    /**
     * Método que se coloca en el evento KeyPress de una caja de texto con el
     * fin de validar si la tecla presioanada es ENTER siempre y cuando el
     * contenido de la caja de texto sea un entero y cambia a otro objeto
     *
     * @param jf Nombre del frame donde se esta haciendo la acción
     * @param ke Variable evt del método KeyPress
     * @param jt Caja de texto donde se verifica que exista un entero
     * @param obj Objeto al que se desea pasar al momento de dar enter
     */
    public static void enterEntero(JFrame jf, KeyEvent ke,
            JTextField jt, Object obj) {
        if (ke.getKeyChar() == '\n') {
            if (verificaEntero(jt)) {
                CtrlInterfaz.cambia(obj);
            } else {
                Mensaje.error(jf, "Se esperaba un entero");
                CtrlInterfaz.selecciona(jt);
            }

        }
    }

    public static void enterFlotante(JFrame jf, KeyEvent ke,
            JTextField jt, Object obj) {
        if (ke.getKeyChar() == '\n') {
            if (verificaDoble(jt)) {
                CtrlInterfaz.cambia(obj);
            } else {
                Mensaje.error(jf, "Se esperaba un flotante");
                CtrlInterfaz.selecciona(jt);
            }

        }
    }

    /**
     * Método que se coloca en el evento KeyPress de una caja de texto con el
     * fin de validar si la tecla presioanada es ENTER siempre y cuando el
     * contenido de la caja de texto sea una cadena y cambia el color de la
     * cadena desde el ultimo espacio hasta el final de la cadena
     *
     *
     * @param txtP TextPane que se verificará el texto
     */
    public static void enterColor(JTextPane txtP) {

        verificaPalabra(txtP);

    }

    public boolean caracteres(char c) {

        int cCode = (int) c;
        return c == '!' || c == '"' || c == '#' || c == '$' || c == '%' || c == '&'
                || c == '/' || c == '(' || c == ')' || c == '=' || c == '?' || c == '¡'
                || c == '|' || c == '¿' || c == '*' || c == '+' || c == '}' || c == '{'
                || c == '[' || c == ']' || c == '^' || c == '-' || c == '.' || c == ','
                || c == ';' || c == ':' || c == '_' || c == '¬' || c == '~' || c == '>'
                || c == '#' || c == '<' || c == ' ' || c == '@' || cCode == 8 || cCode == 39;
    }

    public boolean letras(char c) {

        return c >= 'a' || c <= 'z' || c >= 'A' || c <= 'Z';
    }

    public boolean numeros(char c) {

        return c >= '0' || c <= '9';
    }

    public static void verificaPalabra(JTextPane txtP) {

        ArrayList<String> listPalabras;

        Color colorPalabra;
        Color cAzul = new Color(0, 0, 204);
        Color cVerde = new Color(0, 204, 0);
        Color cRojo = new Color(255, 0, 0);
        Color cNaranja = new Color(255, 102, 0);
        Color cRosa = new Color(255, 0, 255);
        Color cAmarillo = new Color(255, 204, 0);
        Color cCafe = new Color(102, 0, 51);
        Color cNegro = new Color(0, 0, 0);
        Color cGris = new Color(102, 102, 102);
        Color cMorado = new Color(153, 0, 204);

        String s = txtP.getText();
        if (s.length() != 0) {

            /*listPalabras = new ArrayList<>();

            
            char[] palabra = s.toCharArray();
            String nuevaCadena = "";

            for (int i = 0; i < palabra.length; i++) {
                nuevaCadena += palabra[i];
                if (palabra[i] == ' ' || palabra[i] == '\n' || palabra[i] == '\t' || palabra[i] == '\r' || palabra[i] == ';'
                        || palabra[i] == '(' || palabra[i] == ')'
                        || palabra[i] == '{' || palabra[i] == '}' || palabra[i] == '='
                        || palabra[i] == '>' || palabra[i] == '<' || palabra[i] == '+'
                        || palabra[i] == '-' || palabra[i] == '*'
                        || palabra[i] == '!') {
                    listPalabras.add(nuevaCadena);
                    nuevaCadena = "";
                }
                if ((i + 1) != palabra.length) {
                    if (palabra[i + 1] == ' ' || palabra[i + 1] == '\n' || palabra[i + 1] == '\t'
                            || palabra[i + 1] == '\r' || palabra[i + 1] == ';'
                            || palabra[i] == ')' || palabra[i] == '('
                            || palabra[i] == '{' || palabra[i] == '}' || palabra[i] == '='
                            || palabra[i] == '>' || palabra[i] == '<' || palabra[i] == '+'
                            || palabra[i] == '-' || palabra[i] == '*'
                            || palabra[i] == '!') {
                        listPalabras.add(nuevaCadena);
                        nuevaCadena = "";
                    }
                } else {
                    listPalabras.add(nuevaCadena);
                    nuevaCadena = "";
                }
            }
             */
            ArrayList<String> palabras = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(txtP.getText(), "+ .-=*&| {}()[]^/%;:,<>\n\t\r!\" ", true);
            String cadena;
            boolean aux1;

            while (st.hasMoreElements()) {
                String aux = "";
                aux1 = false;
                cadena = st.nextToken();

                if (cadena.equals("+")) {

                    if (st.hasMoreElements()) {
                        aux = st.nextToken();
                        if (aux.equals("=")) {
                            cadena += aux;
                        } else if (aux.equals("+")) {
                            cadena += aux;
                        } else if (aux.equals(" ")) {
                            cadena += aux;
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
                            cadena += aux;
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
                            cadena += aux;
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
                            cadena += aux;
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
                            cadena += aux;
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
                            cadena += aux;
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
                            cadena += aux;
                        } else {
                            aux1 = true;
                        }
                    }
                } else if (cadena.equals("/")) {
/////////////////////////////////////////////////////////////////
                    if (st.hasMoreElements()) {
                        aux = st.nextToken();

                        if (aux.equals("*")) {
                            cadena += aux;
                            while (st.hasMoreElements()) {
                                aux = st.nextToken();
                                if (aux.equals("*")) {
                                    cadena += aux;
                                    aux = st.nextToken();
                                    if (aux.equals("/")) {
                                        cadena += aux;
                                        break;
                                    } else {
                                        if (aux.equals("\n")) {
                                            cadena += aux;
                                        }
                                    }
                                } else {
                                    cadena += aux;
                                }
                            }
                        } else if (aux.equals("/")) {

                            while (st.hasMoreElements()) {
                                cadena += aux;
                                aux = st.nextToken();
                                if (aux.equals("\n")) {
                                    cadena += aux;
                                    break;
                                }
                            }

                        } else if (aux.equals("=")) {
                            cadena += aux;
                        } else if (aux.equals(" ")) {
                            cadena += aux;
                        } else if (aux.equals("\n")) {
                            cadena += aux;
                        } else {
                            aux1 = true;
                        }

                    }
                    ///////////////////////////////////////////////////
                } else if (cadena.equals("<")) {

                    if (st.hasMoreElements()) {
                        aux = st.nextToken();
                        if (aux.equals("=")) {
                            cadena += aux;
                        } else if (aux.equals(">")) {
                            cadena += aux;
                        } else if (aux.equals(" ")) {
                            cadena += aux;
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
                            cadena += aux;
                        } else {
                            aux1 = true;
                        }

                    }
                } else if (cadena.equals("\"")) {
                    while (st.hasMoreElements()) {
                        aux = st.nextToken();
                        if (aux.contains("\"")) {
                            cadena += aux;
                            break;
                        } else if (aux.equals("\n")) {
                            cadena += "\n";
                        } else if (aux.equals(" ")) {
                            cadena += " ";
                        } else {
                            cadena += aux;
                        }
                    }

                }

                try {
                    int aux32 = Integer.parseInt(cadena);
                    if (st.hasMoreElements()) {
                        aux = st.nextToken();
                        if (aux.equals("=")) {
                            cadena += aux;
                        } else if (aux.equals(" ")) {
                            cadena += aux;
                        } else {
                            aux1 = true;
                        }

                    }

                } catch (Exception e) {

                    palabras.add(cadena);
                    if (aux1) {
                        palabras.add(aux);
                    }
                }

            }
            txtP.setText("");

            for (int i = 0; i < palabras.size(); i++) {
                switch (palabras.get(i).toLowerCase()) {
                    case "publico":
                    case "public":
                    case "privado":
                    case "private":
                    case "protegido":
                    case "protected":
                    case "clase":
                    case "class":
                    case "paquete":
                    case "package":
                    case "importar":
                    case "import":
                    case "estatico":
                    case "static":
                    case "final":
                    case "super":
                    case "retorno":
                    case "return":
                    case "procedimiento":
                    case "procedure":
                    case "funcion":
                    case "funtion":
                    case "sistema":
                    case "system":
                    case "linea":
                    case "line":
                    case "abstracta":
                    case "abstract":
                    case "extender":
                    case "extends":
                    case "implementar":
                    case "implements":
                    case "interfaz":
                    case "interface":
                    case "principal":
                    case "main":
                    case "nuevo":
                    case "new":
                    case "este":
                    case "this":
                    case "nulo":
                    case "null":
                    case "leer":
                    case "read":
                    case "escribir":
                    case "write":
                    case "defecto":
                    case "default":
                    case "terminar":
                    case "break":
                    case "salir":
                    case "exit":
                    case "caso":
                    case "switch":
                    case "alternativa":
                    case "case":
                    case "si":
                    case "if":
                    case "sino":
                    case "else":
                    case "sinosi":
                    case "elseif":
                    case "mientras":
                    case "while":
                    case "hacer":
                    case "do":
                    case "para":
                    case "for":
                    case "verdadero":
                    case "true":
                    case "falso":
                    case "false":
                    case "entero":
                    case "int":
                    case "flotante":
                    case "float":
                    case "doble":
                    case "double":
                    case "cadena":
                    case "string":
                    case "booleano":
                    case "boolean":
                    case "largo":
                    case "long":
                    case "objeto":
                    case "object":
                    case "caracter":
                    case "char":
                    case "corto":
                    case "short":
                    case "byte":
                    case "captar":
                    case "catch":
                    case "intentar":
                    case "try":
                    case "finalmente":
                    case "finally":
                    case "constructor":
                        colorPalabra = cAzul;

                        break;

                    case "entrada":
                    case "in":
                    case "salida":
                    case "out":
                        colorPalabra = cVerde;
                        break;
                    default:
                        try {
                            int x = Integer.parseInt(palabras.get(i));
                            colorPalabra = cNaranja;
                        } catch (NumberFormatException e) {
                            try {
                                float x = Float.parseFloat(palabras.get(i));
                                colorPalabra = cNaranja;
                            } catch (NumberFormatException e1) {
                                colorPalabra = cNegro;
                            }
                        }
                        break;

                }
                if (palabras.get(i).startsWith("$") || palabras.get(i).startsWith("&")) {
                    TextPaneTest.ponerEstilo(txtP, "NEGRITAS", palabras.get(i));
                } else if (palabras.get(i).startsWith("/*") || palabras.get(i).startsWith("//")) {
                    TextPaneTest.appendToPane(txtP, palabras.get(i), cGris);
                } else if (palabras.get(i).startsWith("'") || palabras.get(i).startsWith("\"")) {
                    TextPaneTest.appendToPane(txtP, palabras.get(i), cNaranja);
                } else {
                    TextPaneTest.appendToPane(txtP, palabras.get(i), colorPalabra);
                }

            }

        }
    }

    public static void conviertePalabra(JTextPane txtP) {

        ArrayList<String> listPalabras;

        Color colorPalabra;
        Color cAzul = new Color(0, 0, 204);
        Color cVerde = new Color(0, 204, 0);
        Color cRojo = new Color(255, 0, 0);
        Color cNaranja = new Color(255, 102, 0);
        Color cRosa = new Color(255, 0, 255);
        Color cAmarillo = new Color(255, 204, 0);
        Color cCafe = new Color(102, 0, 51);
        Color cNegro = new Color(0, 0, 0);
        Color cGris = new Color(102, 102, 102);
        Color cMorado = new Color(153, 0, 204);

        String s = txtP.getText();
        if (s.length() != 0) {

            listPalabras = new ArrayList<>();

            char[] palabra = s.toCharArray();
            String nuevaCadena = "";

            for (int i = 0; i < palabra.length; i++) {
                nuevaCadena += palabra[i];
                if (palabra[i] == ' ' || palabra[i] == '\n' || palabra[i] == '\t' || palabra[i] == '\r' || palabra[i] == ';'
                        || palabra[i] == '(' || palabra[i] == ')'
                        || palabra[i] == '{' || palabra[i] == '}' || palabra[i] == '='
                        || palabra[i] == '>' || palabra[i] == '<' || palabra[i] == '+'
                        || palabra[i] == '-' || palabra[i] == '*'
                        || palabra[i] == '!') {
                    listPalabras.add(nuevaCadena);
                    nuevaCadena = "";
                }
                if ((i + 1) != palabra.length) {
                    if (palabra[i + 1] == ' ' || palabra[i + 1] == '\n' || palabra[i + 1] == '\t'
                            || palabra[i + 1] == '\r' || palabra[i + 1] == ';'
                            || palabra[i] == ')' || palabra[i] == '('
                            || palabra[i] == '{' || palabra[i] == '}' || palabra[i] == '='
                            || palabra[i] == '>' || palabra[i] == '<' || palabra[i] == '+'
                            || palabra[i] == '-' || palabra[i] == '*'
                            || palabra[i] == '!') {
                        listPalabras.add(nuevaCadena);
                        nuevaCadena = "";
                    }
                } else {
                    listPalabras.add(nuevaCadena);
                    nuevaCadena = "";
                }
            }

            txtP.setText("");

            for (int i = 0; i < listPalabras.size(); i++) {
                switch (listPalabras.get(i).toLowerCase()) {
                    case "publico":

                    case "privado":
                    case "protegido":
                    case "clase":
                    case "paquete":
                    case "importar":
                    case "estatico":
                    case "final":
                    case "super":
                    case "retorno":
                    case "procedimiento":
                    case "funcion":
                    case "sistema":
                    case "linea":
                    case "abstracta":
                    case "extender":
                    case "implementar":
                    case "interfaz":
                    case "obtener":
                    case "asignar":
                    case "principal":
                    case "nuevo":
                    case "este":
                    case "nulo":
                    case "leer":
                    case "imprimir":
                    case "defecto":
                    case "terminar":
                    case "salir":
                    case "caso":
                    case "alternativa":
                    case "si":
                    case "sino":
                    case "sinoSi":
                    case "mientras":
                    case "hacerMientras":
                    case "para":
                    case "verdadero":
                    case "falso":
                    case "entero":
                    case "flotante":
                    case "doble":
                    case "cadena":
                    case "booleano":
                    case "largo":
                    case "objeto":
                    case "caracter":
                    case "corto":
                    case "byte":
                        colorPalabra = cAzul;

                        break;

                    case "entrada":
                    case "salida":
                        colorPalabra = cVerde;
                        break;
                    default:
                        try {
                            int x = Integer.parseInt(listPalabras.get(i));
                            colorPalabra = cNaranja;
                        } catch (NumberFormatException e) {
                            try {
                                float x = Float.parseFloat(listPalabras.get(i));
                                colorPalabra = cNaranja;
                            } catch (NumberFormatException e1) {
                                colorPalabra = cNegro;
                            }
                        }
                        break;

                }
                TextPaneTest.appendToPane(txtP, listPalabras.get(i), colorPalabra);

            }

        }
    }

}
