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
            } catch (Exception e) {
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

    public int operaciones(ColaD pResultado) {
        PilaD pOperacion = new PilaD();
        int resultado = 0;
        while (pResultado.getF() != null) {
            String s = pResultado.elimina(null).getS();
            if (isOperador(s)) {
                String op2 = pOperacion.elimina(null).getS();
                String op1 = pOperacion.elimina(null).getS();
                String res[];
                res = expresionFinal(op1, op2, s);
                if ("TRUE".equals(res[0])) {
                    pOperacion.inserta(new Nodo(String.valueOf(res[1]), -1), null);
                } else {
                    break;
                    //No se pudo realizar la operacion
                    //s[3];
                }
            } else {
                pOperacion.inserta(new Nodo(s, -1), null);
            }
        }
        return resultado;

    }

    public String tipoDato(String s) {
        String tipoDato;

        try {
            byte S0 = Byte.parseByte(s);
            return "BYTE";
        } catch (Exception e) {
            try {
                short S01 = Short.parseShort(s);
                return "SHORT";
            } catch (Exception e9) {
                try {
                    int S1 = Integer.parseInt(s);
                    return "ENTERO";
                } catch (NumberFormatException e2) {
                    try {
                        float S2 = Float.parseFloat(s);
                        return "FLOTANTE";
                    } catch (Exception e3) {
                        try {
                            double S3 = Double.parseDouble(s);
                            return "DOUBLE";
                        } catch (Exception e4) {
                            try {
                                long S4 = Long.parseLong(s);
                                return "LONG";
                            } catch (Exception e5) {
                                try {
                                    s.length();
                                } catch (Exception e6) {

                                }
                            }
                        }
                    }
                }
            }

        }
        return s;
    }

    public static Object tablaResultados(Object obj1, Object obj2) {
        boolean bandera = false;

        return bandera;
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

    public String[] expresionFinal(String op1, String op2, String operador) {
        String resultados[] = new String[3];
        if (operador.equals("+")) {

        } else if (operador.equals("-")) {

        } else if (operador.equals("*")) {

        } else if (operador.equals("/")) {

        } else if (operador.equals("^")) {

        } else if (operador.equals("%")) {

        } else if (operador.equals(">")) {

        } else if (operador.equals(">=")) {

        } else if (operador.equals("<")) {

        } else if (operador.equals("<=")) {

        } else if (operador.equals("==")) {

        } else if (operador.equals("!=")) {

        } else if (operador.equals("&&")) {

        } else if (operador.equals("||")) {

        }
        return resultados;
    }

    public int prioridad(String operador) {
        if (operador.equals("^")) {
            return 6;
        } else if (operador.equals("/") || operador.equals("*") || operador.equals("%")) {
            return 5;
        } else if (operador.equals("+") || operador.equals("-")) {
            return 4;
        } else if (operador.equals(">") || operador.equals("<") || operador.equals(">=") || operador.equals("<=") || operador.equals("==") || operador.equals("!=")) {
            return 3;
        } else if (operador.equals("&&") || operador.equals("||") || operador.equals("!")) {
            return 2;
        }
        return 1;
    }

    public boolean isOperador(String s) {

        if (s.equals("%") || s.equals("*") || s.equals("^") || s.equals("/") || s.equals("+") || s.equals("-") || s.equals("!") || s.equals("&&") || s.equals("||") || s.equals("<") || s.equals(">") || s.equals(">=") || s.equals("<=") || s.equals("==") || s.equals("!=")) {
            return true;
        }

        return false;
    }
    //Lexema

}
