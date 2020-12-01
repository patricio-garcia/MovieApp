package cl.desafiolatam.moviesapp.data;

import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.desafiolatam.moviesapp.data.model.MovieDetail;
import cl.desafiolatam.moviesapp.data.model.MovieResult;
import cl.desafiolatam.moviesapp.data.model.MovieSearch;

public class DataLoaderUtils {
    private DataLoaderUtils() {
        throw  new IllegalStateException("Utils class!!");
    }

    public static List<MovieResult> readMovies(AssetManager assetManager) {
        Gson gson  = new Gson();
        try {
            InputStream inputStream = assetManager.open("BatmanList.json");
            InputStreamReader reader = new InputStreamReader(inputStream);
            MovieSearch movieSearch = gson.fromJson(reader, MovieSearch.class);
            return movieSearch.getSearch();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static Map<String, MovieDetail> readAllMovieDetails(AssetManager assetManager) {
        Map<String, MovieDetail> movieDetailMap = new HashMap<>();
        List<String> movieNames = new ArrayList<>(Arrays
                .asList("Batman.json",
                        "BatmanAndRobin.json",
                        "BatmanBegins.json",
                        "BatmanForever.json",
                        "BatmanReturns.json",
                        "BatmanTheanimatedSeries.json",
                        "BatmanUnderTheRedHood.json",
                        "BatmanVSupernan.json",
                        "TheLegoBatmanMovie.json"));
        for (String fileName: movieNames) {
            MovieDetail movieDetail = readMovieDetail(assetManager, fileName);
            if (movieDetail != null) {
                movieDetailMap.put(movieDetail.getImdbID(), movieDetail);
            }
        }
        return movieDetailMap;
    }

    private static MovieDetail readMovieDetail(AssetManager assetManager, String fileName) {
        Gson gson = new Gson();
        try {
            InputStream inputStream = assetManager.open(fileName);
            InputStreamReader reader = new InputStreamReader(inputStream);
            return gson.fromJson(reader, MovieDetail.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
