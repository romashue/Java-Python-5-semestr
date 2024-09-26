import java.util.Scanner;

public class P2 
{
    public static void main (String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите натуральное число n > 0 (количество чисел): ");
        int number = scanner.nextInt(); // количество чисел
        int sum = 0; //Сумма ряда
        for (int i=1; i<=number; i++)
        {
            Scanner in = new Scanner(System.in);
            int a = in.nextInt();
            if (i%2==1)
            {
                sum+=a;
            }
            else
            {
                sum-=a;
            }
        }
        System.out.printf("Получившаяся сумма ряда: " + sum);
    }
}
