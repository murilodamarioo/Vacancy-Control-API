package com.rocketseat.vacancy_control.models.company.controllers;

import com.rocketseat.vacancy_control.modules.company.dto.CreateJobDTO;
import com.rocketseat.vacancy_control.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("Should be able to create a new job")
    public void should_be_able_to_create_a_new_job() throws Exception {
        var createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .level("LEVEL_TEST")
                .description("DESCRIPTION_TEST")
                .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson(createJobDTO)).header("Authorization", TestUtils.generateToken(UUID.fromString("e5025975-2bce-4bd2-ba7b-39ff4c490401"), "JAVAGAS_@123#")))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        System.out.println(result);
    }


}
