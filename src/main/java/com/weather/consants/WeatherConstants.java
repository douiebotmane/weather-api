package com.weather.consants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeatherConstants {

    // Temperature and Pressure Trend
    public static final String INCREASING = "Increasing";
    public static final String SHARPLY_INCREASING = "Sharply increasing";
    public static final String DECREASING = "Decreasing";
    public static final String SHARPLY_DECREASING = "Sharply decreasing";
    public static final String STABLE = "stable";

    // General Evolution
    public static final String IMPROVING = "Improving";
    public static final String DETERIORATING = "Deteriorating";

    // Wind Scale (Beaufort)
    public static final String CALM = "Calm";
    public static final String LIGHT_AIR = "Light Air";
    public static final String LIGHT_BREEZE = "Light Breeze";
    public static final String GENTLE_BREEZE = "Gentle Breeze";
    public static final String MODERATE_BREEZE = "Moderate Breeze";
    public static final String FRESH_BREEZE = "Fresh Breeze";
    public static final String STRONG_BREEZE = "Strong Breeze";
    public static final String NEAR_GALE = "Near Gale";
    public static final String GALE = "Gale";
    public static final String STRONG_GALE = "Strong Gale";
    public static final String STORM = "Storm";
    public static final String VIOLENT_STORM = "Violent Storm";
    public static final String HURRICANE = "Hurricane";
}
