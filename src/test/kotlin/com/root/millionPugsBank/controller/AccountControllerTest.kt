package com.root.millionPugsBank.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.root.millionPugsBank.model.User
import com.root.millionPugsBank.service.crud.dto.AccountDTO
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AccountControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper,
) {
    val baseUrl = "/api/account"
    val userUrl = "/api/user"

    @Order(0)
    @Test
    fun `should create new user`() {
        val user = User(firstName = "John", lastName = "Doe", loginID = "12345", password = "haslo123")

        mockMvc.post(userUrl) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(user)
        }
    }

    @Order(1)
    @Test
    fun `should create new account for user and return info`() {
        val accountDto = AccountDTO(balance = 15.0, userID = 1)

        val postAccount = mockMvc.post(baseUrl) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(accountDto)
        }

        postAccount.andDo { print() }
            .andExpect {
                status { isOk() }
                jsonPath("$.balance") { value(15.0) }
            }
    }

    @Order(2)
    @Test
    fun `should return 2 accounts for user`() {
        val userId = 1
        mockMvc.get("$baseUrl/list/$userId")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                jsonPath("$") { isArray() }
                jsonPath("$[0].balance") { value(0.0) }
                jsonPath("$[1].balance") { value(15.0) }
            }
    }

    @Order(3)
    @Test
    fun `should delete account of id 1 and leave account with id 2 for user`() {
        val accountId = 1
        val userId = 1
        mockMvc.delete("$baseUrl/$accountId")
            .andExpect { status { isOk() } }

        mockMvc.get("$baseUrl/list/$userId")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                jsonPath("$") { isArray() }
                jsonPath("$[0].id") { value(2) }
                jsonPath("$[0].balance") { value(15.0) }
            }
    }

    @Order(4)
    @Test
    fun `should return balance of account equal 30`() {
        val accountDto = AccountDTO(id = 2, balance = 20.0, userID = 1)
        val addBalance = mockMvc.patch("$baseUrl/add") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(accountDto)
        }
        addBalance.andDo { print() }
            .andExpect {
                status { isOk() }
                jsonPath("$.balance") { value(35.0) }
            }
    }

    @Order(5)
    @Test
    fun `should return balance of account equal 0`() {
        val accountDto = AccountDTO(id = 2, balance = 30.0, userID = 1)
        val removeBalance = mockMvc.patch("$baseUrl/subtract") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(accountDto)
        }
        removeBalance.andDo { print() }
            .andExpect {
                status { isOk() }
                jsonPath("$.balance") { value(5.0) }
            }
    }

    @Order(6)
    @Test
    fun `should return balance equal 5`() {
        val id = 2
        mockMvc.get("$baseUrl/$id").andDo { print() }
            .andExpect {
                status { isOk() }
                jsonPath("$") { value(5.0) }
            }
    }

    @Order(7)
    @Test
    fun `should return balance in euro more than 5`() {
        val id = 2
        val code = "EUR"
        val result = mockMvc.get("$baseUrl/$id/$code").andDo { print() }.andExpect { status { isOk() } }.andReturn()
        assert(result.response.contentAsString.toDouble()>5)

    }
}