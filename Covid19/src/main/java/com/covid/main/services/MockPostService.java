package com.covid.main.services;

import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Service;

import com.covid.main.model.MockPostModel;
import com.covid.main.repository.MockPostRepository;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class MockPostService {
	private MockPostRepository service;
	
	public MockPostService() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://jsonplaceholder.typicode.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		service = retrofit.create(MockPostRepository.class);
	}
	
	public MockPostModel inputModel(MockPostModel model) {
		Call<MockPostModel> call = service.inputMock(model);
		Response<MockPostModel> response = null;
		try {
			response = call.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response.body();
	}
}
