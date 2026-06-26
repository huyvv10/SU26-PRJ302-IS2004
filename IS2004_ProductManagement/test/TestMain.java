
import dal.CategoryDao;
import dal.ProductDao;
import java.util.ArrayList;
import java.util.List;
import models.Category;
import models.Product;

public class TestMain {
    public static void main(String[] args) {
        CategoryDao catDao = new CategoryDao();
        List<Category> catList = new ArrayList<>();
        catList = catDao.getAllCategories();
        
        for (Category c : catList) {
            System.out.println(c.toString());
        }
        
        ProductDao prdDao =  new ProductDao();
        List<Product> prdList = new ArrayList<>();
        prdList = prdDao.getAllProducts();
        
        for (Product p : prdList) {
            System.out.println(p.toString());
        }
        
        System.out.println("----Test searching -----");
        prdList = prdDao.searchProducts("HP", "C0005");
        
        for (Product p : prdList) {
            System.out.println(p.toString());
        }        
        System.out.println("----Test paging -----");
        prdList = prdDao.getProductsPaging(3);
        
        for (Product p : prdList) {
            System.out.println(p.toString());
        }        
        
    }
}
