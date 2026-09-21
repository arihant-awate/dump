package com.arihant.rewind.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplayFrame {
    private InputKey inputKey;
    private InputAction inputAction;
    private long timestamp;
    private double x;
    private double y;
}