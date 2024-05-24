package Design_Datastructure.kafka;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Partition {
  private final List < Message > messages;

  public Partition() {
    this.messages = Collections.synchronizedList(new LinkedList < > ());
  }

  public void addMessage(Message message) {
    synchronized(messages) {
      messages.add(message);
    }
  }

  public List < Message > getMessages() {
    synchronized(messages) {
      return new LinkedList < > (messages);
    }
  }
}
