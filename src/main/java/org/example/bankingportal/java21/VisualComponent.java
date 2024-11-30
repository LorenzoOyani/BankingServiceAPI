package org.example.bankingportal.java21;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class VisualComponent {

    private final CopyOnWriteArrayList<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

    public VisualComponent(KeyListener[] keyListeners, MouseListener[] mouseListeners) {
        this.keyListeners.add(keyListeners[0]);
        this.mouseListeners.add(mouseListeners[0]);
    }

    public Object getLast(Vector<Integer> element) {
        int lastIndex = element.size() - 1;
        return element.get(lastIndex);

    }

    public Object removeLast(Vector<Integer> element) {
        int lastIndex = element.size() - 1;
        return element.remove(lastIndex);
    }
}
