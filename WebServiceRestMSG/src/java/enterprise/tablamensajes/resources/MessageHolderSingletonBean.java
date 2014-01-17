package enterprise.tablamensajes.resources;

import enterprise.tablamensajes.entities.Message;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class MessageHolderSingletonBean {

    private LinkedList<Message> list = new LinkedList<Message>();
    private int maxMessages = 10;
    int currentId = 0;

    public MessageHolderSingletonBean() {
        // initial content
        addMessage("Mensaje 0", new Date());
        addMessage("Mensaje 1", new Date());
        addMessage("Mensaje 2", new Date());
    }

    public List<Message> getMessages() {
        List<Message> l = new LinkedList<Message>();

        int index = 0;

        while (index < list.size() && index < maxMessages) {
            l.add(list.get(index));
            index++;
        }

        return l;
    }

    private synchronized int getNewId() {
        return currentId++;
    }

    public synchronized Message addMessage(String msg) {
        return addMessage(msg, new Date());
    }

    private synchronized Message addMessage(String msg, Date date) {
        Message m = new Message(date, msg, getNewId());

        list.addFirst(m);

        return m;
    }

    public Message getMessage(int uniqueId) {
        int index = 0;
        Message m;

        while (index < list.size()) {
            if ((m = list.get(index)).getUniqueId() == uniqueId) {
                return m;
            }
            index++;
        }

        return null;
    }

    public synchronized boolean deleteMessage(int uniqueId) {
        int index = 0;

        while (index < list.size()) {
            if (list.get(index).getUniqueId() == uniqueId) {
                list.remove(index);
                return true;
            }
            index++;
        }

        return false;
    }
}
