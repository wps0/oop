package pl.wieczorekp.mim.oop.bst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AVLTreeTest {
    private static final long SEED = 121864693L;
    private static final int RNG_MIN_VALUE = -10_000_000;
    private static final int RNG_MAX_VALUE = 10_000_000;
    private static Random rng;
    AVLTree<String> fullBinaryTree;
    AVLNode<String> root, rootLeftChild, rootRightChild, alpha, beta, gamma, delta;

    @BeforeEach
    void setupEach() {
        rng = new Random(SEED);
        fullBinaryTree = new AVLTree<>();
        root = new AVLNode<>("root");
        fullBinaryTree.setRoot(root);

        rootLeftChild = new AVLNode<>("root left child");
        rootLeftChild.setParent(root);
        root.setLeft(rootLeftChild);

        rootRightChild = new AVLNode<>("root right child");
        rootRightChild.setParent(root);
        root.setRight(rootRightChild);

        alpha = new AVLNode<>("alpha");
        alpha.setParent(rootLeftChild);
        rootLeftChild.setLeft(alpha);

        beta = new AVLNode<>("beta");
        beta.setParent(rootLeftChild);
        rootLeftChild.setRight(beta);

        gamma = new AVLNode<>("gamma");
        gamma.setParent(rootRightChild);
        rootRightChild.setLeft(gamma);

        delta = new AVLNode<>("delta");
        delta.setParent(rootRightChild);
        rootRightChild.setRight(delta);
    }

    @Test
    void rightRotate_rotateRootTest() {
        // when
        fullBinaryTree.rotateRight(root);

        // then
        assertEquals(rootLeftChild, fullBinaryTree.root());
        assertEquals(alpha, rootLeftChild.left());
        assertEquals(root, rootLeftChild.right());
        assertEquals(beta, root.left());
        assertEquals(rootRightChild, root.right());
        assertEquals(gamma, rootRightChild.left());
        assertEquals(delta, rootRightChild.right());
    }

    @Test
    void leftRotate_rotateRootTest() {
        // when
        fullBinaryTree.rotateLeft(root);

        // then
        assertEquals(rootRightChild, fullBinaryTree.root());
        assertEquals(root, rootRightChild.left());
        assertEquals(delta, rootRightChild.right());
        assertEquals(rootLeftChild, root.left());
        assertEquals(gamma, root.right());
        assertEquals(alpha, rootLeftChild.left());
        assertEquals(beta, rootLeftChild.right());
    }

    @Test
    void givenASampleTreeShouldRotateItCorrectly() {
        // given
        AVLTree<Integer> tree = new AVLTree<>();

        // when
        tree.insert(1);
        tree.insert(3);
        tree.insert(2);
        AVLNode<Integer> node1 = (AVLNode<Integer>) tree.search(1);
        AVLNode<Integer> node2 = (AVLNode<Integer>) tree.search(2);
        AVLNode<Integer> node3 = (AVLNode<Integer>) tree.search(3);

        // then
        assertEquals(1, tree.minimum());
        assertEquals(3, tree.maximum());
        assertEquals(1, node1.value());
        assertEquals(2, node2.value());
        assertEquals(3, node3.value());

        assertNull(node2.parent());

        assertEquals(node2, node1.parent());
        assertNull(node1.left());
        assertNull(node1.right());

        assertEquals(node2, node3.parent());
        assertNull(node3.left());
        assertNull(node3.right());
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1_000, 10_000})
    @Timeout(value = 7, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void randomInsertTest(int n) {
        // given
        AVLTree<Integer> tree = new AVLTree<>();
        ArrayList<Integer> values = new ArrayList<>(generateNDifferentInts(n, -2 * n, 2 * n));
        SortedSet<Integer> addedValues = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            values.add(rng.nextInt(-2 * n, 2 * n));
        }
        Collections.shuffle(values, rng);

        // when
        for (int i = 0; i < n; i++) {
            int x = values.get(i);
            tree.insert(x);
            addedValues.add(x);

            assertEquals(addedValues.first(), tree.minimum(), "Minimum mismatch at index " + i);
            assertEquals(addedValues.last(), tree.maximum(), "Maximum mismatch at index " + i);

            for (Integer val : addedValues) {
                Node<Integer> integerNode = tree.search(val);

                assertEquals(val, integerNode.value(), "Value " + x + " not found in the tree. i = " + i);
            }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1_000, 10_000, 100_000, 1_000_000})
    @Timeout(value = 1, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void givenASequenceOfConsecutiveIntegersShouldSortThemInLogarithmicTime(int n) {
        // given
        AVLTree<Integer> tree = new AVLTree<>();

        // when
        for (int i = 0; i < n; i++) {
            tree.insert(i);
        }

        // then
        for (int i = 0; i < n; i++) {
            assertEquals(tree.minimum(), i, "Mismatch at index " + i);
            tree.delete(i);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1_000, 10_000, 100_000, 1_000_000})
    @Timeout(unit = TimeUnit.MILLISECONDS, value = 3500, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void givenASetOfRandomIntegersShouldRandomSearchThemInLogNTime(int n) {
        // given
        AVLTree<Integer> tree = new AVLTree<>();
        SortedSet<Integer> values = generateNDifferentInts(n, RNG_MIN_VALUE, RNG_MAX_VALUE + 1);
        ArrayList<Integer> valArray = new ArrayList<>(values);
        Collections.shuffle(valArray, rng);

        // when
        for (int i = 0; i < n; i++) {
            tree.insert(valArray.get(i));
        }

        // then
        for (int i = 0; i < n; i++) {
            int min = values.first();
            assertEquals(min, tree.minimum(), "Mismatch at index " + i);
            tree.delete(min);
            values.remove(min);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 30, 40, 50, 60, 70, 80, 90, 100, 1_000, 10_000, 100_000, 1_000_000})
    @Timeout(value = 4, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void givenASetOfRandomIntegersShouldInsertAndDeleteThemMaintainingTheAVLTreeStructure(int n) {
        AVLTree<Integer> tree = new AVLTree<>();
        SortedSet<Integer> values = generateNDifferentInts(n, RNG_MIN_VALUE, RNG_MAX_VALUE + 1);
        ArrayList<Integer> valArray = new ArrayList<>(values);
        Collections.shuffle(valArray, rng);

        // when
        for (int i = 0; i < n; i++) {
            tree.insert(valArray.get(i));
        }

        // then
        Collections.shuffle(valArray, rng);

        for (int i = 0; i < n; i++) {
            int min = values.first();
            int max = values.last();

            assertEquals(min, tree.minimum(), "Minimum mismatch at index " + i);
            assertEquals(max, tree.maximum(), "Maximum mismatch at index " + i);
            tree.delete(valArray.get(i));
            values.remove(valArray.get(i));

        }
    }

    private static SortedSet<Integer> generateNDifferentInts(int n, int lb, int ub) {
        SortedSet<Integer> values = new TreeSet<>();
        while (values.size() < n) {
            values.add(rng.nextInt(lb, ub));
        }
        return values;
    }
}