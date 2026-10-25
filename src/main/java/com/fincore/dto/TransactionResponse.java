
package com.fincore.dto;

import com.fincore.entity.Transaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        Long id,
        BigDecimal amount,
        String type,
        String status,
        String sourceAccountNumber,
        String targetAccountNumber,
        LocalDateTime createdAt
) {
    public static TransactionResponse from(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getType().name(),
                transaction.getStatus().name(),
                transaction.getSourceAccount() != null
                        ? transaction.getSourceAccount().getAccountNumber()
                        : null,
                transaction.getTargetAccount() != null
                        ? transaction.getTargetAccount().getAccountNumber()
                        : null,
                transaction.getCreatedAt()
        );
    }
}
