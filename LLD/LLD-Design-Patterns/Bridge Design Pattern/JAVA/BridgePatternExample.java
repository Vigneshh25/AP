// Implementor interface
interface DeviceImplementor {
    void turnOn();
    void turnOff();
    void setChannel(int channel);
}

// Concrete Implementor 1
class TV implements DeviceImplementor {
    private int channel = 1;

    @Override
    public void turnOn() {
        System.out.println("TV is ON");
    }

    @Override
    public void turnOff() {
        System.out.println("TV is OFF");
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("TV channel set to " + channel);
    }
}

// Concrete Implementor 2
class Radio implements DeviceImplementor {
    private int frequency = 100;

    @Override
    public void turnOn() {
        System.out.println("Radio is ON");
    }

    @Override
    public void turnOff() {
        System.out.println("Radio is OFF");
    }

    @Override
    public void setChannel(int frequency) {
        this.frequency = frequency;
        System.out.println("Radio frequency set to " + frequency);
    }
}

// Abstraction
abstract class Device {
    protected DeviceImplementor implementor;

    protected Device(DeviceImplementor implementor) {
        this.implementor = implementor;
    }

    public abstract void turnOn();
    public abstract void turnOff();
    public abstract void setChannel(int channel);
}

// Refined Abstraction 1
class TVRemote extends Device {
    public TVRemote(DeviceImplementor implementor) {
        super(implementor);
    }

    @Override
    public void turnOn() {
        implementor.turnOn();
    }

    @Override
    public void turnOff() {
        implementor.turnOff();
    }

    @Override
    public void setChannel(int channel) {
        implementor.setChannel(channel);
    }
}

// Refined Abstraction 2
class RadioRemote extends Device {
    public RadioRemote(DeviceImplementor implementor) {
        super(implementor);
    }

    @Override
    public void turnOn() {
        implementor.turnOn();
    }

    @Override
    public void turnOff() {
        implementor.turnOff();
    }

    @Override
    public void setChannel(int frequency) {
        implementor.setChannel(frequency);
    }
}

public class BridgePatternExample {
    public static void main(String[] args) {
        Device tvRemote = new TVRemote(new TV());
        Device radioRemote = new RadioRemote(new Radio());

        tvRemote.turnOn();
        tvRemote.setChannel(5);
        tvRemote.turnOff();

        radioRemote.turnOn();
        radioRemote.setChannel(101);
        radioRemote.turnOff();
    }
}
