import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 19.09.2016.
 * @author KatyaKos
 */
public class MatrixTest {
    Matrix matrix;

    private void resize3() {
        matrix = new Matrix(3);
        int[][] temp = {{5, 3, 6}, {1, 5, 9}, {2, 6, 10}};
        matrix.set(temp);
    }

    private void resize1() {
        matrix = new Matrix(1);
        int[][] temp = {{1}};
        matrix.set(temp);
    }

    @Before
    public void pretest() throws Exception {
        matrix = new Matrix(5);
        int[][] temp = {{5, 3, 6, 2, 4}, {1, 5, 9, 13, 17}, {2, 6, 10, 14, 18}, {3, 7, 11, 15, 19}, {4, 8, 12, 16, 20}};
        matrix.set(temp);
    }

    @Test
    public void spiralTest() throws Exception {
        int[] correct5 = {10, 11, 15, 14, 13, 9, 5, 6, 7, 8, 12, 16, 20, 19, 18, 17, 4, 2, 6, 3, 5, 1, 2, 3, 4};
        assertArrayEquals(correct5, matrix.spiral());

        resize3();
        int[] correct3 = {5, 6, 10, 9, 6, 3, 5, 1, 2};
        assertArrayEquals(correct3, matrix.spiral());

        resize1();
        int[] correct1 = {1};
        assertArrayEquals(correct1, matrix.spiral());
    }

    @Test
    public void sortTest() throws Exception {
        int[][] correct5 = {{2, 3, 4, 5, 6}, {13, 5, 17, 1, 9}, {14, 6, 18, 2, 10}, {15, 7, 19, 3, 11}, {16, 8, 20, 4, 12}};
        matrix.sort();
        assertArrayEquals(correct5, matrix.getMatrix());

        resize3();
        int[][] correct3 = {{3, 5, 6}, {5, 1, 9}, {6, 2, 10}};
        matrix.sort();
        assertArrayEquals(correct3, matrix.getMatrix());

        resize1();
        int[][] correct1 = {{1}};
        matrix.sort();
        assertArrayEquals(correct1, matrix.getMatrix());
    }

}