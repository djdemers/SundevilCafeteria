package com.domain.model;

/**
 * Interface for implementing the Observer pattern.
 * Observers are notified of changes in the state of a subject they are observing.
 * This interface provides the contract for updating observers when such changes occur.
 */
public interface Observer {

    /**
     * Method called to update the observer with the latest status or state.
     * This is triggered by the subject when its state changes.
     *
     * @param status The new status or state being communicated to the observer.
     */
    void update(String status);
}
