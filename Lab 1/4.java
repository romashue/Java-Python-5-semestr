import java.util.Scanner;

public class P4 
{
    public static void main(String[] args)
    {
        System.out.print("Введите количество дорог: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt(); // количество дорог
        int numberPath = 0; // номер дороги
        int max = 0; // максимальная высота
        for (int i=0; i<number; i++){
            int min = Integer.MAX_VALUE; // будем следить за нижней границей высоты, пока присваиваем максимально возможное значение
            int Tunnel = scanner.nextInt(); // высоты туннелей
            for(int q=0; q<Tunnel; q++)
            {
                int height = scanner.nextInt();
                if(min>height)
                {
                    min=height;
                }
            }
            if (max<min)
            {
                max=min;
                numberPath = i+1; // +1 в силу индекса
            }
        }
        System.out.printf(numberPath + " " + max);
    }
}
