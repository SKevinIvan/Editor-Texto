/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructurasDatos;

import java.io.*;


/**
 *
 * @author Jose Antonio
 */
public class Lecturas
{

    private InputStreamReader isr = new InputStreamReader(System.in);
    private BufferedReader br = new BufferedReader(isr);
    private static  InputStreamReader isrC = new InputStreamReader(System.in);
    private  static BufferedReader brC = new BufferedReader(isrC);

    public int entero()
    {
        try
        {
            return Integer.parseInt(br.readLine());
        } catch (Exception ex)
        {
            System.out.println("Error...se esperaba un entero");
            return 0;
        }
    }
    
    public int entero(Boolean b)
    {
        do
        {
            try
            {
                return Integer.parseInt(br.readLine());
            } catch (Exception e)
            {
                System.out.println("Error se esperaba un entero, vuelva a intentarlo");
            }
        } while (b);
        return 0;
    }
    public double doble()
    {
        try
        {
            return Double.parseDouble(br.readLine());
        } catch (Exception ex)
        {
            System.out.println("Error...se esperaba un flotante");
            return 0;
        }
    }
    
    public double doble(Boolean b)
    {
        do
        {
            try
            {
                return Double.parseDouble(br.readLine());
            } catch (Exception e)
            {
                System.out.println("Error se esperaba un flotante, vuelva a intentarlo");
            }
        } while (b);
        return 0;
    }

    public char caracter()
    {
        char c=' ';
        try
        {
            c = (char) br.read();
            String s = br.readLine();
        } catch (Exception e)
        {
            System.out.println("ERROR");
        }
        return c;
    }

    public String cadena()
    {
        try
        {
            return br.readLine();
        } catch (Exception e)
        {
            System.out.println("ERROR");
            return "";
        }
    }
      
    public static  int enteroC()
    {
        try
        {
            return Integer.parseInt(brC.readLine());
        } catch (Exception ex)
        {
            System.out.println("Error...se esperaba un entero");
            return 0;
        }
    }
    
    public static int enteroC(Boolean b)
    {
        do
        {
            try
            {
                return Integer.parseInt(brC.readLine());
            } catch (Exception e)
            {
                System.out.println("Error se esperaba un entero, vuelva a intentarlo");
            }
        } while (b);
        return 0;
    }
    public static double dobleC()
    {
        try
        {
            return Double.parseDouble(brC.readLine());
        } catch (Exception ex)
        {
            System.out.println("Error...se esperaba un flotante");
            return 0;
        }
    }
    
    public static double dobleC(Boolean b)
    {
        do
        {
            try
            {
                return Double.parseDouble(brC.readLine());
            } catch (Exception e)
            {
                System.out.println("Error se esperaba un flotante, vuelva a intentarlo");
            }
        } while (b);
        return 0;
    }

    public static  char caracterC()
    {
        char c=' ';
        try
        {
            c = (char) brC.read();
            String s = brC.readLine();
        } catch (Exception e)
        {
            System.out.println("ERROR");
        }
        return c;
    }

    public static String cadenaC()
    {
        try
        {
            return brC.readLine();
        } catch (Exception e)
        {
            System.out.println("ERROR");
            return "";
        }
    }

}
