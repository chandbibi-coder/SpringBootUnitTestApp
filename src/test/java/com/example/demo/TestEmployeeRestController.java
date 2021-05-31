package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.controller.EmployeeRestController;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class TestEmployeeRestController {
	public static Logger logger=  LoggerFactory.getLogger(EmployeeRestController.class);
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Disabled
	public void testSaveEmployee() throws Exception {
     logger.info("testing save employee");
		//1. create dummy request
		MockHttpServletRequestBuilder req =
		MockMvcRequestBuilders
			.post("/employee/save")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"empName\" :\"sam\", \"empSal\":3000.0}");
		
		//2. execute request and get result
		  MvcResult result = mockMvc.perform(req).andReturn();
		
		//3. read response
		  MockHttpServletResponse  response =   result.getResponse();
		  
		//4. test using assert method
		  assertEquals(HttpStatus.OK.value(), response.getStatus());
		  if(!response.getContentAsString().contains("Employee saved")) {
			  fail("save employee not working!!");
		  }
	}

	@Test
	@Disabled
	public void testGetAllEmployees() throws Exception {
		MockHttpServletRequestBuilder req=
		MockMvcRequestBuilders.get("/employee/all");
		
		MvcResult result =   mockMvc.perform(req).andReturn();
		MockHttpServletResponse response =   result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
		if(response.getContentAsString().length()<=0) {
			fail("no data provided");
		}
	}
	
	@Test
	@Disabled
	public void tsetGetEmployeeExist() throws Exception {
		MockHttpServletRequestBuilder request=
				MockMvcRequestBuilders.get("/employee/2");
		 MvcResult result=   mockMvc.perform(request).andReturn();
		 MockHttpServletResponse response =   result.getResponse();
		 
		 assertEquals(HttpStatus.OK.value(), response.getStatus());
		 assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
		 if(response.getContentAsString().isEmpty()) {
			 fail("employee data should present");
		 }
	}
	
	@Test
	@Disabled
	public void tsetGetEmployeeNotExist() throws Exception {
		MockHttpServletRequestBuilder request=
				MockMvcRequestBuilders.get("/employee/30");
		
		MvcResult result=   mockMvc.perform(request).andReturn();
		 MockHttpServletResponse response=  result.getResponse();
		 assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		 if(!response.getContentAsString().equals("employee not found")) {
			 fail("may be data exists");
		 }
	}
	
	@Test
	@Disabled
	public void testDeleteEmployee() throws Exception {
		 MockHttpServletRequestBuilder req=   MockMvcRequestBuilders.delete("/employee/7");
		 MvcResult result = mockMvc.perform(req).andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 
		 assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
		 assertEquals("deleted", response.getContentAsString());
	}
	
	@Test
	@Disabled
	public void testDeleteEmployeeNotExist() throws Exception {
		   MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/employee/30");
		   MvcResult result= mockMvc.perform(request).andReturn();
		   
		   MockHttpServletResponse response =  result.getResponse();
		   
		   assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		   assertEquals("employee not found", response.getContentAsString());
	}
	
	
	@Test
	public void testUpdateEmployee() throws Exception {

		//1. create dummy request
		MockHttpServletRequestBuilder req =  MockMvcRequestBuilders
			.put("/employee/10")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"empName\" :\"fam\", \"empSal\":3000.0}");
		
		//2. execute request and get result
		  MvcResult result = mockMvc.perform(req).andReturn();
		
		//3. read response
		  MockHttpServletResponse  response =   result.getResponse();
		  
		//4. test using assert method
		  assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}
