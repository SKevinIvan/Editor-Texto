/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author por_s
 */
public class ManipulaErrores {

    public static void insertaError(ArrayList<Errores> tablaErrores, String idError, String error, String descripcion, String tipo, int lineaCodigo) {
        Errores e = new Errores();
        e.setIdError(idError);
        e.setError(error);
        e.setDescripcion(descripcion);
        e.setTipo(tipo);
        e.setLineaCodigo(lineaCodigo);
        tablaErrores.add(e);
    }
}
