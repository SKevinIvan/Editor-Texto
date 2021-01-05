/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructurasDatos;


import javax.swing.JFrame;

/**
 *
 * @author Mauro Sanchez2
 */
public class PilaD
{
    private Nodo tope=null;

    /**
     * @return the tope
     */
    public Nodo getTope()
    {
        return tope;
    }

    /**
     * @param tope the tope to set
     */
    public void setTope(Nodo tope)
    {
        this.tope = tope;
    }
    
    public void inserta(Nodo n, JFrame jf)
    {
        if (n == null)
        {
          //  Mensaje.error(jf, "No se pude insertar el nodo en la pila");
                    
        }else
        {
            n.setSig(tope);
            tope=n;
        }
    }
    
    public Nodo elimina(JFrame jf)
    {
        if (tope ==null)
        {
         //   Mensaje.error(jf, "No hay datos en la pila");
            return null;
        } else
        {
            Nodo aux = tope;
            tope=tope.getSig();
            aux.setSig(null);
            return aux;
        }
    }
}
