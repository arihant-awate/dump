package com.arihant.rewind.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "input_events")
public class InputEvent {

    @Id
    private String id;

    private InputKey inputKey;
    private InputAction inputAction;
    private long timestamp;
    private double x;
    private double y;
}
