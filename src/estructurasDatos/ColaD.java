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
public class ColaD
{
    private Nodo f = null;
    private Nodo a = null;

    /**
     * @return the f
     */
    public Nodo getF()
    {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(Nodo f)
    {
        this.f = f;
    }

    /**
     * @return the a
     */
    public Nodo getA()
    {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(Nodo a)
    {
        this.a = a;
    }
    
    public void inserta(Nodo n, JFrame jf)
    {
        if (n==null)
        {
        //    Mensaje.error(jf, "No se puede insertar el nodo  en la cola");
        } else
        {
            if(a== null)
            {
                f=a=n;
            }else
            {
                a.setSig(n);
                a=n;
            }
           // Mensaje.exito(jf, "Dato insertado correctamente");
        }
    }
    
    public Nodo elimina(JFrame jf)
    {
        
        if (f==null)
        {
          //  Mensaje.error(jf, "No hay datos en la cola");
            return null;
        } else
        {
            Nodo aux = f;
            if (f==a)
            {
                f=a=null;
            } else
            {
                f=f.getSig();
                aux.setSig(null);
            }
            return aux;        
        }
    }
}
