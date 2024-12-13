package org.example.bankingportal.service;

import lombok.AllArgsConstructor;
import org.example.bankingportal.entities.Transaction;
import org.example.bankingportal.mapper.TransactionMapper;
import org.example.bankingportal.payload.PagedResponse;
import org.example.bankingportal.payload.TransactionDTO;
import org.example.bankingportal.repositories.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public PagedResponse<TransactionDTO> getAllTransactions(PageRequest pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize());
        Page<Transaction> transactionPage = transactionRepository.findAll(pageable);

        List<TransactionDTO> transactionDTO = transactionMapper.toDTOList(transactionPage.getContent());

        return new PagedResponse<>(
                transactionDTO,
                (int) transactionPage.getTotalElements(),
                transactionPage.getNumber(),
                transactionPage.getTotalPages(),
                transactionPage.getSize(),
                transactionPage.isLast()

        );


    }
}
