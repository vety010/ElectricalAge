package mods.eln.transparentnode.arcfurnace;

import mods.eln.gui.GuiContainerEln;
import mods.eln.gui.GuiHelperContainer;
import mods.eln.node.transparent.TransparentNodeElementInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class ArcFurnaceGuiDraw extends GuiContainerEln {
    private TransparentNodeElementInventory inventory;
    ArcFurnaceRender render;

    public ArcFurnaceGuiDraw(EntityPlayer player, IInventory inventory, ArcFurnaceRender render) {
        super( new ArcFurnaceContainer( null, player, inventory ) );
        this.inventory = (TransparentNodeElementInventory) inventory;
        this.render = render;
    }

    public void initGui() {
        super.initGui();
    }

    @Override
    protected GuiHelperContainer newHelper() {
        return new GuiHelperContainer(this, 176, 166, 8, 84);
    }
}
