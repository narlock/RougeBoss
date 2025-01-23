package com.narlock.RogueV2.util;

import com.narlock.RogueV2.model.Level;
import com.narlock.RogueV2.model.LevelType;
import com.narlock.RogueV2.model.RBType;
import com.narlock.RogueV2.model.Stage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StageUtilsTests {

    @Test
    void generateRandomStagesTest() {
        // when
        List<Stage> stages = StageUtils.generateRandomStages(0);

        // then
        assertNotNull(stages);
        assertEquals(6, stages.size());

        // level specifics
        for(int i = 0; i < 6; i++) {
            Stage stage = stages.get(i);
            assertNotNull(stage.getThemeType());
            assertEquals(5, stage.getLevels().size());

            RBType themeType = stage.getThemeType();

            Level level1 = stage.getLevels().get(0);
            assertEquals(LevelType.TRIAL, level1.getType());
            assertEquals(1, level1.getEnemies().size());
            assertEquals(themeType, level1.getEnemies().get(0).getType());

            Level level2 = stage.getLevels().get(1);
            assertEquals(LevelType.TRIAL, level2.getType());
            assertEquals(2, level2.getEnemies().size());
            assertEquals(themeType, level2.getEnemies().get(0).getType());
            assertEquals(themeType, level2.getEnemies().get(1).getType());

            Level level3 = stage.getLevels().get(2);
            assertEquals(LevelType.TRIAL, level3.getType());
            assertEquals(3, level3.getEnemies().size());
            assertEquals(themeType, level3.getEnemies().get(0).getType());
            assertEquals(themeType, level3.getEnemies().get(1).getType());

            Level level4 = stage.getLevels().get(3);
            assertEquals(LevelType.TRIAL, level4.getType());
            assertEquals(4, level4.getEnemies().size());
            assertEquals(themeType, level4.getEnemies().get(0).getType());
            assertEquals(themeType, level4.getEnemies().get(1).getType());

            Level level5 = stage.getLevels().get(4);
            assertEquals(LevelType.BOSS, level5.getType());
            assertEquals(1, level5.getEnemies().size());
            assertEquals(themeType, level5.getEnemies().get(0).getType());
        }
    }
}
