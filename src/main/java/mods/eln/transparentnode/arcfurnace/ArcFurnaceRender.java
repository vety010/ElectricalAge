package mods.eln.transparentnode.arcfurnace;

import mods.eln.node.transparent.TransparentNodeDescriptor;
import mods.eln.node.transparent.TransparentNodeElementInventory;
import mods.eln.node.transparent.TransparentNodeElementRender;
import mods.eln.node.transparent.TransparentNodeEntity;

public class ArcFurnaceRender extends TransparentNodeElementRender {
    public ArcFurnaceRender(TransparentNodeEntity tileEntity,
                                      TransparentNodeDescriptor descriptor ) {
        super(tileEntity, descriptor);

        // it doesn't work anyway :(
        this.adesc = (ArcFurnaceDescriptor) descriptor;
    }

    TransparentNodeElementInventory inventory;
    ArcFurnaceDescriptor adesc;


    @Override
    public void draw() {
        if(adesc != null) {
            adesc.draw( front );
        }
    }

    public void init() {

    }


}
