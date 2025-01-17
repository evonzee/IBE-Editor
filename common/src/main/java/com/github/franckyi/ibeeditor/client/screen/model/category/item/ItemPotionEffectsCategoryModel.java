package com.github.franckyi.ibeeditor.client.screen.model.category.item;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.ibeeditor.client.screen.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.item.PotionEffectEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.item.PotionSelectionEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.MutableComponent;

public class ItemPotionEffectsCategoryModel extends ItemEditorCategoryModel {
    private ListTag potionEffectList;

    public ItemPotionEffectsCategoryModel(ItemEditorModel editor) {
        super(ModTexts.POTION_EFFECTS, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new PotionSelectionEntryModel(this, ModTexts.DEFAULT_POTION,
                getTag().getString("Potion"), getCustomPotionColor(),
                p -> getTag().putString("Potion", p), this::setCustomPotionColor));
        getTag().getList("CustomPotionEffects", Tag.TAG_COMPOUND).stream()
                .map(CompoundTag.class::cast)
                .map(this::createPotionEffectEntry)
                .forEach(getEntries()::add);
    }

    @Override
    public int getEntryListStart() {
        return 1;
    }

    @Override
    public EntryModel createNewListEntry() {
        return createPotionEffectEntry(null);
    }

    @Override
    public int getEntryHeight() {
        return 50;
    }

    @Override
    protected MutableComponent getAddListEntryButtonTooltip() {
        return ModTexts.EFFECT;
    }

    private int getCustomPotionColor() {
        return getTag().contains("CustomPotionColor", Tag.TAG_INT) ? getTag().getInt("CustomPotionColor") : Color.NONE;
    }

    private void setCustomPotionColor(int color) {
        if (color != Color.NONE) {
            getOrCreateTag().putInt("CustomPotionColor", color);
        } else {
            getOrCreateTag().remove("CustomPotionColor");
        }
    }

    private EntryModel createPotionEffectEntry(CompoundTag tag) {
        if (tag != null) {
            int id = tag.getInt("Id");
            int amplifier = tag.getInt("Amplifier"); // defaults to 0
            int duration = tag.contains("Duration", Tag.TAG_INT) ? tag.getInt("Duration") : 1;
            boolean ambient = tag.getBoolean("Ambient"); // defaults to false
            boolean showParticles = !tag.contains("ShowParticles", Tag.TAG_BYTE) || tag.getBoolean("ShowParticles");
            boolean showIcon = tag.getBoolean("ShowIcon");
            return new PotionEffectEntryModel(this, id, amplifier, duration, ambient, showParticles, showIcon, this::addPotionEffect);
        }
        return new PotionEffectEntryModel(this, 1, 0, 1, false, true, true, this::addPotionEffect);
    }

    @Override
    public void apply() {
        potionEffectList = new ListTag();
        super.apply();
        if (!potionEffectList.isEmpty()) {
            getOrCreateTag().put("CustomPotionEffects", potionEffectList);
        } else if (getOrCreateTag().contains("CustomPotionEffects")) {
            getOrCreateTag().remove("CustomPotionEffects");
        }
    }

    private void addPotionEffect(int id, int amplifier, int duration, boolean ambient, boolean showParticles, boolean showIcon) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("Id", id);
        tag.putInt("Amplifier", amplifier);
        tag.putInt("Duration", duration);
        tag.putBoolean("Ambient", ambient);
        tag.putBoolean("ShowParticles", showParticles);
        tag.putBoolean("ShowIcon", showIcon);
        potionEffectList.add(tag);
    }
}
