package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.minecraft.api.common.BlockPos;
import com.github.franckyi.minecraft.api.common.network.Buffer;
import com.github.franckyi.minecraft.api.common.world.Item;

public class UpdateBlockInventoryItemPacket extends UpdatePlayerInventoryItemPacket {
    private final BlockPos blockPos;

    public UpdateBlockInventoryItemPacket(Item item, int slotId, BlockPos blockPos) {
        super(item, slotId);
        this.blockPos = blockPos;
    }

    public UpdateBlockInventoryItemPacket(Buffer buffer) {
        super(buffer);
        blockPos = buffer.readPos();
    }

    @Override
    public void write(Buffer buffer) {
        super.write(buffer);
        buffer.writePos(blockPos);
    }

    public BlockPos getPos() {
        return blockPos;
    }
}
