package mods.eln.sim.watchdogs;

import mods.eln.Eln;
import mods.eln.gridnode.transformer.GridTransformerElement;
import mods.eln.misc.Coordinate;
import mods.eln.node.INodeElement;
import mods.eln.node.six.SixNodeElement;
import mods.eln.node.transparent.TransparentNodeElement;
import mods.eln.sim.electrical.ElectricalLoad;
import mods.eln.sim.electrical.mna.component.Bipole;
import mods.eln.sim.electrical.mna.component.Component;
import mods.eln.sim.electrical.mna.component.VoltageSource;
import mods.eln.sim.electrical.mna.state.State;
import mods.eln.simplenode.energyconverter.EnergyConverterElnToOtherNode;
import net.minecraft.init.Blocks;

public class WorldFailure implements IDestructible {

    Object origin;

    Coordinate c;
    float strength;
    String type;

    INodeElement node;

    VoltageSource boomboom = new VoltageSource("Boomboom source");








    public WorldFailure(SixNodeElement e) {
        this.c = e.getCoordinate();
        this.type = e.toString();
        origin = e;
        node = e;


    }

    public WorldFailure(TransparentNodeElement e) {
        this.c = e.coordinate();
        this.type = e.toString();
        origin = e;
        node = e;
    }

    public WorldFailure cableExplosion() {
        strength = 1.5f;
        return this;
    }

    public WorldFailure machineExplosion() {
        strength = 3;
        return this;
    }

    @Override
    public void destructImpl() {
        if (Eln.explosionEnable)
            c.world().createExplosion(null, c.x, c.y, c.z, strength, true);
        else
            c.world().setBlock(c.x, c.y, c.z, Blocks.air);
    }

    @Override
    public String describe() {
        return String.format("%s (%s)", this.type, this.c.toString());
    }

    @Override
    public void failureImpl(float intensity) {
        //System.out.println(node.toString() + " failure");
    if(node instanceof GridTransformerElement) {
      //  System.out.println("gsfgdsfgfdsgdsfgdsf");
        GridTransformerElement e = (GridTransformerElement) node;
        // ratio is max 0.25


        e.getPrimaryVoltageSource().setU(e.getPrimaryVoltageSource().getU() * (1 + (Math.random() - 0.5) * 0.1));
        e.getSecondaryVoltageSource().setU(e.getSecondaryVoltageSource().getU() * (1 + (Math.random() - 0.5) * 0.1));

        //e.getInterSystemProcess().
    }

        //c.world().spawnParticle("", c.x+(Math.random()-0.5), c.y+(Math.random()), c.z+(Math.random()-0.5), 0, 0, 0);
    }


    @Override
    public void cancelFailure() {
        if(node instanceof GridTransformerElement) {
            GridTransformerElement e = (GridTransformerElement) node;

        }
    }
}
