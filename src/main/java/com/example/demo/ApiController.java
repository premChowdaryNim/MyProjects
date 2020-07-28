package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.demo.Exception.BadRequestException;
import com.example.model.Constants;
import com.example.model.Details;
import com.example.model.HereResponse;
import com.example.model.Results;

@Component
public class ApiController implements ApiService {
	@Autowired
	private HereMapsInterface proxy;
	
	@Override
	public Object getDetailsDocument(Details details) throws Exception {
		
		if(null !=details) {
			if(null !=details.getLocation())
			{
				if(null != details.getLocation().getxLoc() && !details.getLocation().getxLoc().isEmpty()
						&& null != details.getLocation().getyLoc() && !details.getLocation().getyLoc().isEmpty()) {
					
					List<CompletableFuture<HereResponse>> list= new ArrayList<CompletableFuture<HereResponse>>();
					HereResponse response=new HereResponse();
					Results resArray=new Results();
					List<Object> lstItems= new ArrayList<>();
					
					for(String arrStr:Arrays.asList("petrol-station","restaurant","shopping")) {
						CompletableFuture<HereResponse> a1=getDetailsFromHereApi(details,arrStr);
						list.add(a1);
					}
					for(CompletableFuture<HereResponse> i:list)
					{
						for(int j=0;j<i.get().getResults().getItems().size();j++)
						 lstItems.add(i.get().getResults().getItems().get(j));
					}
					resArray.setItems(lstItems);
					response.setResults(resArray);
					response.setSearch(list.get(0).get().getSearch());
					return response;
				}
				else
					throw new BadRequestException(Constants.BAD_REQUEST);	
			}
			else
				throw new BadRequestException(Constants.BAD_REQUEST);
		}
		else
			throw new BadRequestException(Constants.BAD_REQUEST);
		
	}

	@Async
	private CompletableFuture<HereResponse> getDetailsFromHereApi(Details details, String arrStr) {
		HereResponse hereResponse = proxy.retrieveByLoc(String.join(",", Arrays.asList(details.getLocation().getxLoc(),details.getLocation().getyLoc()))
				,arrStr, 3);
		return CompletableFuture.completedFuture(hereResponse);
	}

}
