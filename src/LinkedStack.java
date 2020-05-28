public class LinkedStack<E> implements Stack<E> {
    private Node<E> topStack;

    private static class Node<E>{
        private E elem;
        private Node<E> next;
        public Node(E e, Node<E> n) {
            elem=e;
            next=n;
        }
    }

    public LinkedStack(){
        this.topStack=null;
    }

    @Override
    public boolean isEmpty(){
        return this.topStack==null;
    }

    @Override
    public E top(){
        return this.topStack.elem;
    }

    @Override
    public void pop(){
        this.topStack=this.topStack.next;
    }

    @Override
    public void push(E e){
        Node<E> aux = this.topStack;
        this.topStack.elem=e;
        this.topStack.next=aux;
    }
}
