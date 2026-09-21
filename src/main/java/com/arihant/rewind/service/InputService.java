package com.arihant.rewind.service;

import com.arihant.rewind.model.InputAction;
import com.arihant.rewind.model.InputEvent;
import com.arihant.rewind.model.InputKey;
import reactor.core.publisher.Mono;

public interface InputService {

    Mono<InputEvent> recordEvent(
            InputKey key,
            InputAction action,
            long timestamp,
            double x,
            double y
    );
}
