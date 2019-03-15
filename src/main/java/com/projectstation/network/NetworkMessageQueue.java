package com.projectstation.network;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NetworkMessageQueue<T extends INetworkVisit> {
    private final Deque<T> visits = new LinkedList<>();

    public T poll() {
        synchronized (visits) {
            return visits.poll();
        }
    }

    public void queue(T t) {
        synchronized (visits) {
            visits.add(t);
        }
    }

    public boolean isEmpty() {
        synchronized (visits) {
            return visits.isEmpty();
        }
    }
}
