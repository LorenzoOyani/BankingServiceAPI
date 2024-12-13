package org.example.bankingportal.mapper;

import org.example.bankingportal.Util.BaseEntity;
import org.example.bankingportal.entities.Transaction;
import org.example.bankingportal.payload.TransactionDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionMapper extends BaseEntity<TransactionDTO, Transaction> {
    @Override
    public Transaction convertToEntity(TransactionDTO transactionDTO, Object... args) {
        Transaction transaction = new Transaction();
        if (ObjectUtils.isEmpty(transactionDTO)) {
            BeanUtils.copyProperties(transactionDTO, transaction);
        }
        return transaction;
    }

    @Override
    public TransactionDTO convertFromEntity(Transaction transaction, Object... args) {
        TransactionDTO transactionDTO = new TransactionDTO();
        if (ObjectUtils.isEmpty(transaction)) {
            BeanUtils.copyProperties(transaction, transactionDTO);
        }
        return transactionDTO;
    }

    public List<TransactionDTO> toDTOList(List<Transaction> transaction) {
        if(transaction == null || transaction.isEmpty()) {
            return List.of();
        }

        return transaction.stream()
                .map(this::convertFromEntity)
                .collect(Collectors.toList());
    }
}
