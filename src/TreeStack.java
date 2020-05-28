public abstract class TreeStack<E> {

    public LinkedStack<Pair<E, E>> treeStack(LinkedBinarySearchTree<E, E> tree){

        LinkedStack<Pair<E, E>> stack = new LinkedStack<>();

        if(tree.isEmpty()){ return stack; }
        LinkedBinarySearchTree<E,E> t = tree;
        LinkedStack<Pair<E, E>> s = new LinkedStack<>();
        boolean bool = true;
        while(bool){
            if(!t.isEmpty()){
                s.push(t.root());
                t=t.left();
            }else{
                if(s.isEmpty()){ return stack; }
                t=tree.put(tree.getKeyPair(s.top()), tree.getValuePair(s.top()));
                s.pop();
                stack.push(t.root());
                t=t.right();
            }
        }

        return stack; //nunca realiza este return.
    }
}
