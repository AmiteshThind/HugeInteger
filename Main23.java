/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main23;

import java.math.BigInteger;

/**
 *
 * @author AmiteshThind
 */
public class Main23 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
        
        HugeInteger a = new HugeInteger(500);
        HugeInteger b = new HugeInteger(500);
        HugeInteger c = new HugeInteger("-432523423");
        HugeInteger d = new HugeInteger("2321353212");
        
        HugeInteger multi = a.multiply(b);
        HugeInteger addition = a.add(b);
        HugeInteger subtraction = a.subtract(b);
                
        HugeInteger multi2 = c.multiply(d);
        HugeInteger addition2 = c.add(d);
        HugeInteger subtraction2 = c.subtract(d);
        

    System.out.printf("%s\n",multi.toString());
    System.out.printf("%s\n",addition.toString());
    System.out.printf("%s\n",subtraction.toString());
        
    System.out.printf("%s\n",multi2.toString());
    System.out.printf("%s\n",addition2.toString());
    System.out.printf("%s\n",subtraction2.toString());    
  
    }
    
}
