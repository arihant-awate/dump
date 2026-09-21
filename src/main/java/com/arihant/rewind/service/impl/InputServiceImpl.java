package com.arihant.rewind.service.impl;

import com.arihant.rewind.model.InputAction;
import com.arihant.rewind.model.InputEvent;
import com.arihant.rewind.model.InputKey;
import com.arihant.rewind.repository.InputEventRepository;
import com.arihant.rewind.service.InputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InputServiceImpl implements InputService {

    private final InputEventRepository inputRepo;

    @Override
    public Mono<InputEvent> recordEvent(
            InputKey key,
            InputAction action,
            long timestamp,
            double x,
            double y
    ) {
        InputEvent event = InputEvent.builder()
                .inputKey(key)
                .inputAction(action)
                .timestamp(timestamp)
                .x(x)
                .y(y)
                .build();

        return inputRepo.save(event);
    }
}
