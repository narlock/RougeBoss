package com.narlock.RogueV2.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.narlock.RogueV2.model.PlayerStorageType;
import com.narlock.RogueV2.model.SaveFile;
import com.narlock.RogueV2.model.SaveFileMode;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class SaveFileUtils {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static SaveFile loadSaveFile() throws IOException {
        String filePath = getBossFilePath();
        File file = new File(filePath);

        if (!file.exists()) {
            // TODO
            // Create an option pane to select RPG or PEACEFUL mode and player storage type IN_FILE or NOT_IN_FILE
            // Create a new save file and save to disk
            return null;
        } else {
            return OBJECT_MAPPER.readValue(file, SaveFile.class);
        }
    }

    private static SaveFile createNewSaveFile(SaveFileMode saveFileMode, PlayerStorageType playerStorageType) {
        return SaveFile.builder()
                .border(0)
                .mode(saveFileMode)
                .storyRepeatCount(0)
                .currentStage(0)
                .currentLevel(0)
                .stages(StageUtils.generateRandomStages(0))
                .playerStorageType(playerStorageType)
                .players(Collections.emptyList())
                .build();
    }

    private static String getBossFilePath() {
        String userHome = System.getProperty("user.home");
        return userHome
                + File.separator
                + "Documents"
                + File.separator
                + "narlock"
                + File.separator
                + "RogueBoss"
                + File.separator
                + "saveFile.json";
    }
}
