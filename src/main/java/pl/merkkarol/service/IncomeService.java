package pl.merkkarol.service;

import org.springframework.stereotype.Service;
import pl.merkkarol.model.Income;
import pl.merkkarol.model.IncomeRepository;

import java.util.List;

@Service
public class IncomeService {

    private final IncomeRepository repository;
    private final AccountService accountService;

    public IncomeService(IncomeRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }
    public Income addIncome(Income income){
        accountService.addIncomeOperation(income);
        return repository.save(income);
    }
    public List<Income> findAllIncome(){
        return repository.findAll();
    }
}
