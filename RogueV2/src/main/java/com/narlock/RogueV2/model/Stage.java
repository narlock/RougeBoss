package com.narlock.RogueV2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stage {
    private RBType themeType;
    private List<Level> levels;
}
