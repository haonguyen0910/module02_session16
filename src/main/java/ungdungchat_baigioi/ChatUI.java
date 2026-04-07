package ungdungchat_baigioi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatUI {
    private ChatService chatService;
    private Scanner scanner;

    public ChatUI() {
        this.chatService = new ChatService(new ArrayList<>());
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Nhập tên người gửi (hoặc 'exit' để thoát):");
            String sender = scanner.nextLine().trim();

            if (sender.equalsIgnoreCase("exit")) {
                System.out.println("Tạm biệt!");
                break;
            }

            if (!InputValidator.isValidInput(sender)) {
                System.out.println("Tên người gửi không được để trống!");
                continue;
            }

            System.out.println("Nhập nội dung tin nhắn:");
            String content = scanner.nextLine().trim();

            if (!InputValidator.isValidInput(content)) {
                System.out.println("Nội dung không được để trống!");
                continue;
            }

            Message newMessage = new Message(sender, content, LocalDateTime.now());
            chatService.addMessage(newMessage);
            System.out.println("Đã gửi tin nhắn thành công!");

            handlePostMessageMenu();
        }
        scanner.close();
    }

    private void handlePostMessageMenu() {
        while (true) {
            System.out.println("Nhập 'history' để xem lịch sử, hoặc 'filter' để lọc tin nhắn theo người gửi, hoặc 'date' để lọc theo ngày:");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "history":
                    showHistory();
                    break;
                case "filter":
                    filterBySender();
                    break;
                case "date":
                    filterByDate();
                    break;
                default:
                    return;
            }
        }
    }

    private void showHistory() {
        if (chatService.isEmpty()) {
            System.out.println("Chưa có tin nhắn nào!");
            return;
        }
        System.out.println("Lịch sử chat:");
        chatService.getAllMessages().forEach(System.out::println);
    }

    private void filterBySender() {
        System.out.println("Nhập tên người gửi để lọc:");
        String sender = scanner.nextLine().trim();

        List<Message> filteredMessages = chatService.filterBySender(sender);

        if (filteredMessages.isEmpty()) {
            System.out.println("Không có tin nhắn từ " + sender);
        } else {
            System.out.println("Tin nhắn từ " + sender + ":");
            filteredMessages.forEach(System.out::println);
        }
    }

    private void filterByDate() {
        System.out.println("Nhập ngày (dd-MM-yyyy):");
        String dateInput = scanner.nextLine().trim();

        try {
            LocalDate targetDate = InputValidator.parseDate(dateInput);
            List<Message> filteredMessages = chatService.filterByDate(targetDate);

            if (filteredMessages.isEmpty()) {
                System.out.println("Không có tin nhắn trong ngày " + dateInput);
            } else {
                System.out.println("Tin nhắn trong ngày " + targetDate + ":");
                filteredMessages.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Định dạng ngày không hợp lệ! Vui lòng nhập theo định dạng dd-MM-yyyy");
        }
    }
}
