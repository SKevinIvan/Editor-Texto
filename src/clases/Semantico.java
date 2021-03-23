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
 * @author por_s
 */
public class Semantico {

    public static ArrayList<TablaSimbolos> tablaSimboloses;

    public void proyecto() {

    }

    public ColaD posfijo(ColaD cola, ArrayList<TablaSimbolos> tb, String var) {

        ColaD pResultado = new ColaD();
        PilaD pOperadores = new PilaD();
        while (cola.getF() != null) {
            String s = cola.elimina(null).getS();
            try {
                int num = Integer.parseInt(s);
                //Es un operando, por lo tanto se inserta en la cola Resultado
                pResultado.inserta(new Nodo(s, -1), null);
            } catch (NumberFormatException e) {
                //Es un operador
                if (s.equals(")")) {

                    while (pOperadores.getTope() != null) {
                        if (pOperadores.getTope().getS().equals("(")) {
                            pOperadores.elimina(null);
                            break;
                        } else {
                            String op = pOperadores.elimina(null).getS();
                            pResultado.inserta(new Nodo(op, -1), null);
                        }
                    }
                } else {
                    if (isOperador(s)) {
                        if (pOperadores.getTope() != null) {
                            String s2 = pOperadores.elimina(null).getS();
                            if (isOperador(s2)) {
                                if (prioridad(s2) >= prioridad(s)) {
                                    pOperadores.inserta(new Nodo(s, -1), null);
                                    pResultado.inserta(new Nodo(s2, -1), null);
                                } else if (prioridad(s2) < prioridad(s)) {
                                    pOperadores.inserta(new Nodo(s2, -1), null);
                                    pResultado.inserta(new Nodo(s, -1), null);
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
                    } else {
                        pOperadores.inserta(new Nodo(s, -1), null);
                    }
                }
            }

        }
        while (pOperadores.getTope() != null) {
            String op = pOperadores.elimina(null).getS();
            pResultado.inserta(new Nodo(op, -1), null);
        }

        //Insertar el valor en la tabla de simbolos
        /*  for (int i = 0; i < tb.size(); i++) {
            if (tb.get(i).getLexema().equals(var)) {
                tb.get(i).setValor(r);
            }
        }*/
        return pResultado;
    }

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
                    System.out.println("Compatilibidad de operaciones err");
                    break;
                }
            } else {
                pOperacion.inserta(new Nodo(s, -1), null);
            }
        }

