package mods.eln.transparentnode.arcfurnace;

import mods.eln.gui.GuiContainerEln;
import mods.eln.gui.GuiHelperContainer;
import mods.eln.node.transparent.TransparentNodeElementInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import scala.Console;

public class ArcFurnaceGuiDraw extends GuiContainerEln {
    private TransparentNodeElementInventory inventory;
    ArcFurnaceRender render;

    public ArcFurnaceGuiDraw(EntityPlayer player, IInventory inventory, ArcFurnaceRender render) {
        super( new ArcFurnaceContainer( null, player, inventory ) );
        this.inventory = (TransparentNodeElementInventory) inventory;
        this.render = render;
        Console.println("thing works 1");
    }

    public void initGui() {
        super.initGui();
        Console.println("thing works 3");
    }

    @Override
    protected GuiHelperContainer newHelper() {
        Console.println("thing works 2");
        return new GuiHelperContainer(this, 176, 166, 8, 84);
    }
}
