package mods.eln.transparentnode.arcfurnace;

import mods.eln.misc.Direction;
import mods.eln.node.transparent.TransparentNodeDescriptor;
import mods.eln.node.transparent.TransparentNodeElementInventory;
import mods.eln.node.transparent.TransparentNodeElementRender;
import mods.eln.node.transparent.TransparentNodeEntity;
import mods.eln.transparentnode.waterturbine.WaterTurbineGuiDraw;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import scala.Console;

import java.io.DataInputStream;
import java.io.IOException;

public class ArcFurnaceRender extends TransparentNodeElementRender {
    public ArcFurnaceRender(TransparentNodeEntity tileEntity,
                                      TransparentNodeDescriptor descriptor ) {
        super(tileEntity, descriptor);

        this.adesc = (ArcFurnaceDescriptor) descriptor;
    }

    TransparentNodeElementInventory inventory = new TransparentNodeElementInventory(0, 64, this);
    ArcFurnaceDescriptor adesc;


    @Override
    public void draw() {
        if(adesc != null) {
            front.glRotateXnRef();
            GL11.glTranslatef(-1, 0, 0);
            GL11.glColor3f(1f, 1f, 1f);
            adesc.draw(front);
        }
    }

    public void init() {
        Console.println("thing works 340");
    }

    public void refresh(float deltaTime) {

    }

    @Nullable
    @Override
    public GuiScreen newGuiDraw(@NotNull Direction side, @NotNull EntityPlayer player) {
        Console.println("thing works 341");
        return new ArcFurnaceGuiDraw(player, inventory, this);
    }

    public boolean hasGui() {
        Console.println("thing works 342");
        return true;
    }

    @Override
    public boolean cameraDrawOptimisation() {
        return true;
    }

    @Override
    public void serverPacketUnserialize(DataInputStream stream) {

        super.serverPacketUnserialize(stream);
        try {
            stream.readByte();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
