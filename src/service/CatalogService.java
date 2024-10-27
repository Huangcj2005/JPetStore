package service;

import dao.CategoryDao;
import dao.ItemDao;
import dao.ProductDao;
import dao.impl.CategoryDaoImpl;
import dao.impl.ItemDaoImpl;
import dao.impl.ProductDaoImpl;
import domain.Category;
import domain.Item;
import domain.Product;

import java.util.List;

public class CatalogService {
    private ProductDao productDao;
    private ItemDao itemDao;
    private CategoryDao categoryDao;

    public CatalogService(){
        this.productDao = new ProductDaoImpl();
        this.itemDao = new ItemDaoImpl();
        this.categoryDao = new CategoryDaoImpl();
    }
    public List<Category> getCategoryList() {
        return categoryDao.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryDao.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productDao.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDao.getProductListByCategory(categoryId);
    }

    // TODO enable using more than one keyword
    public List<Product> searchProductList(String keyword) {
        return productDao.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId) {
        return  itemDao.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDao.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDao.getInventoryQuantity(itemId) > 0;
    }



}
