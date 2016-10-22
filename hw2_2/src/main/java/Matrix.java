/**
 * Created by KatyaKos on 19.09.2016.
 * @author KatyaKos
 * Matrix of int elements with sizes NxN where N is odd.
 */
public class Matrix {
    private int[][] matrix;
    private int size;

    public Matrix() {
        matrix = new int[11][11];
        size = 11;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 10 * i + j;
            }
        }
    }

    /**
     * Constructor with $size parameter.
     * @param size - size of Matrix you need. If $size is not odd than this Matrix's size will be 2 * $size + 1.
     */
    public Matrix(int size) {
        if (size % 2 == 1) {
            matrix = new int[size][size];
            this.size = size;
        } else {
            this.size = 2 * size + 1;
            matrix = new int[size][size];
        }
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                matrix[i][j] = 10 * i + j;
            }
        }
    }

    /**
     * Returns int[][] Matrix.
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Returns Matrix's size.
     */
    public int size() {
        return size;
    }

    /**
     * Sets values in Matrix. If $matrix has different sizes from ours than this method does nothing.
     * @param matrix int[][] with values
     */
    public void set(int[][] matrix) {
        if (matrix.length != size) {
            return;
        }
        for (int i = 0; i < size; i++) {
            if (matrix[i].length != size) {
                return;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    /**
     * Prints matrix to console
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
        int i = size / 2;
        int j = size / 2;
        int[] answer = new int[size * size];
        answer[0] = matrix[i][j];
        int done = 1;

        for (int step = 1; step < size ; step++) {
            for (int twice = 1; twice <= 2; twice++) {
                for (int move = 0; move < step; move++) {
                    i += (twice % 2) * (step % 2 - 1 * ((step - 1) % 2));
                    j += ((twice - 1) % 2) * (step % 2 - 1 * ((step - 1) % 2));
                    answer[done] = matrix[i][j];
                    done++;
                }
            }
        }
        for (int step = 1; step < size; step++) {
            i++;
            answer[done] = matrix[i][j];
            done++;
        }
        return answer;
    }

    /**
     * Merges two arrays and remembers switched positions.
     * @param leftBound arr1 = [leftBound, middle)
     * @param middle arr1 = [leftBound, middle); arr2 = [middle, rightBound)
     * @param rightBound arr2 = [middle, rightBound)
     */
    private void merge(int leftBound, int middle, int rightBound, int[] positions) {
        int lLength = middle - leftBound;
        int rLength = rightBound - middle;
        int length = lLength + rLength;
        int i = 0;
        int j = 0;
        int[] tempMatrix = new int[length];
        int[] tempPositions = new int[length];
        for (int k = 0; k < length; k++) {
            if (i < lLength && (j == rLength || matrix[0][leftBound + i] <= matrix[0][middle + j])) {
                tempMatrix[k] = matrix[0][leftBound + i];
                tempPositions[k] = positions[leftBound + i];
                i++;
            } else {
                tempMatrix[k] = matrix[0][middle + j];
                tempPositions[k] = positions[middle + j];
                j++;
            }
        }
        for (int k = leftBound; k < rightBound; k++) {
            matrix[0][k] = tempMatrix[k - leftBound];
            positions[k] = tempPositions[k - leftBound];
        }
    }

    /**
     * Sorts array in bounds l and r.
     * @param leftBound arr = [leftBound, rightBound)
     * @param rightBound arr = [leftBound, rightBound)
     */
    private void sort(int leftBound, int rightBound, int[] positions) {
        if (leftBound + 1 >= rightBound) {
            return;
        }
        int middle = (leftBound + rightBound) / 2;
        sort(leftBound, middle, positions);
        sort(middle, rightBound, positions);
        merge(leftBound, middle, rightBound, positions);
    }

    /**
     * Sorts the whole matrix.
     */
    public void sort() {
        int[][] old = new int[size][size];
        int[] positions = new int[size];
        for (int i = 0; i < size; i++) {
            positions[i] = i;
            for (int j = 0; j < size; j++) {
                old[i][j] = matrix[i][j];
            }
        }

        int leftBound = 0;
        int rightBound = size;
        int middle = (leftBound + rightBound) / 2;
        sort(leftBound, middle, positions);
        sort(middle, rightBound, positions);
        merge(leftBound, middle, rightBound, positions);

        for (int j = 0; j < size; j++) {
            int pos = positions[j];
            for (int i = 1; i < size; i++) {
                matrix[i][j] = old[i][pos];
            }
        }
    }

}
