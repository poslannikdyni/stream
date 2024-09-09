package by.clevertec.util.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberList<T> {
    private final Map<Integer, List<T>> content = new HashMap<>();
    private int currentNumber;
    private final int limit;

    public NumberList(int startFrom, int limit) {
        this.currentNumber = startFrom;
        this.limit = limit;
    }

    public void add(T item) {
        var list = content.get(currentNumber);
        if (list == null) {
            list = new ArrayList<>();
            content.put(currentNumber, list);
        } else if (list.size() >= limit) {
            list = new ArrayList<>();
            content.put(++currentNumber, list);
        }

        list.add(item);
    }

    public void merge(NumberList<T> other) {
        for (var list : other.getContent().values()) {
            for (T item : list) {
                add(item);
            }
        }
    }

    public Map<Integer, List<T>> getContent() {
        return new HashMap<>(content);
    }
}
