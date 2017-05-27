package com.data.test.concurrent;

public final class PCData {
    private final int intData;

    public PCData(int d) {
        this.intData = d;
    }

    public PCData(String d) {
        this.intData = Integer.parseInt(d);
    }

    public int getData() {
        return intData;
    }

    @Override public String toString() {
        return "data:" + intData;
    }
}
