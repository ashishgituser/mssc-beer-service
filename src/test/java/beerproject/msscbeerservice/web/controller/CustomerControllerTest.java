package beerproject.msscbeerservice.web.controller;

import beerproject.msscbeerservice.web.model.CustomerDto;
import beerproject.msscbeerservice.web.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    CustomerDto customerDto;
    @MockBean
    private CustomerService customerService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        customerDto = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Ashish")
                .build();
    }

    @Test
    void getCustomer() throws Exception {
        //Given
        given(customerService.getCustomerById(any(UUID.class))).willReturn(customerDto);

        //Action
        mvc.perform(get("/api/v1/customer/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void handlePost() throws Exception {
        //Given
        CustomerDto savedDto = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("as")
                .build();
        String customerJson = objectMapper.writeValueAsString(savedDto);
        given(customerService.saveNewCustomer(any())).willReturn(savedDto);

        //Action
        mvc.perform(post("/api/v1/customer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated());
    }

    @Test
    void handleUpdate() {
    }

    @Test
    void deleteById() {
    }
}