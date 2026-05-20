package com.indivaragroup.activity.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class MoviePlaylistDto {
    private List<MovieDto> updatedList = new ArrayList<>();
    private List<MovieDto> notUpdatedList = new ArrayList<>();
}