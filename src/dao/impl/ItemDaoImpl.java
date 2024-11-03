package dao.impl;

import dao.ItemDao;
import domain.Item;
import persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDaoImpl implements ItemDao {

    private static final String GET_ITEM_LIST_BY_PRODUCT_STRING =
        """
            SELECT I.ITEMID,
              LISTPRICE,
              UNITCOST,
              SUPPLIER AS supplierId,
              I.PRODUCTID AS "product.productId",
              NAME AS "product.name",
              DESCN AS "product.description",
              CATEGORY AS "product.categoryId",
              STATUS,
              ATTR1 AS attribute1,
              ATTR2 AS attribute2,
              ATTR3 AS attribute3,
              ATTR4 AS attribute4,
              ATTR5 AS attribute5
            FROM ITEM I, PRODUCT P
            WHERE P.PRODUCTID = I.PRODUCTID
            AND I.PRODUCTID = ?""";

    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item> itemList = new ArrayList<Item>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM_LIST_BY_PRODUCT_STRING);
            preparedStatement.setString(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Item item = new Item();
                item.setItemId(resultSet.getString("ITEMID"));
                item.setListPrice(resultSet.getBigDecimal("LISTPRICE"));
                item.setUnitCost(resultSet.getBigDecimal("UNITCOST"));
                item.setSupplierId(resultSet.getInt("supplierId"));
                item.setProductId(resultSet.getString("product.productId"));
                item.setStatus(resultSet.getString("STATUS"));
                item.setAttribute1(resultSet.getString("attribute1"));
                item.setAttribute2(resultSet.getString("attribute2"));
                item.setAttribute3(resultSet.getString("attribute3"));
                item.setAttribute4(resultSet.getString("attribute4"));
                item.setAttribute5(resultSet.getString("attribute5"));
                itemList.add(item);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return itemList;
    }

    //----------------------------------------------------------------------------------------------------------------//

    private static final String UPDATE_INVENTORY_QUANTITY_STRING =
        """
            UPDATE INVENTORY SET
            QTY = QTY - ?
            WHERE ITEMID = ?""";

    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVENTORY_QUANTITY_STRING);
            preparedStatement.setString(1,(String)param.get("itemId"));
            preparedStatement.setString(2,(String)param.get("quantity"));
            int rows = preparedStatement.executeUpdate();
            if(rows > 0){
                System.out.println("商品余量信息修改成功");
            }else {
                System.out.println("商品余量未修改");
            }
            preparedStatement.close();
            connection.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------------------------//

    private static final String GET_INVENTORY_QUANTITY_STRING =
        """
            SELECT QTY AS value
            FROM INVENTORY
            WHERE ITEMID = ?""";

    @Override
    public int getInventoryQuantity(String itemId) {
        int quantity = 0;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_INVENTORY_QUANTITY_STRING);
            preparedStatement.setString(1,itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                quantity = resultSet.getInt("value");
            }else {
                System.out.println("物品ID错误");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return quantity;
    }

    //----------------------------------------------------------------------------------------------------------------//

    private static final String GET_ITEM_STRING =
        """
            select
              I.ITEMID,
              LISTPRICE,
              UNITCOST,
              SUPPLIER AS supplierId,
              I.PRODUCTID AS "product.productId",
              NAME AS "product.name",
              DESCN AS "product.description",
              CATEGORY AS "product.categoryId",
              STATUS,
              ATTR1 AS attribute1,
              ATTR2 AS attribute2,
              ATTR3 AS attribute3,
              ATTR4 AS attribute4,
              ATTR5 AS attribute5,
              QTY AS quantity
            from ITEM I, INVENTORY V, PRODUCT P
            where P.PRODUCTID = I.PRODUCTID
              and I.ITEMID = V.ITEMID
              and I.ITEMID = ?""";

    @Override
    public Item getItem(String itemId) {
        Item item = new Item();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM_STRING);
            preparedStatement.setString(1,itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                item.setItemId(resultSet.getString("ITEMID"));
                item.setListPrice(resultSet.getBigDecimal("LISTPRICE"));
                item.setUnitCost(resultSet.getBigDecimal("UNITCOST"));
                item.setSupplierId(resultSet.getInt("supplierId"));
                item.setProductId(resultSet.getString("product.productId"));
                item.setStatus(resultSet.getString("STATUS"));
                item.setAttribute1(resultSet.getString("attribute1"));
                item.setAttribute2(resultSet.getString("attribute2"));
                item.setAttribute3(resultSet.getString("attribute3"));
                item.setAttribute4(resultSet.getString("attribute4"));
                item.setAttribute5(resultSet.getString("attribute5"));
                item.setQuantity(resultSet.getInt("quantity"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return item;
    }
//
//    public static void main(String[] args) {
//        System.out.println(new ItemDaoImpl().getInventoryQuantity("EST-28"));
//    }
}
