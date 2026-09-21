package com.arihant.rewind.dto;

import com.arihant.rewind.model.InputAction;
import com.arihant.rewind.model.InputKey;
import lombok.Data;

@Data
public class InputRequest {
    private InputKey key;
    private InputAction action;
    private long timestamp;
    private double x;
    private double y;
}
