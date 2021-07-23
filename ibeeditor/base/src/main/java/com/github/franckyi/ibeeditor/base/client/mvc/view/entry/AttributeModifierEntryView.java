package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.EnumButton;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.util.Predicates;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.AttributeModifierEntryModel;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class AttributeModifierEntryView extends EntryView {
    private TextField attributeNameField;
    private Button attributeListButton;
    private EnumButton<AttributeModifierEntryModel.Slot> slotButton;
    private EnumButton<AttributeModifierEntryModel.Operation> operationButton;
    private TextField amountField;

    @Override
    public void build() {
        super.build();
        operationButton.valueProperty().addListener(op -> operationButton.getTooltip().setAll(op.getTooltip()));
    }

    @Override
    protected Node createContent() {
        return vBox(content -> {
            content.add(hBox(top -> {
                top.add(attributeNameField = textField().prefHeight(16)
                        .tooltip(translated("ibeeditor.gui.attribute_name")), 1);
                top.add(attributeListButton = button(text("...")).prefWidth(20).tooltip(translated("ibeeditor.gui.choose_attribute")));
                top.align(CENTER).spacing(2);
            }));
            content.add(hBox(bottom -> {
                bottom.add(slotButton = enumButton(AttributeModifierEntryModel.Slot.class)
                        .textFactory(AttributeModifierEntryModel.Slot::getText)
                        .tooltip(translated("ibeeditor.gui.slot")));
                bottom.add(operationButton = enumButton(AttributeModifierEntryModel.Operation.class)
                        .textFactory(AttributeModifierEntryModel.Operation::getText));
                bottom.add(amountField = textField().validator(Predicates.IS_DOUBLE)
                        .tooltip(translated("ibeeditor.gui.amount")).prefHeight(16), 1);
                bottom.align(CENTER).spacing(2);
            }));
            content.spacing(2).align(CENTER).fillWidth();
        });
    }

    public TextField getAttributeNameField() {
        return attributeNameField;
    }

    public Button getAttributeListButton() {
        return attributeListButton;
    }

    public EnumButton<AttributeModifierEntryModel.Slot> getSlotButton() {
        return slotButton;
    }

    public EnumButton<AttributeModifierEntryModel.Operation> getOperationButton() {
        return operationButton;
    }

    public TextField getAmountField() {
        return amountField;
    }
}