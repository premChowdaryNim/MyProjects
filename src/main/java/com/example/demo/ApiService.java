package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Details;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1")
public interface ApiService {

	
	@PostMapping("/details")
	@ApiOperation(value = "Get NearBy Location Details", notes = "Location co-ordinates need to pass in request, we will get near by restaurants , parking spots and petrol stations", tags = {
			"Details APIS" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "No Data Found"),
			@ApiResponse(code = 500, message = "Internal Server Error Occured") })

	public Object getDetailsDocument(
			@RequestBody(required= true) Details details)throws Exception;
	
	
	
}
