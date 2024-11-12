package dao.impl;

import dao.DBUtil;
import dao.OrderDao;
import domain.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private static final String GET_ORDERS_BY_USER_NAME_STRING = """
            select
                  BILLTOFIRSTNAME,
                  BILLTOLASTNAME,
                  BILLADDR1 AS billAddress1,
                  BILLADDR2 AS billAddress2,
                  BILLCITY,
                  BILLSTATE,
                  BILLZIP,
                  BILLCOUNTRY,
                  SHIPTOFIRSTNAME,
                  SHIPTOLASTNAME,
                  SHIPADDR1 AS shipAddress1,
                  SHIPADDR2 AS shipAddress2,
                  SHIPCITY,
                  SHIPSTATE,
                  SHIPZIP,
                  SHIPCOUNTRY,
                  CARDTYPE,
                  COURIER,
                  CREDITCARD,
                  EXPRDATE AS expiryDate,
                  LOCALE,
                  ORDERDATE,
                  ORDERS.ORDERID,
                  TOTALPRICE,
                  USERID AS username,
                  STATUS
                FROM ORDERS, ORDERSTATUS
                WHERE ORDERS.USERID = ?
                    AND ORDERS.ORDERID = ORDERSTATUS.ORDERID
                  ORDER BY ORDERDATE""";
    private static final String GET_ORDER_STRING = """
            select
                  BILLADDR1 AS billAddress1,
                  BILLADDR2 AS billAddress2,
                  BILLCITY,
                  BILLCOUNTRY,
                  BILLSTATE,
                  BILLTOFIRSTNAME,
                  BILLTOLASTNAME,
                  BILLZIP,
                  SHIPADDR1 AS shipAddress1,
                  SHIPADDR2 AS shipAddress2,
                  SHIPCITY,
                  SHIPCOUNTRY,
                  SHIPSTATE,
                  SHIPTOFIRSTNAME,
                  SHIPTOLASTNAME,
                  SHIPZIP,
                  CARDTYPE,
                  COURIER,
                  CREDITCARD,
                  EXPRDATE AS expiryDate,
                  LOCALE,
                  ORDERDATE,
                  ORDERS.ORDERID,
                  TOTALPRICE,
                  USERID AS username,
                  STATUS
                FROM ORDERS, ORDERSTATUS
                WHERE ORDERS.ORDERID = ?
                  AND ORDERS.ORDERID = ORDERSTATUS.ORDERID""";
    private static final String INSERT_ORDER_STRING = """
            INSERT INTO ORDERS (ORDERID, USERID, ORDERDATE, SHIPADDR1, SHIPADDR2, SHIPCITY, SHIPSTATE,
                  SHIPZIP, SHIPCOUNTRY, BILLADDR1, BILLADDR2, BILLCITY, BILLSTATE, BILLZIP, BILLCOUNTRY,
                  COURIER, TOTALPRICE, BILLTOFIRSTNAME, BILLTOLASTNAME, SHIPTOFIRSTNAME, SHIPTOLASTNAME,
                  CREDITCARD, EXPRDATE, CARDTYPE, LOCALE)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
    private static final String INSERT_ORDER_STATUS_STRING = """
            INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS)
                VALUES (?, ?, ?, ?)""";
    @Override
    public List<Order> getOrdersByUsername(String username) {
        List<Order> orders = new ArrayList<Order>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS_BY_USER_NAME_STRING);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setBillToFirstName(resultSet.getString("BILLTOFIRSTNAME"));
                order.setBillToLastName(resultSet.getString("BILLTOLASTNAME"));
                order.setBillAddress1(resultSet.getString("billAddress1"));
                order.setBillAddress2(resultSet.getString("billAddress2"));
                order.setBillCity(resultSet.getString("BILLCITY"));
                order.setBillState(resultSet.getString("BILLSTATE"));
                order.setBillZip(resultSet.getString("BILLZIP"));
                order.setBillCountry(resultSet.getString("BILLCOUNTRY"));
                order.setShipToFirstName(resultSet.getString("SHIPTOFIRSTNAME"));
                order.setShipToLastName(resultSet.getString("SHIPTOLASTNAME"));
                order.setShipAddress1(resultSet.getString("shipAddress1"));
                order.setShipAddress2(resultSet.getString("shipAddress2"));
                order.setShipCity(resultSet.getString("SHIPCITY"));
                order.setShipState(resultSet.getString("SHIPSTATE"));
                order.setShipZip(resultSet.getString("SHIPZIP"));
                order.setShipCountry(resultSet.getString("SHIPCOUNTRY"));

                order.setCardType(resultSet.getString("CARDTYPE"));
                order.setCourier(resultSet.getString("COURIER"));
                order.setCreditCard(resultSet.getString("CREDITCARD"));
                order.setExpiryDate(resultSet.getString("expiryDate"));
                order.setLocale(resultSet.getString("LOCALE"));
                order.setOrderDate(resultSet.getDate("ORDERDATE"));
                order.setOrderId(resultSet.getInt("ORDERID"));
                order.setTotalPrice(resultSet.getBigDecimal("TOTALPRICE"));
                order.setUsername(resultSet.getString("username"));
                order.setStatus(resultSet.getString("STATUS"));

                orders.add(order);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order getOrder(int orderId) {
        Order order = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_STRING);
            preparedStatement.setString(1, String.valueOf(orderId));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = new Order();
                order.setBillToFirstName(resultSet.getString("BILLTOFIRSTNAME"));
                order.setBillToLastName(resultSet.getString("BILLTOLASTNAME"));
                order.setBillAddress1(resultSet.getString("billAddress1"));
                order.setBillAddress2(resultSet.getString("billAddress2"));
                order.setBillCity(resultSet.getString("BILLCITY"));
                order.setBillState(resultSet.getString("BILLSTATE"));
                order.setBillZip(resultSet.getString("BILLZIP"));
                order.setBillCountry(resultSet.getString("BILLCOUNTRY"));
                order.setShipToFirstName(resultSet.getString("SHIPTOFIRSTNAME"));
                order.setShipToLastName(resultSet.getString("SHIPTOLASTNAME"));
                order.setShipAddress1(resultSet.getString("shipAddress1"));
                order.setShipAddress2(resultSet.getString("shipAddress2"));
                order.setShipCity(resultSet.getString("SHIPCITY"));
                order.setShipState(resultSet.getString("SHIPSTATE"));
                order.setShipZip(resultSet.getString("SHIPZIP"));
                order.setShipCountry(resultSet.getString("SHIPCOUNTRY"));

                order.setCardType(resultSet.getString("CARDTYPE"));
                order.setCourier(resultSet.getString("COURIER"));
                order.setCreditCard(resultSet.getString("CREDITCARD"));
                order.setExpiryDate(resultSet.getString("expiryDate"));
                order.setLocale(resultSet.getString("LOCALE"));
                order.setOrderDate(resultSet.getDate("ORDERDATE"));
                order.setOrderId(resultSet.getInt("ORDERID"));
                order.setTotalPrice(resultSet.getBigDecimal("TOTALPRICE"));
                order.setUsername(resultSet.getString("username"));
                order.setStatus(resultSet.getString("STATUS"));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void insertOrder(Order order) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_STRING);

            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setString(2, order.getUsername());
            preparedStatement.setDate(3, order.getOrderDate());
            preparedStatement.setString(4, order.getShipAddress1());
            preparedStatement.setString(5, order.getShipAddress2());
            preparedStatement.setString(6, order.getShipCity());
            preparedStatement.setString(7, order.getShipState());
            preparedStatement.setString(8, order.getShipZip());
            preparedStatement.setString(9, order.getShipCountry());
            preparedStatement.setString(10, order.getBillAddress1());
            preparedStatement.setString(11, order.getBillAddress2());
            preparedStatement.setString(12, order.getBillCity());
            preparedStatement.setString(13, order.getBillState());
            preparedStatement.setString(14, order.getBillZip());
            preparedStatement.setString(15, order.getBillCountry());
            preparedStatement.setString(16, order.getCourier());
            preparedStatement.setBigDecimal(17, order.getTotalPrice());
            preparedStatement.setString(18, order.getBillToFirstName());
            preparedStatement.setString(19, order.getBillToLastName());
            preparedStatement.setString(20, order.getShipToFirstName());
            preparedStatement.setString(21, order.getShipToLastName());
            preparedStatement.setString(22, order.getCreditCard());
            preparedStatement.setString(23, order.getExpiryDate());
            preparedStatement.setString(24, order.getCardType());
            preparedStatement.setString(25, order.getLocale());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrderStatus(Order order) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_STATUS_STRING);

            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getLineItems().size());
            preparedStatement.setDate(3, (java.sql.Date)order.getOrderDate());
            preparedStatement.setString(4, order.getStatus());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
