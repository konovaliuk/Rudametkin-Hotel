package com.rudametkin.hotelsystem.database.transaction;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public interface TransactionManager {
    <R> R execute(TransactionFunction<R> function);
    void execute(TransactionAction action);
}
