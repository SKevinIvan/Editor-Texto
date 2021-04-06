/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import planetprogrammig.EditorPlanetProgramming;
import recursos.Mensaje;

/**
 *
 * @author por_s
 */
public class RutinasSemanticas {

    private ArrayList<Lexema> lexemas;
    private int i;

    public int getI() {
        return i;
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
    }

    public void analisisSemantico() {
        if (lexemas.get(i).getNumToken() == 70) {
            proyecto();
        } else {
            Errores e = new Errores();
            e.setIdError("E18");
            e.setDescripcion("Se esperaba el nombre del proyecto");
            e.setLineaCodigo(lexemas.get(i).getRenglon());
            e.setTipo("ERROR SINTACTICO");
            EditorPlanetProgramming.lstError.add(e);
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
                    Errores e = new Errores();
                    e.setIdError("E18");
                    e.setDescripcion("Se esperaba el nombre del proyecto");
                    e.setLineaCodigo(lexemas.get(i).getRenglon());
                    e.setTipo("ERROR SINTACTICO");
                    EditorPlanetProgramming.lstError.add(e);
                }
            } else {
                Errores e = new Errores();
                e.setIdError("E18");
                e.setDescripcion("Se esperaba el nombre del proyecto");
                e.setLineaCodigo(lexemas.get(i).getRenglon());
                e.setTipo("ERROR SINTACTICO");
                EditorPlanetProgramming.lstError.add(e);

            }
        } else {
            Errores e = new Errores();
            e.setIdError("E18");
            e.setDescripcion("Se esperaba el nombre del proyecto");
            e.setLineaCodigo(lexemas.get(i).getRenglon());
            e.setTipo("ERROR SINTACTICO");
            EditorPlanetProgramming.lstError.add(e);
        }
    }

    private void comentariosIniciales() {
        if (lexemas.get(i).getNumToken() == 82) {
            comentario();
            paquete();
            comentariosIniciales();
        } else if (lexemas.get(i).getNumToken() == 83) {
            comentario();
            paquete();
            comentariosIniciales();
        } else if (lexemas.get(i).getNumToken() == 5) {
            paquete();
            comentariosIniciales();
        } else if (lexemas.get(i).getNumToken() == 62) {
//Salir
        } else {
            Errores e = new Errores();
            e.setIdError("E18");
            e.setDescripcion("Se esperaba el nombre del proyecto");
            e.setLineaCodigo(lexemas.get(i).getRenglon());
            e.setTipo("ERROR SINTACTICO");
            EditorPlanetProgramming.lstError.add(e);
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

                    }
                } else {

                }

            } else {

            }
        } else {

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

                    }
                } else {

                }

            } else {

            }

        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 5, 82, 83, 6, 1, 2, 3, 7, 20, 17, 4)) {
            clase();
        } else {

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
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 82, 83, 6, 1, 2, 3, 7, 20, 17, 4)) {
            modificadorAcceso();
            estatico();
            polimorfismo();
            clase();
        } else if (lexemas.get(i).getNumToken() == 62) {
            //Salir
        } else {

        }

    }

    private void comentario() {
        if (lexemas.get(i).getNumToken() == 82) {
            i++;
        } else if (lexemas.get(i).getNumToken() == 83) {
            i++;
        } else {

        }
    }

    private void libreria() {
        if (lexemas.get(i).getNumToken() == 6) {
            i++;
            importarLibreria();
        } else {

        }
    }

    private void importarLibreria() {
        if (lexemas.get(i).getNumToken() == 71) {
            importarLibreria2();
            if (lexemas.get(i).getNumToken() == 81) {
                i++;
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {

                }
            } else {

            }
        } else if (lexemas.get(i).getNumToken() == 81) {
            importarLibreria2();
            if (lexemas.get(i).getNumToken() == 81) {
                i++;
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void importarLibreria2() {
        if (lexemas.get(i).getNumToken() == 71) {
            i++;
            if(lexemas.get(i).getNumToken() == 59){
                i++;
                importarLibreria2();
            }else{
                
            }
        } else if (lexemas.get(i).getNumToken() == 81) {
            //Salir
        } else {

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
        if (lexemas.get(i).getNumToken() == 20) {
            //24
        } else if (lexemas.get(i).getNumToken() == 17) {
            //25
        } else if (lexemas.get(i).getNumToken() == 4) {
            //26
        } else {

        }

    }

    private void extension() {
        if (lexemas.get(i).getNumToken() == 18) {
            //27
        } else if (lexemas.get(i).getNumToken() == 19) {
            //28
        } else if (lexemas.get(i).getNumToken() == 61) {
            //29
        } else {

        }

    }

    private void herencia() {
        if (lexemas.get(i).getNumToken() == 18) {
            //30
        } else {

        }
    }

    private void implementacion() {
        if (lexemas.get(i).getNumToken() == 19) {
            //31
        } else if (lexemas.get(i).getNumToken() == 61) {
            //32
        } else {

        }
    }

    private void implementar() {
        if (lexemas.get(i).getNumToken() == 19) {
            //33
        } else {

        }
    }

    private void implementar1() {
        if (lexemas.get(i).getNumToken() == 57) {
            //34
        } else {

        }
    }

    private void implementar2() {
        if (lexemas.get(i).getNumToken() == 57) {
            //35
        } else if (lexemas.get(i).getNumToken() == 61) {
            //36
        } else {

        }
    }

    private void instruccion() {
        if (lexemas.get(i).getNumToken() == 82) {
            //37
        } else if (lexemas.get(i).getNumToken() == 83) {
            //37
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40)) {
//38

        } else if (lexemas.get(i).getNumToken() == 62) {
            //39
        } else {

        }
    }

    private void controlAcceso() {
        if (lexemas.get(i).getNumToken() == 7) {
            //40
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 44, 45, 46, 72, 12, 11)) {
//41
        } else if (lexemas.get(i).getNumToken() == 40) {
            //42
        } else {

        }
    }

    private void encapsulamiento() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 44, 45, 46, 72)) {
//43
        } else if (lexemas.get(i).getNumToken() == 12) {
            //44
        } else if (lexemas.get(i).getNumToken() == 11) {
            //44
        } else {

        }
    }

    private void atributo() {
        if (lexemas.get(i).getNumToken() == 44) {
            //45
        } else if (lexemas.get(i).getNumToken() == 45) {
            //45
        } else if (lexemas.get(i).getNumToken() == 46) {
            //45
        } else if (lexemas.get(i).getNumToken() == 72) {
            //46
        } else {

        }
    }

    private void declaracionAtributo() {
        if (lexemas.get(i).getNumToken() == 44) {
            //47
        } else if (lexemas.get(i).getNumToken() == 45) {
            //47
        } else if (lexemas.get(i).getNumToken() == 46) {
            //47
        } else {

        }
    }

    private void conjuntoAtributo() {
        if (lexemas.get(i).getNumToken() == 57) {
            //48
        } else if (lexemas.get(i).getNumToken() == 58) {
            //49
        } else {

        }

    }

    private void asignacionAtributo() {
        if (lexemas.get(i).getNumToken() == 74) {
            //50
        } else if (lexemas.get(i).getNumToken() == 75) {
            //51
        } else {

        }
    }

    private void variable() {
        if (lexemas.get(i).getNumToken() == 74) {
            //52
        } else {

        }
    }

    private void constante() {
        if (lexemas.get(i).getNumToken() == 75) {
            //53
        } else {

        }
    }

    private void inicializacionVariable() {
        if (lexemas.get(i).getNumToken() == 63) {
            //54
        } else if (lexemas.get(i).getNumToken() == 54) {
            //55
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//56
        } else {

        }
    }

    private void declaracionArregloVariable() {
        if (lexemas.get(i).getNumToken() == 54) {
            //57
        } else {

        }

    }

    private void declaracionArregloVariable2() {
        if (lexemas.get(i).getNumToken() == 54) {
            //58
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//59
        } else {

        }
    }

    private void inicializacionConstante() {
        if (lexemas.get(i).getNumToken() == 54) {
            //60
        } else if (lexemas.get(i).getNumToken() == 63) {
            //61
        } else {

        }
    }

    private void declaracionArregloConstante() {
        if (lexemas.get(i).getNumToken() == 63) {
            //62
        } else {

        }
    }

    private void declaracionArregloConstante2() {
        if (lexemas.get(i).getNumToken() == 63) {
            //63
        } else if (lexemas.get(i).getNumToken() == 54) {
            //64
        } else {

        }
    }

    private void arreglo1() {
        if (lexemas.get(i).getNumToken() == 63) {
            //65
        } else {
        }
    }

    private void arreglo2() {
        if (lexemas.get(i).getNumToken() == 63) {
            //66
        } else {

        }
    }

    private void inicializacionAtributo() {
        if (lexemas.get(i).getNumToken() == 54) {
            //67
        } else {

        }
    }

    private void declaracionObjeto() {
        if (lexemas.get(i).getNumToken() == 72) {
            //68
        } else {

        }
    }

    private void conjuntoObjetos() {
        if (lexemas.get(i).getNumToken() == 57) {
            //69
        } else if (lexemas.get(i).getNumToken() == 58) {
            //70
        } else {

        }
    }

    private void inicializacionObjeto() {
        if (lexemas.get(i).getNumToken() == 54) {
            //71
        } else if (lexemas.get(i).getNumToken() == 63) {
            //73
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//72
        } else {

        }
    }

    private void igualacionObjeto() {
        if (lexemas.get(i).getNumToken() == 24) {
            //74
        } else if (lexemas.get(i).getNumToken() == 76) {
            //75
        } else if (lexemas.get(i).getNumToken() == 22) {
            //76
        } else {
        }
    }

    private void arregloObjeto() {
        if (lexemas.get(i).getNumToken() == 63) {
            //78
        } else if (lexemas.get(i).getNumToken() == 54) {
            //79
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//77
        } else {

        }
    }

    private void arregloObjeto2() {
        if (lexemas.get(i).getNumToken() == 63) {
            //80
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57, 54)) {
//81
        } else {

        }
    }

    private void asignacionObjeto() {
        if (lexemas.get(i).getNumToken() == 54) {
            //82
        } else if (lexemas.get(i).getNumToken() == 63) {
            //82
        } else {

        }
    }

    private void asignacionObjeto2() {
        if (lexemas.get(i).getNumToken() == 54) {
            //83
        } else if (lexemas.get(i).getNumToken() == 63) {
            //84
        } else {

        }
    }

    private void asignacionObjeto3() {
        if (lexemas.get(i).getNumToken() == 24) {
            //85
        } else if (lexemas.get(i).getNumToken() == 22) {
            //86
        } else if (lexemas.get(i).getNumToken() == 76) {
            //87
        } else {
        }
    }

    private void arregloVar1() {
        if (lexemas.get(i).getNumToken() == 63) {
            //88
        } else {

        }
    }

    private void arregloVar1Aux() {
        if (lexemas.get(i).getNumToken() == 63) {
            //89
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57, 54)) {
//90
        } else {

        }
    }

    private void arregloVar2() {
        if (lexemas.get(i).getNumToken() == 63) {
            //91
        } else {

        }
    }

    private void arregloVar3() {
        if (lexemas.get(i).getNumToken() == 63) {
            //92
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57, 54)) {
//93
        } else {

        }
    }

    private void posicion() {
        if (lexemas.get(i).getNumToken() == 77) {
            //94
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 72, 23, 76, 73, 74, 75)) {
//95
        } else {

        }
    }

    private void posicionAux() {
        if (lexemas.get(i).getNumToken() == 94) {
            //97
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 77, 72, 23, 76, 73, 74, 75)) {
//96
        } else {

        }
    }

    private void arregloVar4() {
        if (lexemas.get(i).getNumToken() == 63) {
            //98
        } else if (lexemas.get(i).getNumToken() == 54) {
            //99
        } else if (lexemas.get(i).getNumToken() == 53) {
            //99
        } else {

        }

    }

    private void claseInstancia() {
        if (lexemas.get(i).getNumToken() == 72) {
            //100
        } else if (lexemas.get(i).getNumToken() == 23) {
            //101
        } else if (lexemas.get(i).getNumToken() == 76) {
            //102
        } else if (lexemas.get(i).getNumToken() == 73) {
            //103
        } else if (lexemas.get(i).getNumToken() == 74) {
            //104
        } else if (lexemas.get(i).getNumToken() == 75) {
            //105
        } else {

        }
    }

    private void claseInstancia2() {
        if (lexemas.get(i).getNumToken() == 73) {
            //106
        } else if (lexemas.get(i).getNumToken() == 74) {
            //107
        } else if (lexemas.get(i).getNumToken() == 75) {
            //108
        } else if (lexemas.get(i).getNumToken() == 76) {
            //109
        } else {

        }
    }

    private void claseInstancia22() {
        if (lexemas.get(i).getNumToken() == 59) {
            //110
        } else if (lexemas.get(i).getNumToken() == 63) {
            //111
        } else {

        }
    }

    private void arregloVar22() {
        if (lexemas.get(i).getNumToken() == 63) {
            //112
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57, 60, 52, 66, 64, 49, 52, 50, 51, 55, 48, 74, 57)) {
//113
        } else {

        }
    }

    private void atributoSentencia() {
        if (lexemas.get(i).getNumToken() == 44) {
            //114
        } else if (lexemas.get(i).getNumToken() == 45) {
            //114
        } else if (lexemas.get(i).getNumToken() == 46) {
            //114
        } else if (lexemas.get(i).getNumToken() == 72) {
            //115
        } else if (lexemas.get(i).getNumToken() == 23) {
            //116
        } else if (lexemas.get(i).getNumToken() == 76) {
            //116
        } else if (lexemas.get(i).getNumToken() == 74) {
            //116
        } else if (lexemas.get(i).getNumToken() == 73) {
            //116
        } else {

        }
    }

    private void refDeclaracionObjeto() {
        if (lexemas.get(i).getNumToken() == 59) {
            //117
        } else if (lexemas.get(i).getNumToken() == 76) {
            //118
        } else {

        }
    }

    private void referenciaAtributo() {
        if (lexemas.get(i).getNumToken() == 23) {
            //119
        } else if (lexemas.get(i).getNumToken() == 76) {
            //120
        } else if (lexemas.get(i).getNumToken() == 74) {
            //121
        } else if (lexemas.get(i).getNumToken() == 73) {
            //265
        } else {

        }
    }

    private void referenciaAtributo2() {
        if (lexemas.get(i).getNumToken() == 74) {
            //122
        } else if (lexemas.get(i).getNumToken() == 76) {
            //123
        } else if (lexemas.get(i).getNumToken() == 73) {
            //266
        } else {

        }
    }

    private void referenciaAtributo22() {
        if (lexemas.get(i).getNumToken() == 59) {
            //124
        } else if (lexemas.get(i).getNumToken() == 54) {
            //125
        } else if (lexemas.get(i).getNumToken() == 63) {
            //125
        } else {

        }
    }

    private void arregloVar2Obj() {
        if (lexemas.get(i).getNumToken() == 54) {
            //126
        } else if (lexemas.get(i).getNumToken() == 63) {
            //126
        } else {

        }
    }

    private void asignacionAtributoVar() {
        if (lexemas.get(i).getNumToken() == 54) {
            //127
        } else if (lexemas.get(i).getNumToken() == 53) {
            //128
        } else {

        }
    }

    private void asignacionAtributoVar2() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 72, 23, 76, 73, 74, 75, 77, 78, 48, 79, 80, 24, 46, 65)) {
            //129
        } else if (lexemas.get(i).getNumToken() == 68) {
            //130
        } else if (lexemas.get(i).getNumToken() == 69) {
            //130
        } else if (lexemas.get(i).getNumToken() == 15) {
            //131
        } else if (lexemas.get(i).getNumToken() == 22) {
            //267
        } else {

        }
    }

    private void tipoDato() {
        if (lexemas.get(i).getNumToken() == 44) {
            //132
        } else if (lexemas.get(i).getNumToken() == 45) {
            //133
        } else if (lexemas.get(i).getNumToken() == 46) {
            //134
        } else {

        }
    }

    private void valor() {
        if (lexemas.get(i).getNumToken() == 77) {
            //135
        } else if (lexemas.get(i).getNumToken() == 78) {
            //135
        } else if (lexemas.get(i).getNumToken() == 80) {
            //136
        } else if (lexemas.get(i).getNumToken() == 79) {
            //137
        } else if (lexemas.get(i).getNumToken() == 38) {
            //138
        } else if (lexemas.get(i).getNumToken() == 39) {
            //138
        } else if (lexemas.get(i).getNumToken() == 24) {
            //139
        } else {

        }
    }

    private void numero() {
        if (lexemas.get(i).getNumToken() == 77) {
            //140
        } else if (lexemas.get(i).getNumToken() == 78) {
            //141
        } else {

        }

    }

    private void boleano() {
        if (lexemas.get(i).getNumToken() == 38) {
            //142
        } else if (lexemas.get(i).getNumToken() == 39) {
            //143
        } else {

        }
    }

    private void constructor() {
        if (lexemas.get(i).getNumToken() == 40) {
            //144
        } else {

        }
    }

    private void argumento() {
        if (lexemas.get(i).getNumToken() == 44) {
            //145
        } else if (lexemas.get(i).getNumToken() == 45) {
            //145
        } else if (lexemas.get(i).getNumToken() == 46) {
            //145
        } else if (lexemas.get(i).getNumToken() == 72) {
            //259
        } else if (lexemas.get(i).getNumToken() == 66) {
            //146
        } else {

        }
    }

    private void argumento1() {
        if (lexemas.get(i).getNumToken() == 57) {
            //147
        } else if (lexemas.get(i).getNumToken() == 66) {
            //148
        } else {

        }
    }

    private void metodo() {
        if (lexemas.get(i).getNumToken() == 12) {
            //149
        } else if (lexemas.get(i).getNumToken() == 11) {
            //150
        } else {

        }
    }

    private void procedimiento() {
        if (lexemas.get(i).getNumToken() == 11) {
            //151
        } else {

        }
    }

    private void procedimiento1() {
        if (lexemas.get(i).getNumToken() == 73) {
            //152
        } else if (lexemas.get(i).getNumToken() == 21) {
            //153
        } else {

        }
    }

    private void procedimientoPrincipal() {
        if (lexemas.get(i).getNumToken() == 21) {
            //154
        } else {

        }
    }

    private void funcion() {
        if (lexemas.get(i).getNumToken() == 12) {
            //155
        } else {

        }
    }

    private void retorna() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 72, 23, 76, 73, 74, 75, 77, 78, 48, 79, 80, 24, 46, 56, 65)) {
            //156
        } else {

        }
    }

    private void procedimientoNormal() {
        if (lexemas.get(i).getNumToken() == 73) {
            //157
        } else {

        }
    }

    private void parametro() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 72, 23, 76, 74, 75)) {
            //158
        } else if (lexemas.get(i).getNumToken() == 66) {
            //159
        } else {

        }
    }

    private void parametro1() {
        if (lexemas.get(i).getNumToken() == 57) {
            //160
        } else if (lexemas.get(i).getNumToken() == 66) {
            //161
        } else {

        }
    }

    private void iteracion() {
        if (lexemas.get(i).getNumToken() == 68) {
            //162
        } else if (lexemas.get(i).getNumToken() == 69) {
            //163
        } else {

        }
    }

    private void sentencia() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 41, 82, 83, 32, 35, 36, 37, 30, 44, 45, 46, 72, 23, 76, 74, 15)) {
            //164
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 62, 28, 31, 27, 10)) {
//165
        } else {

        }
    }

    private void sentencia2() {
        if (lexemas.get(i).getNumToken() == 41) {
            //166
        } else if (lexemas.get(i).getNumToken() == 82) {
            //167
        } else if (lexemas.get(i).getNumToken() == 83) {
            //167

        } else if (lexemas.get(i).getNumToken() == 32) {
            //168
        } else if (lexemas.get(i).getNumToken() == 35) {
            //169
        } else if (lexemas.get(i).getNumToken() == 36) {
            //170
        } else if (lexemas.get(i).getNumToken() == 37) {
            //171
        } else if (lexemas.get(i).getNumToken() == 30) {
            //172
        } else if (lexemas.get(i).getNumToken() == 44) {
            //173
        } else if (lexemas.get(i).getNumToken() == 45) {
            //173
        } else if (lexemas.get(i).getNumToken() == 46) {
            //173
        } else if (lexemas.get(i).getNumToken() == 72) {
            //173
        } else if (lexemas.get(i).getNumToken() == 23) {
            //173
        } else if (lexemas.get(i).getNumToken() == 76) {
            //173
        } else if (lexemas.get(i).getNumToken() == 74) {
            //173
        } else if (lexemas.get(i).getNumToken() == 15) {
            //174

        } else {

        }
    }

    private void sentenciaSi() {
        if (lexemas.get(i).getNumToken() == 32) {
            //175
        } else {

        }
    }

    private void sentenciaSi1() {
        if (lexemas.get(i).getNumToken() == 33) {
            //176
        } else if (lexemas.get(i).getNumToken() == 34) {
            //177
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 62, 28, 31, 27, 10, 41, 82, 83, 32, 35, 36, 37, 30, 44, 45, 46, 72, 23, 76, 74, 15)) {
//178
        } else {

        }
    }

    private void sentenciaSino() {
        if (lexemas.get(i).getNumToken() == 33) {
            //179
        } else {

        }
    }

    private void sentenciaSinoSi() {
        if (lexemas.get(i).getNumToken() == 34) {
            //180
        } else {

        }
    }

    private void sentenciaPara() {
        if (lexemas.get(i).getNumToken() == 37) {
            //181
        } else {

        }
    }

    private void sentenciaMientras() {
        if (lexemas.get(i).getNumToken() == 36) {
            //183
        } else {

        }
    }

    private void sentenciaHacerMientras() {
        if (lexemas.get(i).getNumToken() == 36) {
            //183
        } else {

        }
    }

    private void sentenciaCaso() {
        if (lexemas.get(i).getNumToken() == 30) {
            //184
        } else {

        }
    }

    private void alternativa() {
        if (lexemas.get(i).getNumToken() == 31) {
            //185
        } else {

        }
    }

    private void terminar() {
        if (lexemas.get(i).getNumToken() == 28) {
            //186
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 62, 31, 27)) {
//187
        } else {

        }
    }

    private void alternativa1() {
        if (lexemas.get(i).getNumToken() == 31) {
            //188
        } else if (lexemas.get(i).getNumToken() == 27) {
            //189
        } else if (lexemas.get(i).getNumToken() == 62) {
//190
        } else {

        }
    }

    private void alternativa2() {
        if (lexemas.get(i).getNumToken() == 27) {
            //191
        } else {

        }
    }

    private void operacionSistema() {
        if (lexemas.get(i).getNumToken() == 15) {
            //192
        } else {

        }
    }

    private void operacionSistema1() {
        if (lexemas.get(i).getNumToken() == 13) {
            //193
        } else if (lexemas.get(i).getNumToken() == 14) {
            //194
        } else if (lexemas.get(i).getNumToken() == 29) {
            //195
        } else {

        }
    }

    private void lectura() {
        if (lexemas.get(i).getNumToken() == 13) {
            //196
        } else {

        }
    }

    private void escritura() {
        if (lexemas.get(i).getNumToken() == 14) {
            //197
        } else {

        }
    }

    private void linea() {
        if (lexemas.get(i).getNumToken() == 59) {
            //198
        } else if (lexemas.get(i).getNumToken() == 65) {
//199
        } else {

        }
    }

    private void salida() {
        if (lexemas.get(i).getNumToken() == 29) {
            //200
        } else {

        }
    }

    private void excepcion() {
        if (lexemas.get(i).getNumToken() == 41) {
            //201
        } else {

        }
    }

    private void captarExtendido() {
        if (lexemas.get(i).getNumToken() == 42) {
            //202
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 62, 28, 31, 27, 10, 41, 82, 83, 32, 35, 36, 37, 30, 44, 45, 46, 72, 23, 76, 74, 15, 43)) {
//203
        } else {

        }
    }

    private void finalmente() {
        if (lexemas.get(i).getNumToken() == 43) {
            //204
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 62, 28, 31, 27, 10, 41, 82, 83, 32, 35, 36, 37, 30, 44, 45, 46, 72, 23, 76, 74, 15)) {
//205
        } else {

        }
    }

    private void concatenacion() {
        if (lexemas.get(i).getNumToken() == 52) {
            //206
        } else if (lexemas.get(i).getNumToken() == 60) {
//207
        } else {

        }
    }

    private void operacionAritmetica() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 72, 23, 76, 73, 74, 75)) {
            //211
        } else if (lexemas.get(i).getNumToken() == 77) {
            //208
        } else if (lexemas.get(i).getNumToken() == 78) {
            //208
        } else if (lexemas.get(i).getNumToken() == 48) {
            //209
        } else if (lexemas.get(i).getNumToken() == 52) {
            //210
        } else if (lexemas.get(i).getNumToken() == 65) {
            //212
        } else {

        }
    }

    private void operacionAritmetica2() {
        if (lexemas.get(i).getNumToken() == 49) {
            //213
        } else if (lexemas.get(i).getNumToken() == 48) {
            //257
        } else if (lexemas.get(i).getNumToken() == 52) {
            //258
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 50, 51, 55, 66, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//259
        } else {

        }
    }

    private void condicion() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 72, 23, 76, 73, 74, 75)) {
            //214
        } else if (lexemas.get(i).getNumToken() == 77) {
            //215
        } else if (lexemas.get(i).getNumToken() == 78) {
            //215
        } else if (lexemas.get(i).getNumToken() == 48) {
            //216
        } else if (lexemas.get(i).getNumToken() == 79) {
            //217
        } else if (lexemas.get(i).getNumToken() == 80) {
            //218
        } else if (lexemas.get(i).getNumToken() == 24) {
            //219
        } else if (lexemas.get(i).getNumToken() == 46) {
            //220
        } else if (lexemas.get(i).getNumToken() == 56) {
            //221
        } else if (lexemas.get(i).getNumToken() == 65) {
            //222
        } else {

        }
    }

    private void operadorCondicion() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 49, 52, 50, 51, 55)) {
            //223
        } else if (lexemas.get(i).getNumToken() == 55) {
            //224
        } else {

        }
    }

    private void condicionMat() {
        if (lexemas.get(i).getNumToken() == 49) {
            //225
        } else if (lexemas.get(i).getNumToken() == 52) {
            //226
        } else if (lexemas.get(i).getNumToken() == 50) {
            //227
        } else if (lexemas.get(i).getNumToken() == 51) {
            //227
        } else if (lexemas.get(i).getNumToken() == 48) {
            //227

        } else {

        }
    }

    private void condicionRelComp() {
        if (lexemas.get(i).getNumToken() == 50) {
            //228
        } else if (lexemas.get(i).getNumToken() == 51) {
            //229
        } else {

        }
    }

    private void condicionComp() {
        if (lexemas.get(i).getNumToken() == 51) {
            //230
        } else {

        }
    }

    private void condicionLog() {
        if (lexemas.get(i).getNumToken() == 55) {
            //231
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 50, 51, 55, 66, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//232
        } else {

        }
    }

    private void operacion() {
        if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 72, 23, 76, 73, 74, 75)) {
            //233
        } else if (lexemas.get(i).getNumToken() == 77) {
            //234

        } else if (lexemas.get(i).getNumToken() == 78) {
            //234
        } else if (lexemas.get(i).getNumToken() == 48) {
            //235
        } else if (lexemas.get(i).getNumToken() == 79) {
            //236
        } else if (lexemas.get(i).getNumToken() == 80) {
            //237
        } else if (lexemas.get(i).getNumToken() == 24) {
            //238
        } else if (lexemas.get(i).getNumToken() == 46) {
            //239
        } else if (lexemas.get(i).getNumToken() == 56) {
            //240
        } else if (lexemas.get(i).getNumToken() == 65) {
            //241
        } else {

        }
    }

    private void operador() {
        if (lexemas.get(i).getNumToken() == 49) {
            //242
        } else if (lexemas.get(i).getNumToken() == 52) {
            //242
        } else if (lexemas.get(i).getNumToken() == 50) {
            //243
        } else if (lexemas.get(i).getNumToken() == 51) {
            //243
        } else if (lexemas.get(i).getNumToken() == 55) {
            //244
        } else if (lexemas.get(i).getNumToken() == 48) {
            //242
        } else {

        }
    }

    private void operacionMat() {
        if (lexemas.get(i).getNumToken() == 49) {
            //246
        } else if (lexemas.get(i).getNumToken() == 48) {
            //247
        } else if (lexemas.get(i).getNumToken() == 50) {
            //248
        } else if (lexemas.get(i).getNumToken() == 51) {
            //248
        } else if (lexemas.get(i).getNumToken() == 52) {
            //245
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 50, 51, 55, 66, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//260
        } else {

        }
    }

    private void operacionRelComp() {
        if (lexemas.get(i).getNumToken() == 50) {
            //249
        } else if (lexemas.get(i).getNumToken() == 51) {
            //250
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 50, 51, 55, 66, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//251
        } else {

        }
    }

    private void operacionComp() {
        if (lexemas.get(i).getNumToken() == 51) {
            //252
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 50, 51, 55, 66, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//253
        } else {

        }
    }

    private void operacionLog() {
        if (lexemas.get(i).getNumToken() == 55) {
            //254
        } else if (RutinasSemanticas.esSiguiente(lexemas.get(i).getNumToken(), 58, 50, 51, 55, 66, 62, 82, 83, 1, 2, 3, 7, 44, 45, 46, 72, 12, 11, 40, 57)) {
//255
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
}
