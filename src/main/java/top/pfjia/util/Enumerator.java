package top.pfjia.util;

import java.util.*;

/**
 * @author pfjia
 * @since 2018/9/26 20:50
 */
public final class Enumerator<E> implements Enumeration<E> {
    private Iterator<E> iterator;

    public Enumerator(Collection<E> collection) {
        this(collection.iterator());
    }

    public Enumerator(Iterator<E> iterator) {
        this.iterator = null;
        this.iterator = iterator;
    }

    public Enumerator(Map<?, E> map) {
        this(map.values().iterator());
    }

    @Override
    public boolean hasMoreElements() {
        return this.iterator.hasNext();
    }

    @Override
    public E nextElement() throws NoSuchElementException {
        return this.iterator.next();
    }
}
