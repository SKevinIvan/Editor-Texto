/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import recursos.Mensaje;

/**
 * Clase que guarda o carga archivos binarios o de texto
 *
 * @author kevin2
 */
public class ManipulaArchivos {

    //public static final long SerialVersionUID = 1L;
    /**
     * Método estático para guardar un ArrayList en archivos binarios
     *
     * @param arL - ArrayList que se almacenara en el archivo
     * @param url - URL en donde se guardara el archivo
     */
    public static void guarda(ArrayList<String> arL, String url) {
        try {
            FileOutputStream fos = new FileOutputStream(url);
            ObjectOutputStream arch = new ObjectOutputStream(fos);
            if (arL != null) {
                arch.writeObject(arL);
            }
        } catch (FileNotFoundException ex) {
            Mensaje.error(null, "Archivo no encontrado al guardar como archivo binario... " + "\n" + ex.getMessage());
        } catch (IOException ex) {
            Mensaje.error(null, "Error en el archivo al guardar" + "\n" + ex.getMessage());
        }
    }

    /**
     * Método estático para cargar archivos binarios
     *
     * @param url ruta donde se encuentra el archivo
     * @return el ArrayList
     */
    public static ArrayList<String> carga(String url) {

        ArrayList<String> obj = null;
        try {
            FileInputStream fis = new FileInputStream(url);

            ObjectInputStream arch = new ObjectInputStream(fis);
            obj = (ArrayList<String>) arch.readObject();
        } catch (ClassNotFoundException ex) {
            Mensaje.error(null, "Error... Carga" + ex.getMessage()+"\n"+ex.getMessage());
        } catch (IOException ex) {
            Mensaje.error(null, "Error... Carga " + ex.getLocalizedMessage()+"\n"+ex.getMessage());
        }
        return obj;
    }

