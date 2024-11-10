package dao.impl;

import dao.DBUtil;
import dao.LogDao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LogDaoImpl implements LogDao {
    private static final String INSERT_LOG_STRING = """
            INSERT INTO LOG (USERNAME, TIME, MSG)
                VALUES (?, ?, ?)""";
    @Override
    public void insertLog(String username, String time, String msg) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOG_STRING);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, time);
            preparedStatement.setString(3, msg);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
