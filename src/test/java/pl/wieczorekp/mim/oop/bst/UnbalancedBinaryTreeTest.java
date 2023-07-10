package pl.wieczorekp.mim.oop.bst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnbalancedBinaryTreeTest {
    private static final int RNG_MIN_VALUE = -100_000;
    private static final int RNG_MAX_VALUE = 100_000;
    private static long SEED = 6921749823580L;
    private Random rng;

    @BeforeEach
    void init() {
        rng = new Random(SEED);
    }

    @Test
    void givenSampleDataShouldComputeTreeMethodsCorrectly() {
        // given
        UnbalancedBinaryTree<Integer> tree = new UnbalancedBinaryTree<>();
        Node<Integer> node;

        // when & then
        tree.insert(10);
        node = tree.search(10);
        assertEquals(10, tree.minimum());
        assertEquals(10, tree.maximum());
        assertEquals(10, node.value());
        assertNull(tree.successor(node));
        assertNull(tree.predecessor(node));

        tree.insert(11);
        node = tree.search(11);
        assertEquals(10, tree.minimum());
        assertEquals(11, tree.maximum());
        assertEquals(11, node.value());
        assertNull(tree.successor(node));
        assertEquals(10, tree.predecessor(node));

        tree.delete(11);
        node = tree.search(11);
        assertNull(node);
        assertEquals(10, tree.minimum());
        assertEquals(10, tree.maximum());

        tree.insert(15);
        node = tree.search(15);
        assertEquals(10, tree.minimum());
        assertEquals(15, tree.maximum());
        assertEquals(15, node.value());
        assertNull(tree.successor(node));
        assertEquals(10, tree.predecessor(node));

        tree.insert(7);
        node = tree.search(7);
        assertEquals(7, tree.minimum());
        assertEquals(15, tree.maximum());
        assertEquals(7, node.value());
        assertEquals(10, tree.successor(node));
        assertNull(tree.predecessor(node));

        tree.insert(9);
        node = tree.search(9);
        assertEquals(7, tree.minimum());
        assertEquals(15, tree.maximum());
        assertEquals(9, node.value());
        assertEquals(10, tree.successor(node));
        assertEquals(7, tree.predecessor(node));

        tree.insert(8);
        node = tree.search(8);
        assertEquals(7, tree.minimum());
        assertEquals(15, tree.maximum());
        assertEquals(8, node.value());
        assertEquals(9, tree.successor(node));
        assertEquals(7, tree.predecessor(node));

        tree.insert(6);
        node = tree.search(6);
        assertEquals(6, tree.minimum());
        assertEquals(15, tree.maximum());
        assertEquals(6, node.value());
        assertEquals(7, tree.successor(node));
        assertNull(tree.predecessor(node));

        tree.insert(5);
        node = tree.search(5);
        assertEquals(5, tree.minimum());
        assertEquals(15, tree.maximum());
        assertEquals(5, node.value());
        assertEquals(6, tree.successor(node));
        assertNull(tree.predecessor(node));

        tree.insert(3);
        node = tree.search(3);
        assertEquals(3, tree.minimum());
        assertEquals(15, tree.maximum());
        assertEquals(3, node.value());
        assertEquals(5, tree.successor(node));
        assertNull(tree.predecessor(node));

        tree.insert(20);
        node = tree.search(20);
        assertEquals(3, tree.minimum());
        assertEquals(20, tree.maximum());
        assertEquals(20, node.value());
        assertNull(tree.successor(node));
        assertEquals(15, tree.predecessor(node));

        tree.insert(2);
        node = tree.search(2);
        assertEquals(2, tree.minimum());
        assertEquals(20, tree.maximum());
        assertEquals(2, node.value());
        assertEquals(3, tree.successor(node));
        assertNull(tree.predecessor(node));

        tree.insert(-10);
        node = tree.search(-10);
        assertEquals(-10, tree.minimum());
        assertEquals(20, tree.maximum());
        assertEquals(-10, node.value());
        assertEquals(2, tree.successor(node));
        assertNull(tree.predecessor(node));

        tree.delete(20);
        node = tree.search(20);
        assertNull(node);
        assertEquals(-10, tree.minimum());
        assertEquals(15, tree.maximum());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 10, 100, 1000, 10000})
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void givenNRandomNumbersShouldReturnNonNullTreeNode(int n) {
        // given
        int[] arr = IntStream.generate(() -> n * rng.nextInt(Integer.MAX_VALUE/n)).limit(n).toArray();
        List<Node<Integer>> actual = new ArrayList<>(n);
        UnbalancedBinaryTree<Integer> tree = new UnbalancedBinaryTree<>();

        // when
        for (int i = 0; i < n; i++) {
            tree.insert(arr[i]);
            actual.add(tree.search(arr[i]));
        }

        // then
        for (int i = 0; i < n; i++) {
            assertEquals(arr[i], actual.get(i).value(), "Values at index " + i + " do not match");
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10, 100, 1000, 10000})
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void givenNRandomNumbersShouldSortThemCorrectly(int n) {
        // given
        int[] primitiveArr = IntStream.generate(() -> rng.nextInt(50)).limit(n).toArray();;
        Set<Integer> expected = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            expected.add(primitiveArr[i]);
        }
        Integer[] expectedArr = expected.toArray(new Integer[0]);

        List<Integer> actual = new ArrayList<>(n);
        UnbalancedBinaryTree<Integer> tree = new UnbalancedBinaryTree<>();

        // when
        for (int i = 0; i < n; i++) {
            tree.insert(primitiveArr[i]);
        }
        for (int i = 0; i < n; i++) {
            if (tree.isEmpty()) {
                break;
            }
            int min = tree.minimum();
            actual.add(min);
            tree.delete(min);
        }

        // then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expectedArr.length; i++) {
            assertEquals(expectedArr[i], actual.get(i), "Values at index " + i + " do not match");
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 100, 1_000, 5_000, 10_000})
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void givenASetOfRandomIntegersShouldInsertAndDeleteThemRandomly(int n) {
        // given
        UnbalancedBinaryTree<Integer> tree = new UnbalancedBinaryTree<>();
        SortedSet<Integer> values = new TreeSet<>();
        while (values.size() < n) {
            values.add(rng.nextInt(RNG_MIN_VALUE, RNG_MAX_VALUE+1));
        }

        ArrayList<Integer> valArray = new ArrayList<>(values);
        Collections.shuffle(valArray, rng);

        // when
        for (int i = 0; i < n; i++) {
            tree.insert(valArray.get(i));
        }

        // then
        Collections.shuffle(valArray, rng);

        for (int i = 0; i < n; i++) {
            int x = valArray.get(i);
            int min = values.first();
            int max = values.last();

            assertEquals(min, tree.minimum(), "Minimum mismatch at index " + i);
            assertEquals(max, tree.maximum(), "Maximum mismatch at index " + i);
            tree.delete(x);
            values.remove(x);

            for (Integer val : values) {
                Node<Integer> integerNode = tree.search(val);

                assertEquals(val, integerNode.value(), "Value " + x + " not found in the tree. i = " + i);
            }
        }
    }
}