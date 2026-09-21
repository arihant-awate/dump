package com.arihant.rewind.controller;

import com.arihant.rewind.dto.ReplayFrameResponse;
import com.arihant.rewind.service.ReplayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReplayController {

    private final ReplayService replayService;

    @GetMapping(value = "/replay-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<ReplayFrameResponse>> streamReplay() {
        log.info("SSE replay stream started");

        return replayService.generateReplay()
                .map(frame -> ServerSentEvent.<ReplayFrameResponse>builder()
                        .data(frame)
                        .build())
                .concatWith(Flux.just(
                        ServerSentEvent.<ReplayFrameResponse>builder()
                                .event("end")
                                .comment("Stream complete")
                                .build()
                ))
                .doOnComplete(() -> log.info("SSE stream completed"))
                .doOnError(e -> log.error("SSE stream error", e));
    }

    @PostMapping("/reset")
    public Mono<Void> reset() {
        log.info("Reset called");
        return replayService.clearAllData();
    }
}
