package mods.eln.sixnode.electricalsource;

import mods.eln.Eln;
import mods.eln.cable.CableRenderDescriptor;
import mods.eln.misc.*;
import mods.eln.node.six.SixNodeDescriptor;
import mods.eln.node.six.SixNodeElementRender;
import mods.eln.node.six.SixNodeEntity;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.DataInputStream;
import java.io.IOException;

public class ElectricalSourceRender extends SixNodeElementRender {

    ElectricalSourceDescriptor descriptor;

    double voltage = 0, current = 0;
    int color = 0;

    public ElectricalSourceRender(SixNodeEntity tileEntity, Direction side, SixNodeDescriptor descriptor) {
        super(tileEntity, side, descriptor);
        this.descriptor = (ElectricalSourceDescriptor) descriptor;
    }

    @Override
    public void draw() {
        super.draw();

        front.glRotateOnX();

        descriptor.draw(voltage >= 25);
    }

    @Override
    public void publishUnserialize(DataInputStream stream) {
        super.publishUnserialize(stream);
        try {
            voltage = stream.readFloat();
            needRedrawCable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public GuiScreen newGuiDraw(@NotNull Direction side, @NotNull EntityPlayer player) {
        return new ElectricalSourceGui(this);
    }

    @Nullable
    @Override
    public CableRenderDescriptor getCableRender(@NotNull LRDU lrdu) {
        if (descriptor.isSignalSource()) return Eln.instance.signalCableDescriptor.render;
        if (voltage < Eln.instance.lowVoltageCableT2Descriptor.electricalMaximalVoltage)
            return Eln.instance.lowVoltageCableT2Descriptor.render;
        if (voltage < Eln.instance.mediumVoltageCableT2Descriptor.electricalMaximalVoltage)
            return Eln.instance.mediumVoltageCableT2Descriptor.render;
        if (voltage < Eln.instance.highVoltageCableT2Descriptor.electricalMaximalVoltage)
            return Eln.instance.highVoltageCableT2Descriptor.render;
        return Eln.instance.veryHighVoltageCableT2Descriptor.render;
    }
}
