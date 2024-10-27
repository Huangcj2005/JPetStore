package service;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import domain.Account;

public class AccountService {
    private AccountDao accountDao;

    public AccountService(){
        accountDao = new AccountDaoImpl();
    }

    public Account getAccount(String username, String password) {   // 依据用户名与密码获取 Account 对象
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDao.getAccountByUsernameAndPassword(account);
    }

    public void insertAccount(Account account) {    // 注册用户
        accountDao.insertAccount(account);
        accountDao.insertProfile(account);
        accountDao.insertSignon(account);
    }

    public void updateAccount(Account account) {    // 更新用户信息
        accountDao.updateAccount(account);
        accountDao.updateProfile(account);

        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountDao.updateSignon(account);
        }
    }
}
