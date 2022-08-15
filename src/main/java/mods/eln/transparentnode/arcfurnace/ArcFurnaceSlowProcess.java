package mods.eln.transparentnode.arcfurnace;

import mods.eln.node.transparent.TransparentNodeDescriptor;

public class ArcFurnaceSlowProcess {
    ArcFurnaceElement elem;
    public ArcFurnaceSlowProcess(ArcFurnaceElement elem) {
        this.elem = elem;
    }

    public void process( double time ) {
        TransparentNodeDescriptor d = elem.descriptor;
    }
}
