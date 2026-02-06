//package com.jfs.operations.lite.controller.api.impl;
//
//import com.jfs.operations.lite.controller.api.admin.impl.AdminController;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
////@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class AdminControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void whenInsertingCustomers_thenCustomersAreCreated() throws Exception {
//        this.mockMvc.perform(put("/api/admin/reload-data"))
//                .andExpect(status().is2xxSuccessful());
//    }
//}
