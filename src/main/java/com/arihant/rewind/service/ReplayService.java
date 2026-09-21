package com.arihant.rewind.service;

import com.arihant.rewind.dto.ReplayFrameResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReplayService {

    Flux<ReplayFrameResponse> generateReplay();

    Mono<Void> clearAllData();
}
