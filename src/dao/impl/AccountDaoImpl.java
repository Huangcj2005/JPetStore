package dao.impl;

import dao.AccountDao;
import domain.Account;
import dao.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDaoImpl implements AccountDao {

    private static final String GET_ACCOUNT_BY_USERNAME_STRING = """
            SELECT
                      SIGNON.USERNAME,
                      ACCOUNT.EMAIL,
                      ACCOUNT.FIRSTNAME,
                      ACCOUNT.LASTNAME,
                      ACCOUNT.STATUS,
                      ACCOUNT.ADDR1 AS address1,
                      ACCOUNT.ADDR2 AS address2,
                      ACCOUNT.CITY,
                      ACCOUNT.STATE,
                      ACCOUNT.ZIP,
                      ACCOUNT.COUNTRY,
                      ACCOUNT.PHONE,
                      PROFILE.LANGPREF AS languagePreference,
                      PROFILE.FAVCATEGORY AS favouriteCategoryId,
                      PROFILE.MYLISTOPT AS listOption,
                      PROFILE.BANNEROPT AS bannerOption,
                      BANNERDATA.BANNERNAME
                FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA
                WHERE ACCOUNT.USERID = ?
                  AND SIGNON.USERNAME = ACCOUNT.USERID
                  AND PROFILE.USERID = ACCOUNT.USERID
                  AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY""";

    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD_STRING = """
            SELECT
                  SIGNON.USERNAME,
                  ACCOUNT.EMAIL,
                  ACCOUNT.FIRSTNAME,
                  ACCOUNT.LASTNAME,
                  ACCOUNT.STATUS,
                  ACCOUNT.ADDR1 AS address1,
                  ACCOUNT.ADDR2 AS address2,
                  ACCOUNT.CITY,
                  ACCOUNT.STATE,
                  ACCOUNT.ZIP,
                  ACCOUNT.COUNTRY,
                  ACCOUNT.PHONE,
                  PROFILE.LANGPREF AS languagePreference,
                  PROFILE.FAVCATEGORY AS favouriteCategoryId,
                  PROFILE.MYLISTOPT AS listOption,
                  PROFILE.BANNEROPT AS bannerOption,
                  BANNERDATA.BANNERNAME
                FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA
                WHERE ACCOUNT.USERID = ?
                  AND SIGNON.PASSWORD = ?
                  AND SIGNON.USERNAME = ACCOUNT.USERID
                  AND PROFILE.USERID = ACCOUNT.USERID
                  AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY""";

    private static final String INSERT_ACCOUNT_STRING = """
            INSERT INTO ACCOUNT
                  (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID)
                VALUES
                  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
    private static final String INSERT_PROFILE_STRING = """
            INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID)
                VALUES (?, ?, ?)""";
    private static final String INSERT_SIGNON_STRING = """
            INSERT INTO SIGNON (PASSWORD,USERNAME)
                VALUES (?, ?)""";
    private static final String UPDATE_ACCOUNT_STRING = """
            UPDATE ACCOUNT SET
                  EMAIL = ?,
                  FIRSTNAME = ?,
                  LASTNAME = ?,
                  STATUS = ?,
                  ADDR1 = ?,
                  ADDR2 = ?,
                  CITY = ?,
                  STATE = ?,
                  ZIP = ?,
                  COUNTRY = ?,
                  PHONE = ?
                WHERE USERID = ?""";
    private static final String UPDATE_PROFILE_STRING = """
            UPDATE PROFILE SET
                  LANGPREF = ?,
                  FAVCATEGORY = ?
                WHERE USERID = ?""";
    private static final String UPDATE_SIGNON_STRING = """
            UPDATE SIGNON SET PASSWORD = ?
                WHERE USERNAME = ?""";

    @Override
    public Account getAccountByUsername(String username) {
        Account account = new Account();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_STRING);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                account.setUsername(resultSet.getString(1));
                account.setEmail(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setState(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                account.setLanguagePreference(resultSet.getString(13));
                account.setFavouriteCategoryId(resultSet.getString(14));
                account.setListOption(resultSet.getInt(15)==1);
                account.setBannerOption(resultSet.getInt(16)==1);
                account.setBannerName(resultSet.getString(17));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account acc = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD_STRING);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                acc = new Account();
                acc.setUsername(resultSet.getString(1));
                acc.setEmail(resultSet.getString(2));
                acc.setFirstName(resultSet.getString(3));
                acc.setLastName(resultSet.getString(4));
                acc.setStatus(resultSet.getString(5));
                acc.setAddress1(resultSet.getString(6));
                acc.setAddress2(resultSet.getString(7));
                acc.setCity(resultSet.getString(8));
                acc.setState(resultSet.getString(9));
                acc.setZip(resultSet.getString(10));
                acc.setCountry(resultSet.getString(11));
                acc.setPhone(resultSet.getString(12));
                acc.setLanguagePreference(resultSet.getString(13));
                acc.setFavouriteCategoryId(resultSet.getString(14));
                acc.setListOption(resultSet.getInt(15)==1);
                acc.setBannerOption(resultSet.getInt(16)==1);
                acc.setBannerName(resultSet.getString(17));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return acc;
    }

    @Override
    public void insertAccount(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_STRING);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertProfile(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFILE_STRING);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SIGNON_STRING);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_STRING);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFILE_STRING);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateSignon(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SIGNON_STRING);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        AccountDao accountDao = new AccountDaoImpl();
//        Account account = new Account();
//        account.setUsername("j2ee");
//        account.setPassword("j2ee");
//        Account result = accountDao.getAccountByUsernameAndPassword(account);
//        System.out.println("success");
//    }
}
