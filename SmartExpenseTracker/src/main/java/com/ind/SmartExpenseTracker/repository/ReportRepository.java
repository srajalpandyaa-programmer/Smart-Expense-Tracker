package com.ind.SmartExpenseTracker.repository;

import com.ind.SmartExpenseTracker.entity.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.YearMonth;
import java.util.Optional;

/**
 * Repository interface for accessing Report documents in MongoDB.
 */
public interface ReportRepository extends MongoRepository<Report, String> {

    Optional<Report> findByUserIdAndPeriod(String userId, YearMonth period);
}


