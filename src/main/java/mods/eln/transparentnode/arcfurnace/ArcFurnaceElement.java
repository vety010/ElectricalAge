package mods.eln.transparentnode.arcfurnace;

import mods.eln.misc.Direction;
import mods.eln.misc.LRDU;
import mods.eln.node.NodeBase;
import mods.eln.node.transparent.TransparentNode;
import mods.eln.node.transparent.TransparentNodeDescriptor;
import mods.eln.node.transparent.TransparentNodeElement;
import mods.eln.node.transparent.TransparentNodeElementInventory;
import mods.eln.sim.electrical.ElectricalLoad;
import mods.eln.sim.electrical.mna.component.Resistor;
import mods.eln.sim.electrical.nbt.NbtElectricalLoad;
import mods.eln.sim.watchdogs.VoltageStateWatchDog;
import mods.eln.sim.watchdogs.WorldExplosion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import javax.annotation.Nullable;

public class ArcFurnaceElement extends TransparentNodeElement {
    TransparentNodeDescriptor descriptor;
    public TransparentNodeElementInventory inventory = new TransparentNodeElementInventory(5, 1, this);

    public ArcFurnaceElement(TransparentNode transparentNode, TransparentNodeDescriptor descriptor) {
        super(transparentNode, descriptor);
        this.descriptor = descriptor;
    }
    @Override
    public void initialize() {

    }

    private NbtElectricalLoad electricalLoad = new NbtElectricalLoad("electricalLoad");
    private Resistor electricalResistor = new Resistor(electricalLoad, null);

    private VoltageStateWatchDog voltageWatchdog = new VoltageStateWatchDog();

    public void init() {
        electricalLoadList.add(electricalLoad);
        electricalComponentList.add(electricalResistor);

        WorldExplosion exp = new WorldExplosion( this ).machineExplosion();
        slowProcessList.add( voltageWatchdog.set(electricalLoad).setUNominal(800).set(exp) );
    }

    @Override
    public String multiMeterString( Direction side ) {
        //return Utils.plotUIP( electricalLoad.u, electricalLoad.current );
        return "hi there";
    }

    @Nullable
    public ElectricalLoad getElectricalLoad(Direction side, LRDU lrdu) {
        return electricalLoad;
    }

    @Override
    public int getConnectionMask(Direction side, LRDU lrdu) {
        if (lrdu != LRDU.Down) return 0;
        return NodeBase.maskElectricalPower;
    }

    public void initalize() {
        connect();
    }

    @Override
    public boolean hasGui() {
        return true;
    }

    @Override
    public Container newContainer(Direction side, EntityPlayer ply) {

        return new ArcFurnaceContainer( node, ply, inventory );
    }

    public void applyTo(Resistor Resistor) {

    }
}
