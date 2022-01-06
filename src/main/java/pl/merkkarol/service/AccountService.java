package pl.merkkarol.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.merkkarol.model.Account;
import pl.merkkarol.model.AccountRepository;
import pl.merkkarol.model.Expense;

@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account addExpenseOperation(Expense expense){
        if(!repository.findAll().isEmpty()){
            Account lastAccount = repository.findById(repository.findIdOfLastRecord());
            double actualBalance = lastAccount.getAccountBalance() - expense.getValue();
            double expenseValue = expense.getValue() * (-1);
            Account account = new Account(actualBalance, expenseValue);
            return repository.save(account);
        }else {
            double value = expense.getValue() * (-1);
            Account account = new Account(value, value);
            return repository.save(account);
        }
    }

}
