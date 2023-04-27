package com.cts;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.entity.SurveyReport;
import com.cts.repository.ClaimDetailsRepo;
import com.cts.service.SurveyReportService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class SurveyServiceApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private SurveyReportService surveyReportService;

    @MockBean
    private ClaimDetailsRepo claimDetailsRepo;
	
	ObjectMapper mapper = new ObjectMapper();
	

	@Test
	public void testAddEmployee() throws Exception {
		SurveyReport survey = new SurveyReport("CL12345678","AS12345",10000,12000,1000,21000,54000);
		
		String jsonString = mapper.writeValueAsString(survey);
		
		mockMvc.perform(post("/api/surveyors/new").content(jsonString).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isCreated());
		
	}
	

	@Test
	public void testGetEmployeeById() throws Exception {
		mockMvc.perform(get("/api/survey/CL12345678").accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());
	}
	
	@Test
    public void testUpdateClaim() throws Exception {
        String claimId = "CL12345678";
        SurveyReport surveyReport = new SurveyReport();
        surveyReport.setLabour_Charges(100);
        surveyReport.setParts_Cost(200);
        surveyReport.setPolicy_Class(300);
        surveyReport.setDepreciation_Cost(50);
                
        mockMvc.perform(put("/api/survey/CL12345678", claimId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(surveyReport)));
    }


	private byte[] asJsonString(SurveyReport surveyReport) {
		return null;
	}


}
