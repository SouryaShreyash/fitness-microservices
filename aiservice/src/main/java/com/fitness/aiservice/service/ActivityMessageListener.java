package com.fitness.aiservice.service;

import com.fitness.aiservice.Model.Activity;
import com.fitness.aiservice.Model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAiservice aiservice;
    private final RecommendationRepository recommendationRepository;

    @RabbitListener(queues = "activity.queue")
    public void processActivity(Activity activity){
        log.info("Received activity for processing: {}", activity.getId());
//        log.info("Generated Recommendation: {}", aiservice.generateRecommendation(activity));
        Recommendation recommendation=aiservice.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
    }
}
