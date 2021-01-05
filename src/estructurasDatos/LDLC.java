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
public class LDLC
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
            //Mensaje.error(jf, "No se puede insertar en la lista");
        } else
        {
            if (r==null)
            {
                r=n;
                r.setSig(n);
                r.setAnt(n);
            } else
            {
                if (n.getS().compareTo(r.getS())>=0 || n.getS().compareTo(r.getSig().getS())<=0)
                {
                    n.setSig(r.getSig());
                    n.setAnt(r);
                    r.getSig().setAnt(n);
                    r.setSig(n);
                    if(n.getS().compareTo(r.getS())>=0)
                    {
                        r=n;
                    }
                } else
                {
                    Nodo aux = r.getSig();
                    boolean b=true;
                    while(b)
                    {
                        if (aux.getSig().getS().compareTo(n.getS())>=0)
                        {
                            n.setSig(aux.getSig());
                            n.setAnt(aux);
                            aux.getSig().setAnt(n);
                            aux.setSig(n);
                            b=false;
                        } else
                        {
                            aux=aux.getSig();
                        }
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
            if(s.compareTo(r.getSig().getS())<0 || s.compareTo(r.getS())>0)
            {
           //     Mensaje.error(jf, "Dato fuera de la lista");
            }else
            {
                if(s.equals(r.getSig().getS()))
                {
                    aux=r.getSig();
                    r.getSig().setAnt(r);
                    r.setSig(aux.getSig());
                    if(aux==r)
                    {
                        r=null;
                    }
                    aux.setSig(null);
                    aux.setAnt(null);
                }else
                {
                    Nodo aux2 =r.getSig();
                    boolean b=true;
                    while(aux2 != r && b)
                    {
                        if(s.equals(aux2.getSig().getS()))
                        {
                            aux=aux2.getSig();
                            aux.getSig().setAnt(aux2);
                            aux2.setSig(aux.getSig());
                            aux.setSig(null);
                            aux.setAnt(null);
                            if(aux==r)
                            {
                                r=aux2;
                            }
                            b=false;
                        }else
                        {
                            aux2=aux2.getSig();
                        }
                    }
                    if(b)
                    {
             //           Mensaje.error(jf, "No se encontro el dato");
                    }
                }
            }
        }
        return aux;
    }  
    
    
    public String desp()
    {
        String s="";
        Nodo aux =r.getSig();
        do
        {
            s+= aux.desp() + "\n" ;
            aux=aux.getSig();
        }while(aux != r.getSig());
        return s;
    }
}
