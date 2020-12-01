package cl.desafiolatam.moviesapp.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MovieSearch{

	@SerializedName("Response")
	private String response;

	@SerializedName("totalResults")
	private String totalResults;

	@SerializedName("Search")
	private List<MovieResult> search;

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	public void setTotalResults(String totalResults){
		this.totalResults = totalResults;
	}

	public String getTotalResults(){
		return totalResults;
	}

	public void setSearch(List<MovieResult> search){
		this.search = search;
	}

	public List<MovieResult> getSearch(){
		return search;
	}
}