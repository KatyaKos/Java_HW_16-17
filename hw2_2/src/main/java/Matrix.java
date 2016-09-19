/**
 * Created by KatyaKos on 19.09.2016.
 * @author KatyaKos
 */
public class Matrix {
    protected int[][] matrix;
    private int n;
    private int[] positions;

    public Matrix() {
        matrix = new int[11][11];
        n = 11;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 10 * i + j;
            }
        }
    }

    public Matrix(int m) {
        if (m % 2 == 1) {
            matrix = new int[m][m];
            n = m;
        } else {
            n = 2 * m + 1;
            matrix = new int[n][n];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 10 * i + j;
            }
        }
    }

    /**
     * @return int[][] matrix
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Sets values in matrix
     * @param mat int[][] with values
     */
    public void set(int[][] mat) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = mat[i][j];
            }
        }
    }

    /**
     * Prints matrix to console
     */
    public void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * @return array of values sorted in order for spiral bypass from the center
     */
    public int[] spiral() {
        int i = n / 2, j = n / 2;
        int[] answer = new int[n * n];
        answer[0] = matrix[i][j];
        int done = 1;

        for (int step = 1; step < n ; step++) {
            for (int twice = 1; twice <= 2; twice++) {
                for (int move = 0; move < step; move++) {
                    i += (twice % 2) * (step % 2 - 1 * ((step - 1) % 2));
                    j += ((twice - 1) % 2) * (step % 2 - 1 * ((step - 1) % 2));
                    answer[done] = matrix[i][j];
                    done++;
                }
            }
        }
        for (int step = 1; step < n; step++) {
            i++;
            answer[done] = matrix[i][j];
            done++;
        }
        return answer;
    }

    /**
     * Merges two arrays and remembers switched positions.
     * @param l arr1 = [l, m)
     * @param m arr1 = [l, m); arr2 = [m, r)
     * @param r arr2 = [m, r)
     */
    private void merge(int l, int m, int r) {
        int lLength = m - l, rLength = r - m, length = lLength + rLength;
        int i = 0, j = 0;
        int[] tempMatrix = new int[length];
        int[] tempPositions = new int[length];
        for (int k = 0; k < length; k++) {
            if (i < lLength && (j == rLength || matrix[0][l + i] <= matrix[0][m + j])) {
                tempMatrix[k] = matrix[0][l + i];
                tempPositions[k] = positions[l + i];
                i++;
            } else {
                tempMatrix[k] = matrix[0][m + j];
                tempPositions[k] = positions[m + j];
                j++;
            }
        }
        for (int k = l; k < r; k++) {
            matrix[0][k] = tempMatrix[k - l];
            positions[k] = tempPositions[k - l];
        }
    }

    /**
     * Sorts array in bounds l and r.
     * @param l arr = [l, r)
     * @param r arr = [l, r)
     */
    private void sort(int l, int r) {
        if (l + 1 >= r) {
            return;
        }
        int m = (l + r) / 2;
        sort(l, m);
        sort(m, r);
        merge(l, m, r);
    }

    /**
     * Sorts the whole matrix.
     */
    public void sort() {
        int[][] old = new int[n][n];
        positions = new int[n];
        for (int i = 0; i < n; i++) {
            positions[i] = i;
            for (int j = 0; j < n; j++) {
                old[i][j] = matrix[i][j];
            }
        }

        int l = 0, r = n;
        int m = (l + r) / 2;
        sort(l, m);
        sort(m, r);
        merge(l, m, r);

        for (int j = 0; j < n; j++) {
            int pos = positions[j];
            for (int i = 1; i < n; i++) {
                matrix[i][j] = old[i][pos];
            }
        }
    }

}
