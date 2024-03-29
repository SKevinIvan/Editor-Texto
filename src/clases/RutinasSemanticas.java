/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import estructurasDatos.PilaD;
import java.util.ArrayList;
import planetprogrammig.EditorPlanetProgramming;

/**
 *
 * @author por_s
 */
public class RutinasSemanticas {

    private ArrayList<Lexema> lexemas;
    private int i;
    private PilaD pilaLlaves;
    private boolean lexCorrecto = true;
    private boolean sinCorrecto = true;
    private boolean semCorrecto = true;

    public int getI() {
        return i;
    }

    public PilaD getPilaLlaves() {
        return pilaLlaves;
    }

    public void setPilaLlaves(PilaD pilaLlaves) {
        this.pilaLlaves = pilaLlaves;
    }

    public boolean isLexCorrecto() {
        return lexCorrecto;
    }

    public void setLexCorrecto(boolean lexCorrecto) {
        this.lexCorrecto = lexCorrecto;
    }

    public boolean isSinCorrecto() {
        return sinCorrecto;
    }

    public void setSinCorrecto(boolean sinCorrecto) {
        this.sinCorrecto = sinCorrecto;
    }

    public boolean isSemCorrecto() {
        return semCorrecto;
    }

    public void setSemCorrecto(boolean semCorrecto) {
        this.semCorrecto = semCorrecto;
    }

    public void setI(int i) {
        this.i = i;
    }

    public ArrayList<Lexema> getLexemas() {
        return lexemas;
    }

    public void setLexemas(ArrayList<Lexema> lexemas) {
        this.lexemas = lexemas;
    }

    public RutinasSemanticas(ArrayList<Lexema> lexemas) {
        this.lexemas = lexemas;
        this.i = 0;
        this.pilaLlaves = new PilaD();
    }

    public void analisisSemantico() {
        if (lexemas.isEmpty()) {
            mensajeError("AXX", "No existen instrucciones", lexemas.get(i).getRenglon(), "ADVERTENCIA SINTACTICA");
        } else {
            proyecto();
        }

    }

    private void proyecto() {
        if (lexemas.get(i).getNumToken() == 70) {
            i++;
            if (lexemas.get(i).getNumToken() == 61) {
                i++;
                comentariosIniciales();
                if (lexemas.get(i).getNumToken() == 62) {
                    i++;
                } else {
                    sinCorrecto = false;
                    if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                        mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");

                    } else {
                        mensajeError("E16", "Se esperaba una llave que cierra } ", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");

                    }

                }
            } else {
                sinCorrecto = false;
                if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                    mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                } else {
                    mensajeError("E16", "Se esperaba una llave que abre { ", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                }
            }
        } else {
            sinCorrecto = false;
            if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
            } else {
                mensajeError("E16", "Se esperaba el nombre de proyecto ", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
            }

        }
    }

    private void comentariosIniciales() {
        switch (lexemas.get(i).getNumToken()) {
            case 82:
                comentario();
                paquete();
                comentariosIniciales();
                break;
            case 83:
                comentario();
                paquete();
                comentariosIniciales();
                break;
            case 5:
                paquete();
                comentariosIniciales();
                break;
            case 62:
                break;
            default:
                sinCorrecto = false;
                mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                break;
        }

    }

    private void paquete() {
        if (lexemas.get(i).getNumToken() == 5) {
            i++;
            if (lexemas.get(i).getNumToken() == 71) {
                i++;
                if (lexemas.get(i).getNumToken() == 61) {
                    i++;
                    paquete2();
                    if (lexemas.get(i).getNumToken() == 62) {
                        i++;
                    } else {
                        sinCorrecto = false;
                        if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                            mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                        } else {
                            mensajeError("E16", "Se esperaba  una llave que cierra } ", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                        }
                    }
                } else {
                    sinCorrecto = false;
                    if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                        mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                    } else {
                        mensajeError("E16", "Se esperaba una llave que abre { ", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                    }

                }

            } else {
                sinCorrecto = false;
                if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                    mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                } else {
                    mensajeError("E16", "Se esperaba el nombre de paquete ", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                }
            }
        } else {
            sinCorrecto = false;
            if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
            } else {
                mensajeError("E16", "Se esperaba la palabra reservada paquete(es) o package(in)", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
            }
        }

    }

    private void paquete2() {
        if (lexemas.get(i).getNumToken() == 5) {
            i++;
            if (lexemas.get(i).getNumToken() == 71) {
                i++;
                if (lexemas.get(i).getNumToken() == 61) {
                    i++;
                    paquete2();
                    if (lexemas.get(i).getNumToken() == 62) {
                        i++;
                    } else {
                        sinCorrecto = false;
                        if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                            mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                        } else {
                            mensajeError("E16", "Se esperaba una llave que cierra } ", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                        }
                    }
                } else {
                    sinCorrecto = false;
                    if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                        mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                    } else {
                        mensajeError("E16", "Se esperaba una llave que abre { ", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                    }
                }

            } else {
                sinCorrecto = false;
                if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                    mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                } else {
                    mensajeError("E16", "Se esperaba el nombre del paquete", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                }

            }

        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 82, 83, 6, 1, 2, 3, 7, 20, 17, 4)) {
            clase();
        } else {
            sinCorrecto = false;
            if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
            } else {
                mensajeError("E16", "Se esperaba la palabra reservada paquete(es) o package(in)", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
            }
        }
    }

    private void clase() {
        if (lexemas.get(i).getNumToken() == 82) {
            comentario();
            clase();
        } else if (lexemas.get(i).getNumToken() == 83) {
            comentario();
            clase();
        } else if (lexemas.get(i).getNumToken() == 6) {
            libreria();
            clase();
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 1, 2, 3, 7, 20, 17, 4)) {
            modificadorAcceso();
            estatico();
            polimorfismo();
            clase();
        } else if (lexemas.get(i).getNumToken() == 62) {
            //Salir
        } else {
            sinCorrecto = false;
            if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
            } else {
                mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
            }
        }

    }

    private void comentario() {
        switch (lexemas.get(i).getNumToken()) {
            case 82:
                i++;
                break;
            case 83:
                i++;
                break;
            default:
                sinCorrecto = false;
                if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                    mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                } else {
                    mensajeError("E16", "Se esperaba un comentario de linea o de bloque ", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                }
                break;
        }
    }

    private void libreria() {
        if (lexemas.get(i).getNumToken() == 6) {
            i++;
            importarLibreria();
        } else {
            sinCorrecto = false;
            if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
            } else {
                mensajeError("E16", "Se esperaba la palabra reservada importar(es) o import(in)", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
            }
        }
    }

    private void importarLibreria() {
        switch (lexemas.get(i).getNumToken()) {
            case 71:
                importarLibreria2();
                if (lexemas.get(i).getNumToken() == 81) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 58) {
                        i++;
                    } else {
                        sinCorrecto = false;
                        if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                            mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                        } else {
                            mensajeError("E16", "Se esperaba un punto y coma ;", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                        }
                    }
                } else {
                    sinCorrecto = false;
                    if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                        mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                    } else {
                        mensajeError("E16", "Se esperaba el nombre de una libreria", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                    }

                }
                break;
            case 81:
                importarLibreria2();
                if (lexemas.get(i).getNumToken() == 81) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 58) {
                        i++;
                    } else {
                        sinCorrecto = false;
                        if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                            mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                        } else {
                            mensajeError("E16", "Se esperaba un punto y coma ;", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                        }
                    }
                } else {
                    sinCorrecto = false;
                    if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                        mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                    } else {
                        mensajeError("E16", "Se esperaba el nombre de una libreria", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                    }

                }
                break;
            default:
                sinCorrecto = false;
                if (tokenNoPermitido(lexemas.get(i).getRenglon())) {
                    mensajeError("E17", "Error al recibir " + lexemas.get(i).getLexema(), lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                } else {
                    mensajeError("E16", "Se esperaba la palabra reservada paquete(es) o package(in)", lexemas.get(i).getRenglon(), "ERROR SINTACTICO");
                }
                break;
        }
    }

    private void importarLibreria2() {
        switch (lexemas.get(i).getNumToken()) {
            case 71:
                i++;
                if (lexemas.get(i).getNumToken() == 59) {
                    i++;
                    importarLibreria2();
                } else {

                }
                break;
            case 81:
                break;
            default:
                break;
        }
    }

    private void modificadorAcceso() {
        if (lexemas.get(i).getNumToken() == 1) {
            i++;
        } else if (lexemas.get(i).getNumToken() == 2) {
            i++;
        } else if (lexemas.get(i).getNumToken() == 3) {
            i++;
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 62, 7, 44, 45, 45, 46, 72, 12, 11, 40, 20, 17, 4)) {
//Salir
        } else {

        }
    }

    private void estatico() {
        if (lexemas.get(i).getNumToken() == 7) {
            i++;
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 20, 17, 4)) {
//Salir
        } else {

        }
    }
