import java.util.Objects;
import java.util.Scanner;

public class P3 
{
    public static void main (String[] args)
    {
        System.out.print("Введите координаты клада: ");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt(); // координата х клада
        int y = scanner.nextInt(); // координата у клада
        int X = 0; // текущая координата х
        int Y = 0; // текущая кордината у
        scanner.nextLine(); // читаем сторону света
        String Path = scanner.nextLine(); 
        int count = 0; // счётчик
        while (!Objects.equals(Path, "стоп"))
        {
            int a = scanner.nextInt();
            if(X!=x || Y!=y)
            {
                switch (Path) //switch case для соответсвия сторон света и количества шагов по координатам
                { 
                    case "север":
                        Y+=a;
                        break;
                    case "юг":
                        Y-=a;
                        break;
                    case "запад":
                        X-=a;
                        break;
                    case "восток":
                        X+=a;
                        break;
                }
                count++;
            }
            scanner.nextLine();
            Path = scanner.nextLine(); // всё по новой :)))))
        }
        System.out.printf("Минимальное количество указаний карты: " + count);
    }
}
