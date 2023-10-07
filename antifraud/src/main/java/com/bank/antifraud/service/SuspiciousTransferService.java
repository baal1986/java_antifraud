package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.entity.SuspiciousTransfer;
import com.bank.antifraud.exception.service.SuspiciousTransferServiceException;
import com.bank.antifraud.mapper.SuspiciousTransferMapper;
import com.bank.antifraud.repository.SuspiciousTransferRepository;
import lombok.Getter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Getter
@Transactional(readOnly = true)
public class SuspiciousTransferService<T extends SuspiciousTransfer, R extends SuspiciousTransferDto>
        implements SuspiciousTransferServiceOperation<T, R> {

    private final SuspiciousTransferRepository<T> suspiciousTransferRepository;

    private final SuspiciousTransferMapper<T, R> suspiciousTransferMapper;


    public SuspiciousTransferService(SuspiciousTransferRepository<T> suspiciousTransferRepository,
                                     SuspiciousTransferMapper<T, R> suspiciousTransferMapper) {
        this.suspiciousTransferRepository = suspiciousTransferRepository;
        this.suspiciousTransferMapper = suspiciousTransferMapper;

    }

    @Override
    public List<R> findAll() throws EntityNotFoundException {
        return Optional.of(suspiciousTransferRepository
                        .findAll()
                        .stream()
                        .map(suspiciousTransferMapper::toDto)
                        .toList())
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public R findById(Long id) throws EntityNotFoundException {
        return Optional.of(suspiciousTransferRepository.findById(id)
                        .orElseThrow(EntityNotFoundException::new))
                .map(suspiciousTransferMapper::toDto).orElse(null);
    }


    @Transactional()
    @Override
    public T save(R suspiciousTransfer) {
        return suspiciousTransferRepository.save(suspiciousTransferMapper.fromDto(suspiciousTransfer));
    }


    @Transactional
    @Override
    public T update(Long id, R suspiciousTransfer) throws EntityNotFoundException {
        var result = Optional.of(suspiciousTransferRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        save(suspiciousTransfer);
        return result.get();
    }

    @Transactional
    @Override
    public T delete(Long id) throws SuspiciousTransferServiceException, EntityNotFoundException {
        Optional<T> result = Optional.of(suspiciousTransferRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
        try {
            suspiciousTransferRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new SuspiciousTransferServiceException();
        }
        return result.get();
    }
}
