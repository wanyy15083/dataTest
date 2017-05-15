package com.data.test.struct;

/**
 * Created by Frotly on 2017/4/17.
 */
public class Stack {
    public static final String ERROR = "error";
    public static final String OK = "ok";
    private int data[];
    private int top;

    public String push(Stack s, int e) {
        if (s.top == data.length - 1)
            return ERROR;
        top++;
        data[top] = e;
        return OK;
    }

    public String pop(Stack s, int e) {
        if (s.top == -1)
            return ERROR;
        e = data[top];
        top--;
        return OK;
    }
}
