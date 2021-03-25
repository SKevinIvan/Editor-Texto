/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anexos;

import estructurasDatos.ColaD;
import estructurasDatos.Nodo;
import estructurasDatos.PilaD;
import java.util.ArrayList;

/**
 *
 * @author por_s
 */
public class Semantico {

    /**
     * Método que realiza el proceso de validación entre operaciones, y
     * asignaciones
     *
     * @param arr
     * @return
     */
    public static String conversionArrayCola(ArrayList<String> arr) {
        String valor;
        if (arr.size() == 1) {
            return arr.get(0);
        } else {
            ColaD colaOperaciones = new ColaD();
            for (int i = 0; i < arr.size(); i++) {
                Nodo n = new Nodo(arr.get(i), -1);
                colaOperaciones.inserta(n, null);
            }
            Semantico s = new Semantico();
            ColaD notacionPostfija = s.posfijo(colaOperaciones);

            valor = String.valueOf(s.operaciones(notacionPostfija));
        }

        return valor;
    }

    /**
     * Método que realiza la converión de una notación infija, a una postfija.
     * Con el uso de pilas
     *
     * @param cola cadena resultado
     * @return una cola con la notación posfija
     */
    public ColaD posfijo(ColaD cola) {

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

    /**
     * Método que verifica que el lexema sea un operador o no
     *
     * @param s
     * @return si es un operador, retorna true
     */
    private boolean isOperador(String s) {
        return s.equals("%") || s.equals("*") || s.equals("^") || s.equals("/") || s.equals("+") || s.equals("-") || s.equals("!") || s.equals("&&") || s.equals("||") || s.equals("<") || s.equals(">") || s.equals(">=") || s.equals("<=") || s.equals("==") || s.equals("!=");
    }

    /**
     * Método que retorna el numero de prioridad de las operaciones, segun su
     * prioridad
     *
     * @param operador
     * @return
     */
    private int prioridad(String operador) {
        switch (operador) {
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

    /**
     * Metodo que realiza la operación, segun la prioridad de las operaciones
     *
     * @param pResultado
     * @return
     */
    public Object operaciones(ColaD pResultado) {
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
                    if ("TRUE".equals(res[0])) {
                        pOperacion.inserta(new Nodo(String.valueOf(res[1]), -1), null);

                        resultado = res[1];
                    } else {
                        //ERROR NO SE PUEDE REALIZAR LA OPERACION
                        System.out.println("Error de operacion");
                        break;
                    }

                } else {
                    //MARCAR UN ERROR DE INCOMPATIBILIDAD DE OPERACIONES
                    System.out.println("Compatilibidad de operaciones");
                    break;
                }
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
    private String[] expresionFinal(String op1, String op2, String operador, String tipoDato1, String tipoDato2) {
        String resultados[] = new String[3];

        if (tipoDato1.equals("INTEGER") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);
                        int res = var1 + var2;
                        resultados[1] = res + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);
                        int var3 = var1 - var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "*":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);
                        int var3 = var1 * var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "/":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);
                        int var3 = var1 / var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "^":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);
                        String s = String.valueOf(Math.pow(var2, var1));
                        int res = Integer.parseInt(s);
                        resultados[1] = res + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "%":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);
                        int var3 = var1 % var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);
                        boolean var3 = var1 > var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);
                        boolean var3 = var1 >= var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);
                        boolean var3 = var1 < var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);
                        boolean var3 = var1 <= var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "==":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);
                        boolean var3 = var1 == var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "!=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);
                        boolean var3 = var1 != var2;
                        resultados[1] = var3 + "";
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
                        float var3 = var1 + var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);
                        float var3 = var1 - var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "*":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);
                        float var3 = var1 * var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "/":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);
                        float var3 = var1 / var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "^":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);
                        String s = String.valueOf(Math.pow(var2, var1));
                        float var3 = Float.parseFloat(s);
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "%":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);
                        float var3 = var1 % var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);
                        boolean var3 = var1 > var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        boolean var3 = var1 >= var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        boolean var3 = var1 < var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        boolean var3 = var1 <= var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "==":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        boolean var3 = var1 == var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "!=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        boolean var3 = var1 != var2;
                        resultados[1] = var3 + "";
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
            resultados[0] = "FALSE";
        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("BOOLEAN")) {
            resultados[0] = "FALSE";
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        float var3 = var1 + var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);
                        float var3 = var1 - var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "*":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);
                        float var3 = var1 * var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "/":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);
                        float var3 = var1 / var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "^":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);
                        String s = String.valueOf(Math.pow(var2, var1));
                        float var3 = Float.parseFloat(s);
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "%":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);
                        float var3 = var1 % var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);
                        boolean var3 = var1 > var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);
                        boolean var3 = var1 >= var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        boolean var3 = var1 < var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);
                        boolean var3 = var1 <= var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "==":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);
                        boolean var3 = var1 == var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "!=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);
                        boolean var3 = var1 != var2;
                        resultados[1] = var3 + "";
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
                        float var3 = var1 + var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);
                        float var3 = var1 - var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "*":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);
                        float var3 = var1 * var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "/":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);
                        float var3 = var1 / var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "^":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        String s = String.valueOf(Math.pow(var2, var1));
                        float var3 = Float.parseFloat(s);
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "%":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);
                        float var3 = var1 % var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);
                        boolean var3 = var1 > var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);
                        boolean var3 = var1 >= var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);
                        boolean var3 = var1 < var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);
                        boolean var3 = var1 <= var2;
                        resultados[1] = var3 + "";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "==":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);
                        boolean var3 = var1 == var2;
                        resultados[1] = var3 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "!=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);
                        boolean var3 = var1 != var2;
                        resultados[1] = var3 + "";
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
            resultados[0] = "FALSE";
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("BOOLEAN")) {
            resultados[0] = "FALSE";
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
                    return "INTEGER";
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
            return "NOT";
        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("BOOLEAN")) {
            return "NOT";
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
            return "NOT";
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("BOOLEAN")) {
            return "NOT";
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    return "STRING";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    return "STRING";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                    return "STRING";
                case "-":
                case "*":
                case "/":
                case "%":
                case "^":
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
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("INTEGER")) {
            return "NOT";
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("FLOAT")) {
            return "NOT";
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("STRING")) {
            return "NOT";
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
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

    /**
     * Método que verifica que el lexema sea un operador o no
     *
     * @param s
     * @return si es un operador, retorna true
     */
    public boolean isOperadorS(String s) {
        return s.equals("%") || s.equals("*") || s.equals("^") || s.equals("/") || s.equals("+") || s.equals("-") || s.equals("!") || s.equals("&&") || s.equals("||") || s.equals("<") || s.equals(">") || s.equals(">=") || s.equals("<=") || s.equals("==") || s.equals("!=") || s.equals("(") || s.equals(")");
    }

    public static String tipoDatoID(String tipoD) {
        switch (tipoD) {
            case "int":
                return "INTEGER";
            case "float":
                return "FLOAT";
            case "String":
                return "STRING";
            case "boolean":
                return "BOOLEAN";
            default:
                return "?";
        }
    }

    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("345");
        arr.add("+");
        arr.add("(");
        arr.add("53");
        arr.add("+");
        arr.add("(");
        arr.add("345");
        arr.add("+");
        arr.add("(");
        arr.add("23");
        arr.add("+");
        arr.add("35");
        arr.add(")");
        arr.add("+");
        arr.add("345");
        arr.add("+");
        arr.add("4");
        arr.add(")");
        arr.add(")");
        String id = "var";
        String tipo = "int";//Si no esta declarado debe marcar ERROR!! :(
        String tipoDatoID = Semantico.tipoDatoID(tipo);
        if (tipoDatoID.equals("?")) {
            System.out.println("ERROR, ACTUALIZAR TABLA Y DECIR QUE NO ESTA DECLARADA");
        } else {
            String valor = Semantico.conversionArrayCola(arr); //RETORNA EL VALOR DE LA OPERACION
            String tipoDatoValor = Semantico.tipoDato(valor); //RETORNA EL TIPO DE DATO DEL VALOR
            if (Semantico.asignacion(tipoDatoID, tipoDatoValor)) { //VALIDA EL TIPO DE DATO DEL IDENTIFICADOR CON EL DEL VALOR
                System.out.println("" + valor);
            } else {
                System.out.println("ERROR DE ASIGNACION");
            }
        }
    }
}
