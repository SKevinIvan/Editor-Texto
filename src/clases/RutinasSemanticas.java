/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import planetprogrammig.EditorPlanetProgramming;

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
//Salir
            case 62:
                break;
            default:
                Errores e = new Errores();
                e.setIdError("E18");
                e.setDescripcion("Se esperaba el nombre del proyecto");
                e.setLineaCodigo(lexemas.get(i).getRenglon());
                e.setTipo("ERROR SINTACTICO");
                EditorPlanetProgramming.lstError.add(e);
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
        switch (lexemas.get(i).getNumToken()) {
            case 82:
                i++;
                break;
            case 83:
                i++;
                break;
            default:
                break;
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
        switch (lexemas.get(i).getNumToken()) {
            case 71:
                importarLibreria2();
                if (lexemas.get(i).getNumToken() == 81) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 58) {
                        i++;
                    } else {
                        
                    }
                } else {
                    
                }   break;
            case 81:
                importarLibreria2();
                if (lexemas.get(i).getNumToken() == 81) {
                    i++;
                    if (lexemas.get(i).getNumToken() == 58) {
                        i++;
                    } else {
                        
                    }
                } else {
                    
                }   break;
            default:
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
                    
                }   break;
        //Salir
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
                i++;if (lexemas.get(i).getNumToken() == 4) {
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
                    
                }   break;
            case 17:
                //25
                i++;if (lexemas.get(i).getNumToken() == 4) {
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
                    
                }   break;
            case 4:
                //26
                i++;if (lexemas.get(i).getNumToken() == 72) {
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
                    
                }   break;
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
                    
                }   break;
            case 45:
                //47
                tipoDato();
                asignacionAtributo();
                conjuntoAtributo();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {
                    
                }   break;
            case 46:
                //47
                tipoDato();
                asignacionAtributo();
                conjuntoAtributo();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {
                    
                }   break;
            default:
                break;
        }
    }

    private void conjuntoAtributo() {
        switch (lexemas.get(i).getNumToken()) {
            case 57:
                //48
                i++;asignacionAtributo();
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
                i++;if (lexemas.get(i).getNumToken() == 22) {
                    i++;
                    tipoDato();
                } else {
                    
                }   break;
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
                i++;if (lexemas.get(i).getNumToken() == 76) {
                    i++;
                    inicializacionObjeto();
                    conjuntoObjetos();
                } else {
                    
                }   break;
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
                i++;break;
            case 76:
                //75
                i++;break;
            case 22:
                //76
                i++;if (lexemas.get(i).getNumToken() == 72) {
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
                    
                }   break;
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
                i++;asignacionObjeto3();
                break;
            case 63:
                //84
                i++;posicion();
                if (lexemas.get(i).getNumToken() == 64) {
                    i++;
                    arregloObjeto2();
                    if (lexemas.get(i).getNumToken() == 54) {
                        i++;
                        asignacionObjeto3();
                    } else {
                        
                    }
                } else {
                    
                }   break;
            default:
                break;
        }
    }

    private void asignacionObjeto3() {
        switch (lexemas.get(i).getNumToken()) {
            case 24:
                //85
                i++;break;
            case 22:
                //86
                i++;if (lexemas.get(i).getNumToken() == 72) {
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
                    
                }   break;
            case 76:
                //87
                i++;break;
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
                i++;posicion();
                if (lexemas.get(i).getNumToken() == 64) {
                    i++;
                    arregloVar4();
                } else {
                    
                }   break;
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
                i++;if (lexemas.get(i).getNumToken() == 59) {
                    i++;
                    claseInstancia2();
                } else {
                    
                }   break;
            case 23:
                //101
                i++;if (lexemas.get(i).getNumToken() == 59) {
                    i++;
                    claseInstancia2();
                } else {
                    
                }   break;
            case 76:
                //102
                i++;claseInstancia22();
                break;
            case 73:
                //103
                i++;if (lexemas.get(i).getNumToken() == 65) {
                    i++;
                    parametro();
                    if (lexemas.get(i).getNumToken() == 66) {
                        i++;
                    } else {
                        
                    }
                } else {
                    
                }   break;
            case 74:
                //104
                i++;arregloVar22();
                break;
            case 75:
                //105
                i++;arregloVar22();
                break;
            default:
                break;
        }
    }

    private void claseInstancia2() {
        switch (lexemas.get(i).getNumToken()) {
            case 73:
                //106
                i++;if (lexemas.get(i).getNumToken() == 65) {
                    i++;
                    parametro();
                    if (lexemas.get(i).getNumToken() == 66) {
                        i++;
                    } else {
                        
                    }
                } else {
                    
                }   break;
            case 74:
                //107
                i++;arregloVar22();
                break;
            case 75:
                //108
                i++;arregloVar22();
                break;
            case 76:
                //109
                i++;claseInstancia22();
                break;
            default:
                break;
        }
    }

    private void claseInstancia22() {
        switch (lexemas.get(i).getNumToken()) {
            case 59:
                //110
                i++;claseInstancia2();
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
                i++;refDeclaracionObjeto();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {
                    
                }   break;
            case 23:
                //116
                referenciaAtributo();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {
                    
                }   break;
            case 76:
                //116
                referenciaAtributo();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {
                    
                }   break;
            case 74:
                //116
                referenciaAtributo();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {
                    
                }   break;
            case 73:
                //116
                referenciaAtributo();
                if (lexemas.get(i).getNumToken() == 58) {
                    i++;
                } else {
                    
                }   break;
            default:
                break;
        }
    }

    private void refDeclaracionObjeto() {
        switch (lexemas.get(i).getNumToken()) {
            case 59:
                //117
                i++;referenciaAtributo2();
                break;
            case 76:
                //118
                i++;inicializacionObjeto();
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
                i++;if (lexemas.get(i).getNumToken() == 59) {
                    i++;
                    referenciaAtributo2();
                } else {
                    
                }   break;
            case 76:
                //120
                i++;referenciaAtributo22();
                break;
            case 74:
                //121
                i++;arregloVar4();
                asignacionAtributoVar();
                break;
            case 73:
                //265
                i++;if (lexemas.get(i).getNumToken() == 65) {
                    i++;
                    parametro();
                    if (lexemas.get(i).getNumToken() == 66) {
                        i++;
                    } else {
                        
                    }
                } else {
                    
                }   break;
            default:
                break;
        }
    }

    private void referenciaAtributo2() {
        switch (lexemas.get(i).getNumToken()) {
            case 74:
                //122
                i++;arregloVar4();
                asignacionAtributoVar();
                break;
            case 76:
                //123
                i++;referenciaAtributo22();
                break;
            case 73:
                //266
                i++;if (lexemas.get(i).getNumToken() == 65) {
                    i++;
                    parametro();
                    if (lexemas.get(i).getNumToken() == 66) {
                        i++;
                    } else {
                        
                    }
                } else {
                    
                }   break;
            default:
                break;
        }
    }

    private void referenciaAtributo22() {
        switch (lexemas.get(i).getNumToken()) {
            case 59:
                //124
                i++;referenciaAtributo2();
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
                i++;asignacionAtributoVar2();
                break;
            case 53:
                //128
                i++;operacionAritmetica();
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
            if (lexemas.get(i).getNumToken() == 59){
                i++;
                lectura();
            }else{
                
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
                i++;break;
            case 45:
                //133
                i++;break;
            case 46:
                //134
                i++;break;
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
                i++;break;
            case 79:
                //137
                i++;concatenacion();
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
                i++;break;
            default:
                break;
        }
    }

    private void numero() {
        switch (lexemas.get(i).getNumToken()) {
            case 77:
                //140
                i++;break;
            case 78:
                //141
                i++;break;
            default:
                break;
        }

    }

    private void boleano() {
        switch (lexemas.get(i).getNumToken()) {
            case 38:
                //142
                i++;break;
            case 39:
                //143
                i++;break;
            default:
                break;
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
