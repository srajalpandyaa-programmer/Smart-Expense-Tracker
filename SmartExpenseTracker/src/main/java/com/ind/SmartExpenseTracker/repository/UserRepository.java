package com.ind.SmartExpenseTracker.repository;

import com.ind.SmartExpenseTracker.entity.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}

