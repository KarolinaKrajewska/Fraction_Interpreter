import java.math.BigInteger;
import java.util.Arrays;
import edu.princeton.cs.algs4.*;
public class Fraction implements Comparable<Fraction>{
    BigInteger n,d;
    
    public static void main(String[] args){
        Fraction f;
        Stack<String> ops = new Stack<String>(); //stack of operators
        Stack<Fraction> vals = new Stack<Fraction>(); //stack of values
        Fraction a = new Fraction(1, 6);
        Fraction b = new Fraction(2, 6);
        Fraction c = new Fraction(3, 6);
        Fraction[] array = {c, b, a};
        Arrays.sort(array, 0, 3);
        System.out.println(Arrays.toString(array));

        Fraction p = new Fraction(1, 2); // test for Compare To
        Fraction g = new Fraction(1, 3);
        int k = g.compareTo(p);
        System.out.println(k); 

        Fraction d = new Fraction(1, 2);
        Fraction e = new Fraction(1, 2);
        Fraction j = new Fraction(1, 3);
        Fraction l = new Fraction(23, 56);
        Fraction m = new Fraction(46, 112);
        boolean test1 = d.equals(e);
        boolean test2 = d.equals(j);
        boolean test3 = l.equals(m);
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
        while (!StdIn.isEmpty()){
            //Read Token, push into operators stack if it is an operator
            String s =StdIn.readString();

            if(s.equals("+")){
                ops.push(s);
            }
            else if(s.equals("-")){
                ops.push(s);
            }
            else if(s.equals("*")){
                ops.push(s);
            }
            else if(s.equals("/")){
                ops.push(s);
            }
            else if(s.equals("sqrt")){
                ops.push(s);
            }
            else if (s.equals("(")){

            }
            else if (s.equals("=")){
                ops.push(s);
            }
            else if(s.equals(")")){ //if you find a right parenthesis, do calculations as needed
                String op = ops.pop();
                f = vals.pop();
                if(op.equals("/")) { //two possibilities when meeting / - create a fraction or compute two fraction
                    f = vals.pop().divide(f);
                    vals.push(f);
                    
                    
                }
                if(op.equals("+")){
                    f = vals.pop().add(f);
                    vals.push(f);
                }
                if(op.equals("-")){
                    f = vals.pop().subtract(f);
                    vals.push(f);
                }
                if(op.equals("*")){
                    f = vals.pop().multiply(f);
                    vals.push(f);
                }
                if (op.equals("=")){

                }
                BigInteger gcd = f.n.gcd(f.d);
                f.n = f.n.divide(gcd);
                f.d = f.d.divide(gcd);
                if (f.d.equals(BigInteger.valueOf(1))){
                    int o = f.intValue();
                    System.out.println(o);
                } else {
                    String string = f.toString();
                    System.out.println(string);
                }            
            }
            
            else if(!(s.equals("(")||s.equals(")")||s.equals("+")||s.equals("-")||
            s.equals("*")||s.equals("/") || s.equals("="))){ //if it's not a symbol it's an int
                BigInteger i = new BigInteger(s);
                Fraction h = new Fraction(i, BigInteger.valueOf(1));
                vals.push(h); 

                 
            }
            
        }


    }
    public Fraction(BigInteger n, BigInteger d){ //test1
        this.n = n;
        this.d = d;
        BigInteger gcd = n.gcd(d);
        n = n.divide(gcd);
        d = d.divide(gcd); 
        if(d.equals(BigInteger.ZERO)){
            throw new IllegalArgumentException();
        }
        
    }
    public Fraction(BigInteger n){ //test2
        this.n=n;
        this.d = BigInteger.valueOf(1);
    }
    public Fraction(int n, int d){ //test3
        this.n = BigInteger.valueOf(n);
        this.d = BigInteger.valueOf(d);
         BigInteger gcd = this.n.gcd(this.d);
        this.n = this.n.divide(gcd);
        this.d = this.d.divide(gcd); 
        if(d == 0){
            throw new IllegalArgumentException();
        }
    }
    public Fraction(int n){ //test4
        this.n = BigInteger.valueOf(n);
        this.d = BigInteger.valueOf(1);
        
    }
    public BigInteger getNumerator(){ //test5
        return this.n;
    }
    public BigInteger getDenominator(){ //test6
        return this.d;
    }
    public Fraction add(Fraction f){ //test7
        return new Fraction((this.n.multiply(f.d)).add(this.d.multiply(f.n)), this.d.multiply(f.d)); //how do I differentiate between n of the first fraction and the n of the f fraction? (with this operator?) ; how do I operate with two different types at once? Casting did not help(?)
    }
    public Fraction subtract(Fraction f){ //test8
        return new Fraction((this.n.multiply(f.d)).subtract(this.d.multiply(f.n)), this.d.multiply(f.d));
    }
    public Fraction multiply(Fraction f){ //test9
        return new Fraction(this.n.multiply(f.n),this.d.multiply(f.d));
    }
    public Fraction divide(Fraction f){ //test10
        return new Fraction(this.n.multiply(f.d), this.d.multiply(f.n));
    }
    public boolean equals(Object o){ //test11
        Fraction f = (Fraction)o;
        if(this.n.equals(f.n) && this.d.equals(f.d)){
            return true;
        } else {
            return false;
        }
        
    }
    
    public String toString(){ //test12
        Fraction o = new Fraction(0, 1);
        String n1 = new String(this.n.toString());
        String d1 = new String(this.d.toString());
        String converted = new String(n1 + "/" + d1);
        return converted;
    }
    

    static Fraction valueOf(int n, int d){ //test13
        return new Fraction(n, d);
    }

    public double doubleValue(){ //test14
        double g;
        double n1 = this.n.doubleValue();
        double d1 = this.d.doubleValue();
        g = n1/d1;
        return g;
    } 
    public float floatValue(){ //test15
        float h;
        float n1 = this.n.floatValue();
        float d1 = this.d.floatValue();
        h = n1/d1;
        return h;
    } 
    public long longValue(){ //test16
        long g;
        long n1 = this.n.longValue();
        long d1 = this.d.longValue();
        g = n1/d1;
        return g;
    } 
    public int intValue(){ //test17
        int g;
        int n1 = this.n.intValue();
        int d1 = this.d.intValue();
        g = n1/d1;
        return g;
    }

    public int compareTo(Fraction f){
        if (this.n.multiply(f.d).compareTo(this.d.multiply(f.n)) == 0){ //same
            return 0;
        }
        else if (this.n.multiply(f.d).compareTo(this.d.multiply(f.n)) == -1){ //smaller
            return -1;
        }
        else{ //bigger
            return 1;
        
            
        }
    }
}
   










