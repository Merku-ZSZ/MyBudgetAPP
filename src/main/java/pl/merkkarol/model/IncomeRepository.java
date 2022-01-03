package pl.merkkarol.model;

import java.util.List;

public interface IncomeRepository {
    Income save(Income entity);
    List<Income> findAll();
}
