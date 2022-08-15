package mods.eln.transparentnode.arcfurnace;

import mods.eln.misc.Direction;
import mods.eln.misc.Obj3D;
import mods.eln.node.transparent.TransparentNodeDescriptor;

public class ArcFurnaceDescriptor extends TransparentNodeDescriptor {
    public double nominalPower = 0;
    public double nominalVoltage = 800;
    public double maxVoltage = nominalVoltage * 1.3;

    public ArcFurnaceDescriptor(
        String name, Obj3D obj
    ) {
        super( name, ArcFurnaceElement.class, ArcFurnaceRender.class );
    }

    public void draw(Direction front) {

    }
}
