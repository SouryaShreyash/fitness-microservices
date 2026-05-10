package com.fitness.aiservice.Model;



import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Activity {
    private String id;
    private String userId;
    private Integer duration;
    private Integer caloriesBurned;
    private String type;
    private LocalDateTime startTime;
    private Map<String,Object> additionalMetrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}