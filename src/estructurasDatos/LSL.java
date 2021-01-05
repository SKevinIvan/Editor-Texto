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
public class LSL
{
    private Nodo  r=null;

    /**
     * @return the r
     */
    public Nodo getR()
    {
        return r;
    }

    /**
     * @param r the r to set
     */
    public void setR(Nodo r)
    {
        this.r = r;
    }
    
    public void inserta(Nodo n,  JFrame jf)
    {
        if (n==null)
        {
         //   Mensaje.error(jf, "No se puede insertar en la lista");
        } else
        {
            if (r==null)
            {
                r=n;
            } else
            {
                if (n.getS().compareTo(r.getS())<=0)
                {
                    n.setSig(r);
                    r=n;
                } else
                {
                    Nodo aux = r;
                    boolean b=true;
                    while(aux.getSig() != null && b)
                    {
                        if (aux.getSig().getS().compareTo(n.getS())>=0)
                        {
                            n.setSig(aux.getSig());
                            aux.setSig(n);
                            b=false;
                        } else
                        {
                            aux=aux.getSig();
                        }
                    }
                    if (b)
                    {
                        aux.setSig(n);
                    }
                }
            }         
        }
    }
    
    public Nodo elimina(JFrame jf, String s)
    {
        Nodo aux =null;
        if(r ==null)
        {
           // Mensaje.error(jf, "No hay datos en la lista");
        }else
        {
            if(s.equals(r.getS()))
            {
                aux=r;
                r=r.getSig();
                aux.setSig(null);
            }else
            {
                Nodo aux2 =r;
                boolean b=true;
                while(aux2.getSig() != null && b)
                {
                    if(s.equals(aux2.getSig().getS()))
                    {
                        aux=aux2.getSig();
                        aux2.setSig(aux.getSig());
                        aux.setSig(null);
                        b=false;
                    }else
                    {
                        aux2=aux2.getSig();
                    }
                }
                if(b)
                {
              //      Mensaje.error(jf, "No se encontro el dato");
                }
            }
        }
        return aux;
    }  
    
    
    public String desp()
    {
        String s="";
        Nodo aux =r;
        while(aux != null)
        {
            s+= aux.desp() + "\n" ;
            aux=aux.getSig();
        }
        return s;
    }
}
