package BehavioralDesignPatterns.Iterator.spammer;

import BehavioralDesignPatterns.Iterator.iterators.ProfileIterator;
import BehavioralDesignPatterns.Iterator.profile.Profile;
import BehavioralDesignPatterns.Iterator.social_networks.SocialNetwork;

public class SocialSpammer {
    public SocialNetwork network;
    public ProfileIterator iterator;

    public SocialSpammer(SocialNetwork network){
        this.network=network;
    }

    public void sendSpamToFriends(String profileEmail, String message){
        System.out.println("\nIterating over friends...\n");
        iterator = network.createFriendsIterator(profileEmail);
        sendSpam(message);
    }

    public void sendSpamToCoworkers(String profileEmail, String message){
        System.out.println("\nIterating over coworkers...\n");
        iterator = network.createCowokersIterator(profileEmail);
        sendSpam(message);
    }

    private void sendSpam(String message){
        while(iterator.hasNext()){
            Profile profile = iterator.getNext();
            sendMessage(profile.getEmail(), message);
        }
        iterator.reset();
    }

    public void sendMessage(String email, String message){
        System.out.println("Sent message to: '" + email + "'. Message body: '" + message + "'");
    }
}
