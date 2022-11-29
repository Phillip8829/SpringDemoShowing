package com.galvanize.cerialCats;


import com.galvanize.cerialCats.Model.Report;
import com.galvanize.cerialCats.Repo.ReportRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc


public class ReportControllerTests {

    Report a;
    Report b;

    @Autowired
    ReportRepo reportRepo;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void init() {
        a = new Report();
        b = new Report();

        a.setDateTime(LocalDateTime.now());
        a.setLocation("test a");
        a.setEventType(true);
        a.setHarm(true);
        a.setIndividualsInvolved(Arrays.asList("test a1", "test a2"));
        a.setEventCategory(Arrays.asList("test a1", "test a2"));
        a.setIncidentEffect(true);
        a.setWitnessName("test a");
        a.setWitnessTelephone("test a");
        a.setDepartmentsInvolved(Arrays.asList("test a1", "test a2"));
        a.setDescription("test a");
        a.setActions("test a");
        a.setPatientName("test a");
        a.setPatientSSN("test a");
        a.setPatientPhone("test a");
        a.setPatientAddress("test a");

        b.setDateTime(LocalDateTime.now());
        b.setLocation("test b");
        b.setEventType(false);
        b.setHarm(false);
        b.setIndividualsInvolved(Arrays.asList("test b1", "test b2"));
        b.setEventCategory(Arrays.asList("test b1", "test b2"));
        b.setIncidentEffect(false);
        b.setWitnessName("test b");
        b.setWitnessTelephone("test b");
        b.setDepartmentsInvolved(Arrays.asList("test b1", "test b2"));
        b.setDescription("test b");
        b.setActions("test b");
        b.setPatientName("test b");
        b.setPatientSSN("test b");
        b.setPatientPhone("test b");
        b.setPatientAddress("test b");
    }

    @Transactional
    @Rollback
    @Test
    public void getAllReportsReturns2Reports() throws Exception {
        this.reportRepo.save(a);
        this.reportRepo.save(b);

        mockMvc.perform(get("/Report")).andExpect(jsonPath("$", hasSize(2)));
    }

    @Transactional
    @Rollback
    @Test
    void getOneReportReturnsCorrectReport() throws Exception {
        Report target = this.reportRepo.save(a);
        this.reportRepo.save(b);

        this.mockMvc.perform(get("/Report/" + target.getId()))
                .andExpect(jsonPath("$.id").value(target.getId()));
    }


    @Transactional
    @Rollback
    @Test
    void postMappingAddsItemToDataBase() throws Exception {

        assertEquals(0, this.reportRepo.count());

        mockMvc.perform(post("/Report").contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {

                              "dateTime": "2010-12-05 05:22:00",
                               "location": "Fort Hood",
                                "eventType": true,
                                "harm":true,
                                "individualsInvolved": ["Billy", "Timmy"],
                                "eventCategory": ["Car Accident", "Drugs"],
                                "incidentEffect": true,
                                "witnessName": "BJ",
                                "witnessTelephone": "999-999-9999",
                                "departmentInvolved": ["Army", "AirForce"],
                                "description": "A Super Description",
                                "action": "Man when baseball bat hit nicks cat",
                                "patientName": "Forest",
                                "patientSSN": "111-1111-1111",
                                "patientPhone": "912-555-4444",
                                "patientAddress": "SWF AVE"
                            }
                        """)).andExpect(status().isOk());

        assertEquals(1, this.reportRepo.count());

    }

    @Test
    @Transactional
    @Rollback
    void deleteReportMappingRemovesItemFromDb() throws Exception {
        Report target = reportRepo.save(a);
        MockHttpServletRequestBuilder request = delete(String.format("/Report/%d", target.getId()));
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").doesNotHaveJsonPath());

    }

    @Test
    @Transactional
    @Rollback
    void patchReportMapping() throws Exception {
        reportRepo.save(a);
        Report c = new Report();
        c.setPatientName("test c");
        reportRepo.save(c);

    MockHttpServletRequestBuilder request = get("/Report/"+c.getId());

        mockMvc.perform(request)
                .andExpect(jsonPath("$.patientName").value("test c"));

        MockHttpServletRequestBuilder requestPatch = patch("/Report/"+c.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                                                    "dateTime": "2010-12-05 05:22",
                                                    "location": "Fort Hood",
                                                    "eventType": true,
                                                    "harm":true,
                                                    "individualsInvolved": ["Billy", "Timmy"],
                                                    "eventCategory": ["Car Accident", "Drugs"],
                                                    "incidentEffect": true,
                                                    "witnessName": "BJ",
                                                            "witnessTelephone": "999-999-9999",
                                                                    "departmentInvolved": ["Army", "AirForce"],
                                                            "description": "A Super Description",
                                                            
                                                                            "action": "Man when baseball bat hit nicks cat",
                                                                            "patientName": "Forest",
                                                                            "patientSSN": "111-1111-1111",
                                                                            "patientPhone": "912-555-4444",
                                                                            "patientAddress": "SWF AVE"
                                                   
                        }
                        """);

        mockMvc.perform(requestPatch)
                .andExpect(jsonPath("$.dateTime").value("2010-12-05 05:22"));
        System.out.println(reportRepo.findAll());
        System.out.println(reportRepo.count());









//        Report target = reportRepo.save(a);
//        this.a.setPatientName("bob");
//        Report c = new Report();
//        c.setPatientName("bob");
//        reportRepo.save(c);
//        ObjectMapper obj = new ObjectMapper();
//        String json = obj.writeValueAsString(c);
//        System.out.println(json);
//
//        this.mockMvc.perform(patch("/Report/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("patientName","bob")
//                        .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.patientName").value("bob"));

//        MockHttpServletRequestBuilder request = patch("/Report/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("""
//                        {
//                        "patientName": "bob"
//                        }
//                        """);
//        this.mockMvc.perform(request)
//                .andExpect(status().isOk());
    }

}

