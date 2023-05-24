package com.rudametkin.hotelsystem.database.transaction;

import com.rudametkin.hotelsystem.database.daofactory.DAOException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class JPATransactionManager implements TransactionManager {

    @Transactional
    public <R> R execute(TransactionFunction<R> function) {
        R result = null;
        try {
            result = function.apply(null);
        } catch (DAOException ignore) {}
        return result;
    }
    @Transactional
    public void execute(TransactionAction action) {
        try {
            action.apply(null);
        } catch (DAOException ignore) {}
    }
}
