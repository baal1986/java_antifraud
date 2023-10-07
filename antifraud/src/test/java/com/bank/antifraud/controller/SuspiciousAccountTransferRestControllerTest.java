package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.dto.SuspiciousTransferDtoImpl;
import com.bank.antifraud.entity.SuspiciousAccountTransfer;
import com.bank.antifraud.entity.SuspiciousTransfer;
import com.bank.antifraud.service.SuspiciousAccountTransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {SuspiciousAccountTransferRestController.class})
@ExtendWith(MockitoExtension.class)
class SuspiciousAccountTransferRestControllerTest<T extends SuspiciousTransfer, R extends SuspiciousTransferDto> {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuspiciousAccountTransferService suspiciousAccountTransferService;

    @Test
    void getAllShouldReturnStatusOk() throws Exception {
        List<R> result = new ArrayList<>();
        result.add((R) new SuspiciousTransferDtoImpl());
        when(suspiciousAccountTransferService.findAll())
                .thenReturn((List<SuspiciousTransferDtoImpl>) result);
        mockMvc.perform(MockMvcRequestBuilders.get("/transfer/accounts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllShouldReturnStatusBad() throws Exception {
        List<R> result = new ArrayList<>();
        when(suspiciousAccountTransferService.findAll())
                .thenReturn((List<SuspiciousTransferDtoImpl>) result);
        mockMvc.perform(MockMvcRequestBuilders.get("/transfer/accounts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getTransferShouldReturnStatusOk() throws Exception {
        R result = (R) new SuspiciousTransferDtoImpl(777L,
                true, true,
                "Oops", "No");
        when(suspiciousAccountTransferService.findById(777L))
                .thenReturn((SuspiciousTransferDtoImpl) result);
        mockMvc.perform(MockMvcRequestBuilders.get("/transfer/accounts/777")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transferId", Matchers.is(777)))
                .andExpect(jsonPath("$.isBlocked", Matchers.is(true)))
                .andExpect(jsonPath("$.isSuspicious", Matchers.is(true)))
                .andExpect(jsonPath("$.blockedReason", Matchers.is("Oops")))
                .andExpect(jsonPath("$.suspiciousReason", Matchers.is("No")));
    }

    @Test
    void getTransferShouldReturnStatusBad() throws Exception {
        when(suspiciousAccountTransferService.findById(777L))
                .thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/transfer/accounts/777")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addTransferShouldReturnStatusOk() throws Exception {
        var request = new SuspiciousTransferDtoImpl(777L, true, true, "Oops", "No");
        var response = new SuspiciousAccountTransfer();
        response.setId(1L);
        response.setAccountTransferId(777L);
        response.setIsBlocked(request.getIsBlocked());
        response.setIsSuspicious(request.getIsSuspicious());
        response.setBlockedReason(request.getBlockedReason());
        response.setSuspiciousReason(request.getSuspiciousReason());

        when(suspiciousAccountTransferService.save(any()))
                .thenReturn(response);

        String json = new ObjectMapper().writeValueAsString(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/transfer/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.accountTransferId", Matchers.is(777)))
                .andExpect(jsonPath("$.isBlocked", Matchers.is(response.getIsBlocked())))
                .andExpect(jsonPath("$.isSuspicious", Matchers.is(response.getIsSuspicious())))
                .andExpect(jsonPath("$.blockedReason", Matchers.is(response.getBlockedReason())))
                .andExpect(jsonPath("$.suspiciousReason", Matchers.is(response.getSuspiciousReason())));
    }

    @Test
    void addTransferShouldReturnStatusBad() throws Exception {
        when(suspiciousAccountTransferService.save(any()))
                .thenReturn(null);
        String json = new ObjectMapper().writeValueAsString(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/transfer/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateTransferShouldReturnStatusOk() throws Exception {
        var request = new SuspiciousTransferDtoImpl(777L, true, true, "Oops", "No");
        var response = new SuspiciousAccountTransfer();
        response.setIsBlocked(request.getIsBlocked());
        response.setIsSuspicious(request.getIsSuspicious());
        response.setBlockedReason(request.getBlockedReason());
        response.setSuspiciousReason(request.getSuspiciousReason());

        when(suspiciousAccountTransferService.update(any(), any()))
                .thenReturn(response);

        String json = new ObjectMapper().writeValueAsString(response);
        mockMvc.perform(MockMvcRequestBuilders.put("/transfer/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void updateTransferShouldReturnStatusBad() throws Exception {
        var response = new SuspiciousAccountTransfer();
        when(suspiciousAccountTransferService.update(any(), any()))
                .thenReturn(null);

        String json = new ObjectMapper().writeValueAsString(response);
        mockMvc.perform(MockMvcRequestBuilders.put("/transfer/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteTransferShouldReturnStatusOk() throws Exception {
        var request = new SuspiciousTransferDtoImpl(777L, true, true, "Oops", "No");
        var response = new SuspiciousAccountTransfer();
        response.setIsBlocked(request.getIsBlocked());
        response.setIsSuspicious(request.getIsSuspicious());
        response.setBlockedReason(request.getBlockedReason());
        response.setSuspiciousReason(request.getSuspiciousReason());

        when(suspiciousAccountTransferService.delete(any()))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.delete("/transfer/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTransferShouldReturnStatusBad() throws Exception {
        when(suspiciousAccountTransferService.delete(any()))
                .thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.delete("/transfer/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}