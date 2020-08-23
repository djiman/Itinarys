package com.djiman.projects.itinarys.controllers;

import com.djiman.projects.itinarys.dto.GareDTO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.djiman.projects.itinarys.dto.LigneDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class LigneControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testCreateLigne() throws Exception {

        LigneDTO ligneDto = new LigneDTO();
        ligneDto.setCommentaire("Test Ligne Dto");
        ligneDto.setNomLigne("Test Nom Ligne");
        ligneDto.setStatut('1');
        ligneDto.setType("Train");

        List<GareDTO> garesDto = new ArrayList<>();
        GareDTO gareDto = new GareDTO("nom gare", "type", "gareId", "commentaire", '1');
        garesDto.add(gareDto);
        ligneDto.setGaresDto(garesDto);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(ligneDto);

        ResultActions actions = this.mvc.perform(post("/createLigne").contentType(MediaType.APPLICATION_JSON).content(json));
        actions.andExpect(status().isOk());
    }
}