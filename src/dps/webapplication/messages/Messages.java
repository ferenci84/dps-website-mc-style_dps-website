package dps.webapplication.messages;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Named("Messages")
@SessionScoped
public class Messages implements Serializable {

    LinkedList<Message> messages = new LinkedList<>();

    public void addMessage(Type type, String message)
    {
        Message m = new Message();
        m.setType(type);
        m.setMessage(message);
        messages.add(m);
    }

    public List<Message> getMessages()
    {
        List<Message> messageCpy = new LinkedList<>();
        messageCpy.addAll(messages);
        messages.clear();
        return messageCpy;
    }

    public static class Message {
        Type type;
        String message;

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public enum Type {
        Success, Error
    }

}

