package study.chapter1;

public class Deque<T> { 
    private class DeNode{
        T item;
        DeNode front;
        DeNode next;
    }   
    private DeNode first;
    private DeNode last;
    private int currentLength;

    public void addFirst(T item) {
        DeNode newNode=new DeNode();
        newNode.item=item;
        newNode.next=first;
        if (isEmpty()) {
            first=newNode;
            last=first;
        }else {
            first.front=newNode;
            first=newNode;
        }
    }

    public void addLast(T item) {
        DeNode newNode=new DeNode();
        newNode.item=item;
        newNode.front=last;
        if (isEmpty()) {
            last=newNode;
            first=last;
        }else {
            last.next=newNode;
            last=newNode;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        T item=first.item;
        first.next.front=null;
        first=first.next;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        T item=last.item;
        last.front.next=null;
        last=last.front;
        return item;
    }

    public T peek() {
        DeNode f=first;
        return (f==null)?null:f.item;
    }

    public boolean isEmpty() {
        return first==null&&last==null;
    }

    public int size() {
        return currentLength;
    }
}
