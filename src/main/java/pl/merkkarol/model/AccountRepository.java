package pl.merkkarol.model;

import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AccountRepository {
Account save(Account account);
Account findById(int id);
List<Account> findAll();
void deleteById(int id);
@Query("SELECT max(id) FROM Account")
int findIdOfLastRecord();
}
