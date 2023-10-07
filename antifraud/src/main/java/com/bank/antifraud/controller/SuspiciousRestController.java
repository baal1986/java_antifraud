package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.entity.SuspiciousTransfer;
import com.bank.antifraud.service.SuspiciousTransferService;

import io.micrometer.core.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public class SuspiciousRestController<T extends SuspiciousTransfer, R extends SuspiciousTransferDto> {

    private final SuspiciousTransferService<T, R> suspiciousTransferService;

    public SuspiciousRestController(SuspiciousTransferService<T, R> suspiciousTransferService) {
        this.suspiciousTransferService = suspiciousTransferService;

    }

    @Timed
    public ResponseEntity<List<R>> getAll() {
        return Optional.ofNullable(suspiciousTransferService.findAll())
                .filter(rs -> !rs.isEmpty())
                .map(rs -> new ResponseEntity<>(rs, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Timed
    public ResponseEntity<R> getTransfer(Long id) {
        return Optional.ofNullable(suspiciousTransferService.findById(id))
                .map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Timed
    public ResponseEntity<T> addTransfer(R suspiciousTransfer) {
        return Optional.ofNullable(suspiciousTransferService.save(suspiciousTransfer))
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
    @Timed
    public ResponseEntity<HttpStatus> updateTransfer(Long id, R suspiciousTransfer) {
        return Optional.ofNullable(suspiciousTransferService.update(id, suspiciousTransfer))
                .isPresent() ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @Timed
    public ResponseEntity<HttpStatus> deleteTransfer(Long id) {
        return Optional.ofNullable(suspiciousTransferService.delete(id))
                .isPresent() ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
