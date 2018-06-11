package com.mj.util;

public class MissingGameOverEvent extends RuntimeException {
    public MissingGameOverEvent(String message) {
        super(message);
    }
}
