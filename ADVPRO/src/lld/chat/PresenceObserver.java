package lld.chat;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // PresenceObserver interface
interface PresenceObserver {
    void onPresenceChange(User user, boolean online);
}
