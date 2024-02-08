package EDD;

import Worker.Worker;

public class Nodo {
    private Worker element;
    private Nodo next;
    private Nodo previous;

    public Nodo(Worker element) {
        this.element = element;
        this.next = null;
        this.previous = null;
    }

    
    public Worker getElement() {
        return element;
    }

    
    public void setElement(Worker element) {
        this.element = element;
    }

    
    public Nodo getNext() {
        return next;
    }

    
    public void setNext(Nodo next) {
        this.next = next;
    }

    
    public Nodo getPrevious() {
        return previous;
    }

    
    public void setPrevious(Nodo previous) {
        this.previous = previous;
    }
}