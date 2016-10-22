/**
 * Created by KatyaKos on 19.09.2016.
 * @author KatyaKos
 */
public class Main {
    public static void main(String[] args) {
        int n = 5;
        Matrix matrix = new Matrix(n);
        int[][] temp = {{5, 3, 6, 2, 4}, {1, 5, 9, 13, 17}, {2, 6, 10, 14, 18}, {3, 7, 11, 15, 19}, {4, 8, 12, 16, 20}};

        matrix.set(temp);
        System.out.println("Given:");
        matrix.print();
        int[] spiralMatrix = matrix.spiral();
        System.out.println("Spiral:");
        for (int i = 0; i < n * n; i++) {
            System.out.printf("%d ", spiralMatrix[i]);
        }
        System.out.println('\n');
        matrix.sort();
        System.out.println("Sorted:");
        matrix.print();
    }
}
