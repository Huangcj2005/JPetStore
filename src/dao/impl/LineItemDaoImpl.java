package dao.impl;

import dao.DBUtil;
import dao.LineItemDao;
import domain.LineItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LineItemDaoImpl implements LineItemDao {

    private static final String GET_LINE_ITEMS_BY_ORDERID_STRING = """
            SELECT
                  ORDERID,
                  LINENUM AS lineNumber,
                  ITEMID,
                  QUANTITY,
                  UNITPRICE
                FROM LINEITEM
                WHERE ORDERID = ?""";
    private static final String INSERT_LINE_ITEMS_STRING = """
            INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE)
                VALUES (?, ?, ?, ?, ?)""";
    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> list = new ArrayList<LineItem>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LINE_ITEMS_BY_ORDERID_STRING);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(orderId);
                lineItem.setLineNumber(resultSet.getInt("lineNumber"));
                lineItem.setItemId(resultSet.getString("ITEMID"));
                lineItem.setQuantity(resultSet.getInt("QUANTITY"));
                lineItem.setUnitPrice(resultSet.getBigDecimal("UNITPRICE"));

                list.add(lineItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LINE_ITEMS_STRING);

            preparedStatement.setInt(1, lineItem.getOrderId());
            preparedStatement.setInt(2, lineItem.getLineNumber());
            preparedStatement.setString(3, lineItem.getItemId());
            preparedStatement.setInt(4, lineItem.getQuantity());
            preparedStatement.setBigDecimal(5, lineItem.getUnitPrice());

            preparedStatement.executeUpdate();
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
