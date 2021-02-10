package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.event.ScreenEventListener;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.builder.Builder;
import com.github.franckyi.guapi.util.Insets;
import com.github.franckyi.guapi.util.ScreenEventType;

public interface GenericNodeBuilder<N extends Node> extends Node, Builder<N> {
    default N minWidth(int value) {
        return with(n -> n.setMinWidth(value));
    }

    default N minHeight(int value) {
        return with(n -> n.setMinHeight(value));
    }

    default N prefWidth(int value) {
        return with(n -> n.setPrefWidth(value));
    }

    default N prefHeight(int value) {
        return with(n -> n.setPrefHeight(value));
    }

    default N maxWidth(int value) {
        return with(n -> n.setMaxWidth(value));
    }

    default N maxHeight(int value) {
        return with(n -> n.setMaxHeight(value));
    }

    default N backgroundColor(int value) {
        return with(n -> n.setBackgroundColor(value));
    }

    default N padding(Insets value) {
        return with(n -> n.setPadding(value));
    }

    default N padding(int topRightBottomLeft) {
        return padding(new Insets(topRightBottomLeft));
    }

    default N padding(int topBottom, int rightLeft) {
        return padding(new Insets(topBottom, rightLeft));
    }

    default N padding(int top, int rightLeft, int bottom) {
        return padding(new Insets(top, rightLeft, bottom));
    }

    default N padding(int top, int right, int bottom, int left) {
        return padding(new Insets(top, right, bottom, left));
    }

    default N disable(boolean value) {
        return with(n -> n.setDisable(value));
    }

    default N renderPriority(int value) {
        return with(n -> n.setRenderPriority(value));
    }

    default <E extends ScreenEvent> N listener(ScreenEventType<E> type, ScreenEventListener<E> listener) {
        return with(n -> n.addListener(type, listener));
    }

    default N onClick(ScreenEventListener<MouseButtonEvent> listener) {
        return listener(ScreenEventType.MOUSE_CLICKED, listener);
    }
}