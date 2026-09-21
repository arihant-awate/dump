package com.arihant.rewind.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplayFrameResponse {
    private String key;
    private String action;
    private long timestamp;
    private double x;
    private double y;
}
