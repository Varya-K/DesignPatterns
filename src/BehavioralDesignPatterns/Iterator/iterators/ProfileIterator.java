package BehavioralDesignPatterns.Iterator.iterators;

import BehavioralDesignPatterns.Iterator.profile.Profile;

public interface ProfileIterator {

    public boolean hasNext();
    public Profile getNext();
    void reset();
}
