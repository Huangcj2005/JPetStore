package dao.impl;

import dao.DBUtil;
import dao.SequenceDao;
import domain.Sequence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceDaoImpl implements SequenceDao {
    private static final String GET_SEQUENCE_STRING = """
            SELECT name, nextid
                FROM SEQUENCE
                WHERE NAME = ?""";
    private static final String UPDATE_SEQUENCE_STRING = """
            UPDATE SEQUENCE
                SET NEXTID = ?
                WHERE NAME = ?""";
    @Override
    public Sequence getSequence(Sequence sequence) {
        Sequence result = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SEQUENCE_STRING);
            preparedStatement.setString(1, sequence.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = new Sequence();
                result.setName(resultSet.getString(1));
                result.setNextId(resultSet.getInt(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void updateSequence(Sequence sequence) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SEQUENCE_STRING);
            preparedStatement.setInt(1, sequence.getNextId());
            preparedStatement.setString(2, sequence.getName());
            preparedStatement.executeUpdate();
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
