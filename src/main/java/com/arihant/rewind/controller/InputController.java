package com.arihant.rewind.controller;

import com.arihant.rewind.dto.InputRequest;
import com.arihant.rewind.service.InputService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/input")
public class InputController {

    private final InputService inputService;

    @PostMapping
    public Mono<Void> handleInput(@RequestBody InputRequest request) {
        return inputService.recordEvent(
                request.getKey(),
                request.getAction(),
                request.getTimestamp(),
                request.getX(),
                request.getY()
        ).then();
    }
}
