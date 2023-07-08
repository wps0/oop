package pl.wieczorekp.mim.oop.bst;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AVLTreeTest {
    private static final long SEED = 12186469L;
    private static final int RNG_MIN_VALUE = -10_000_000;
    private static final int RNG_MAX_VALUE = 10_000_000;
    private static Random rng;
    AVLTree<String> fullBinaryTree;
    AVLNode<String> root, rootLeftChild, rootRightChild, alpha, beta, gamma, delta;

    @BeforeAll
    static void init() {
        rng = new Random(SEED);
    }

    @BeforeEach
    void setupTree() {
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
    @Timeout(value = 3, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
void givenASetOfRandomIntegersShouldRandomSearchThemInLogNTime(int n) {
        // given
        AVLTree<Integer> tree = new AVLTree<>();
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
        for (int i = 0; i < n; i++) {
            int min = values.first();
            assertEquals(min, tree.minimum(), "Mismatch at index " + i);
            tree.delete(min);
            values.remove(min);
        }
    }
}