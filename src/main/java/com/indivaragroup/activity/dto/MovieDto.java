package com.indivaragroup.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private String releaseCode;
    private Date releaseDate;
}