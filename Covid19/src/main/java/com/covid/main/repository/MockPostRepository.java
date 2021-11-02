package com.covid.main.repository;

import com.covid.main.model.MockPostModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MockPostRepository {
	
	@POST("posts")
	Call<MockPostModel> inputMock (@Body MockPostModel model);

}
