package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.model.HereResponse;

@FeignClient(name="M43szwHxO6CFOL8A9K4o",url="https://places.ls.hereapi.com/places/v1/discover/explore?")
public interface HereMapsInterface {

	
	@GetMapping("?at={Lat}&cat={forg}&size={size}&apiKey=c3jxuWMjNPRkcA5laDbcYlY6q1250p96XPTwx-3gOb0")
	public  HereResponse retrieveByLoc(@PathVariable String Lat,@PathVariable String forg,@PathVariable int size);
	
	
}
