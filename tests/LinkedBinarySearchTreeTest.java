import java.util.Comparator;

import static org.junit.Assert.*;

public class LinkedBinarySearchTreeTest {

    Comparator<Integer> cmp =  new Comparator<Integer>() {
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    };

    @org.junit.Test
    public void isEmpty() {
        LinkedBinarySearchTree<Integer, Integer> link = new LinkedBinarySearchTree<>(cmp);
        assertTrue(link.isEmpty());
    }

    @org.junit.Test
    public void containsKey() {
        LinkedBinarySearchTree<Integer, Integer> link = new LinkedBinarySearchTree<>(cmp);
        link = link.put(4, 40);
        link = link.put(2, 20);
        link = link.put(9, 12);
        assertTrue(link.containsKey(9));
        assertFalse(link.containsKey(1));
    }

    @org.junit.Test
    public void get(){
        LinkedBinarySearchTree<Integer, Integer> link = new LinkedBinarySearchTree<>(cmp);
        link = link.put(4, 40);
        link = link.put(2, 20);
        link = link.put(3,50);
        link =link.remove(3);
        assertEquals(link.get(2), (Integer) 20);
    }

    @org.junit.Test
    public void put() {

        LinkedBinarySearchTree<Integer, Integer> link = new LinkedBinarySearchTree<>(cmp);
        link = link.put(4, 40);
        link = link.put(2, 20);
        link.put(2, 90);
        //su funcionamiento se comprueba con los anteriores.
    }

    @org.junit.Test
    public void remove() {

        LinkedBinarySearchTree<Integer, Integer> link = new LinkedBinarySearchTree<>(cmp);
        link = link.put(4, 40);
        link = link.put(2, 20);
        link = link.put(9, 90);
        link.remove(19);
        //su funcionamiento se comprueba con los anteriores.
    }
}