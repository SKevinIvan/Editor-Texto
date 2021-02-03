/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructurasDatos;

/**
 *
 * @author por_sa
 */
public class PrbListas
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        LDLC l = new LDLC();
        Nodo n1= new Nodo("1", -1);
        Nodo n2= new Nodo("2", -1);
        Nodo n3= new Nodo("3", -1);
        Nodo n4= new Nodo("11", -1);
        Nodo n5= new Nodo("12", -1);
        Nodo n6= new Nodo("13", -1);
        Nodo n7= new Nodo("14", -1);
        Nodo n8= new Nodo("15", -1);
        Nodo n9= new Nodo("99", -1);
        Nodo n10= new Nodo("12", -1);
        
        l.inserta(n1, null);
        l.inserta(n2, null);
        l.inserta(n3, null);
        l.inserta(n4, null);
        l.inserta(n5, null);
        l.inserta(n6, null);
        l.inserta(n7, null);
        l.inserta(n8, null);
        l.inserta(n9, null);
        l.inserta(n10, null);
        
        System.out.println(l.desp());
    }   
}