//////////////////////////////////////////////////////////////////

    private void polimorfismo() {
        switch (lexemas.get(i).getNumToken()) {
            case 20:
                //24
                i++;
                if (lexemas.get(i).getNumToken() == 4) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 72) {
                        i++;
                        extension();
                        if (lexemas.get(i).getNumToken() == 61) {
                            i++;
                            instruccion();
                            if (lexemas.get(i).getNumToken() == 62) {
                                i++;
                            } else {
                                //Salir
                            }
                        } else {

                        }
                    } else {

                    }
                } else {

                }
                break;
            case 17:
                //25
                i++;
                if (lexemas.get(i).getNumToken() == 4) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 72) {
                        i++;
                        extension();
                        if (lexemas.get(i).getNumToken() == 61) {
                            i++;
                            instruccion();
                            if (lexemas.get(i).getNumToken() == 62) {
                                i++;
                            } else {

                            }
                        } else {

                        }
                    } else {

                    }
                } else {

                }
                break;
            case 4:
                //26
                i++;
                if (lexemas.get(i).getNumToken() == 72) {
                    i++;
                    extension();
                    if (lexemas.get(i).getNumToken() == 61) {
                        i++;
                        instruccion();
                        if (lexemas.get(i).getNumToken() == 62) {
                            i++;
                        } else {

                        }
                    } else {

                    }
                } else {

                }
                break;
            default:
                break;
        }

    }

    private void extension() {
        switch (lexemas.get(i).getNumToken()) {
            case 18:
                //27
                herencia();
                break;
            case 19:
                //28
                implementacion();
                break;
            //29
            //Salir
            case 61:
                break;
            default:
                break;
        }

    }

    private void herencia() {
        if (lexemas.get(i).getNumToken() == 18) {
            //30
            i++;
            if (lexemas.get(i).getNumToken() == 72) {
                i++;
                implementacion();
            } else {

            }
        } else {

        }
    }

    private void implementacion() {
        switch (lexemas.get(i).getNumToken()) {
            case 19:
                //31
                implementar();
                break;
            //32
            //Salir
            case 61:
                break;
            default:
                break;
        }
    }

    private void implementar() {
        if (lexemas.get(i).getNumToken() == 19) {
            //33
            i++;
            if (lexemas.get(i).getNumToken() == 72) {
                i++;
                implementar2();
            } else {

            }
        } else {

        }
    }

    private void implementar1() {
        if (lexemas.get(i).getNumToken() == 57) {
            //34
            i++;
            if (lexemas.get(i).getNumToken() == 72) {
                i++;
                implementar2();
            } else {

            }
        } else {

        }
    }

    private void implementar2() {
        switch (lexemas.get(i).getNumToken()) {
            case 57:
                //35
                implementar1();
                break;
            //36
            //Salir
            case 61:
                break;
            default:
                break;
        }
    }

    private void instruccion() {
        if (lexemas.get(i).getNumToken() == 82) {
            //37
            comentario();
            instruccion();
        } else if (lexemas.get(i).getNumToken() == 83) {
            //37
            comentario();
            instruccion();
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40)) {
//38
            modificadorAcceso();
            controlAcceso();
            instruccion();
        } else if (lexemas.get(i).getNumToken() == 62) {
            //39
            //Salir
        } else {

        }
    }

    private void controlAcceso() {
        if (lexemas.get(i).getNumToken() == 7) {
            //40
            i++;
            encapsulamiento();
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 44, 45, 46, 72, 12, 11)) {
//41
            encapsulamiento();
        } else if (lexemas.get(i).getNumToken() == 40) {
            //42
            constructor();
        } else {

        }
    }

    private void encapsulamiento() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 44, 45, 46, 72)) {
//43
            atributo();
        } else if (lexemas.get(i).getNumToken() == 12) {
            //44
            metodo();
        } else if (lexemas.get(i).getNumToken() == 11) {
            //44
            metodo();
        } else {

        }
    }

    private void atributo() {
        switch (lexemas.get(i).getNumToken()) {
            case 44:
                //45
                declaracionAtributo();
                break;
            case 45:
                //45
                declaracionAtributo();
                break;
            case 46:
                //45
                declaracionAtributo();
                break;
            case 72:
                //46
                declaracionObjeto();
                break;
            default:
                break;
        }
    }

    private void declaracionAtributo() {
        switch (lexemas.get(i).getNumToken()) {
            case 44:
                //47
                tipoDato();
                asignacionAtributo();
                conjuntoAtributo();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {

                }
                break;
            case 45:
                //47
                tipoDato();
                asignacionAtributo();
                conjuntoAtributo();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {

                }
                break;
            case 46:
                //47
                tipoDato();
                asignacionAtributo();
                conjuntoAtributo();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {

                }
                break;
            default:
                break;
        }
    }

    private void conjuntoAtributo() {
        switch (lexemas.get(i).getNumToken()) {
            case 57:
                //48
                i++;
                asignacionAtributo();
                conjuntoAtributo();
                break;
            //49
            //Salir
            case 58:
                break;
            default:
                break;
        }

    }

    private void asignacionAtributo() {
        switch (lexemas.get(i).getNumToken()) {
            case 74:
                //50
                variable();
                break;
            case 75:
                //51
                constante();
                break;
            default:
                break;
        }
    }

    private void variable() {
        if (lexemas.get(i).getNumToken() == 74) {
            //52
            i++;
            inicializacionVariable();
        } else {

        }
    }

    private void constante() {
        if (lexemas.get(i).getNumToken() == 75) {
            //53
            i++;
            inicializacionConstante();
        } else {

        }
    }

    private void inicializacionVariable() {
        if (lexemas.get(i).getNumToken() == 63) {
            //54
            arregloVar3();
            declaracionArregloVariable();
        } else if (lexemas.get(i).getNumToken() == 54) {
            //55
            inicializacionAtributo();
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//56
//Salir
        } else {

        }
    }

    private void declaracionArregloVariable() {
        if (lexemas.get(i).getNumToken() == 54) {
            //57
            declaracionArregloVariable2();
        } else {

        }

    }

    private void declaracionArregloVariable2() {
        if (lexemas.get(i).getNumToken() == 54) {
            //58
            i++;
            if (lexemas.get(i).getNumToken() == 22) {
                i++;
                tipoDato();
                arregloVar2();
            } else {

            }
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//59
//Salir
        } else {

        }
    }

    private void inicializacionConstante() {
        switch (lexemas.get(i).getNumToken()) {
            case 54:
                //60
                inicializacionAtributo();
                break;
            case 63:
                //61
                declaracionArregloConstante();
                break;
            default:
                break;
        }
    }

    private void declaracionArregloConstante() {
        if (lexemas.get(i).getNumToken() == 63) {
            //62
            arreglo1();
            declaracionArregloConstante2();
            arreglo2();
        } else {

        }
    }

    private void declaracionArregloConstante2() {
        switch (lexemas.get(i).getNumToken()) {
            case 63:
                //63
                arreglo1();
                declaracionArregloConstante2();
                arreglo2();
                break;
            case 54:
                //64
                i++;
                if (lexemas.get(i).getNumToken() == 22) {
                    i++;
                    tipoDato();
                } else {

                }
                break;
            default:
                break;
        }
    }

    private void arreglo1() {
        if (lexemas.get(i).getNumToken() == 63) {
            //65
            i++;
            if (lexemas.get(i).getNumToken() == 64) {
                i++;
            } else {

            }
        } else {
        }
    }

    private void arreglo2() {
        if (lexemas.get(i).getNumToken() == 63) {
            //66
            i++;
            posicion();
            if (lexemas.get(i).getNumToken() == 64) {
                i++;
            } else {

            }
        } else {

        }
    }

    private void inicializacionAtributo() {
        if (lexemas.get(i).getNumToken() == 54) {
            //67
            i++;
            operacion();
        } else {

        }
    }

    private void declaracionObjeto() {
        if (lexemas.get(i).getNumToken() == 72) {
            //68
            i++;
            if (lexemas.get(i).getNumToken() == 76) {
                i++;
                inicializacionObjeto();
                conjuntoObjetos();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void conjuntoObjetos() {
        switch (lexemas.get(i).getNumToken()) {
            case 57:
                //69
                i++;
                if (lexemas.get(i).getNumToken() == 76) {
                    i++;
                    inicializacionObjeto();
                    conjuntoObjetos();
                } else {

                }
                break;
            //70
            //Salir
            case 58:
                break;
            default:
                break;
        }
    }

    private void inicializacionObjeto() {
        if (lexemas.get(i).getNumToken() == 54) {
            //71
            i++;
            igualacionObjeto();
        } else if (lexemas.get(i).getNumToken() == 63) {
            //73
            i++;
            if (lexemas.get(i).getNumToken() == 64) {
                i++;
                arregloObjeto();
            } else {

            }
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//72
//Salir
        } else {

        }
    }

    private void igualacionObjeto() {
        switch (lexemas.get(i).getNumToken()) {
            case 24:
                //74
                i++;
                break;
            case 76:
                //75
                i++;
                break;
            case 22:
                //76
                i++;
                if (lexemas.get(i).getNumToken() == 72) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 65) {
                        i++;
                        parametro();
                        if (lexemas.get(i).getNumToken() == 66) {
                            i++;
                        } else {

                        }
                    } else {

                    }
                } else {

                }
                break;
            default:
                break;
        }
    }

    private void arregloObjeto() {
        if (lexemas.get(i).getNumToken() == 63) {
            //78
            i++;
            if (lexemas.get(i).getNumToken() == 64) {
                i++;
                arregloObjeto();
            } else {

            }
        } else if (lexemas.get(i).getNumToken() == 54) {
            //79
            i++;
            if (lexemas.get(i).getNumToken() == 22) {
                i++;
                if (lexemas.get(i).getNumToken() == 72) {
                    i++;
                    arregloObjeto2();
                } else {

                }
            } else {

            }
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//77
//Salir
        } else {

        }
    }

    private void arregloObjeto2() {
        if (lexemas.get(i).getNumToken() == 63) {
            //80
            i++;
            posicion();
            if (lexemas.get(i).getNumToken() == 64) {
                i++;
                arregloObjeto2();
            } else {

            }
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57, 54)) {
//81
//Salir
        } else {

        }
    }

    private void asignacionObjeto() {
        switch (lexemas.get(i).getNumToken()) {
            case 54:
                //82
                asignacionObjeto2();
                break;
            case 63:
                //82
                asignacionObjeto2();
                break;
            default:
                break;
        }
    }

    private void asignacionObjeto2() {
        switch (lexemas.get(i).getNumToken()) {
            case 54:
                //83
                i++;
                asignacionObjeto3();
                break;
            case 63:
                //84
                i++;
                posicion();
                if (lexemas.get(i).getNumToken() == 64) {
                    i++;
                    arregloObjeto2();
                    if (lexemas.get(i).getNumToken() == 54) {
                        i++;
                        asignacionObjeto3();
                    } else {

                    }
                } else {

                }
                break;
            default:
                break;
        }
    }

    private void asignacionObjeto3() {
        switch (lexemas.get(i).getNumToken()) {
            case 24:
                //85
                i++;
                break;
            case 22:
                //86
                i++;
                if (lexemas.get(i).getNumToken() == 72) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 65) {
                        i++;
                        parametro();
                        if (lexemas.get(i).getNumToken() == 66) {
                            i++;
                        } else {

                        }
                    } else {

                    }
                } else {

                }
                break;
            case 76:
                //87
                i++;
                break;
            default:
                break;
        }
    }

    private void arregloVar1() {
        if (lexemas.get(i).getNumToken() == 63) {
            //88
            i++;
            if (lexemas.get(i).getNumToken() == 64) {
                i++;
                arregloVar1Aux();
            } else {

            }
        } else {

        }
    }

    private void arregloVar1Aux() {
        if (lexemas.get(i).getNumToken() == 63) {
            //89
            i++;
            if (lexemas.get(i).getNumToken() == 64) {
                i++;
                arregloVar1Aux();
            } else {

            }
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57, 54)) {
//90
//Salir
        } else {

        }
    }

    private void arregloVar2() {
        if (lexemas.get(i).getNumToken() == 63) {
            //91
            i++;
            posicionAux();
        } else {

        }
    }

    private void arregloVar3() {
        if (lexemas.get(i).getNumToken() == 63) {
            //92
            i++;
            if (lexemas.get(i).getNumToken() == 64) {
                i++;
                arregloVar3();
            } else {

            }
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57, 54)) {
//93
//Salir
        } else {

        }
    }

    private void posicion() {
        if (lexemas.get(i).getNumToken() == 77) {
            //94
            i++;
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 72, 23, 76, 73, 74, 75)) {
//95
            claseInstancia();
        } else {

        }
    }

    private void posicionAux() {
        if (lexemas.get(i).getNumToken() == 94) {
            //97
            i++;
            arregloVar3();

        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 77, 72, 23, 76, 73, 74, 75)) {
//96
            posicion();
            if (lexemas.get(i).getNumToken() == 64) {
                i++;
                arregloVar22();
            } else {

            }
        } else {

        }
    }

    private void arregloVar4() {
        switch (lexemas.get(i).getNumToken()) {
            case 63:
                //98
                i++;
                posicion();
                if (lexemas.get(i).getNumToken() == 64) {
                    i++;
                    arregloVar4();
                } else {

                }
                break;
            //99
            //Salir
            case 54:
                break;
            //99
            //Salir
            case 53:
                break;
            default:
                break;
        }

    }

    private void claseInstancia() {
        switch (lexemas.get(i).getNumToken()) {
            case 72:
                //100
                i++;
                if (lexemas.get(i).getNumToken() == 59) {
                    i++;
                    claseInstancia2();
                } else {

                }
                break;
            case 23:
                //101
                i++;
                if (lexemas.get(i).getNumToken() == 59) {
                    i++;
                    claseInstancia2();
                } else {

                }
                break;
            case 76:
                //102
                i++;
                claseInstancia22();
                break;
            case 73:
                //103
                i++;
                if (lexemas.get(i).getNumToken() == 65) {
                    i++;
                    parametro();
                    if (lexemas.get(i).getNumToken() == 66) {
                        i++;
                    } else {

                    }
                } else {

                }
                break;
            case 74:
                //104
                i++;
                arregloVar22();
                break;
            case 75:
                //105
                i++;
                arregloVar22();
                break;
            default:
                break;
        }
    }

    private void claseInstancia2() {
        switch (lexemas.get(i).getNumToken()) {
            case 73:
                //106
                i++;
                if (lexemas.get(i).getNumToken() == 65) {
                    i++;
                    parametro();
                    if (lexemas.get(i).getNumToken() == 66) {
                        i++;
                    } else {

                    }
                } else {

                }
                break;
            case 74:
                //107
                i++;
                arregloVar22();
                break;
            case 75:
                //108
                i++;
                arregloVar22();
                break;
            case 76:
                //109
                i++;
                claseInstancia22();
                break;
            default:
                break;
        }
    }

    private void claseInstancia22() {
        switch (lexemas.get(i).getNumToken()) {
            case 59:
                //110
                i++;
                claseInstancia2();
                break;
            case 63:
                //111
                arregloVar22();
                break;
            default:
                break;
        }
    }

    private void arregloVar22() {
        if (lexemas.get(i).getNumToken() == 63) {
            //112
            arregloVar2();
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57, 60, 52, 66, 64, 49, 52, 50, 51, 55, 48, 74, 57)) {
//113
//Salir
        } else {

        }
    }

    private void atributoSentencia() {
        switch (lexemas.get(i).getNumToken()) {
            case 44:
                //114
                declaracionAtributo();
                break;
            case 45:
                //114
                declaracionAtributo();
                break;
            case 46:
                //114
                declaracionAtributo();
                break;
            case 72:
                //115
                i++;
                refDeclaracionObjeto();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {

                }
                break;
            case 23:
                //116
                referenciaAtributo();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {

                }
                break;
            case 76:
                //116
                referenciaAtributo();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {

                }
                break;
            case 74:
                //116
                referenciaAtributo();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {

                }
                break;
            case 73:
                //116
                referenciaAtributo();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {

                }
                break;
            default:
                break;
        }
    }

    private void refDeclaracionObjeto() {
        switch (lexemas.get(i).getNumToken()) {
            case 59:
                //117
                i++;
                referenciaAtributo2();
                break;
            case 76:
                //118
                i++;
                inicializacionObjeto();
                conjuntoObjetos();
                break;
            default:
                break;
        }
    }

    private void referenciaAtributo() {
        switch (lexemas.get(i).getNumToken()) {
            case 23:
                //119
                i++;
                if (lexemas.get(i).getNumToken() == 59) {
                    i++;
                    referenciaAtributo2();
                } else {

                }
                break;
            case 76:
                //120
                i++;
                referenciaAtributo22();
                break;
            case 74:
                //121
                i++;
                arregloVar4();
                asignacionAtributoVar();
                break;
            case 73:
                //265
                i++;
                if (lexemas.get(i).getNumToken() == 65) {
                    i++;
                    parametro();
                    if (lexemas.get(i).getNumToken() == 66) {
                        i++;
                    } else {

                    }
                } else {

                }
                break;
            default:
                break;
        }
    }

    private void referenciaAtributo2() {
        switch (lexemas.get(i).getNumToken()) {
            case 74:
                //122
                i++;
                arregloVar4();
                asignacionAtributoVar();
                break;
            case 76:
                //123
                i++;
                referenciaAtributo22();
                break;
            case 73:
                //266
                i++;
                if (lexemas.get(i).getNumToken() == 65) {
                    i++;
                    parametro();
                    if (lexemas.get(i).getNumToken() == 66) {
                        i++;
                    } else {

                    }
                } else {

                }
                break;
            default:
                break;
        }
    }

    private void referenciaAtributo22() {
        switch (lexemas.get(i).getNumToken()) {
            case 59:
                //124
                i++;
                referenciaAtributo2();
                break;
            case 54:
                //125
                arregloVar2Obj();
                break;
            case 63:
                //125
                arregloVar2Obj();
                break;
            default:
                break;
        }
    }

    private void arregloVar2Obj() {
        switch (lexemas.get(i).getNumToken()) {
            case 54:
                //126
                asignacionObjeto();
                break;
            case 63:
                //126
                asignacionObjeto();
                break;
            default:
                break;
        }
    }

    private void asignacionAtributoVar() {
        switch (lexemas.get(i).getNumToken()) {
            case 54:
                //127
                i++;
                asignacionAtributoVar2();
                break;
            case 53:
                //128
                i++;
                operacionAritmetica();
                break;
            default:
                break;
        }
    }

    private void asignacionAtributoVar2() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 72, 23, 76, 73, 74, 75, 77, 78, 48, 79, 80, 24, 46, 65)) {
            //129
            operacion();
        } else if (lexemas.get(i).getNumToken() == 68) {
            //130
            iteracion();
        } else if (lexemas.get(i).getNumToken() == 69) {
            //130
            iteracion();
        } else if (lexemas.get(i).getNumToken() == 15) {
            //131
            i++;
            if (lexemas.get(i).getNumToken() == 59) {
                i++;
                lectura();
            } else {

            }
        } else if (lexemas.get(i).getNumToken() == 22) {
            //267
            i++;
            tipoDato();
            arregloVar2();
        } else {

        }
    }

    private void tipoDato() {
        switch (lexemas.get(i).getNumToken()) {
            case 44:
                //132
                i++;
                break;
            case 45:
                //133
                i++;
                break;
            case 46:
                //134
                i++;
                break;
            default:
                break;
        }
    }

    private void valor() {
        switch (lexemas.get(i).getNumToken()) {
            case 77:
                //135
                numero();
                break;
            case 78:
                //135
                numero();
                break;
            case 80:
                //136
                i++;
                break;
            case 79:
                //137
                i++;
                concatenacion();
                break;
            case 38:
                //138
                boleano();
                break;
            case 39:
                //138
                boleano();
                break;
            case 24:
                //139
                i++;
                break;
            default:
                break;
        }
    }

    private void numero() {
        switch (lexemas.get(i).getNumToken()) {
            case 77:
                //140
                i++;
                break;
            case 78:
                //141
                i++;
                break;
            default:
                break;
        }

    }

    private void boleano() {
        switch (lexemas.get(i).getNumToken()) {
            case 38:
                //142
                i++;
                break;
            case 39:
                //143
                i++;
                break;
            default:
                break;
        }
    }

    private void constructor() {
        if (lexemas.get(i).getNumToken() == 40) {
            //144
            i++;
            if (lexemas.get(i).getNumToken() == 72) {
                i++;
                if (lexemas.get(i).getNumToken() == 65) {
                    i++;
                    argumento();
                    if (lexemas.get(i).getNumToken() == 66) {
                        i++;
                        if (lexemas.get(i).getNumToken() == 61) {
                            i++;
                            sentencia();
                            if (lexemas.get(i).getNumToken() == 62) {
                                i++;
                            } else {

                            }
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void argumento() {
        switch (lexemas.get(i).getNumToken()) {
            case 44:
                //145
                tipoDato();
                arregloVar22();
                if (lexemas.get(i).getNumToken() == 74) {
                    i++;
                    argumento1();
                } else {

                }
                break;
            case 45:
                //145
                tipoDato();
                arregloVar22();
                if (lexemas.get(i).getNumToken() == 74) {
                    i++;
                    argumento1();
                } else {

                }
                break;
            case 46:
                //145
                tipoDato();
                arregloVar22();
                if (lexemas.get(i).getNumToken() == 74) {
                    i++;
                    argumento1();
                } else {

                }
                break;
            case 72:
                //256
                i++;
                if (lexemas.get(i).getNumToken() == 76) {
                    i++;
                    argumento1();
                } else {

                }
                break;
            //146
            //Salir
            case 66:
                break;
            default:
                break;
        }
    }

    private void argumento1() {
        switch (lexemas.get(i).getNumToken()) {
            case 57:
                //147
                i++;
                argumento();
                break;
            //148
            //Salir
            case 66:
                break;
            default:
                break;
        }
    }

    private void metodo() {
        switch (lexemas.get(i).getNumToken()) {
            case 12:
                //149
                funcion();
                break;
            case 11:
                //150
                procedimiento();
                break;
            default:
                break;
        }
    }

    private void procedimiento() {
        if (lexemas.get(i).getNumToken() == 11) {
            //151
            i++;
            procedimiento1();
        } else {

        }
    }

    private void procedimiento1() {
        switch (lexemas.get(i).getNumToken()) {
            case 73:
                //152
                procedimientoNormal();
                break;
            case 21:
                //153
                procedimientoPrincipal();
                break;
            default:
                break;
        }
    }

    private void procedimientoPrincipal() {
        if (lexemas.get(i).getNumToken() == 21) {
            //154
            i++;
            if (lexemas.get(i).getNumToken() == 65) {
                i++;
                if (lexemas.get(i).getNumToken() == 44) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 63) {
                        i++;
                        if (lexemas.get(i).getNumToken() == 64) {
                            i++;
                            if (lexemas.get(i).getNumToken() == 74) {
                                i++;
                                if (lexemas.get(i).getNumToken() == 66) {
                                    i++;
                                    if (lexemas.get(i).getNumToken() == 61) {
                                        i++;
                                        sentencia();
                                        if (lexemas.get(i).getNumToken() == 62) {
                                            i++;
                                        } else {

                                        }
                                    } else {

                                    }
                                } else {

                                }
                            } else {

                            }
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void funcion() {
        if (lexemas.get(i).getNumToken() == 12) {
            //155
            i++;
            tipoDato();
            if (lexemas.get(i).getNumToken() == 73) {
                i++;
                if (lexemas.get(i).getNumToken() == 65) {
                    i++;
                    argumento();
                    if (lexemas.get(i).getNumToken() == 66) {
                        i++;
                        if (lexemas.get(i).getNumToken() == 61) {
                            i++;
                            sentencia();
                            if (lexemas.get(i).getNumToken() == 10) {
                                i++;
                                retorna();
                                if (lexemas.get(i).getNumToken() == 58) {
                                    i++;
                                    if (lexemas.get(i).getNumToken() == 62) {
                                        i++;
                                    } else {

                                    }
                                } else {

                                }
                            } else {

                            }
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void retorna() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 72, 23, 76, 73, 74, 75, 77, 78, 48, 79, 80, 24, 46, 56, 65)) {
            //156
            operacion();
        } else {

        }
    }

    private void procedimientoNormal() {
        if (lexemas.get(i).getNumToken() == 73) {
            //157
            i++;
            if (lexemas.get(i).getNumToken() == 65) {
                i++;
                argumento();
                if (lexemas.get(i).getNumToken() == 66) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 61) {
                        i++;
                        sentencia();
                        if (lexemas.get(i).getNumToken() == 62) {
                            i++;
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void parametro() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 72, 23, 76, 74, 75)) {
            //158
            claseInstancia();
            parametro1();
        } else if (lexemas.get(i).getNumToken() == 66) {
            //159
            //Salir
        } else {

        }
    }

    private void parametro1() {
        switch (lexemas.get(i).getNumToken()) {
            case 57:
                //160
                i++;
                parametro();
                break;
            //161
            //Salir
            case 66:
                break;
            default:
                break;
        }
    }

    private void iteracion() {
        switch (lexemas.get(i).getNumToken()) {
            case 68:
                //162
                i++;
                break;
            case 69:
                //163
                i++;
                break;
            default:
                break;
        }
    }

    private void sentencia() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 41, 82, 83, 32, 35, 36, 37, 30, 44, 45, 46, 72, 23, 76, 74, 15)) {
            //164
            sentencia2();
            sentencia();
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 62, 28, 31, 27, 10)) {
//165
//Salir
        } else {

        }
    }

    private void sentencia2() {
        switch (lexemas.get(i).getNumToken()) {
            case 41:
                //166
                excepcion();
                break;
            case 82:
                //167
                comentario();
                break;
            case 83:
                //167
                comentario();
                break;
            case 32:
                //168
                sentenciaSi();
                break;
            case 35:
                //169
                sentenciaMientras();
                break;
            case 36:
                //170
                sentenciaHacerMientras();
                break;
            case 37:
                //171
                sentenciaPara();
                break;
            case 30:
                //172
                sentenciaCaso();
                break;
            case 44:
                //173
                atributoSentencia();
                break;
            case 45:
                //173
                atributoSentencia();
                break;
            case 46:
                //173
                atributoSentencia();
                break;
            case 72:
                //173
                atributoSentencia();
                break;
            case 23:
                //173
                atributoSentencia();
                break;
            case 76:
                //173
                atributoSentencia();
                break;
            case 74:
                //173
                atributoSentencia();
                break;
            case 15:
                //174
                operacionSistema();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {

                }
                break;
            default:
                break;
        }
    }

    private void sentenciaSi() {
        if (lexemas.get(i).getNumToken() == 32) {
            //175
            i++;
            if (lexemas.get(i).getNumToken() == 65) {
                i++;
                condicion();
                if (lexemas.get(i).getNumToken() == 66) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 61) {
                        i++;
                        sentencia();
                        if (lexemas.get(i).getNumToken() == 62) {
                            i++;
                            sentenciaSi1();
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void sentenciaSi1() {
        if (lexemas.get(i).getNumToken() == 33) {
            //176
            sentenciaSino();
        } else if (lexemas.get(i).getNumToken() == 34) {
            //177
            sentenciaSinoSi();
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 62, 28, 31, 27, 10, 41, 82, 83, 32, 35, 36, 37, 30, 44, 45, 46, 72, 23, 76, 74, 15)) {
//178
//Salir
        } else {

        }
    }

    private void sentenciaSino() {
        if (lexemas.get(i).getNumToken() == 33) {
            //179
            i++;
            if (lexemas.get(i).getNumToken() == 61) {
                i++;
                sentencia();
                if (lexemas.get(i).getNumToken() == 62) {
                    i++;
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void sentenciaSinoSi() {
        if (lexemas.get(i).getNumToken() == 34) {
            //180
            i++;
            if (lexemas.get(i).getNumToken() == 65) {
                i++;
                condicion();
                if (lexemas.get(i).getNumToken() == 66) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 61) {
                        i++;
                        sentencia();
                        if (lexemas.get(i).getNumToken() == 62) {
                            i++;
                            sentenciaSi1();
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void sentenciaPara() {
        if (lexemas.get(i).getNumToken() == 37) {
            //181
            i++;
            if (lexemas.get(i).getNumToken() == 65) {
                i++;
                inicializacionVariable();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                    condicion();
                    if (lexemas.get(i).getNumToken() == 58) {
                        i++;
                        if (lexemas.get(i).getNumToken() == 74) {
                            i++;
                            iteracion();
                            if (lexemas.get(i).getNumToken() == 66) {
                                i++;
                                if (lexemas.get(i).getNumToken() == 61) {
                                    i++;
                                    sentencia();
                                    if (lexemas.get(i).getNumToken() == 62) {
                                        i++;
                                    } else {

                                    }
                                } else {

                                }
                            } else {

                            }

                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void sentenciaMientras() {
        if (lexemas.get(i).getNumToken() == 36) {
            //183
            i++;
            if (lexemas.get(i).getNumToken() == 65) {
                i++;
                condicion();
                if (lexemas.get(i).getNumToken() == 66) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 61) {
                        i++;
                        sentencia();
                        if (lexemas.get(i).getNumToken() == 62) {
                            i++;
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void sentenciaHacerMientras() {
        if (lexemas.get(i).getNumToken() == 36) {
            //183
            i++;
            if (lexemas.get(i).getNumToken() == 61) {
                i++;
                sentencia();
                if (lexemas.get(i).getNumToken() == 62) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 35) {
                        i++;
                        if (lexemas.get(i).getNumToken() == 65) {
                            i++;
                            condicion();
                            if (lexemas.get(i).getNumToken() == 66) {
                                i++;
                                if (lexemas.get(i).getNumToken() == 58) {
                                    i++;
                                } else {

                                }
                            } else {

                            }
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void sentenciaCaso() {
        if (lexemas.get(i).getNumToken() == 30) {
            //184
            i++;
            if (lexemas.get(i).getNumToken() == 65) {
                i++;
                if (lexemas.get(i).getNumToken() == 74) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 66) {
                        i++;
                        if (lexemas.get(i).getNumToken() == 61) {
                            i++;
                            alternativa();
                            if (lexemas.get(i).getNumToken() == 62) {
                                i++;
                            } else {

                            }
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void alternativa() {
        if (lexemas.get(i).getNumToken() == 31) {
            //185
            i++;
            valor();
            if (lexemas.get(i).getNumToken() == 60) {
                i++;
                sentencia();
                terminar();
                alternativa1();
            } else {

            }
        } else {

        }
    }

    private void terminar() {
        if (lexemas.get(i).getNumToken() == 28) {
            //186
            i++;
            if (lexemas.get(i).getNumToken() == 58) {
                i++;
            } else {

            }
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 62, 31, 27)) {
//187
//Salir
        } else {

        }
    }

    private void alternativa1() {
        switch (lexemas.get(i).getNumToken()) {
            case 31:
                //188
                alternativa();
                break;
            case 27:
                //189
                alternativa2();
                break;
//190
//Salir
            case 62:
                break;
            default:
                break;
        }
    }

    private void alternativa2() {
        if (lexemas.get(i).getNumToken() == 27) {
            //191
            i++;
            if (lexemas.get(i).getNumToken() == 60) {
                i++;
                sentencia();
                if (lexemas.get(i).getNumToken() == 28) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 58) {
                        i++;
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void operacionSistema() {
        if (lexemas.get(i).getNumToken() == 15) {
            //192
            i++;
            if (lexemas.get(i).getNumToken() == 59) {
                i++;
                operacionSistema1();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void operacionSistema1() {
        switch (lexemas.get(i).getNumToken()) {
            case 13:
                //193
                lectura();
                break;
            case 14:
                //194
                escritura();
                break;
            case 29:
                //195
                salida();
                break;
            default:
                break;
        }
    }

    private void lectura() {
        if (lexemas.get(i).getNumToken() == 13) {
            //196
            i++;
            if (lexemas.get(i).getNumToken() == 59) {
                i++;
                if (lexemas.get(i).getNumToken() == 25) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 65) {
                        i++;
                        if (lexemas.get(i).getNumToken() == 66) {
                            i++;
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void escritura() {
        if (lexemas.get(i).getNumToken() == 14) {
            //197
            i++;
            if (lexemas.get(i).getNumToken() == 59) {
                i++;
                if (lexemas.get(i).getNumToken() == 26) {
                    i++;
                    linea();
                    if (lexemas.get(i).getNumToken() == 65) {
                        i++;
                        operacion();
                        if (lexemas.get(i).getNumToken() == 66) {
                            i++;
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void linea() {
        switch (lexemas.get(i).getNumToken()) {
            case 59:
                //198
                i++;
                if (lexemas.get(i).getNumToken() == 16) {
                    i++;
                } else {

                }
                break;
//199
//Salir
            case 65:
                break;
            default:
                break;
        }
    }

    private void salida() {
        if (lexemas.get(i).getNumToken() == 29) {
            //200
            i++;
            if (lexemas.get(i).getNumToken() == 65) {
                i++;
                if (lexemas.get(i).getNumToken() == 66) {
                    i++;
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void excepcion() {
        if (lexemas.get(i).getNumToken() == 41) {
            //201
            i++;
            if (lexemas.get(i).getNumToken() == 61) {
                i++;
                sentencia();
                if (lexemas.get(i).getNumToken() == 62) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 42) {
                        i++;
                        if (lexemas.get(i).getNumToken() == 65) {
                            i++;
                            if (lexemas.get(i).getNumToken() == 72) {
                                i++;
                                if (lexemas.get(i).getNumToken() == 76) {
                                    i++;
                                    if (lexemas.get(i).getNumToken() == 66) {
                                        i++;
                                        if (lexemas.get(i).getNumToken() == 61) {
                                            i++;
                                            sentencia();
                                            if (lexemas.get(i).getNumToken() == 62) {
                                                i++;
                                                captarExtendido();
                                                finalmente();
                                            } else {

                                            }
                                        } else {

                                        }
                                    } else {

                                    }
                                } else {

                                }
                            } else {

                            }
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void captarExtendido() {
        if (lexemas.get(i).getNumToken() == 42) {
            //202
            i++;
            if (lexemas.get(i).getNumToken() == 65) {
                i++;
                if (lexemas.get(i).getNumToken() == 72) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 76) {
                        i++;
                        if (lexemas.get(i).getNumToken() == 66) {
                            i++;
                            if (lexemas.get(i).getNumToken() == 61) {
                                i++;
                                sentencia();
                                if (lexemas.get(i).getNumToken() == 62) {
                                    i++;
                                    captarExtendido();
                                } else {

                                }
                            } else {

                            }
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 62, 28, 31, 27, 10, 41, 82, 83, 32, 35, 36, 37, 30, 44, 45, 46, 72, 23, 76, 74, 15, 43)) {
//203
//Salir
        } else {

        }
    }

    private void finalmente() {
        if (lexemas.get(i).getNumToken() == 43) {
            //204
            i++;
            if (lexemas.get(i).getNumToken() == 61) {
                i++;
                sentencia();
                if (lexemas.get(i).getNumToken() == 62) {
                    i++;
                } else {

                }
            } else {

            }
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 62, 28, 31, 27, 10, 41, 82, 83, 32, 35, 36, 37, 30, 44, 45, 46, 72, 23, 76, 74, 15)) {
//205
//Salir
        } else {

        }
    }

    private void concatenacion() {
        switch (lexemas.get(i).getNumToken()) {
            case 52:
                //206
                i++;
                operacion();
                break;
//207
//Salir
            case 60:
                break;
            default:
                break;
        }
    }

    private void operacionAritmetica() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 72, 23, 76, 73, 74, 75)) {
            //211
            claseInstancia();
            operacionAritmetica2();
        } else if (lexemas.get(i).getNumToken() == 77) {
            //208
            numero();
            operacionAritmetica2();
        } else if (lexemas.get(i).getNumToken() == 78) {
            //208
            numero();
            operacionAritmetica2();
        } else if (lexemas.get(i).getNumToken() == 48) {
            //209
            i++;
            operacionAritmetica();
        } else if (lexemas.get(i).getNumToken() == 52) {
            //210
            i++;
            operacionAritmetica();
        } else if (lexemas.get(i).getNumToken() == 65) {
            //212
            i++;
            operacionAritmetica();
            if (lexemas.get(i).getNumToken() == 66) {
                i++;
                operacionAritmetica2();
            } else {

            }
        } else {

        }
    }

    private void operacionAritmetica2() {
        if (lexemas.get(i).getNumToken() == 49) {
            //213
            i++;
            operacionAritmetica();
        } else if (lexemas.get(i).getNumToken() == 48) {
            //257
            i++;
            operacionAritmetica();
        } else if (lexemas.get(i).getNumToken() == 52) {
            //258
            i++;
            operacionAritmetica();
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 50, 51, 55, 66, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//259
//Salir
        } else {

        }
    }

    private void condicion() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 72, 23, 76, 73, 74, 75)) {
            //214
            claseInstancia();
            operadorCondicion();
        } else if (lexemas.get(i).getNumToken() == 77) {
            //215
            numero();
            condicionMat();
            condicionLog();
        } else if (lexemas.get(i).getNumToken() == 78) {
            //215
            numero();
            condicionMat();
            condicionLog();
        } else if (lexemas.get(i).getNumToken() == 48) {
            //216
            i++;
            operacionAritmetica();
            condicionRelComp();
            condicionLog();
        } else if (lexemas.get(i).getNumToken() == 79) {
            //217
            i++;
            condicionMat();
            condicionLog();
        } else if (lexemas.get(i).getNumToken() == 80) {
            //218
            i++;
            condicionRelComp();
        } else if (lexemas.get(i).getNumToken() == 24) {
            //219
            i++;
            condicionComp();
        } else if (lexemas.get(i).getNumToken() == 46) {
            //220
            i++;
            condicionLog();
        } else if (lexemas.get(i).getNumToken() == 56) {
            //221
            i++;
            condicion();
        } else if (lexemas.get(i).getNumToken() == 65) {
            //222
            i++;
            condicion();
            if (lexemas.get(i).getNumToken() == 66) {
                i++;
                operadorCondicion();
            } else {

            }
        } else {

        }
    }

    private void operadorCondicion() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 49, 52, 50, 51, 55)) {
            //223
            condicionMat();
            condicionLog();
        } else if (lexemas.get(i).getNumToken() == 55) {
            //224
            condicionLog();
        } else {

        }
    }

    private void condicionMat() {
        switch (lexemas.get(i).getNumToken()) {
            case 49:
                //225
                i++;
                operacionAritmetica();
                condicionRelComp();
                break;
            case 52:
                //226
                i++;
                condicion();
                condicionRelComp();
                break;
            case 50:
                //227
                condicionRelComp();
                break;
            case 51:
                //227
                condicionRelComp();
                break;
            case 48:
                //227
                condicionRelComp();
                break;
            default:
                break;
        }
    }

    private void condicionRelComp() {
        switch (lexemas.get(i).getNumToken()) {
            case 50:
                //228
                i++;
                condicion();
                break;
            case 51:
                //229
                i++;
                condicion();
                break;
            default:
                break;
        }
    }

    private void condicionComp() {
        if (lexemas.get(i).getNumToken() == 51) {
            //230
            i++;
            condicion();
        } else {

        }
    }

    private void condicionLog() {
        if (lexemas.get(i).getNumToken() == 55) {
            //231
            i++;
            condicion();
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 50, 51, 55, 66, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//232
//Salir
        } else {

        }
    }

    private void operacion() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 72, 23, 76, 73, 74, 75)) {
            //233
            claseInstancia();
            operador();
        } else if (lexemas.get(i).getNumToken() == 77) {
            //234
            numero();
            operacionMat();
        } else if (lexemas.get(i).getNumToken() == 78) {
            //234
            numero();
            operacionMat();

        } else if (lexemas.get(i).getNumToken() == 48) {
            //235
            i++;
            operacionAritmetica();
            operacionRelComp();
        } else if (lexemas.get(i).getNumToken() == 79) {
            //236
            i++;
            operacionMat();
        } else if (lexemas.get(i).getNumToken() == 80) {
            //237
            i++;
            operacionRelComp();
        } else if (lexemas.get(i).getNumToken() == 24) {
            //238
            i++;
            operacionComp();
        } else if (lexemas.get(i).getNumToken() == 46) {
            //239
            i++;
            operacionLog();
        } else if (lexemas.get(i).getNumToken() == 56) {
            //240
            i++;
            condicion();
        } else if (lexemas.get(i).getNumToken() == 65) {
            //241
            i++;
            operacion();
            if (lexemas.get(i).getNumToken() == 66) {
                i++;
                operador();
            } else {

            }
        } else {

        }
    }

    private void operador() {
        switch (lexemas.get(i).getNumToken()) {
            case 49:
                //242
                operacionMat();
                operacionRelComp();
                operacionLog();
                break;
            case 52:
                //242
                operacionMat();
                operacionRelComp();
                operacionLog();
                break;
            case 50:
                //243
                operacionComp();
                operacionLog();
                break;
            case 51:
                //243
                operacionComp();
                operacionLog();
                break;
            case 55:
                //244
                operacionLog();
                break;
            case 48:
                //242
                operacionMat();
                operacionRelComp();
                operacionLog();
                break;
            default:
                break;
        }
    }

    private void operacionMat() {
        if (lexemas.get(i).getNumToken() == 49) {
            //246
            i++;
            operacionAritmetica();
        } else if (lexemas.get(i).getNumToken() == 48) {
            //247
            i++;
            operacionAritmetica();
        } else if (lexemas.get(i).getNumToken() == 50) {
            //248
            operacionRelComp();
        } else if (lexemas.get(i).getNumToken() == 51) {
            //248
            operacionRelComp();
        } else if (lexemas.get(i).getNumToken() == 52) {
            //245
            i++;
            operacion();
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 50, 51, 55, 66, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//260
//Salir
        } else {

        }
    }

    private void operacionRelComp() {
        if (lexemas.get(i).getNumToken() == 50) {
            //249
            i++;
            condicion();
            operacionLog();
        } else if (lexemas.get(i).getNumToken() == 51) {
            //250
            i++;
            condicion();
            operacionLog();
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 50, 51, 55, 66, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//251
//Salir
        } else {

        }
    }

    private void operacionComp() {
        if (lexemas.get(i).getNumToken() == 51) {
            //252
            i++;
            condicion();
            operacionLog();
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 50, 51, 55, 66, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//253
//Salir
        } else {

        }
    }

    private void operacionLog() {
        if (lexemas.get(i).getNumToken() == 55) {
            //254
            i++;
            condicion();
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 50, 51, 55, 66, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//255
//Salir
        } else {

        }
    }

    private static boolean esSiguiente(int numToken, Object... jts) {
        boolean b = false;
        ArrayList<Integer> posibilidades = new ArrayList<>();

        for (Object x : jts) {
            if (x instanceof Integer) {
                posibilidades.add((Integer) x);
            }
        }
        for (int i = 0; i < posibilidades.size(); i++) {
            if (numToken == posibilidades.get(i)) {
                b = true;
            }
        }
        return b;

    }

    /*
    Eliminar todo lo que haya despues de algo inesperado hasta que se encuentre un punto y coma, una llave que cierra, y si viene una llave que abre, abrir una pila de llaves que abren y cierran por pares hasta encontrar su llave que cierra.
     */
    private void puntoRecuperacion1() {
        //INSTRUCCIONES GLOBALES
        switch (lexemas.get(i).getNumToken()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    private void puntoRecuperacion2() {
        //INSTRUCCIONES DENTRO DE BLOQUES
        switch (lexemas.get(i).getNumToken()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    public void mensajeError(String id, String desc, int lineaCod, String tipo) {
        Errores e = new Errores();
        e.setIdError(id);
        e.setDescripcion(desc);
        e.setLineaCodigo(lineaCod);
        e.setTipo(tipo);
        EditorPlanetProgramming.lstError.add(e);
    }

    private boolean tokenNoPermitido(int renglon) {
  
        return false;
    }

}
