package com.root.millionPugsBank.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.root.millionPugsBank.model.User
import org.json.JSONObject
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
internal class UserControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper,
) {

    val baseUrl = "/api/user"

    @Order(0)
    @Test
    fun `should create new user and return it back with hashed password`() {
        val user = User(firstName = "John", lastName = "Doe", loginID = "12345", password = "haslo123")

        val postUser = mockMvc.post(baseUrl) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(user)
        }
        val userResult = postUser.andDo { print() }
            .andExpect {
                status { isOk() }
                jsonPath("$.firstName") { value("John") }
                jsonPath("$.lastName") { value("Doe") }
                jsonPath("$.loginID") { value("12345") }
            }.andReturn()
        val jsonObject = JSONObject(userResult.response.contentAsString)
        assert(!jsonObject.get("password").equals(user.password))
    }

    @Order(1)
    @Test
    fun `should return John as name of first user in db`() {
        mockMvc.get(baseUrl)
            .andDo { print() }
            .andExpect {
                status { isOk() }
                jsonPath("$[0].firstName") { value("John") }
            }
    }

    @Order(2)
    @Test
    fun `should return John as name of found user`() {
        val id = 1
        mockMvc.get("$baseUrl/$id")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                jsonPath("$.firstName") { value("John") }
            }
    }

    @Order(3)
    @Test
    fun `should update user first name John to user first name Adam`() {
        val user = User(
            id = 1,
            firstName = "Adam",
            lastName = "Doe",
            loginID = "12345",
            password = "haslo123",
            email = "someEmail",
            address = "someAddress"
        )
        val id = 1
        mockMvc.patch(baseUrl) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(user)
        }

        mockMvc.get("$baseUrl/$id")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                jsonPath("$.firstName") { value("Adam") }
            }
    }

    @Order(4)
    @Test
    fun `should delete user`() {
        val id = 1
        mockMvc.delete("$baseUrl/$id")
            .andExpect {
                status { isOk() }
            }
        mockMvc.get("$baseUrl/$id")
            .andDo { print() }
            .andExpect {
                status { isNotFound() }
            }
    }
}