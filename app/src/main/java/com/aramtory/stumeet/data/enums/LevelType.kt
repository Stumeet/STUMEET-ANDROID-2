package com.aramtory.stumeet.data.enums

import com.aramtory.stumeet.R

enum class LevelType(private val levelResId: Int) {
    SEEDS(R.string.enum_level_seeds),
    SPROUT(R.string.enum_level_sprout),
    LEAF(R.string.enum_level_leaf),
    FLOWER(R.string.enum_level_flower),
    TREE(R.string.enum_level_tree),
    FRUIT(R.string.enum_level_fruit);

    fun getLevelResId(levelType: LevelType): Int {
        return levelType.levelResId
    }
}
