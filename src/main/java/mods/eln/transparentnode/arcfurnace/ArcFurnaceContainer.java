package mods.eln.transparentnode.arcfurnace;

import mods.eln.misc.BasicContainer;
import mods.eln.node.INodeContainer;
import mods.eln.node.NodeBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ArcFurnaceContainer extends BasicContainer implements INodeContainer {
    NodeBase node = null;
    public ArcFurnaceContainer(NodeBase node, EntityPlayer ply, IInventory inventory) {
        super( ply, inventory, new Slot[]{

        });
        this.node = node;
    }

    @Override
    public NodeBase getNode() {
        return node;
    }

    @Override
    public int getRefreshRateDivider() {
        return 4; // why 4? we'll never know.
    }
}
