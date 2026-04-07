package quanlysukien_baikha;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EventManager manager = new EventManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== HỆ THỐNG QUẢN LÝ SỰ KIỆN ===");
            System.out.println("1. Thêm sự kiện");
            System.out.println("2. Hiển thị danh sách sự kiện");
            System.out.println("3. Kiểm tra trạng thái tất cả sự kiện");
            System.out.println("4. Kiểm tra sự kiện cụ thể");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
                choice = -1;
                continue;
            }

            switch (choice) {
                case 1:
                    manager.addEvent();
                    break;
                case 2:
                    manager.displayEvents();
                    break;
                case 3:
                    manager.checkEventsStatus();
                    break;
                case 4:
                    manager.checkSpecificEvent();
                    break;
                case 0:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);

        scanner.close();
    }
}
