package com.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForecastSummaryDto {
    private String evolution;
    private String tempTrend;
    private String pressureTrend;
    private String windCategory;
}
