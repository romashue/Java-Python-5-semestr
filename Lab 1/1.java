import java.util.Scanner;

public class P1 
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in); // просим пользователя ввести натуральное число
        System.out.print("Введите любое одно натуральное число: ");
        int number = scanner.nextInt(); // переменная для текущего состояния числа в Сиракузской последовательности
        int count = 0; // подсчёт шагов
        while (number != 1)
        {
            if (number % 2 == 0)
            {
                number/=2;
                count++;
            }
            else
            {
                number = number*3+1;
                count++;
            }
        }
        System.out.printf("Количество шагов - " + count);
    }
}