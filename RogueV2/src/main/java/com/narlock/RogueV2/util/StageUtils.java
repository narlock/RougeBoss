package com.narlock.RogueV2.util;

import com.narlock.RogueV2.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class StageUtils {

    /**
     * Generates a random list of stages. This is used for generating a new save file or for generating a new list of
     * stages after a story has been completed (a story is completed after all the stages previously have been
     * cleared)
     * @param storyRepeatCount the amount of times the story has been repeated. This is treated as a multiplier for
     *                         determining the level and health of the enemies in the list of stages.
     * @return The list of stages for the save file.
     */
    public static List<Stage> generateRandomStages(int storyRepeatCount) {
        List<Stage> stages = new ArrayList<>();
        List<RBType> rbTypes = new ArrayList<>();
        Collections.addAll(rbTypes, RBType.values());

        // Shuffle to randomize order
        Collections.shuffle(rbTypes, new Random());

        // Ensure there are exactly 6 stages
        for (int i = 0; i < 6; i++) {
            RBType stageType = rbTypes.get(i);
            List<Level> levels = new ArrayList<>();

            // Generate 5 levels for each stage
            for (int levelIndex = 1; levelIndex <= 5; levelIndex++) {
                LevelType levelType = (levelIndex == 5) ? LevelType.BOSS : LevelType.TRIAL;
                List<Enemy> enemies = new ArrayList<>();

                if (levelIndex == 1) {
                    // One enemy of the stage's type
                    enemies.add(generateEnemy(stageType, storyRepeatCount, i, levelIndex));
                } else if (levelIndex == 2) {
                    // Two enemies of the stage's type
                    enemies.add(generateEnemy(stageType, storyRepeatCount, i, levelIndex));
                    enemies.add(generateEnemy(stageType, storyRepeatCount, i, levelIndex));
                } else if (levelIndex == 3) {
                    // Two enemies of the stage's type and one random type
                    enemies.add(generateEnemy(stageType, storyRepeatCount, i, levelIndex));
                    enemies.add(generateEnemy(stageType, storyRepeatCount, i, levelIndex));
                    enemies.add(generateEnemy(RBType.random(), storyRepeatCount, i, levelIndex));
                } else if (levelIndex == 4) {
                    // Two enemies of the stage's type and two random types
                    enemies.add(generateEnemy(stageType, storyRepeatCount, i, levelIndex));
                    enemies.add(generateEnemy(stageType, storyRepeatCount, i, levelIndex));
                    enemies.add(generateEnemy(RBType.random(), storyRepeatCount, i, levelIndex));
                    enemies.add(generateEnemy(RBType.random(), storyRepeatCount, i, levelIndex));
                } else if (levelIndex == 5) {
                    // BOSS level, one boss enemy
                    enemies.add(generateBossEnemy(stageType, storyRepeatCount, i, levelIndex));
                }

                // Add level to the stage
                levels.add(Level.builder()
                        .type(levelType)
                        .enemies(enemies)
                        .build());
            }

            // Add stage to the list
            stages.add(Stage.builder()
                    .themeType(stageType)
                    .levels(levels)
                    .build());
        }

        return stages;
    }

    /**
     * Generates a regular enemy with the given parameters.
     */
    private static Enemy generateEnemy(RBType type, int storyRepeatCount, int stageIndex, int levelIndex) {
        int level = calculateEnemyLevel(storyRepeatCount, stageIndex, levelIndex);
        return Enemy.builder()
                .enemyType(EnemyType.random())
                .enemyLevel(level)
                .type(type)
                .health(level * 10) // Health is proportional to level
                .damageList(new ArrayList<>()) // Add additional damage details if needed
                .build();
    }

    /**
     * Generates a boss enemy with the given parameters.
     */
    private static Enemy generateBossEnemy(RBType type, int storyRepeatCount, int stageIndex, int levelIndex) {
        int level = calculateEnemyLevel(storyRepeatCount, stageIndex, levelIndex) + 5; // Boss level is higher
        return Enemy.builder()
                .enemyType(EnemyType.random()) // Boss is always DRAGON for this example
                .enemyLevel(level)
                .type(type)
                .health(level * 20) // Boss has more health
                .damageList(new ArrayList<>()) // Add additional damage details if needed
                .build();
    }

    /**
     * Calculates the level of an enemy based on storyRepeatCount, stageIndex, and levelIndex.
     */
    private static int calculateEnemyLevel(int storyRepeatCount, int stageIndex, int levelIndex) {
        return (storyRepeatCount + 1) * (stageIndex + 1) * (levelIndex + 1);
    }
}
