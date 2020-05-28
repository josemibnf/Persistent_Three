import java.util.Comparator;


public class LinkedBinarySearchTree<K, V>
        implements BinarySearchTree<K, V>, BinaryTree<Pair<K, V>>{

    private final Node<K, V> root;
    private final Comparator<K> comparator;



    private static class Node<K, V> {
        private final K key;
        private final V value;
        private final Node<K, V> left;
        private final Node<K, V> right;

        private Node(K key, V value, Node<K,V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this. left = left;
            this.right = right;
        }
    }

    public LinkedBinarySearchTree(Comparator<K> comparator) {
        this.comparator = comparator;
        this.root = null;
    }

    private LinkedBinarySearchTree(Comparator<K> comparator, Node<K, V> root) {
        this.comparator = comparator;
        this.root = root;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(key, this.root);
    }

    private boolean containsKey(K key, Node root){
        return root.key == key || ( root.right!=null && containsKey(key, root.right) ) || ( root.left!=null &&containsKey( key, root.left) );
    }



    @Override
    public V get(K key) {
        if(key==null){
            return null;
        }else{
            return get(key, this.root);
        }
    }

    private V get(K key, Node root){
        if(root==null){return null;}
        else if(root.key == key){
            return (V) root.value;
        }else if(containsKey( key, root.left)){
            return get(key, root.left);
        }else if(containsKey( key, root.right)){
            return get(key, root.right);
        }else{
            return null;
        }
    }



    @Override
    public LinkedBinarySearchTree<K, V> put(K key, V value) {
        if (key == null) {
            return null;
        } else{
            return put(key, value, this.root);
        }
    }

    private LinkedBinarySearchTree<K, V> put(K key, V value, Node root){
        if(root == null){
            return new LinkedBinarySearchTree(this.comparator, new Node<>(key, value, null, null));
        }

        else if(root.key == key){
            return new LinkedBinarySearchTree(this.comparator, new Node<>(key, value, root.left, root.right));
        }
        else if (comparator.compare(key,  (K)root.key) > 0) {  //key > root.key
            return new LinkedBinarySearchTree(this.comparator, new Node(root.key, root.value,  root.left, putn( key, value, root.right)));
        }
        else{                                                  //key < root.key

            return new LinkedBinarySearchTree(this.comparator, new Node(root.key, root.value, putn( key, value, root.left), root.right));
        }
    }

    private Node<K, V> putn(K key, V value, Node root){
        if(root == null){
            return new Node<>(key, value, null, null);
        }

        else if(root.key == key){
            return new Node<>(key, value, root.left,root.right);
        }

        else if (comparator.compare(key,  (K)root.key) > 0) {  //key > root.key
            return (Node<K, V>) new Node(root.key, root.value, root.left, putn( key, value, root.right));
        }

        else{                                                  //key < root.key
            return (Node<K, V>) new Node(root.key, root.value, putn( key, value, root.left), root.right);
        }
    }



    @Override
        public LinkedBinarySearchTree<K, V> remove(K key) {
            return remove( key, this.root);
        }

        private LinkedBinarySearchTree<K, V> remove(K key, Node root){

            if(root.key == key){
                return new LinkedBinarySearchTree(this.comparator);

            }else if (comparator.compare(key,  (K)root.key) > 0 && root.right!=null) {  //key > root.key y existe el hijo
                if(containsKey(key)) {
                    return new LinkedBinarySearchTree(this.comparator, new Node(root.key, root.value, root.left, removen(key, root.right)));
                }else {
                    return new LinkedBinarySearchTree(this.comparator, root);
                }
            }else if(comparator.compare(key,  (K)root.key) < 0 && root.left!=null){    //key < root.key y existe el hijo
                if(containsKey(key)) {
                    return new LinkedBinarySearchTree(this.comparator, new Node(root.key, root.value, removen(key, root.left), root.right));
                }else {
                    return new LinkedBinarySearchTree(this.comparator, root);
                }
            }else{  // key no se encuentra en el ABB (no es igual a key y tampoco hay hijos).
                return new LinkedBinarySearchTree<>(this.comparator, this.root);
            }
        }

        private Node<K, V> removen(K key, Node root){
            if(root.key == key){
                return null;
            }else if (comparator.compare(key,  (K)root.key) > 0 && root.right!=null) {  //key > root.key y existe el hijo
                return new Node(root.key, root.value, root.left, removen( key, root.right));

            }else if(comparator.compare(key,  (K)root.key) < 0 && root.left!=null){      //key < root.key y existe el hijo
                return new Node(root.key, root.value, removen( key, root.left), root.right);

            }else{    // key no se encuentra en el ABB
                return new Node(root.key, root.value, root.left, root.right);
            }
    }


    @Override
    public Pair<K, V> root(){
        if(this.isEmpty()){
            throw new NullPointerException("Árbol vacío");

        }else{
            if(this.root==null){return null;}
            return new Pair<>(this.root.key, this.root.value);
        }
    }

    public K getKeyPair(Pair<K, V> pair){
        return pair.first();
    }

    public V getValuePair(Pair<K, V> pair){
        return pair.second();
    }

    @Override
    public LinkedBinarySearchTree<K, V> left() {
        if(this.isEmpty()) {
            throw new NullPointerException("Árbol vacío");

        }else if(this.root.left==null){
            return null;

        }else{
            return new LinkedBinarySearchTree<>(this.comparator, this.root.left);
        }
    }

    @Override
    public LinkedBinarySearchTree<K, V> right() {
        if(this.isEmpty()) {
            throw new NullPointerException("Árbol vacío");

        }else if(this.root.right==null){
            return null;

        }else{
            return new LinkedBinarySearchTree<>(this.comparator, this.root.right);
        }
    }
}