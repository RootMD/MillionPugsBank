package com.root.millionPugsBank.exception

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class ExceptionControllerTest @Autowired constructor(
    val mockMvc: MockMvc
) {
    @Test
    fun `should return 404 with message error`() {
        val userID = 1234
        mockMvc.get("/api/user/$userID")
            .andDo { print() }
            .andExpect {
                status { isNotFound() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.message") { isNotEmpty() }
            }
    }
}