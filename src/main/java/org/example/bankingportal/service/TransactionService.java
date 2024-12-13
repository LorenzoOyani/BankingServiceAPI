package org.example.bankingportal.service;

import org.example.bankingportal.payload.PagedResponse;
import org.example.bankingportal.payload.TransactionDTO;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public interface TransactionService {


    PagedResponse<TransactionDTO> getAllTransactions(PageRequest pageRequest);
}
