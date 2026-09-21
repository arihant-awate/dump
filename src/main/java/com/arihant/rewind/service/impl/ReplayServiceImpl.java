package com.arihant.rewind.service.impl;

import com.arihant.rewind.dto.ReplayFrameResponse;
import com.arihant.rewind.repository.InputEventRepository;
import com.arihant.rewind.service.ReplayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplayServiceImpl implements ReplayService {

    private final InputEventRepository repo;

    @Override
    public Flux<ReplayFrameResponse> generateReplay() {
        return repo.findAllByOrderByTimestampAsc()
                .doOnSubscribe(s -> log.info("Starting replay generation"))
                .map(event -> new ReplayFrameResponse(
                        event.getInputKey().name(),
                        event.getInputAction().name(),
                        event.getTimestamp(),
                        event.getX(),
                        event.getY()
                ))
                .doOnComplete(() -> log.info("Replay generation complete"));
    }

    @Override
    public Mono<Void> clearAllData() {
        return repo.deleteAll()
                .doOnSuccess(v -> log.info("All data cleared"));
    }
}
