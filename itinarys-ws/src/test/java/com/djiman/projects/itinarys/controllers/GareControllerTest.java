package com.djiman.projects.itinarys.controllers;

import com.djiman.projects.itinarys.dto.GareDTO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.djiman.projects.itinarys.persistence.GareRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class GareControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private GareRepository gareRepository;

    @Test
    public void testCreateGare() throws Exception {

        String nomGare = "testGare";
        String idGare = "idGareTest";

        GareDTO gareDto = new GareDTO();
        gareDto.setNomGare(nomGare);
        gareDto.setIdGare(idGare);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(gareDto);
        ResultActions actions = this.mvc.perform(post("/gare").contentType(MediaType.APPLICATION_JSON).content(json));
        actions.andExpect(status().isOk());
    }
}