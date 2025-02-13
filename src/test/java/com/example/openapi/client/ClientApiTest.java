package com.example.openapi.client;

import com.example.openapi.OpenapiApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = OpenapiApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class ClientApiTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void init() {
        Client client = new Client();
        client.setLastname("Tester");
        client.setFirstname("Testboy");
        client.setClientId(1l);
        client.setEmail("tester@mytestcompany.com");
        clientRepository.save(client);
    }

    @AfterEach
    void tearDown() {
        clientRepository.deleteAll();
    }

    @Test
    void getAllClients() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/client/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        List<ClientDTO> clients = objectMapper.readValue(json, new TypeReference<>(){});

        assertFalse(clients.isEmpty());
        assertEquals(1, clients.size());
        ClientDTO testerClient = clients.get(0);
        assertEquals("Testboy", testerClient.getFirstname());
        assertEquals("Tester", testerClient.getLastname());
        assertEquals("tester@mytestcompany.com", testerClient.getEmail());
        assertEquals(1L, testerClient.getClientId());
    }

    @Test
    void findClientByClientId_FoundClient() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/client/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        ClientDTO testerClient = objectMapper.readValue(json, new TypeReference<>(){});

        assertEquals("Testboy", testerClient.getFirstname());
        assertEquals("Tester", testerClient.getLastname());
        assertEquals("tester@mytestcompany.com", testerClient.getEmail());
        assertEquals(1L, testerClient.getClientId());
    }

    @Test
    void findClientByClientId_NotFoundClient() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/client/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        ClientDTO testerClient = objectMapper.readValue(json, new TypeReference<>(){});
        assertNull(testerClient.getClientId());
    }

    @Test
    void createClient() throws Exception {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientId(2l);
        clientDTO.setEmail("mymail@mytestcompany.com");
        clientDTO.setLastname("Tester 2");
        clientDTO.setFirstname("Test");
        MvcResult mvcResult = mockMvc.perform(post("/client/")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        ClientDTO testerClient = objectMapper.readValue(json, new TypeReference<>(){});

        assertEquals("Test", testerClient.getFirstname());
        assertEquals("Tester 2", testerClient.getLastname());
        assertEquals("mymail@mytestcompany.com", testerClient.getEmail());
        assertEquals(2L, testerClient.getClientId());
        assertEquals(2, clientRepository.findAll().size());
    }

    @Test
    void updateClient() throws Exception {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setLastname("Modified Tester");
        clientDTO.setFirstname("Modified Testboy");
        clientDTO.setEmail("tester@mytestcompany.com");
        MvcResult mvcResult = mockMvc.perform(put("/client/1")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        ClientDTO testerClient = objectMapper.readValue(json, new TypeReference<>(){});
        assertEquals("Modified Testboy", testerClient.getFirstname());
        assertEquals("Modified Tester", testerClient.getLastname());
        assertEquals("tester@mytestcompany.com", testerClient.getEmail());
        assertEquals(1L, testerClient.getClientId());
        assertEquals(1, clientRepository.findAll().size());
    }

    @Test
    void deleteClientByClientId() throws Exception {
        Client client = new Client();
        client.setLastname("Modified Tester");
        client.setFirstname("Modified Testboy");
        client.setEmail("tester@mytestcompany.com");
        client.setClientId(2L);
        clientRepository.save(client);

        assertEquals(2, clientRepository.findAll().size());

        MvcResult mvcResult = mockMvc.perform(delete("/client/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        assertEquals(1, clientRepository.findAll().size());
    }
}