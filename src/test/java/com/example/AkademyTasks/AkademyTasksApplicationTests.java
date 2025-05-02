package com.example.AkademyTasks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AkademyTasksApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getSimpleInfoAllUsers() throws Exception {
        String expectedJson = """
                [
                    {
                        "name": "John Doe"
                    },
                    {
                        "name": "Jane Smith"
                    }
                ]
                """;

        mockMvc.perform(get("/api/v1/all_users_info"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson))
                .andDo(print());
    }

    @Test
    public void getCurrentUserFullDetailsInfo() throws Exception {
        String expectedJson = """
                {
                    "name": "John Doe",
                    "email": "john@example.com",
                    "order": [
                        {
                            "orderCost": 99.99,
                            "orderStatus": {
                                "title": "Pending"
                            }
                        },
                        {
                            "orderCost": 19.99,
                            "orderStatus": {
                                "title": "Delivered"
                            }
                        }
                    ]
                }
                """;
        mockMvc.perform(get("/api/v1/details")
                        .param("name", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson))
                .andDo(print());
    }

}
