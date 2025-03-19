package task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShellSortTest {

    private final ShellSort shellSort = new ShellSort();

    @Test
    void testSortedArray_OK() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        shellSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testRandomArray_OK() {
        int[] input = {5, 2, 9, 1, 5, 6};
        int[] expected = {1, 2, 5, 5, 6, 9};

        shellSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testReverseSortedArray_OK() {
        int[] input = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};

        shellSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testArrayWithDuplicates_OK() {
        int[] input = {10, 10, 10, 7, 10};
        int[] expected = {7, 10, 10, 10, 10};

        shellSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testEmptyArray_OK() {
        int[] input = {};
        int[] expected = {};

        shellSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSingleNumberArray_OK() {
        int[] input = {2};
        int[] expected = {2};

        shellSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testWithNegativeNumbersArray_OK() {
        int[] input = {3, -1, 4, 1, 5, -9, 2, 0, 6, 5};
        int[] expected = {-9, -1, 0, 1, 2, 3, 4, 5, 5, 6};

        shellSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testArrayWithExtremeValues_OK() {
        int[] input = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        int[] expected = {Integer.MIN_VALUE, 0, Integer.MAX_VALUE};
        shellSort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    void testLargeArray_OK() {
        int[] randomArray = {259, 377, 68, -296, 22, 36, -484, 280, 356, 117, -16, 443, -318, 226, 208, 107, 237, 53, -466, 384,
                324, 99, -391, -9, 494, 275, 179, -422, 236, -159, -255, 146, -315, 261, 336, -52, -365, -498, -358,
                -77, 480, -243, 139, 326, 329, -313, -47, 77, 274, 458, 77, -452, -213, 446, -269, 407, -24, 304, 8,
                -309, 173, 464, -434, 348, 233, 431, 52, -133, -169, 422, 417, 230, 158, -112, 103, 367, 77, -11,
                -241, -104, -376, -214, 267, 173, -182, -281, -328, 75, -78, 129, -437, 428, -199, 372, -110, 34,
                177, 207, 347, -469, -83, 93, -246, -153, -310, 344, 140, 14, 371, 229, -275, 487, -35, 96, -277,
                -266, 182, -18, -432, -225, -268, 184, -93, 303, -163, -185, 4, 398, 5, 231, -160, -350, -217, 127,
                -32, -167, 95, -115, -110, -116, 120, 404, -422, 194, 424, 33, -282, -452, 458, 286};
        int[] expected = {-498, -484, -469, -466, -452, -452, -437, -434, -432, -422, -422, -391, -376, -365, -358, -350,
                -328, -318, -315, -313, -310, -309, -296, -282, -281, -277, -275, -269, -268, -266, -255, -246,
                -243, -241, -225, -217, -214, -213, -199, -185, -182, -169, -167, -163, -160, -159, -153, -133,
                -116, -115, -112, -110, -110, -104, -93, -83, -78, -77, -52, -47, -35, -32, -24, -18, -16, -11,
                -9, 4, 5, 8, 14, 22, 33, 34, 36, 52, 53, 68, 75, 77, 77, 77, 93, 95, 96, 99, 103, 107, 117, 120,
                127, 129, 139, 140, 146, 158, 173, 173, 177, 179, 182, 184, 194, 207, 208, 226, 229, 230, 231, 233,
                236, 237, 259, 261, 267, 274, 275, 280, 286, 303, 304, 324, 326, 329, 336, 344, 347, 348, 356, 367,
                371, 372, 377, 384, 398, 404, 407, 417, 422, 424, 428, 431, 443, 446, 458, 458, 464, 480, 487, 494};

        shellSort.sort(randomArray);

        assertArrayEquals(expected, randomArray);
    }

    @Test
    void testNullArray_ThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> shellSort.sort(null));
    }
}
