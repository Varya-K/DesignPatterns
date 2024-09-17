package BehavioralDesignPatterns.Iterator.iterators;

import BehavioralDesignPatterns.Iterator.profile.Profile;
import BehavioralDesignPatterns.Iterator.social_networks.LinkedIn;

import java.util.ArrayList;
import java.util.List;

public class LinkedInIterator implements ProfileIterator{
    private LinkedIn linkedIn;
    private String type;
    private String email;
    private int currentPosition=0;
    private List<String> emails = new ArrayList<>();
    private List<Profile> contacts = new ArrayList<>();

    public LinkedInIterator(LinkedIn linkedIn, String type, String email){
        this.linkedIn = linkedIn;
        this.type = type;
        this.email=email;
    }

    private void lazyLoad(){
        if(emails.isEmpty()){
            List<String> profiles = linkedIn.requestRelatedContactsFromLinkedInAPI(email,type);
            for (String profile: profiles){
                this.emails.add(profile);
                this.contacts.add(null);
            }
        }
    }

    @Override
    public boolean hasNext() {
        lazyLoad();
        return currentPosition<emails.size();
    }

    @Override
    public Profile getNext() {
        if(!hasNext()) {
            return null;
        }
        String friendEmail = emails.get(currentPosition);
        Profile friendProfile = contacts.get(currentPosition);
        if(friendProfile==null){
            friendProfile=linkedIn.requestContactInfoFromLinkedInAPI(friendEmail);
            contacts.set(currentPosition, friendProfile);
        }
        currentPosition++;
        return friendProfile;
    }

    @Override
    public void reset() {
        currentPosition=0;
    }
}
