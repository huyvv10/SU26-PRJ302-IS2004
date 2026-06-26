package dal;

import java.util.ArrayList;
import java.util.List;
import models.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class ProductDao extends DBContext{
    public int numberProductPerPage = 6;
    public List<Product> getAllProducts(){
        List<Product> prdList = new ArrayList<>();
        String sql = "SELECT * FROM tbProduct";
        try {
            PreparedStatement ps =connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                int qty = rs.getInt(3);
                double price = rs.getDouble(4);
                Date impDate = rs.getDate(5);
                String catId = rs.getString(6);
                Product x = new Product(id, name, qty, price, impDate, catId);
                prdList.add(x);
            }
            return prdList;                    
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public List<Product> getProductsPaging(int page){
        List<Product> prdList = new ArrayList<>();
        String sql = "SELECT * FROM tbProduct\n" +
                     "ORDER BY Id\n" +
                     "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement ps =connection.prepareStatement(sql);
            ps.setInt(1, page * numberProductPerPage - numberProductPerPage);
            ps.setInt(2, numberProductPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                int qty = rs.getInt(3);
                double price = rs.getDouble(4);
                Date impDate = rs.getDate(5);
                String catId = rs.getString(6);
                Product x = new Product(id, name, qty, price, impDate, catId);
                prdList.add(x);
            }
            return prdList;                    
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> searchProducts(String nameKw, String catIdKw){
        List<Product> prdList = new ArrayList<>();
        String sql1 = "SELECT * FROM tbProduct\n" +
                     "WHERE Name LIKE '%"+ nameKw +"%'";
        String sql2 ="SELECT * FROM tbProduct\n" +
                     "WHERE Name LIKE '%"+nameKw+"%' AND CatId='"+catIdKw+"'";
        String sql = catIdKw.equals("all") ? sql1 : sql2;
        try {
            PreparedStatement ps =connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                int qty = rs.getInt(3);
                double price = rs.getDouble(4);
                Date impDate = rs.getDate(5);
                String catId = rs.getString(6);
                Product x = new Product(id, name, qty, price, impDate, catId);
                prdList.add(x);
            }
            return prdList;                    
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }    
}
