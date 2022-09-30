package com.example.SpringWebfluxMongo.service;

import com.example.SpringWebfluxMongo.mapper.player.EntityMapper;
import com.example.SpringWebfluxMongo.mapper.player.dto.Dto;
import com.example.SpringWebfluxMongo.mapper.stats.StatsMapper;
import com.example.SpringWebfluxMongo.mapper.stats.dto.StatsDto;
import com.example.SpringWebfluxMongo.repo.Repo;
import com.example.SpringWebfluxMongo.repo.RepoStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class ChessService {
    @Value("${application.service.chess-com-url}")
    private String urlForChess;

    @Value("${application.service.chess-com-url}")
    private String urlForIpsum;

    @Autowired
    Repo repo;
    @Autowired
    RepoStats repoStats;


    private WebClient builderUrlSet(String urlForChess){
        return WebClient.builder().baseUrl(urlForChess).build();
    }
    private Mono<Dto> apiConnectDetails(String userId){
        WebClient webClient = builderUrlSet(urlForChess);

        var resMono = webClient.get().uri("/" + userId).retrieve().bodyToMono(Dto.class).log();
        return resMono;
    }
    private Mono<StatsDto> apiConnectStats(String userId){
        WebClient webClient = builderUrlSet(urlForChess);

        var resMono = webClient.get().uri("/" + userId+ "/stats").retrieve().bodyToMono(StatsDto.class).log();
        return resMono;
    }
    private void showStats(String userId, Integer playerId) throws ExecutionException, InterruptedException {
        var responseStats = apiConnectStats(userId);

        responseStats.doOnSuccess(res -> {

            var resEntity = StatsMapper.INSTANCE.DtoToEntity(res);
            resEntity.setPlayerId(playerId);
            repoStats.save(resEntity);
            log.info("Player Details Added success - "+resEntity.getPlayerId());

        }).subscribe();
        var result = StatsMapper.INSTANCE.DtoToEntity(responseStats.block());
        log.info("blocked...."+result);
    }
    public void uploadDataToDb(String userId) {
        var responseDetails = apiConnectDetails(userId);
        responseDetails.doOnSuccess(res -> {

            var resEntity = EntityMapper.INSTANCE.DtoToEntity(res);
            repo.save(resEntity);

            try {
                showStats(userId,resEntity.getPlayerId());
            } catch (Exception e) {
                log.error("Cannot connect to DB");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }).subscribe();
    }


    public void showIpsum() {
        var responseStats = apiConnectIpsum(urlForIpsum);

        responseStats.doOnSuccess(res -> {

            log.info("Ipsum - "+res);

        }).subscribe();
    }

    private Mono<String> apiConnectIpsum(String urlForIpsum) {
        WebClient webClient = builderUrlSet(urlForChess);

        var resMono = webClient.get().uri("?type=meat-and-filler").
                retrieve().bodyToMono(String.class).log();
        return resMono;
    }

}
