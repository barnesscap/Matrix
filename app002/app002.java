import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class app002 {
    public static void main(String[] args) throws Exception {
        Start();
    }

    private static void Start() throws Exception {
        int input=-1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (input != 1 & input != 2) {
            try {
                System.out.print("Введите 1 для нахождения детерминанта матрицы или 2 для трансформации матрицы: ");
                input = Integer.parseInt(reader.readLine());
                if (input != 1 & input != 2) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Введите правильное значение!");
            }
        }
        if (input==1) StartDeterminat();
        if (input==2) StartTranform();
    }

    private static void StartTranform() throws Exception {
        int height = -1;
        int width =-1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (height <= 0) {
            try {
                System.out.print("Введите высоту матрицы: ");
                height = Integer.parseInt(reader.readLine());
                if (height < 1) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Введеное число меньше 1 или размер измеряется в натуральных числах!! Попробуйте ещё раз.");
                height = -1;
            }
        }
        while (width <= 0) {
            try {
                System.out.print("Введите ширину матрицы: ");
                width = Integer.parseInt(reader.readLine());
                if (width < 1) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Введеное число меньше 1 или размер измеряется в натуральных числах!! Попробуйте ещё раз.");
                width = -1;
            }
        }
        int[][] array = new int[height][width];
        for (int k = 0; k < height; k++) {
            for (int n = 0; n < width; ) {
                int r = k + 1;
                int s = n + 1;
                System.out.println("Введите число в ячейку ряд " + r + " столбец " + s + ":");
                try {
                    array[k][n] = Integer.parseInt(reader.readLine());
                    n++;
                } catch (IllegalArgumentException ex) {
                    System.out.println("Веденное значение должно быть целочисельным числом! Попробуйте ещё раз.");
                }
            }
        }
        System.out.println();
        System.out.println();
        for (int k = 0; k < height; k++) {
            for (int n = 0; n < width; n++) {
                System.out.print(array[k][n] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        WorkWithArrays Work = new WorkWithArrays();
        int [][] newArray = Work.TransformMatrice(array);

        for (int k = 0; k < newArray.length; k++) {
            for (int n = 0; n < newArray[0].length; n++) {
                System.out.print(newArray[k][n] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void StartDeterminat() throws Exception {
        int sizeArray = -1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (sizeArray <= 0) {
            try {
                System.out.print("Введите размер матрицы: ");
                sizeArray = Integer.parseInt(reader.readLine());
                if (sizeArray < 1) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Введеное число меньше 1 или размер измеряется в натуральных числах!! Попробуйте ещё раз.");
                sizeArray = -1;
            }
        }
        int[][] array = new int[sizeArray][sizeArray];
        for (int k = 0; k < sizeArray; k++) {
            for (int n = 0; n < sizeArray; ) {
                int r = k + 1;
                int s = n + 1;
                System.out.println("Введите число в ячейку ряд " + r + " столбец " + s + ":");
                try {
                    array[k][n] = Integer.parseInt(reader.readLine());
                    n++;
                } catch (IllegalArgumentException ex) {
                    System.out.println("Веденное значение должно быть целочисельным числом! Попробуйте ещё раз.");
                }
            }
        }
        System.out.println();
        System.out.println();
        for (int k = 0; k < sizeArray; k++) {
            for (int n = 0; n < sizeArray; n++) {
                System.out.print(array[k][n] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        WorkWithArrays Work = new WorkWithArrays();
        System.out.println(Work.DeterminatMatrix(array));
    }
}

class WorkWithArrays {

    private static int[][] ArraysRCRemove(int[][] array, int indX, int indY) { //Даємо масив і номери строки та стовбця
        int [][] arrayRes = ArraysRowRemove(array, indX); //один метод для строк
        arrayRes = ArraysColumnRemove(arrayRes, indY); //другий для стовбців
        return arrayRes;
    }

    private static int[][] ArraysRowRemove(int[][] array, int indX) {
        if (indX < array.length & indX > -1) {
            int newArray[][] = new int[array.length - 1][array[0].length];
            for (int k = 0; k < array.length; k++) {
                for (int n = 0; n < array[0].length; n++) {
                    if (k == indX) continue;
                    if (k < indX) {
                        if (k == array.length - 1) newArray[k - 1][n] = array[k][n];
                        else newArray[k][n] = array[k][n];
                    } else newArray[k - 1][n] = array[k][n];
                }
            }
            return newArray;
        } else return array;
    }

    private static int[][] ArraysColumnRemove(int[][] array, int indY) {
        if (indY < array[0].length & indY > -1) {
            int newArray[][] = new int[array.length][array[0].length - 1];
            for (int a = 0; a < array.length; a++) {
                for (int b = 0; b < array[0].length; b++) {
                    if (b == indY) continue;
                    if (b < indY) {
                        if (b == array[0].length - 1) newArray[a][b - 1] = array[a][b];
                        else newArray[a][b] = array[a][b];
                    } else newArray[a][b - 1] = array[a][b];
                }
            }
            return newArray;
        } else return array;
    }

    public static int DeterminatMatrix(int[][] matrix) {
        int result = 0;
        List<Integer> list = new ArrayList<>();
        if (matrix.length == 2) {
            result = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        } else {
            for (int k =0; k<matrix[0].length; k++) {
                list.add(matrix[0][k] * DeterminatMatrix(ArraysRCRemove(matrix, 0, k)));
            }
        }
        for(int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) result += list.get(i);
            else result -= list.get(i);
        }
        return result;
    }

    public static int [][] TransformMatrice (int [][] matrix) {
        int [][]resMatrix = new int [matrix[0].length][matrix.length];
        for (int k=0; k<matrix.length; k++) {
            for (int n=0; n<matrix[0].length; n++) {
                resMatrix [n][k] = matrix[k][n];
            }
        }
        return resMatrix;
    }
}
