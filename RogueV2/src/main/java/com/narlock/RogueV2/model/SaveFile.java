package com.narlock.RogueV2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveFile {
    private Integer border;
    private SaveFileMode mode;
    private Integer storyRepeatCount;
    private Integer currentStage;
    private Integer currentLevel;
    private List<Stage> stages;
    private PlayerStorageType playerStorageType;
    private List<Player> players;
}
