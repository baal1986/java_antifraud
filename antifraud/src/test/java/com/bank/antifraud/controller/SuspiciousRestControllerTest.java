package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousTransfer;
import com.bank.antifraud.service.SuspiciousTransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
class SuspiciousRestControllerTest<T extends SuspiciousTransfer, R extends SuspiciousTransferDto> {


    @Mock
    SuspiciousTransferService<T, R> suspiciousTransferService;

    @InjectMocks
    SuspiciousRestController<T,R> suspiciousRestController;

    @Test
    void findAllShouldReturnBadRequest(){
        doReturn(null).when(suspiciousTransferService).findAll();
        var result = suspiciousRestController.getAll();
        assertThat(result).isEqualTo( new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Test
    void findAllShouldReturnOkRequest(){
        List<R> res= new ArrayList<>();
        res.add((R) new SuspiciousTransferDtoImpl());
        doReturn(res).when(suspiciousTransferService).findAll();
        var result = suspiciousRestController.getAll();
        assertThat(result).usingRecursiveComparison()
                .isEqualTo( new ResponseEntity<>(res, HttpStatus.OK));
    }

    @Test
    void getTransferShouldReturnBadRequest(){
        doReturn(null).when(suspiciousTransferService).findById(any());
        var result = suspiciousRestController.getTransfer(any());
        assertThat(result).isEqualTo( new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Test
    void getTransferShouldReturnOkRequest(){
        R res= (R) new SuspiciousTransferDtoImpl();
        doReturn(res).when(suspiciousTransferService).findById(any());
        var result = suspiciousRestController.getTransfer(any());
        assertThat(result).usingRecursiveComparison()
                .isEqualTo( new ResponseEntity<>(res, HttpStatus.OK));
    }

    @Test
    void addTransferShouldReturnBadRequest(){
        doReturn(null).when(suspiciousTransferService).save(any());
        var result = suspiciousRestController.addTransfer(any());
        assertThat(result).isEqualTo( new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Test
    void addTransferShouldReturnOkRequest(){
        T res= (T) new SuspiciousTransfer();
        doReturn(res).when(suspiciousTransferService).save(any());
        var result = suspiciousRestController.addTransfer(any());
        assertThat(result).usingRecursiveComparison()
                .isEqualTo( new ResponseEntity<>(res, HttpStatus.OK));
    }

    @Test
    void updateTransferShouldReturnBadRequest(){
        doReturn(null).when(suspiciousTransferService).update(any(), any());
        var result = suspiciousRestController.updateTransfer(any(), any());
        assertThat(result).isEqualTo( new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Test
    void updateTransferShouldReturnOkRequest(){
        T res = (T) new SuspiciousTransfer();
        doReturn(res).when(suspiciousTransferService).update(any(), any());
        var result = suspiciousRestController.updateTransfer(any(), any());
        assertThat(result).usingRecursiveComparison()
                .isEqualTo( new ResponseEntity<>(HttpStatus.OK));
    }

    @Test
    void deleteTransferShouldReturnBadRequest(){
        doReturn(null).when(suspiciousTransferService).delete(any());
        var result = suspiciousRestController.deleteTransfer(any());
        assertThat(result).isEqualTo( new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Test
    void deleteTransferShouldReturnOkRequest(){
        T res = (T) new SuspiciousTransfer();
        doReturn(res).when(suspiciousTransferService).delete(any());
        var result = suspiciousRestController.deleteTransfer(any());
        assertThat(result).usingRecursiveComparison()
                .isEqualTo( new ResponseEntity<>(HttpStatus.OK));
    }
}