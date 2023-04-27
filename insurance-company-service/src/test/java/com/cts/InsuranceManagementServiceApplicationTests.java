package com.cts;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.controller.InsuranceController;
import com.cts.entity.ClaimDetails;
import com.cts.entity.Fees;
import com.cts.entity.Policy;
import com.cts.entity.Surveyor;
import com.cts.model.AmountResponse;
import com.cts.model.ClaimResponse;
import com.cts.model.PolicyResponse;
import com.cts.repository.ClaimDetailsRepo;
import com.cts.repository.SurveyorRepo;
import com.cts.service.InsuranceService;
import com.cts.service.InsuranceServiceeImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.anyInt;

@AutoConfigureMockMvc
@SpringBootTest
class InsuranceManagementServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private InsuranceController insuranceController;
	
	 @MockBean
	    private SurveyorRepo surveyorRepo;
	
	
	@MockBean
	private InsuranceService insuranceService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@MockBean
	private ClaimDetailsRepo claimDetailsRepo;
	
	
	@Test
	public void testAllClaims() throws Exception {
		mockMvc.perform(get("/api/claims"))
		.andExpect(status().isForbidden());
		
	}
	
	@Test
	public void testAllSurveyors() throws Exception {
		mockMvc.perform(get("/api/surveyors"))
		.andExpect(status().isForbidden());
	}
	
	@Test
	public void testAllPolicies() throws Exception {
		mockMvc.perform(get("/api/policies"))
		.andExpect(status().isForbidden());
		
	}
	
	@Test
	public void testGetClaimById() throws Exception {
		mockMvc.perform(get("/api/claims/CLdeWB2023").accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isForbidden());
	}
	
	@Test
    public void testAddNewPolicy() throws Exception {
      
        PolicyResponse policyResponse = new PolicyResponse();
        policyResponse.setInsuredLastName("DOwney");
        policyResponse.setVehicleNo("MH01AB1234");
        policyResponse.setDateOfInsurance(new Date());

        String json = mapper.writeValueAsString(policyResponse);

        Policy policy = new Policy();
        policy.setPolicyNo("DOXYZ23");
        policy.setInsuredLastName("DOwney");
        policy.setVehicleNo("XYZ123");
        policy.setDateOfInsurance(new Date());
        when(insuranceService.addNewPolicy(policyResponse)).thenReturn(policy);

        mockMvc.perform(post("/api/policies/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isForbidden());
               
     }
	
	
	//NOt neeeded for me
//	@Test
//    void testRaiseClaimRequest() throws Exception {
//		
//        String policyId = "POKP123";
//        String policyNo = "POKP123";
//
//        when(insuranceService.raiseClaimRequest(policyId)).thenReturn(policyNo);
//
//        mockMvc.perform(post("/api/policies/{id}", policyId))
//                .andExpect(status().isOk())
//                .andExpect(content().string(policyNo));
//    }
	
	@Test
    public void testReleaseFees() throws Exception {
     
        String id = "12345";
        Fees fees = new Fees();
        fees.setFeeId(123);
        fees.setFees(1000);
        fees.setEstimatedStartLimit(5000);
        fees.setEstimateEndLimit(10000);

        when(insuranceService.releaseFees(id)).thenReturn(fees);

        mockMvc.perform(get("/api/surveyorfees/{id}", id))
                .andExpect(status().isForbidden());
               
    }
	
	
	
	//FOR IRDA not needed for me
	@Test
	public void testGetAllAmountProvided() throws Exception {
		
	    int month = 4;
	    int year = 2022;
	    AmountResponse amountResponse1 = new AmountResponse();
	    amountResponse1.setAmountApprovedBySurveyor(5000);
	    AmountResponse amountResponse2 = new AmountResponse();
	    amountResponse2.setAmountApprovedBySurveyor(10000);
	    List<AmountResponse> expectedAmounts = Arrays.asList(amountResponse1, amountResponse2);

	    when(insuranceService.getAllAmountProvided(month, year)).thenReturn(expectedAmounts);

	    mockMvc.perform(get("/api/claims/amount/date")
	            .param("month", String.valueOf(month))
	            .param("year", String.valueOf(year)))
	            .andExpect(status().isForbidden());
	          
	    
	}
	
	
	//FOR IRDA
	@Test
    public void testGetAllPendingClaimsByDate() throws Exception {
		
        int month = 4;
        int year = 2023;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/claims/date")
                .param("month", String.valueOf(month))
                .param("year", String.valueOf(year))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();

    }
	
	@Test
    public void testGetClaimsAllocatedSurveyor() throws Exception {
		 
        ClaimDetails claim1 = new ClaimDetails();
        claim1.setClaimId("CLAJKL2023");
        claim1.setEstimatedLoss(1000);
        claim1.setDateOfAccident(new Date());
        claim1.setClaimStatus(true);
        claim1.setAmountApprovedBySurveyor(800);
        claim1.setInsuranceCompanyApproval(true);
        claim1.setWithdrawClaim(false);
        claim1.setSurveyorFees(200);

        ClaimDetails claim2 = new ClaimDetails();
        claim2.setClaimId("CLAJKL2022");
        claim2.setEstimatedLoss(2000);
        claim2.setDateOfAccident(new Date());
        claim2.setClaimStatus(false);
        claim2.setAmountApprovedBySurveyor(1500);
        claim2.setInsuranceCompanyApproval(false);
        claim2.setWithdrawClaim(true);
        claim2.setSurveyorFees(300);

        List<ClaimDetails> claims = new ArrayList<>();
        claims.add(claim1);
        claims.add(claim2);

        when(insuranceService.getClaimsAllocatedSurveyor()).thenReturn(claims);

        mockMvc.perform(get("/api/claims/survyed"))
                .andExpect(status().isForbidden());

    }
	 
	 
	@Test
    public void testGetClaimsWithoutSurveyor() throws Exception {
		 
        ClaimDetails claimDetails = new ClaimDetails();
        claimDetails.setClaimId("CLAJKL2022");
        claimDetails.setEstimatedLoss(1000);
        claimDetails.setDateOfAccident(new Date());
        claimDetails.setClaimStatus(false);
        claimDetails.setAmountApprovedBySurveyor(0);
        claimDetails.setInsuranceCompanyApproval(false);
        claimDetails.setWithdrawClaim(false);
        claimDetails.setSurveyorFees(0);
        claimDetails.setSurveyor(null);
        claimDetails.setPolicy(null);

        List<ClaimDetails> claimList = new ArrayList<>();
        claimList.add(claimDetails);

        when(insuranceService.getClaimsWithoutSurveyor()).thenReturn(claimList);

        mockMvc.perform(get("/api/claims/unsurvyed"))
                .andExpect(status().isForbidden());

    }
	 
	@Test
    public void testUpdateClaim() throws Exception {
		
        String id = "CLAJKL2022";
        ClaimResponse claimResponse = new ClaimResponse();
        claimResponse.setClaimStatus(true);
        claimResponse.setAmountApprovedBySurveyor(5000);
        claimResponse.setInsuranceCompanyApproval(true);
        claimResponse.setSurveyor(new Surveyor());

        ClaimDetails claimDetails = new ClaimDetails();
        claimDetails.setClaimId(id);
        claimDetails.setClaimStatus(true);
        claimDetails.setAmountApprovedBySurveyor(5000);
        claimDetails.setInsuranceCompanyApproval(true);
        claimDetails.setSurveyor(new Surveyor());

        when(insuranceService.updateClaim(claimResponse, id)).thenReturn(claimDetails);

        mockMvc.perform(put("/api/claims/{id}/update", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(claimResponse)))
                .andExpect(status().isForbidden());
        
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    @Test
    public void testGetsrv() throws Exception {
		mockMvc.perform(get("/api/claims/surveyors/26000").accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isForbidden());
	}
}
	
	

