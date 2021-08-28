

/*
 * Name: Zhiyu Gao
 * PID:  A17245309
 */

import java.util.*;

/**
 * Binary search tree implementation.
 *
 * @author Zhiyu Gao
 * @since 8/25/2021
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node
    private final static String delimeter = " ";

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = left;
            this.right = right;
            this.key = key;
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return right;
        }

        /**
         * Setter for left child of the node
         *
         * @param newLeft New left child
         */
        public void setLeft(BSTNode newLeft) {
            left = newLeft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newRight New right child
         */
        public void setRight(BSTNode newRight) {
            right = newRight;
        }

    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        root = null;
        nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return nelems;
    }

    /**
     * Insert a key into BST
     *
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        BSTNode p = null, curNode = root, insertNode = new BSTNode(null, null, key);
        while (curNode != null) {
            p = curNode;
            if (key.compareTo(curNode.getKey()) == 0) {
                return false;
            } else if (key.compareTo(curNode.getKey()) < 0) {
                curNode = curNode.left;
            } else {
                curNode = curNode.right;
            }
        }
        if (p == null) {
            root = insertNode;
            ++nelems;
            return true;
        }
        if (key.compareTo(p.key) < 0) {
            p.left = insertNode;
        } else {
            p.right = insertNode;
        }
        ++nelems;
        return true;
    }

    /**
     * Find the node which contains the 
     *
     * @param key
     * @return BSTNode the node containing the key, {@code null} if not found
     */
    private BSTNode find(T key) {
        BSTNode curNode = root;
        while (curNode != null) {
            if (key.compareTo(curNode.getKey()) == 0) {
                return curNode;
            } else if (key.compareTo(curNode.getKey()) < 0) {
                curNode = curNode.left;
            } else {
                curNode = curNode.right;
            }
        }
        return curNode;
    }

    /**
     * Return true if the tree contains the 'key', false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean contains(T key) {
        BSTNode curNode = root;
        while (curNode != null) {
            if (key.compareTo(curNode.getKey()) == 0) {
                return true;
            } else if (key.compareTo(curNode.getKey()) < 0) {
                curNode = curNode.left;
            } else {
                curNode = curNode.right;
            }
        }
        return false;
    }

    /**
     * Remove the key from the BST
     *
     * @param key To be removed
     * @return True if the 'key' is removed, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean remove(T key) {
        if (key == null)
            throw new NullPointerException();
        BSTNode removeNode = root;
        while (removeNode != null) {
            if (key.compareTo(removeNode.key) == 0) {
                if (removeNode.left == null) {
                    transplant(removeNode, removeNode.right);
                } else if (removeNode.right == null) {
                    transplant(removeNode, removeNode.left);
                } else {
                    BSTNode preNode = findMin(removeNode.right);
                    if (preNode != removeNode.right) {
                        transplant(preNode, preNode.right);
                        preNode.right = removeNode.right;
                    }
                    transplant(removeNode, preNode);
                    preNode.left = removeNode.left;
                }
                --nelems;
                return true;
            } else if (key.compareTo(removeNode.key) < 0) {
                removeNode = removeNode.left;
            } else {
                removeNode = removeNode.right;
            }
        }
        return false;
    }

    /**
     * Transplant a subtree with a given subtree.
     *
     * @param BSTNode u to be replaced
     * @param BSTNode v to replace
     */
    private void transplant(BSTNode u, BSTNode v) {
        BSTNode p = getParent(u);
        if (p == null) {
            root = v;
        } else if (u == p.left) {
            p.left = v;
        } else {
            p.right = v;
        }
    }

    /**
     * Returns the smallest node from a given node
     *
     * @param root Smallest node will be found from this node
     * @return The smallest node from the 'root' node
     */
    private BSTNode findMin(BSTNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    /**
     * Print the BST nodes by preorder traversal
     *
     * @return string of keys in preorder, separated by a single space (“ ”).
     */
    public String printPreOrder() {
        if (root == null) {
            return "";
        }
        Stack<BSTNode> s = new Stack<>();
        String re = new String();
        BSTNode curNode;
        s.push(root);
        while (!s.isEmpty()) {
            curNode = s.pop();
            re += (curNode.getKey().toString() + delimeter);
            if (curNode.right != null) {
                s.push(curNode.right);
            }
            if (curNode.left != null) {
                s.push(curNode.left);
            }
        }
        return re.substring(0, re.length()-1);
    }

    /**
     * Print the BST nodes by postorder traversal
     *
     * @return string of keys in postorder, separated by a single space (“ ”).
     */
    public String printPostOrder() {
        if (root == null) {
            return "";
        }
        Stack<BSTNode> s = new Stack<>(), temp = new Stack<>();
        s.push(root);
        BSTNode curNode;
        String re = new String();
        while (!s.isEmpty()) {
            curNode = s.peek();
            temp.push(curNode);
            s.pop();
            if (curNode.left != null) {
                s.push(curNode.left);
            }
            if (curNode.right != null) {
                s.push(curNode.right);
            }
        }
        while (!temp.isEmpty()) {
            re += (temp.pop().getKey().toString() + delimeter);
        }
        return re.substring(0, re.length()-1);
    }

    /**
     * Print the BST nodes by inorder traversal
     *
     * @return string of keys in inorder, separated by a single space (“ ”).
     */
    public String printInOrder() {
        if (root == null) {
            return "";
        }
        Stack<BSTNode> s = new Stack<>();
        BSTNode curNode = root;
        String re = new String();
        do {
            while (curNode != null) {
                s.push(curNode);
                curNode = curNode.left;
            }
            curNode = s.pop();
            re += (curNode.getKey().toString() + delimeter);
            if (curNode.right != null) {
                curNode = curNode.right;
            } else
                curNode = null;
        } while (curNode != null || !s.empty());
        return re.substring(0, re.length()-1);
    }

    /**
     * Get the parent given specified node.
     * 
     * @param node given specified key
     * @return string of keys in inorder, separated by a single space (“ ”).
     */
    private BSTNode getParent(BSTNode node) {
        if (node == null)
            throw new NullPointerException();
        BSTNode p = null, curNode = root;
        while (curNode != null) {
            if (node.key.compareTo(curNode.key) == 0) {
                return p;
            } else if (node.key.compareTo(curNode.key) < 0) {
                p = curNode;
                curNode = curNode.left;
            } else {
                p = curNode;
                curNode = curNode.right;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return findHeightHelper(root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root == null)
            return -1;
        return Math.max(findHeightHelper(root.left), findHeightHelper(root.right)) + 1;
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {

        Stack<T> s;

        public BSTree_Iterator() {
            s = new Stack<>();
            BSTNode curNode = root;
            while (curNode != null) {
                s.push(curNode.key);
                curNode = curNode.left;
            }
        }

        public boolean hasNext() {
            return !s.isEmpty();
        }

        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            T re = s.pop();
            BSTNode curNode = find(re).right;
            while (curNode != null) {
                s.push(curNode.key);
                curNode = curNode.left;
            }
            return re;
        }
    }

    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }
}
