package com.rocketseat.vacancy_control.models.company.controllers;

import com.rocketseat.vacancy_control.exceptions.CompanyNotFoundException;
import com.rocketseat.vacancy_control.modules.company.dto.CreateJobDTO;
import com.rocketseat.vacancy_control.modules.company.entity.CompanyEntity;
import com.rocketseat.vacancy_control.modules.company.repositories.CompanyRepository;
import com.rocketseat.vacancy_control.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

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
        var company = CompanyEntity.builder()
                .description("COMPANY_DESCRITION")
                .email("email@example.com")
                .password("COMPANY_PASSWORD")
                .username("COMPANY_USERNAME")
                .name("COMPANY_NAME")
                .build();

        company = companyRepository.saveAndFlush(company);

        var createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .level("LEVEL_TEST")
                .description("DESCRIPTION_TEST")
                .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson(createJobDTO))
                .header("Authorization", TestUtils.generateToken(UUID.fromString(company.getId().toString()), "JAVAGAS_@123#")))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        System.out.println(result);
    }

    @Test
    @DisplayName("Should not be able to create a new job if company not found")
    public void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception {
        var createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .level("LEVEL_TEST")
                .description("DESCRIPTION_TEST")
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson(createJobDTO))
                .header("Authorization", TestUtils.generateToken(UUID.fromString(UUID.randomUUID().toString()), "JAVAGAS_@123#")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}
