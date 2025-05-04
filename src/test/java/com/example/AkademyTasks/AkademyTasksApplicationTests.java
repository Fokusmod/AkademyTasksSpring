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
	void contextLoads() {
	}

    @Test
    public void welcomeHome() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void welcomeAnother() throws Exception {
        mockMvc.perform(get("/another"))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }
}
