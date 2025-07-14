package com.tnahsin.bookMovies.repository;

import com.tnahsin.bookMovies.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User , ObjectId> {
    User findByUserName(String userName);
}
