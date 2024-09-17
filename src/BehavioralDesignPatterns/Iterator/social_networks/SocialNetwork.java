package BehavioralDesignPatterns.Iterator.social_networks;

import BehavioralDesignPatterns.Iterator.iterators.ProfileIterator;

public interface SocialNetwork {
    ProfileIterator createFriendsIterator(String profileEmail);
    ProfileIterator createCowokersIterator(String profileEmail);
}
