package designpatterns.behavioral.Iterator;

interface Aggregate<T> {
    Iterator<T> createIterator();
}
