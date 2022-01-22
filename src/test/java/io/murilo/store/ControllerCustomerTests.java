package io.murilo.store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.murilo.store.model.Customer;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControllerCustomerTests {

    @Autowired
    private MockMvc mvc;

    @Order(1)
    @Test
    void testPostAction() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/customer")
                        .content(jsonString(new Customer("Antony", 12, true)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAction() throws Exception {
        mvc.perform(get("/customer")).andExpect(status().isOk());
    }

    @Test
    void testDeleteAction() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/customer/{id}", 1))
                .andExpect(status().isNoContent());
    }

    protected static String jsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
