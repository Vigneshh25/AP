package lld.amazonprime;

interface VideoState {
    void play();

    void pause();

    void stop();
}

class PlayingState implements VideoState {
    public void play() {
        System.out.println("Video is already playing.");
    }

    public void pause() {
        System.out.println("Pausing video...");
    }

    public void stop() {
        System.out.println("Stopping video...");
    }
}

class PausedState implements VideoState {
    public void play() {
        System.out.println("Resuming video...");
    }

    public void pause() {
        System.out.println("Video is already paused.");
    }

    public void stop() {
        System.out.println("Stopping video...");
    }
}

class StoppedState implements VideoState {
    public void play() {
        System.out.println("Starting to play video...");
    }

    public void pause() {
        System.out.println("Cannot pause. Video is stopped.");
    }

    public void stop() {
        System.out.println("Video is already stopped.");
    }
}
