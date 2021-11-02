package com.covid.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.covid.main.model.AllCovidInfoModel;
import com.covid.main.repository.AllCovidRepository;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service //khusus web dan webservice
public class CovidApiService {

	private AllCovidRepository service;

	public CovidApiService() {

		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://covid-19.dataflowkit.com/")
				.addConverterFactory(GsonConverterFactory.create()).build();
		service = retrofit.create(AllCovidRepository.class);
	}

	public List<AllCovidInfoModel> getAllCovidInfo() {
		Call<List<AllCovidInfoModel>> callInfo = service.getAll();
		Response<List<AllCovidInfoModel>> response = null;
		try {
			response = callInfo.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return response.body();

	}
	
	public AllCovidInfoModel getCovidInfo(String country) {
		Call<AllCovidInfoModel> call = service.getByCountry(country);
		Response<AllCovidInfoModel> response = null;
		try {
			response = call.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response.body();
	}
}
