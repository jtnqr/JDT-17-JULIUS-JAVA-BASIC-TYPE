package com.indivaragroup.activity.logic;

import com.indivaragroup.activity.dto.MovieDto;
import com.indivaragroup.activity.dto.MoviePlaylistDto;

import java.util.Calendar;
import java.util.List;

public class MovieService {
    public MoviePlaylistDto processMovies(List<MovieDto> allMovies) {
        MoviePlaylistDto playlist = new MoviePlaylistDto();
        Calendar calendar = Calendar.getInstance();

        for (MovieDto movie : allMovies) {
            if (movie.getReleaseDate() != null) {
                calendar.setTime(movie.getReleaseDate());
                int year = calendar.get(Calendar.YEAR);

                if (year < 2026) {
                    playlist.getNotUpdatedList().add(movie);
                } else {
                    playlist.getUpdatedList().add(movie);
                }
            }
        }
        return playlist;
    }
}