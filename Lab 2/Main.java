import java.util.Arrays; //для работы с масивами
import java.util.Scanner; //для чтения того, что пользователь вбивает с клавиатуры

public class Main 
{
    // Находим подстроку максимальной длины в строке, состоящую из уникальных символов
    public static String p1(String stroka) 
    {
        String temp = ""; // вспомогательная строка
        String max_podstr = ""; // максимальная подстрока
        
        for (int i = 0; i < stroka.length(); i++) 
        {
            boolean flag = true;
            for (int j = 0; j < temp.length(); j++) 
            {
                if (stroka.charAt(i) == temp.charAt(j)) 
                {
                    flag = false;
                    break;
                }
            }
            if (flag == true) 
            {
                temp += stroka.charAt(i);
            } else {
                if (temp.length() > max_podstr.length()) 
                {
                    max_podstr = temp;
                }
                i -= temp.length();
                temp = "";
            }
        }
        if (temp.length() > max_podstr.length()) 
        { 
            max_podstr = temp;
        }
        
        return max_podstr;
    }

    //Сливаем два отсортированных массива и возвращаем отсортированный результат
    public static String p2(int[] mass1, int[] mass2) 
    {
        int[] newMass = new int[mass1.length + mass2.length];
        for (int i = 0; i < mass1.length; i++) 
        {
            newMass[i] = mass1[i];
        }
        for (int j = 0; j < mass2.length; j++) 
        {
            newMass[j + mass1.length] = mass2[j];
        }
        Arrays.sort(newMass);
        String returnString = "";
        for (int i = 0; i < newMass.length; i++) 
        {
            returnString += newMass[i];
            if (i != newMass.length - 1) 
            {
                returnString += ", ";
            }
        }
        return returnString;
    }

    //Находим максимальную сумму двух последовательных элементов в массиве
    public static int p3(int[] mass) 
    {
        int maxSum = 0;
        int temp = 0;
        for (int i = 0; i < mass.length - 1; i++) 
        {
            temp = mass[i] + mass[i + 1];
            if (temp > maxSum) 
            {
                maxSum = temp;
            }
        }
        return maxSum;
    }

    //Переворачиваем двумерный массив по диагонали
    public static String p4(int[][] mass) 
    {
        int[][] newmass = new int[mass[0].length][mass.length];
        for (int j = 0; j < mass[0].length; j++) 
        { // столбцы первого массива
            for (int i = mass.length - 1; i >= 0; i--) 
            { // строки первого массива с конца
                newmass[j][mass.length - 1 - i] = mass[i][j];
            }
        }
        String returnString = "";
        for (int i = 0; i < newmass.length; i++) 
        {
            returnString += "\n";
            for (int j = 0; j < newmass[i].length; j++) 
            {
                returnString += newmass[i][j] + " ";
            }
        }
        return returnString;

    }

    //Находим в массиве mass две пары элементов, сумма которых равна target
    public static String p5(int[] mass, int target) 
    {
        int[] newmass = new int[mass.length];
        boolean flag = false;
        for (int i = 0; i < mass.length - 1; i++) 
        {
            for (int j = i + 1; j < mass.length; j++) 
            {
                if (mass[i] + mass[j] == target) 
                {
                    return "\n5. Подходящие пары: " + mass[i] + ", " + mass[j];
                }
            }
        }
        return null;
    }

    //Вычисляем сумму всех элементов в двумерном массиве
    public static int p6(int[][] mass)
    {
        int sum=0;
        for(int i=0; i<mass.length; i++){
            for(int j=0; j<mass[0].length; j++)
            {
                sum+=mass[i][j];
            }
        }
        return sum;
    }

    //Находим максимальный элемент в каждой строке двумерного массива
    public static String p7(int[][] mass)
    {
        int[] newmass = new int[mass.length];
        for (int i=0; i< mass.length; i++)
        {
            int max=0;
            for (int j=0; j<mass[0].length; j++)
            {
                if(max<mass[i][j]){
                    max=mass[i][j];
                }
            }
            newmass[i]=max;
        }
        String returnString = "";
        for (int i = 0; i < newmass.length; i++) 
        {
            returnString += newmass[i] + " ";
        }
        return returnString;
    }

    //Переворачиваем двумерный массив по горизонтали (как по горизонтали, так и по вертикали).
    public static String p8(int[][] mass)
    {
        int[][] newmass = new int[mass[0].length][mass.length];
        for (int i=mass[0].length-1; i>=0; i--)
        {
            for (int j=0; j< mass.length; j++)
            {
                newmass[mass[0].length-1-i][j]=mass[j][i];
            }
        }
        String returnString = "";
        for (int i = 0; i < newmass.length; i++) 
        {
            returnString += "\n";
            for (int j = 0; j < newmass[i].length; j++) 
            {
                returnString += newmass[i][j] + " ";
            }
        }
        return returnString;
    }

    //Подготовки завершены, переходим к решению задач
    //Запускаем программу, вызывая функции для решения каждой задачи

    public static void main(String[] args) 
    {
        // Задача 1
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String stroka = scanner.nextLine();
        System.out.print("1.Подстрока максимальной длины: ");
        System.out.print(p1(stroka));

        // Задача 2
        int[] mass1 = {3, 9, 5, 7, 1};
        int[] mass2 = {8, 4, 2, 0, 6};
        Arrays.sort(mass1);
        Arrays.sort(mass2);
        System.out.print("\n2. Отсортированный массив: ");
        System.out.print(p2(mass1, mass2));

        // Задача 3
        int[] mass3 = {2, 6, 1, 8, 0};
        System.out.print("\n3. Максмальная сумма последовательных элементов: ");
        System.out.print(p3(mass3));

        // Задача 4
        int[][] mass4 = {{1, 2}, {3, 4}};
        System.out.print("\n4. Перевёрнутый массив имеет вид: ");
        System.out.print(p4(mass4));

        // Задача 5
        int[] mass5 = {1,4, 2, 3, 8, 0, 7, 6, 5};
        int target = 5;
        System.out.print(p5(mass5, target));

        // Задача 6
        int[][] mass6 = {{1, 2, 3}, {4, 5, 6}};
        System.out.print("\n6. Сумма всех элементов массива: ");
        System.out.print(p6(mass6));

        // Задача 7
        int[][] mass7 = {{1, 2, 3}, {6, 4, 5}, {9, 0, 5}};
        System.out.print("\n7. Максимальные элемента каждой строки: ");
        System.out.print(p7(mass7));

        // Задача 8
        int[][] mass8 = {{1, 2, 3}, {4, 5, 6}};
        System.out.print("\n8. Перевёрнутый массив имеет вид: ");
        System.out.print(p8(mass8));
    }
}