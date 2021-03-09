package com.github.franckyi.guapi.api.theme;

import com.github.franckyi.guapi.api.EventTarget;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.util.ScreenEventType;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.client.render.Matrices;

public interface Skin<N extends Node> extends EventTarget {
    default boolean preRender(N node, Matrices matrices, int mouseX, int mouseY, float delta) {
        return false;
    }

    void render(N node, Matrices matrices, int mouseX, int mouseY, float delta);

    default void postRender(N node, Matrices matrices, int mouseX, int mouseY, float delta) {
        if (node.tooltipProperty().hasValue() && node.isHovered() && !node.isDisabled()) {
            Minecraft.getClient().getRenderer().drawTooltip(matrices, node.getTooltip(), mouseX, mouseY);
        }
    }

    int computeWidth(N node);

    int computeHeight(N node);

    <E extends ScreenEvent> void onEvent(ScreenEventType<E> type, E event);
}
