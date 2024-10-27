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
}
