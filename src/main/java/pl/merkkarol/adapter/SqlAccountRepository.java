package pl.merkkarol.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.merkkarol.model.Account;
import pl.merkkarol.model.AccountRepository;

@Repository
public interface SqlAccountRepository extends JpaRepository<Account, Integer>, AccountRepository {
}
