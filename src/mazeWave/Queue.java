package mazeWave;

import java.util.NoSuchElementException;

/** Singly linked queue */
public class Queue<T> {

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) { this.value = value; }
    }

    private Node<T> front;
    private Node<T> back;
    private int size;

    /** Adds a value to the back of the queue. */
    public void enqueue(T value) {
        Node<T> node = new Node<>(value);
        if (back == null) {
            front = node;
        } else {
            back.next = node;
        }
        back = node;
        size++;
    }

    /** Removes and returns the front value. Throws if the queue is empty. */
    public T dequeue() {
        if (front == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        T value = front.value;
        front = front.next;
        if (front == null) {
            back = null;
        }
        size--;
        return value;
    }

    /** Returns the front value without removing it. Throws if empty. */
    public T peek() {
        if (front == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.value;
    }

    public boolean isEmpty() { return front == null; }

    public int size() { return size; }
}
