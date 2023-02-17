package org.example.util;

import java.util.UUID;

public class Lock {

    private static Lock lockInstance = null;

    private boolean isLocked;
    private String name;

    private Lock(String name) {
        this.name = name;
        this.isLocked = false;

    }

    public static Lock getInstance() {
        if (lockInstance == null) {
            lockInstance = new Lock(UUID.randomUUID().toString());
        }
        return lockInstance;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public String getName() {
        return name;
    }
}
