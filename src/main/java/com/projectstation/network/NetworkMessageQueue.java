package com.projectstation.network;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NetworkMessageQueue<T extends INetworkVisit> {
    private final Deque<T> visits = new LinkedList<>();

    private void compress() {
        boolean removed = true;

        int count = 0;
        while (removed && visits.size() >= 2) {
            removed = false;
            T recent = visits.poll();
            T prior = visits.poll();

            if (recent.isPriorRedundant(prior)) {
                visits.addFirst(recent);
                removed = true;
            } else {
                visits.addFirst(prior);
                visits.addFirst(recent);
            }
        }

        if(count > 0)
            System.out.println("Removed " + count + " redundant messages.");
    }

    public T poll() {
        synchronized (visits) {
            compress();
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
