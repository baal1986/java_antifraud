package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousPhoneTransfer;
import com.bank.antifraud.service.SuspiciousPhoneTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/transfer/phones", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "SuspiciousPhoneTransferRestController", description = "Controller SuspiciousPhoneTransfer")
public class SuspiciousPhoneTransferRestController extends SuspiciousRestController<SuspiciousPhoneTransfer, SuspiciousTransferDtoImpl> {


    public SuspiciousPhoneTransferRestController(SuspiciousPhoneTransferService suspiciousPhoneTransferService) {
        super(suspiciousPhoneTransferService);

    }

    @GetMapping
    @Operation(description = "Getting a list of everyone SuspiciousTransfer")
    public ResponseEntity<List<SuspiciousTransferDtoImpl>> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}")
    @Operation(description = "Getting SuspiciousTransfer by id")
    public ResponseEntity<SuspiciousTransferDtoImpl> getTransfer(@PathVariable Long id) {
        return super.getTransfer(id);
    }

    @PostMapping
    @Operation(description = "Addition SuspiciousTransfer")
    public ResponseEntity<SuspiciousPhoneTransfer> addTransfer(@RequestBody SuspiciousTransferDtoImpl suspiciousTransfer) {
        return super.addTransfer(suspiciousTransfer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Edit SuspiciousTransfer by id")
    public ResponseEntity<HttpStatus> updateTransfer(@PathVariable Long id, @RequestBody SuspiciousTransferDtoImpl suspiciousTransfer) {
        return super.updateTransfer(id, suspiciousTransfer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Remove SuspiciousTransfer by id")
    public ResponseEntity<HttpStatus> deleteTransfer(@PathVariable("id") Long id) {
        return super.deleteTransfer(id);
    }


}
