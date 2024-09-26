import java.util.Scanner;

public class P5 
{
    public static void main (String[] args){
        System.out.print("Введите трёхзначное число: ");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(); // наше число
        int units = a%10; // единицы
        a/=10;
        int tenth = a%10; // десятки
        a/=10;
        int hundredths = a%10; // сотни
        if ((units+tenth+hundredths) % 2 == 0 & (units*tenth*hundredths) % 2 == 0) // проверка условия
        {
            System.out.print("Является :)");
        }
        else 
        {
            System.out.print("Не является :(");
        }
    }
}
