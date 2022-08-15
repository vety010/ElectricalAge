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
            adesc.draw( front );
        }
    }

    public void init() {

    }

    @Nullable
    @Override
    public GuiScreen newGuiDraw(@NotNull Direction side, @NotNull EntityPlayer player) {

        return new ArcFurnaceGuiDraw(player, inventory, this);
    }
}
