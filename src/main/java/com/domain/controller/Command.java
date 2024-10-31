package com.domain.controller;

public interface Command {
    void execute();
    void undo();
}
