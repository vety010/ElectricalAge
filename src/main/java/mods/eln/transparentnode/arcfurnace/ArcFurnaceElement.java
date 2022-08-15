package mods.eln.transparentnode.arcfurnace;

import mods.eln.node.transparent.TransparentNode;
import mods.eln.node.transparent.TransparentNodeDescriptor;
import mods.eln.node.transparent.TransparentNodeElement;

public class ArcFurnaceElement extends TransparentNodeElement {
    TransparentNodeDescriptor descriptor;

    public ArcFurnaceElement(TransparentNode transparentNode, TransparentNodeDescriptor descriptor) {
        super(transparentNode, descriptor);
        this.descriptor = descriptor;
    }
    @Override
    public void initialize() {

    }
}