    /**
     * Metodo para guardar archivos de texto Cuando se seleciona la opcion de
     * guardar/Reemplazar el archivo Para guardar un arrayList
     *
     * @param arL ArrayList que guardará el archivo de texto
     * @param ruta dirección del archivo donde se guardará
     */
    public static void guardar(ArrayList<String> arL, String ruta) {

        FileWriter fichero = null;

        try {
            fichero = new FileWriter(ruta);
            PrintWriter pw = new PrintWriter(fichero);
            for (int i = 0; i < arL.size(); i++) {
                pw.println(arL.get(i));
            }

        } catch (IOException ex) {
            Mensaje.error(null, "Error al cerrar archivo Guardar ArrayList"+"\n"+ex.getMessage());
        } finally {
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException ex) {
                    Mensaje.error(null, "Error al cerrar archivo Guardar ArrayList"+"\n"+ex.getMessage());
                }
            }
        }
    }

    /**
     * Metodo para guardar archivos de texto Ahi que guardar el archivo segun
     * como fue escrito Cuando se selecciona el Guardar como Para guardar un
     * arrayList
     *
     * @param arL arreglo a guardar
     * @param ruta ruta especificada del usaurio distinta a la del documento
     * original
     */
    public static void guardarComo(ArrayList<String> arL, String ruta) {

        FileWriter fichero = null;

        try {
            fichero = new FileWriter(ruta);
            PrintWriter pw = new PrintWriter(fichero);
            for (int i = 0; i < arL.size(); i++) {
                pw.println(arL.get(i));
            }

        } catch (IOException ex) {
            Mensaje.error(null, "Error al cerrar archivo GuardarComo ArrayList"+"\n"+ex.getMessage());
        } finally {
            if (fichero != null) {

                try {
                    fichero.close();
                } catch (IOException ex) {
                    Mensaje.error(null, "Error al cerrar archivo GuardarComo ArrayList"+"\n"+ex.getMessage());
                }
            }
        }
    }

    /**
     * Metodo para guardar archivos de texto hay que guardar el archivo segun
     * como fue escrito Cuando se selecciona el Guardar Para guadar un texto
     *
     * @param texto texto a guardar
     * @param ruta ruta especificada del usaurio
     */
    public static void guardar(String texto, String ruta) {

        FileWriter fichero = null;

        try {
            fichero = new FileWriter(ruta);
            PrintWriter pw = new PrintWriter(fichero);

            pw.println(texto);

        } catch (IOException ex) {
            Mensaje.error(null, "Error al cerrar archivo Guardar String"+"\n"+ex.getMessage());
        } finally {
            if (fichero != null) {

                try {
                    fichero.close();
                } catch (IOException ex) {
                    Mensaje.error(null, "Error al cerrar archivo Guardar String"+"\n"+ex.getMessage());
                }
            }
        }
    }

    /**
     * Metodo para guardar archivos de texto Ahi que guardar el archivo segun
     * como fue escrito Cuando se selecciona el Guardar Como Para guadar un
     * texto
     *
     * @param texto texto a guardar
     * @param ruta ruta especificada del usaurio
     */
    public static void guardarComo(String texto, String ruta) {

        FileWriter fichero = null;

        try {
            fichero = new FileWriter(ruta);
            PrintWriter pw = new PrintWriter(fichero);
            pw.println(texto);

        } catch (IOException ex) {
            Mensaje.error(null, "Error al cerrar archivo GuardarComo String"+"\n"+ex.getMessage());
        } finally {
            if (fichero != null) {

                try {
                    fichero.close();
                } catch (IOException ex) {
                    Mensaje.error(null, "Error al cerrar archivo GuardarComo String"+"\n"+ex.getMessage());
                }
            }
        }
    }

    /**
     * Metodo para crear nuevo archivos de texto ruta Carga nuevo
     *
     * @param ruta donde vamos a crear el nuevo archivo
     */
    public static void crearNuevo(String ruta) {

        FileWriter fichero = null;

        try {
            fichero = new FileWriter(ruta);
            PrintWriter pw = new PrintWriter(fichero);

            pw.println("");

        } catch (IOException ex) {
            Mensaje.error(null, "Error al cerrar archivo Crear Nuevo String"+"\n"+ex.getMessage());
        } finally {
            if (fichero != null) {

                try {
                    fichero.close();
                } catch (IOException ex) {
                    Mensaje.error(null, "Error al cerrar archivo CrearNuevo String"+"\n"+ex.getMessage());
                }
            }
        }
    }

    /**
     * Metodo para abrir archivos de texto con ruta especificada
     *
     * @param ruta especificada por el usuario
     * @return arrayList
     */
    public static ArrayList<String> cargar(String ruta) {

        ArrayList<String> cadena = null;

        FileReader fr = null;
        File archivo = new File(ruta);

        try {
            cadena = new ArrayList<>();
            fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;

            while ((linea = br.readLine()) != null) {
                cadena.add(linea + "\n");
            }
        } catch (FileNotFoundException ex) {
            Mensaje.error(null, "Error al cerrar archivo Cargar ArrayList"+"\n"+ex.getMessage());
        } catch (IOException ex) {
            Mensaje.error(null, "Error en el archivo Cargar ArrayList"+"\n"+ex.getMessage());
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Mensaje.error(null, "Error al cerrar archivo Cargar ArrayList"+"\n"+ex.getMessage());
                }
            }
        }
        return cadena;
    }

    /**
     * Metodo para abrir archivos
     *
     * @param ruta es la ruta donde vamos a extraer el archivo
     * @return String (un texto)
     */
    public static String cargarArchivo(String ruta) {

        String cadena = "";

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        archivo = new File(ruta);

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;

            while ((linea = br.readLine()) != null) {
                cadena = cadena + linea + "\n";
            }
        } catch (FileNotFoundException ex) {
          //  Mensaje.error(null, "No se encontro el archivo Cargar String"+"\n"+ex.getMessage());
        } catch (IOException ex) {
          //  Mensaje.error(null, "Error en el archivo Cargar String"+"\n"+ex.getMessage());
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException ex) {
           //         Mensaje.error(null, "Error al cerrar archivo Cargar String"+"\n"+ex.getMessage());
                }
            }
        }
        return cadena;
    }

}
