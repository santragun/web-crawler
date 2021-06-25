import java.util.Scanner;

public class factorization {


    public static int factorial(int n)
    {
        int fact=1;

        if(n==0 || n==1)
        {
            fact=1;
        }
        else
        {
           for(int i=0;i<n;i++)
           {
               fact=fact*(n-i);
           }
        }
        return fact;
    }
    public static void main(String[] args) {

        Scanner in=new Scanner(System.in);
        int number;
        while (true)
        {
            number=in.nextInt();
            System.out.println(factorial(number));
        }

    }
}
