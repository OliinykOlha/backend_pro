package de.ait.accounting.dao;

import de.ait.accounting.model.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

}
