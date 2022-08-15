package mods.eln.transparentnode.arcfurnace;

import mods.eln.generic.GenericItemUsingDamageSlot;
import mods.eln.gui.ISlotSkin;
import mods.eln.item.GraphiteDescriptor;
import mods.eln.item.MachineBoosterDescriptor;
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
            new GenericItemUsingDamageSlot(inventory, 0, 8, 8, 1,
                GraphiteDescriptor.class, ISlotSkin.SlotSkin.medium, new String[]{"Graphite Slot"}),

            new GenericItemUsingDamageSlot(inventory, 1, 16 * 2 + 2 * 2 + 8, 8, 1,
                GraphiteDescriptor.class, ISlotSkin.SlotSkin.medium, new String[]{"Graphite Slot"}),

            new GenericItemUsingDamageSlot(inventory, 2, 16 + 2 + 8, 16 + 2 + 8, 1,
                GraphiteDescriptor.class, ISlotSkin.SlotSkin.medium, new String[]{"Graphite Slot"}),

                // TODO: Put here actual materials
            new GenericItemUsingDamageSlot(inventory, 3, 16 + 2 + 8, 16 * 2 + 2 * 2 + 8, 64,
                GraphiteDescriptor.class, ISlotSkin.SlotSkin.medium, new String[]{"Output Slot"}),

            new GenericItemUsingDamageSlot(inventory, 4, 16 + 2 + 8, 16 * 3 + 2 * 3 + 8, 64,
                GraphiteDescriptor.class, ISlotSkin.SlotSkin.medium, new String[]{"Input Slot"}),

            new GenericItemUsingDamageSlot(inventory, 5, 128, 10, 5,
                MachineBoosterDescriptor.class, ISlotSkin.SlotSkin.medium, new String[]{"Booster upgrade Slot"})
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
