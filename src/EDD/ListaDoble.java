package EDD;

import Worker.Worker;

public class ListaDoble {
    private Nodo head;//cabeza
    private Nodo tail;//ultimo nodo
    private int size;

    public ListaDoble() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public Nodo getHead() {
        return head;
    }

    public void setHead(Nodo head) {
        this.head = head;
    }

    public Nodo getTail() {
        return tail;
    }

    public void setTail(Nodo tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
    public boolean isEmpty(){
        return head == null;
    }
    
    public Nodo insertBegin(Worker data) {
        Nodo node = new Nodo(data);
        if (isEmpty()) {
            setHead(node);
            setTail(node);
        } else {
            node.setNext(getHead());
            getHead().setPrevious(node);
            setHead(node);
            Nodo pointer = getHead();
            while (pointer.getNext() != null) {
                pointer = pointer.getNext();
            }
            setTail(pointer);
        }
        size++;
        return node;
    }
    
    public Nodo insertFinal(Worker data){
        Nodo node = new Nodo(data);
        if (isEmpty()) {
            setHead(node);
            setTail(node);
        } else {
            Nodo pointer = getHead();
            while (pointer.getNext() != null) {
                pointer = pointer.getNext();
            }
            pointer.setNext(node);
            node.setPrevious(pointer);
            setTail(node);
        }
        size++;
        return node;
    }
    
    public Nodo insertInIndex(int index, Worker data) {
        Nodo node = new Nodo(data);
        if (isEmpty()) {
            setHead(node);
        } else {
            if (index > getSize()) {
                System.out.println("The index is bigger than the size");
                return insertFinal(data);
            } else {
                if (index > getSize() / 2) {
                    Nodo pointer = getTail();
                    int cont = 0;
                    while (cont < (getSize() - index) && pointer.getPrevious()!= null) {
                        pointer = pointer.getPrevious();
                        cont++;
                    }
                    //5 8 20 1 2 3 4 12
                    //cont 1 pointer 20
                    node.setNext(pointer.getNext());
                    node.setPrevious(pointer);
                    pointer.getNext().setPrevious(node);
                    pointer.setNext(node);
                } else {
                    Nodo pointer = getHead();
                    int cont = 0;
                    while (cont < (index-1) && pointer.getNext()!= null) {
                        pointer = pointer.getNext();
                        cont++;
                    }
                    node.setNext(pointer.getNext());
                    node.setPrevious(pointer);
                    pointer.getNext().setPrevious(pointer);
                    pointer.setNext(node);
                }
            }
        }
        size++;
        return node;
    }
    
    public Nodo deleteBegin(){
        if (!isEmpty()){
            if (getSize() == 1) {
                setTail(null);
            }
            Nodo pointer = getHead();
            setHead(pointer.getNext());
            pointer.setNext(null);
            size--;
            return pointer;
        }
        return null;
    }
    
    public Nodo deleteFinal(){
        if (!isEmpty()){
            Nodo pointer = getHead();
            if (getSize() == 1) {
                setHead(null);
                size--;
            } else {
                while (pointer.getNext() != null && pointer.getNext().getNext() != null){
                    pointer = pointer.getNext();
                }
                pointer.getNext().setPrevious(null);
                Nodo nodeReturn = pointer.getNext();
                pointer.setNext(null);
                setTail(pointer);
                size--;
                return nodeReturn;
            }
        }
        return null;
    }
    
    public Nodo deleteInIndex(int index) {
        if (!isEmpty()){
            Nodo pointer = getHead();
            if (index > getSize()) {
                System.out.println("The index is bigger than the size");
                return deleteFinal();
            } else {
                
                if (index > getSize() / 2) {
                    pointer = getTail();
                    int cont = 0;
                    while (cont < (getSize() - index -1) && pointer.getPrevious()!= null) {
                        pointer = pointer.getPrevious();
                        cont++;
                    }
                    Nodo temp = pointer.getNext();
                    pointer.setNext(temp.getNext());
                    temp.getNext().setPrevious(pointer);
                    temp.setNext(null);
                    temp.setPrevious(null);
                    size--;
                    
                } else {
                    pointer = getHead();
                    int cont = 0;
                    while (cont < (index-1) && pointer.getNext()!= null) {
                        pointer = pointer.getNext();
                        cont++;
                    }
                    Nodo temp = pointer.getNext();
                    pointer.setNext(temp.getNext());
                    temp.getNext().setPrevious(pointer);
                    temp.setNext(null);
                    temp.setPrevious(null);
                    size--;
                }
            }
        }
        return null;
    }
    
    public void printList(){
        Nodo aux = head;
        for (int i = 0; i < getSize(); i++) {
            System.out.println(aux.getElement());
            aux = aux.getNext();
        }
    }
    
    public void printListInvers(){
        Nodo aux = tail;
        for (int i = 0; i < getSize(); i++) {
            System.out.println(aux.getElement());
            aux = aux.getPrevious();
        }
    } 
}