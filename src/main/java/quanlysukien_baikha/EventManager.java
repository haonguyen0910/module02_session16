package quanlysukien_baikha;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventManager {
    private List<Event> events = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private LocalDateTime inputDateTime(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String dateTimeStr = scanner.nextLine().trim();
                return LocalDateTime.parse(dateTimeStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Enter not valid date");
            }
        }
    }

    public void addEvent() {
        while (true) {
            System.out.print("\nNhập tên sự kiện (hoặc 'exit' để thoát): ");
            String name = scanner.nextLine().trim();

            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            if (name.isEmpty()) {
                System.out.println("Can not enter empty string");
                continue;
            }

            LocalDateTime startDate = inputDateTime("Nhập thời gian bắt đầu (dd-MM-yyyy HH:mm): ");
            LocalDateTime endDate = inputDateTime("Nhập thời gian kết thúc (dd-MM-yyyy HH:mm): ");

            if (endDate.isBefore(startDate)) {
                System.out.println("Thời gian kết thúc phải sau thời gian bắt đầu!");
                continue;
            }

            Event event = new Event(name, startDate, endDate);
            events.add(event);
            System.out.println("Đã thêm sự kiện thành công!");
        }
    }

    public void displayEvents() {
        if (events.isEmpty()) {
            System.out.println("Danh sách sự kiện trống!");
            return;
        }

        System.out.println("\n=== Danh sách sự kiện ===");
        for (Event event : events) {
            System.out.println(event);
        }
    }

    public void checkEventsStatus() {
        if (events.isEmpty()) {
            System.out.println("Không có sự kiện nào để kiểm tra!");
            return;
        }

        System.out.println("\n=== Trạng thái sự kiện ===");
        for (Event event : events) {
            System.out.println("Sự kiện: " + event.getName());
            System.out.println("  Thời gian: " + event.getStartDate().format(formatter) +
                    " -> " + event.getEndDate().format(formatter));
            System.out.println("  Trạng thái: " + event.getStatus());
            System.out.println("------------------------");
        }
    }

    public void checkSpecificEvent() {
        if (events.isEmpty()) {
            System.out.println("Không có sự kiện nào để kiểm tra!");
            return;
        }

        System.out.print("Nhập tên sự kiện cần kiểm tra: ");
        String name = scanner.nextLine().trim();

        for (Event event : events) {
            if (event.getName().equalsIgnoreCase(name)) {
                System.out.println("Sự kiện: " + event.getName());
                System.out.println("Thời gian bắt đầu: " + event.getStartDate().format(formatter));
                System.out.println("Thời gian kết thúc: " + event.getEndDate().format(formatter));
                System.out.println("Trạng thái: " + event.getStatus());
                return;
            }
        }
        System.out.println("Không tìm thấy sự kiện: " + name);
    }
}