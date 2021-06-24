package com.github.franckyi.minecraft.api.common.tag;

public interface FloatTag extends Tag {
    @Override
    default byte getType() {
        return Tag.FLOAT_ID;
    }

    float getValue();

    @Override
    String toString();
}