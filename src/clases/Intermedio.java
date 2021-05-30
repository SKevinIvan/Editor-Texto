/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import estructurasDatos.ColaD;
import estructurasDatos.Nodo;
import estructurasDatos.PilaD;
import java.util.ArrayList;

/**
 *
 * @author Kevin_Sanchez
 */
public class Intermedio {

    public static void recibeTokens(ArrayList<Lexema> lx, ArrayList<TablaSimbolos> tSimbolos) {
        //
        String tipo = "";
        String id = "";

        ColaD colaOperaciones = new ColaD();
        for (int i = 0; i < lx.size(); i++) {
            Nodo n = new Nodo(lx.get(i).getLexema(), -1);
            colaOperaciones.inserta(n, null);
        }
        ColaD notacionPostfija = Intermedio.posfijo(colaOperaciones);
        //
        ColaD colaPost = new ColaD();
        ColaD colaPost2 = new ColaD();
        System.out.println("************* Asi se encuentra la pila: *************");
        while (notacionPostfija.getA() != null) {
            Nodo n = notacionPostfija.elimina(null);
            colaPost.inserta(n, null);
            System.out.println("" + n.getS());
        }
        System.out.println("*****************************************************");
        while (colaPost.getA() != null) {
            Nodo n = colaPost.elimina(null);
            colaPost2.inserta(new Nodo(n.getS(), -1), null);
            notacionPostfija.inserta(new Nodo(n.getS(), -1), null);
        }
        //

        String valor = String.valueOf(Intermedio.operaciones(notacionPostfija));
        Intermedio.operacionesIntermedio(colaPost2);

        String tipoDatoValor = Intermedio.tipoDato(valor);
        if (Intermedio.asignacion(tipo, tipoDatoValor)) {
            System.out.println(id + " = " + valor);
        } else {
            System.out.println("ERROR DE ASIGNACION");
        }
        /*
        if (tSimbolos != null) {
            if (Intermedio.asignacion(id, valor)) {
                //Insertar el valor en la tabla de simbolos
                for (int i = 0; i < tSimbolos.size(); i++) {
                    if (tSimbolos.get(i).getLexema().equals(id)) {
                        tSimbolos.get(i).setValor(valor);
                    }
                }
            }
        }*/
    }

    public static boolean isOperadorS(String s) {
        return s.equals("=") || s.equals("%") || s.equals("*") || s.equals("^") || s.equals("/") || s.equals("+") || s.equals("-") || s.equals("!") || s.equals("&&") || s.equals("||") || s.equals("<") || s.equals(">") || s.equals(">=") || s.equals("<=") || s.equals("==") || s.equals("!=") || s.equals("(") || s.equals(")");
    }

    public static ColaD posfijo(ColaD cola) {

        ColaD pResultado = new ColaD();
        PilaD pOperadores = new PilaD();
        while (cola.getF() != null) {
            String s = cola.elimina(null).getS();
            if (isOperadorS(s)) {
                if (isOperador(s)) {
                    if (pOperadores.getTope() != null) {
                        String s2 = pOperadores.elimina(null).getS();
                        if (isOperador(s2)) {
                            if (prioridad(s2) >= prioridad(s)) {
                                pOperadores.inserta(new Nodo(s, -1), null);
                                pResultado.inserta(new Nodo(s2, -1), null);
                            } else if (prioridad(s2) < prioridad(s)) {
                                pOperadores.inserta(new Nodo(s2, -1), null);
                                pOperadores.inserta(new Nodo(s, -1), null);
                            } else {
                                pOperadores.inserta(new Nodo(s2, -1), null);
                                pOperadores.inserta(new Nodo(s, -1), null);
                            }
                        } else {
                            pOperadores.inserta(new Nodo(s2, -1), null);
                            pOperadores.inserta(new Nodo(s, -1), null);
                        }
                    } else {
                        pOperadores.inserta(new Nodo(s, -1), null);
                    }
                } else if (s.equals("(")) {
                    pOperadores.inserta(new Nodo(s, -1), null);
                } else if (s.equals(")")) {
                    while (pOperadores.getTope() != null) {
                        if (pOperadores.getTope().getS().equals("(")) {
                            pOperadores.elimina(null);
                            break;
                        } else {
                            String op = pOperadores.elimina(null).getS();
                            pResultado.inserta(new Nodo(op, -1), null);
                        }
                    }
                }
            } else {
                pResultado.inserta(new Nodo(s, -1), null);
            }
        }
        while (pOperadores.getTope() != null) {
            String op = pOperadores.elimina(null).getS();
            pResultado.inserta(new Nodo(op, -1), null);
        }

        return pResultado;
    }

