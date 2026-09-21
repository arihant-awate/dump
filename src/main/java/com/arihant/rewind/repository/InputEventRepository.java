package com.arihant.rewind.repository;

import com.arihant.rewind.model.InputEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface InputEventRepository extends ReactiveMongoRepository<InputEvent, String> {

    Flux<InputEvent> findAllByOrderByTimestampAsc();
}
