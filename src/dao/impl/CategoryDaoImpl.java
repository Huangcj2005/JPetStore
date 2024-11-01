package dao.impl;

import dao.CategoryDao;
import domain.Category;
import persistence.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private static final String GET_CATEGORY_LIST =
            "SELECT CATID AS categoryId, NAME, DESCN AS description FROM CATEGORY";

    private static final String GET_CATEGORY =
            "SELECT CATID AS categoryId, NAME, DESCN AS description FROM CATEGORY WHERE CATID = ?";


    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        try{
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_CATEGORY_LIST);
            while(resultSet.next()){
                Category category = new Category();
                category.setCategoryId(resultSet.getString("categoryId"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                categoryList.add(category);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }
    @Override
    public Category getCategory(String categoryId) {
        Category category = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY);
            // 设置sql语句中，用问号替代的参数
            preparedStatement.setString(1, categoryId);
            // 接受查询后返回的结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                category = new Category();
                category.setCategoryId(resultSet.getString("categoryId"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }
}
