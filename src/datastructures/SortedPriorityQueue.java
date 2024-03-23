package datastructures;

import java.util.Comparator;

public class SortedPriorityQueue<T> extends AbstractPriorityQueue<T> {
    private PositionalList<T> list = new LinkedPositionalList<>();

    public SortedPriorityQueue() { super(); }

    public SortedPriorityQueue(Comparator<T> comp) { super(comp); }

    @Override
    public T offer(T value) {
        checkKey(value);
        Position<T> walk = list.last();
        while (walk != null && compare(value, walk.getElement()) < 0)
            walk = list.before(walk);
        if (walk == null)
            list.addFirst(value);
        else
            list.addAfter(walk, value);
        return value;
    }

    @Override
    public T peek() {
        if (list.isEmpty()) return null;
        return list.first().getElement();
    }

    @Override
    public T poll() {
        if (list.isEmpty()) return null;
        return list.remove(list.first());
    }

    @Override
    public int size() { return list.size(); }
}

