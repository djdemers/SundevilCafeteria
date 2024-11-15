package com.domain.controller.command;

/**
 * Interface for implementing the Command design pattern.
 *
 * This interface defines a contract for any command that needs to be executed
 * and potentially undone. It is a core part of the Command design pattern, which
 * helps encapsulate requests as objects, allowing for parameterization, queuing,
 * and undo/redo functionality.
 */
public interface Command {
    /**
     * Executes the command.
     */
    void execute();

    /**
     * Undoes the command, reverting any changes made during execution.
     */
    void undo();
}