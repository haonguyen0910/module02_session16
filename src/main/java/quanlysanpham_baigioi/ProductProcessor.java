package quanlysanpham_baigioi;

import java.util.List;
import java.util.function.Predicate;

public interface ProductProcessor {
    double calculateTotalValue(List<Product> products);

    static void printProductList(List<Product> products) {
        System.out.println("===== DANH SÁCH SẢN PHẨM =====");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("==============================");
    }

    default boolean hasExpensiveProduct(List<Product> products) {
        Predicate<Product> isExpensive = product -> product.getPrice() > 100;
        for (Product product : products) {
            if (isExpensive.test(product)) {
                return true;
            }
        }
        return false;
    }
}
