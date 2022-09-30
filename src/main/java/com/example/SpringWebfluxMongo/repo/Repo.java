package com.example.SpringWebfluxMongo.repo;

import com.example.SpringWebfluxMongo.mapper.player.entity.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends MongoRepository<Entity,Integer> {
}
