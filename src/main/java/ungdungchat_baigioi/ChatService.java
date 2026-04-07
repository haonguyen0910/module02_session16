package ungdungchat_baigioi;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ChatService {
    private List<Message> messages;

    public ChatService(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getAllMessages() {
        return messages;
    }

    public List<Message> filterBySender(String sender) {
        return messages.stream()
                .filter(msg -> msg.getSender().equalsIgnoreCase(sender))
                .collect(Collectors.toList());
    }

    public List<Message> filterByDate(LocalDate date) {
        return messages.stream()
                .filter(msg -> msg.getTimestamp().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return messages.isEmpty();
    }
}