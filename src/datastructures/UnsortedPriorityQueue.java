package datastructures;

import java.util.Comparator;

public class UnsortedPriorityQueue<T> extends AbstractPriorityQueue<T> {
    private PositionalList<T> list = new LinkedPositionalList<>();

    public UnsortedPriorityQueue() { super(); }

    public UnsortedPriorityQueue(Comparator<T> comp) { super(comp); }

    private Position<T> findMin() {
        Position<T> small = list.first();
        for (Position<T> walk : list.positions())
            if (compare(walk.getElement(), small.getElement()) < 0)
                small = walk;
        return small;
    }

    @Override
    public T offer(T value) {
        checkKey(value);
        list.addLast(value);
        return value;
    }

    @Override
    public T peek() {
        if (list.isEmpty()) return null;
        return findMin().getElement();
    }

    @Override
    public T poll() {
        if (list.isEmpty()) return null;
        return list.remove(findMin());
    }

    @Override
    public int size() { return list.size(); }
}