        return resultado;

    }

    public String tipoDato(String s) {

        try {
            int S1 = Integer.parseInt(s);
            return "ENTERO";
        } catch (NumberFormatException e2) {
            try {
                float S2 = Float.parseFloat(s);
                return "FLOTANTE";
            } catch (NumberFormatException e3) {
                if (s.equals("true") || s.equals("false")) {
                    return "BOOLEANO";
                } else {
                    return "STRING";
                }
            }
        }
    }

    public static String tablaResultados(String tipoDato1, String tipoDato2, String operador) {
        String bandera = "BOOLEANO";
        if (tipoDato1.equals("ENTERO") && tipoDato2.equals("ENTERO")) {
            switch (operador) {
                case "+":
                    return "ENTERO";
                case "-":
                    return "ENTERO";
                case "*":
                    return "ENTERO";
                case "/":
                    return "FLOTANTE";
                case "%":
                    return "ENTERO";
                case "^":
                    return "FLOTANTE";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "BOOLEANO";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }

        } else if (tipoDato1.equals("ENTERO") && tipoDato2.equals("FLOTANTE")) {
            switch (operador) {
                case "+":
                    return "FLOTANTE";
                case "-":
                    return "FLOTANTE";
                case "*":
                    return "FLOTANTE";
                case "/":
                    return "FLOTANTE";
                case "%":
                    return "FLOTANTE";
                case "^":
                    return "FLOTANTE";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "BOOLEANO";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }

        } else if (tipoDato1.equals("ENTERO") && tipoDato2.equals("STRING")) {
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

        } else if (tipoDato1.equals("ENTERO") && tipoDato2.equals("BOOLEANO")) {
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
        } else if (tipoDato1.equals("FLOTANTE") && tipoDato2.equals("ENTERO")) {
            switch (operador) {
                case "+":
                    return "FLOTANTE";
                case "-":
                    return "FLOTANTE";
                case "*":
                    return "FLOTANTE";
                case "/":
                    return "FLOTANTE";
                case "%":
                    return "FLOTANTE";
                case "^":
                    return "FLOTANTE";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "BOOLEANO";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("FLOTANTE") && tipoDato2.equals("FLOTANTE")) {
            switch (operador) {
                case "+":
                    return "FLOTANTE";
                case "-":
                    return "FLOTANTE";
                case "*":
                    return "FLOTANTE";
                case "/":
                    return "FLOTANTE";
                case "%":
                    return "FLOTANTE";
                case "^":
                    return "FLOTANTE";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "BOOLEANO";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }

        } else if (tipoDato1.equals("FLOTANTE") && tipoDato2.equals("STRING")) {
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
        } else if (tipoDato1.equals("FLOTANTE") && tipoDato2.equals("BOOLEAN")) {
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
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("ENTERO")) {
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
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("FLOTANTE")) {
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
                case "!=":
                case "==":
                    return "BOOLEANO";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("BOOLEANO")) {
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
        } else if (tipoDato1.equals("BOOLEANO") && tipoDato2.equals("ENTERO")) {
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
        } else if (tipoDato1.equals("BOOLEANO") && tipoDato2.equals("FLOTANTE")) {
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
        } else if (tipoDato1.equals("BOOLEANO") && tipoDato2.equals("STRING")) {
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
        } else if (tipoDato1.equals("BOOLEANO") && tipoDato2.equals("BOOLEANO")) {
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
                    return "BOOLEANO";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        }

        return bandera;
    }

    public String[] expresionFinal(String op1, String op2, String operador, String tipoDato1, String tipoDato2) {
        String resultados[] = new String[3];

        if (tipoDato1.equals("ENTERO") && tipoDato2.equals("ENTERO")) {
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
        } else if (tipoDato1.equals("ENTERO") && tipoDato2.equals("FLOTANTE")) {
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

        } else if (tipoDato1.equals("ENTERO") && tipoDato2.equals("STRING")) {
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

        } else if (tipoDato1.equals("ENTERO") && tipoDato2.equals("BOOLEANO")) {
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

        } else if (tipoDato1.equals("FLOTANTE") && tipoDato2.equals("ENTERO")) {
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

        } else if (tipoDato1.equals("FLOTANTE") && tipoDato2.equals("FLOTANTE")) {
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

        } else if (tipoDato1.equals("FLOTANTE") && tipoDato2.equals("STRING")) {
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

        } else if (tipoDato1.equals("FLOTANTE") && tipoDato2.equals("BOOLEANO")) {
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

        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("ENTERO")) {
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

        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("FLOTANTE")) {
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

        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("BOOLEANO")) {
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

        } else if (tipoDato1.equals("BOOLEANO") && tipoDato2.equals("ENTERO")) {
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

        } else if (tipoDato1.equals("BOOLEANO") && tipoDato2.equals("FLOTANTE")) {
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

        } else if (tipoDato1.equals("BOOLEANO") && tipoDato2.equals("STRING")) {
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

        } else if (tipoDato1.equals("BOOLEANO") && tipoDato2.equals("BOOLEANO")) {
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
     * Método que verifica que a una variable se le pueda asignar el tipo de
     * dato, dependiendo si es compatible con su mismo tipo de dato de la
     * variable
     *
     * @param tipoDato1
     * @param tipoDato2
     * @return
     */
    public boolean asignacion(String tipoDato1, String tipoDato2) {
        if (tipoDato1.equals("ENTERO") && tipoDato2.equals("ENTERO")) {
            return true;
        } else if (tipoDato1.equals("ENTERO") && tipoDato2.equals("FLOTANTE")) {
            return false;
        } else if (tipoDato1.equals("ENTERO") && tipoDato2.equals("STRING")) {
            return false;
        } else if (tipoDato1.equals("ENTERO") && tipoDato2.equals("BOOLEANO")) {
            return false;
        } else if (tipoDato1.equals("FLOTANTE") && tipoDato2.equals("ENTERO")) {
            return true;
        } else if (tipoDato1.equals("FLOTANTE") && tipoDato2.equals("FLOTANTE")) {
            return true;
        } else if (tipoDato1.equals("FLOTANTE") && tipoDato2.equals("STRING")) {
            return false;
        } else if (tipoDato1.equals("FLOTANTE") && tipoDato2.equals("BOOLEANO")) {
            return false;
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("ENTERO")) {
            return false;
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("FLOTANTE")) {
            return false;
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("STRING")) {
            return true;
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("BOOLEANO")) {
            return false;
        } else if (tipoDato1.equals("BOOLEANO") && tipoDato2.equals("ENTERO")) {
            return false;
        } else if (tipoDato1.equals("BOOLEANO") && tipoDato2.equals("FLOTANTE")) {
            return false;
        } else if (tipoDato1.equals("BOOLEANO") && tipoDato2.equals("STRING")) {
            return false;
        } else {
            return tipoDato1.equals("BOOLEANO") && tipoDato2.equals("BOOLEANO");
        }

    }

    /**
     * Método que retorna el numero de prioridad de las operaciones, segun su
     * prioridad
     *
     * @param operador
     * @return
     */
    public int prioridad(String operador) {
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
     * Método que verifica que el lexema sea un operador o no
     *
     * @param s
     * @return si es un operador, retorna true
     */
    public boolean isOperador(String s) {
        return s.equals("%") || s.equals("*") || s.equals("^") || s.equals("/") || s.equals("+") || s.equals("-") || s.equals("!") || s.equals("&&") || s.equals("||") || s.equals("<") || s.equals(">") || s.equals(">=") || s.equals("<=") || s.equals("==") || s.equals("!=");
    }

    public static void main(String[] args) {
        ColaD cola = new ColaD();
        cola.inserta(new Nodo("4", -1), null);
        cola.inserta(new Nodo("*", -1), null);
        cola.inserta(new Nodo("2", -1), null);
        cola.inserta(new Nodo("+", -1), null);
        cola.inserta(new Nodo("(", -1), null);
        cola.inserta(new Nodo("12", -1), null);
        cola.inserta(new Nodo("/", -1), null);
        cola.inserta(new Nodo("3", -1), null);
        cola.inserta(new Nodo(")", -1), null);
        cola.inserta(new Nodo("-", -1), null);
        cola.inserta(new Nodo("5", -1), null);
        Semantico s = new Semantico();
        System.out.println("Operacion infija: ");
        System.out.println(" 4 * 2 +(12/3)-5");
        ColaD res = s.posfijo(cola, null, null);
        ColaD res2 = new ColaD();
        System.out.println("Posfija");
        while (res.getA() != null) {
            Nodo n1 = res.elimina(null);
            Nodo n2 = new Nodo(n1.getS(), -1);
            res2.inserta(n2, null);
            System.out.print(n2.getS() + " ");
        }
        while (res2.getA() != null) {

            Nodo n1 = res2.elimina(null);
            Nodo n2 = new Nodo(n1.getS(), -1);
            res.inserta(n1, null);
        }
        System.out.println("");
        System.out.println("Resultado: " + s.operaciones(res));

    }
}