    private static boolean isOperador(String s) {
        return s.equals("=") || s.equals("%") || s.equals("*") || s.equals("^") || s.equals("/") || s.equals("+") || s.equals("-") || s.equals("!") || s.equals("&&") || s.equals("||") || s.equals("<") || s.equals(">") || s.equals(">=") || s.equals("<=") || s.equals("==") || s.equals("!=");
    }

    private static int prioridad(String operador) {
        switch (operador) {
            case "=":
                return 1;
            case "^":
                return 6;
            case "/":
            case "*":
            case "%":
                return 5;
            case "+":
            case "-":
                return 4;
            case ">":
            case "<":
            case ">=":
            case "<=":
            case "==":
            case "!=":
                return 3;
            case "&&":
            case "||":
            case "!":
                return 2;
            default:
                break;
        }
        return 1;
    }
    static int temp = 0;
    static int Auxtemp = 0;
    static String operador1;
    static String operador2;

    /**
     * Metodo que realiza la operación, segun la prioridad de las operaciones
     *
     * @param pResultado
     * @return
     */
    public static Object operaciones(ColaD pResultado) {
        PilaD pOperacion = new PilaD();
        Object resultado = 0;
        String res[];
        while (pResultado.getF() != null) {
            String s = pResultado.elimina(null).getS();
            if (isOperador(s)) {
                String op2 = pOperacion.elimina(null).getS();
                String op1 = pOperacion.elimina(null).getS();

                String tipoOp1 = tipoDato(op1);
                String tipoOp2 = tipoDato(op2);

                if (!"NOT".equals(tablaResultados(tipoOp1, tipoOp2, s))) {

                    res = expresionFinal(op1, op2, s, tipoOp1, tipoOp2);

                    if (s.equals("=")) {

                    }
                    if ("TRUE".equals(res[0])) {
                        pOperacion.inserta(new Nodo(String.valueOf(res[1]), -1), null);
                        System.out.println("Resultado: " + res[1]);
                        resultado = res[1];
                    } else {
                        //ERROR NO SE PUEDE REALIZAR LA OPERACION
                        System.out.println("Error de operacion");
                        break;
                    }

                } else {
                    //MARCAR UN ERROR DE INCOMPATIBILIDAD DE OPERACIONES
                    System.out.println("Compatilibidad de operaciones err");
                    break;
                }
            } else {
                pOperacion.inserta(new Nodo(s, -1), null);
            }
        }
        return resultado;
    }

    public static Object operacionesIntermedio(ColaD pResultado) {
        PilaD pOperacion = new PilaD();
        Object resultado = 0;
        while (pResultado.getF() != null) {
            String s = pResultado.elimina(null).getS();
            Cuaduplos cI = new Cuaduplos();
            if (isOperador(s)) {
                Nodo nOp1 = pOperacion.elimina(null);
                Nodo nOp2 = pOperacion.elimina(null);

                if (nOp1.getTipo() != "-1") {

                } else {

                }
                if (nOp2.getTipo() != "-1") {

                }
                String op2 = nOp1.getS();
                String op1 = nOp2.getS();

                cI.setOp(s);
                cI.setOp1(op1);
                cI.setOp2(op2);
                if (s.equals("=")) {
                    cI.setTemp(" ");
                } else {
                    cI.setTemp("Temp" + temp);
                }

                pOperacion.inserta(new Nodo("Temp" + temp, -1), null);
                temp++;
                System.out.println(cI.getOp() + " " + cI.getOp1() + " " + cI.getOp2() + " " + cI.getTemp());
            } else {
                pOperacion.inserta(new Nodo(s, -1), null);
            }
        }
        return resultado;
    }

