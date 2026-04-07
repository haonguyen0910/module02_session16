package quanlysanpham_baigioi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        while (true) {
            System.out.println("\n===== MENU QUẢN LÝ SẢN PHẨM =====");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Kiểm tra sản phẩm đắt tiền (>100)");
            System.out.println("3. Tính tổng giá trị sản phẩm");
            System.out.println("4. In danh sách sản phẩm");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập tên sản phẩm: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập giá sản phẩm: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    products.add(new Product(name, price));
                    System.out.println("Đã thêm sản phẩm thành công!");
                    break;

                case 2:
                    ProductProcessor processor = new ProductProcessorImpl();
                    if (processor.hasExpensiveProduct(products)) {
                        System.out.println("\nCó sản phẩm có giá > 100");
                        System.out.println("Các sản phẩm đắt tiền (>100):");
                        for (Product product : products) {
                            if (product.getPrice() > 100) {
                                System.out.println("  - " + product.getName() + ": " + product.getPrice() + " VND");
                            }
                        }
                    } else {
                        System.out.println("Không có sản phẩm đắt tiền (>100)");
                    }
                    break;

                case 3:
                    ProductProcessor processor2 = new ProductProcessorImpl();
                    double totalValue = processor2.calculateTotalValue(products);
                    System.out.println("\nTổng giá trị sản phẩm: " + String.format("%,.0f", totalValue) + " VND");
                    break;

                case 4:
                    if (products.isEmpty()) {
                        System.out.println("Danh sách sản phẩm trống!");
                    } else {
                        ProductProcessor.printProductList(products);
                    }
                    break;

                case 5:
                    System.out.println("Tạm biệt!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Chức năng không hợp lệ!");
            }
        }
    }
}