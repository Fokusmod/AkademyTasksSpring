package com.example.AkademyTasks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AkademyTasksApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllBook_Success() throws Exception {
        mockMvc.perform(get("/api/v1/books")
                        .param("page", "0")
                        .param("size", "2")
                        .param("sortType", "asc")
                        .param("sortBy", "title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andDo(print());
    }

    @Test
    public void getAllBook_PageTwoIsEmpty() throws Exception {
        mockMvc.perform(get("/api/v1/books")
                        .param("page", "2")
                        .param("size", "10")
                        .param("sortType", "desc")
                        .param("sortBy", "title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)))
                .andDo(print());
    }

    @Test
    public void getAllBook_BadRequestType() throws Exception {
        mockMvc.perform(get("/api/v1/books")
                        .param("page", "0")
                        .param("size", "2")
                        .param("sortType", "a")
                        .param("sortBy", "title"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("getAllBook.sortType: size must be between 3 and 4")))
                .andDo(print());
    }

    @Test
    public void getAllBook_BadRequestBy() throws Exception {
        mockMvc.perform(get("/api/v1/books")
                        .param("page", "0")
                        .param("size", "2")
                        .param("sortType", "desc")
                        .param("sortBy", "t"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("getAllBook.sortBy: size must be between 2 and 2147483647")))
                .andDo(print());
    }
}
