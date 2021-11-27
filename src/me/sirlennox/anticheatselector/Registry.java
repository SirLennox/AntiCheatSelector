package me.sirlennox.anticheatselector;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public abstract class Registry<T> {

    private final List<T> registered;

    public Registry() {
        this.registered = new LinkedList<>();
    }

    public abstract void init();

    public boolean register(T... o) {
        return this.registered.addAll(Arrays.asList(o));
    }

    public boolean unregister(T... o) {
        return this.registered.removeAll(Arrays.asList(o));
    }

    public List<T> getRegistered() {
        return this.registered;
    }

    public T getByPredicate(Predicate<? super T> predicate) {
        return this.registered.stream().filter(predicate).findFirst().orElse(null);
    }

}