    /**
     * Método que regresa el valor resultante, comparando primero los tipos de
     * datos asociados, para realizar el parseo entre operandos La operación es
     * binaria
     *
     * @param op1
     * @param op2
     * @param operador
     * @param tipoDato1
     * @param tipoDato2
     * @return un arreglo de tipo String [0] - regresa si es valido hacer la
     * operacion [1] - retorna el resultado final
     */
    private static String[] expresionFinal(String op1, String op2, String operador, String tipoDato1, String tipoDato2) {
        String resultados[] = new String[3];

        if (tipoDato1.equals("INTEGER") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 + var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 - var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "*":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 * var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "/":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 / var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "^":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = Math.pow(var2, var1) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "%":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 % var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 > var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 >= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 < var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 <= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "==":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 == var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "!=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 != var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }
        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 + var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 - var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "*":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 * var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "/":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 / var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "^":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = Math.pow(var2, var1) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "%":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = var1 % var2 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 > var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 >= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 < var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 <= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "==":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 == var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "!=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 != var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 + var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 - var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "*":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 * var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "/":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 / var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "^":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = Math.pow(var2, var1) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "%":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 % var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 > var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 >= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 < var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 <= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "==":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 == var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "!=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 != var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 + var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 - var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "*":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 * var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "/":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 / var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "^":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = Math.pow(var2, var1) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "%":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 % var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 > var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 >= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 < var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 <= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "==":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 == var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "!=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 != var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    try {
                        String var1 = String.valueOf(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = var1 + var2 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    try {
                        String var1 = String.valueOf(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = var1 + var2 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                    try {
                        String var1 = String.valueOf(op1);
                        String var2 = String.valueOf(op2);

                        resultados[1] = var1 + var2 + "";
                        resultados[0] = "TRUE";
                    } catch (Exception e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                    resultados[0] = "FALSE";
                    break;
                case "==":
                    try {
                        String var1 = String.valueOf(op1);
                        String var2 = String.valueOf(op2);

                        resultados[1] = (var1.equals(var2)) + "";
                        resultados[0] = "TRUE";
                    } catch (Exception e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "!=":
                    try {
                        String var1 = String.valueOf(op1);
                        String var2 = String.valueOf(op2);

                        resultados[1] = (!var1.equals(var2)) + "";
                        resultados[0] = "TRUE";
                    } catch (Exception e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                    try {
                        String var1 = String.valueOf(op1);
                        boolean var2 = Boolean.parseBoolean(op2);

                        resultados[1] = (var1 + var2) + "";
                        resultados[0] = "TRUE";
                    } catch (Exception e) {
                        resultados[0] = "FALSE";
                    }

                    break;
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                    resultados[0] = "FALSE";
                    break;
                case "&&":
                    try {
                        boolean var1 = Boolean.parseBoolean(op1);
                        boolean var2 = Boolean.parseBoolean(op2);

                        resultados[1] = (var1 && var2) + "";
                        resultados[0] = "TRUE";
                    } catch (Exception e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "||":
                    try {
                        boolean var1 = Boolean.parseBoolean(op1);
                        boolean var2 = Boolean.parseBoolean(op2);

                        resultados[1] = (var1 || var2) + "";
                        resultados[0] = "TRUE";
                    } catch (Exception e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else {
            resultados[0] = "FALSE";
        }

        System.out.println(resultados[1]);
        return resultados;
    }

    /**
     * Método que verifica que tipo de dato es el que tiene una expresión
     *
     * @param s
     * @return
     */
    public static String tipoDato(String s) {

        try {
            int S1 = Integer.parseInt(s);
            return "INTEGER";
        } catch (NumberFormatException e2) {
            try {
                float S2 = Float.parseFloat(s);
                return "FLOAT";
            } catch (NumberFormatException e3) {
                if (s.equals("true") || s.equals("false")) {
                    return "BOOLEAN";
                } else {
                    return "STRING";
                }
            }
        }
    }

    /**
     * Método que revisa la compatibilidad de los tipos de datos con el operando
     * para retornar el tipo de dato que resultará
     *
     * @param tipoDato1
     * @param tipoDato2
     * @param operador
     * @return
     */
    private static String tablaResultados(String tipoDato1, String tipoDato2, String operador) {
        String bandera = "BOOLEAN";
        if (tipoDato1.equals("INTEGER") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    return "INTEGER";
                case "-":
                    return "INTEGER";
                case "*":
                    return "INTEGER";
                case "/":
                    return "FLOAT";
                case "%":
                    return "INTEGER";
                case "^":
                    return "FLOAT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "BOOLEAN";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }

        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    return "FLOAT";
                case "-":
                    return "FLOAT";
                case "*":
                    return "FLOAT";
                case "/":
                    return "FLOAT";
                case "%":
                    return "FLOAT";
                case "^":
                    return "FLOAT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "BOOLEAN";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }

        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }

        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    return "FLOAT";
                case "-":
                    return "FLOAT";
                case "*":
                    return "FLOAT";
                case "/":
                    return "FLOAT";
                case "%":
                    return "FLOAT";
                case "^":
                    return "FLOAT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "BOOLEAN";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    return "FLOAT";
                case "-":
                    return "FLOAT";
                case "*":
                    return "FLOAT";
                case "/":
                    return "FLOAT";
                case "%":
                    return "FLOAT";
                case "^":
                    return "FLOAT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "BOOLEAN";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }

        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    return "STRING";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    return "STRING";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                    return "STRING";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                    return "NOT";
                case "!=":
                case "==":
                    return "BOOLEAN";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                    return "STRING";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "BOOLEAN";
                default:
                    return "NOT";
            }
        }

        return bandera;
    }

    /**
     * Método que verifica que a una variable se le pueda asignar el tipo de
     * dato, dependiendo si es compatible con su mismo tipo de dato de la
     * variable
     *
     * @param tipoDato1
     * @param tipoDato2
     * @return
     */
    public static boolean asignacion(String tipoDato1, String tipoDato2) {
        if (tipoDato1.equals("INTEGER") && tipoDato2.equals("INTEGER")) {
            return true;
        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("FLOAT")) {
            return false;
        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("STRING")) {
            return false;
        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("BOOLEAN")) {
            return false;
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("INTEGER")) {
            return true;
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("FLOAT")) {
            return true;
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("STRING")) {
            return false;
        } else if (tipoDato1.equals("FLOTANTE") && tipoDato2.equals("BOOLEAN")) {
            return false;
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("INTEGER")) {
            return false;
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("FLOAT")) {
            return false;
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("STRING")) {
            return true;
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("BOOLEAN")) {
            return false;
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("INTEGER")) {
            return false;
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("FLOAT")) {
            return false;
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("STRING")) {
            return false;
        } else {
            return tipoDato1.equals("BOOLEAN") && tipoDato2.equals("BOOLEAN");
        }

    }

    static int alondra = 0;

    public static void main(String[] args) {

        ArrayList<Lexema> arr = new ArrayList<>();
        Lexema a = new Lexema();
        a.setLexema("var");
        arr.add(a);
        Lexema a2 = new Lexema();
        a2.setLexema("=");
        arr.add(a2);
        Lexema a3 = new Lexema();
        a3.setLexema("98");
        arr.add(a3);
        Lexema a4 = new Lexema();
        a4.setLexema("*");
        arr.add(a4);
        Lexema a5 = new Lexema();
        a5.setLexema("90");
        arr.add(a5);
        Lexema a6 = new Lexema();
        a6.setLexema("*");
        arr.add(a6);
        Lexema a7 = new Lexema();
        a7.setLexema("190");
        arr.add(a7);
        Lexema a8 = new Lexema();
        a8.setLexema("*");
        arr.add(a8);
        Lexema a9 = new Lexema();
        a9.setLexema("2");
        arr.add(a9);
        Lexema a10 = new Lexema();
        a10.setLexema("*");
        arr.add(a10);
        Lexema a11 = new Lexema();
        a11.setLexema("9");
        arr.add(a11);
        Intermedio.recibeTokens(arr, null);

    }
}
