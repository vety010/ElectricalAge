package mods.eln.simplenode.computerprobe;

import cpw.mods.fml.common.Optional;
import li.cil.oc.api.driver.DeviceInfo;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;
import mods.eln.Other;
import mods.eln.node.simple.SimpleNodeEntity;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Optional.Interface(iface = "li.cil.oc.api.network.SimpleComponent", modid = Other.modIdOc)
public class ComputerProbeEntity extends SimpleNodeEntity implements SimpleComponent, DeviceInfo {

    public ComputerProbeEntity() {
        super("ElnProbe");
        deviceInfo.put(DeviceAttribute.Class, DeviceClass.Communication);
        deviceInfo.put(DeviceAttribute.Description, "Signal Probe");
        deviceInfo.put(DeviceAttribute.Vendor, "Jrddunbr & The Electrical Age Corporation");
        deviceInfo.put(DeviceAttribute.Product, "ELN Signal probe");
        deviceInfo.put(DeviceAttribute.Serial, getNodeUuid().split("-")[0]);
    }

    private HashMap<String, String> deviceInfo = new HashMap<>();

    @Override
    public Map<String, String> getDeviceInfo() {
        return deviceInfo;
    }

    @Override
    public String getComponentName() {
        return "ElnProbe";
    }

    @Callback
    @Optional.Method(modid = Other.modIdOc)
    public Object[] signalSetDir(Context context, Arguments args) {
        ComputerProbeNode n = getNode();
        if (n == null) return null;
        return n.signalSetDir(context, args);
    }

    @Callback
    @Optional.Method(modid = Other.modIdOc)
    public Object[] signalGetDir(Context context, Arguments args) {
        ComputerProbeNode n = getNode();
        if (n == null) return null;
        return n.signalGetDir(context, args);
    }

    @Callback
    @Optional.Method(modid = Other.modIdOc)
    public Object[] signalSetOut(Context context, Arguments args) {
        ComputerProbeNode n = getNode();
        if (n == null) return null;
        return n.signalSetOut(context, args);
    }

    @Callback
    @Optional.Method(modid = Other.modIdOc)
    public Object[] signalGetOut(Context context, Arguments args) {
        ComputerProbeNode n = getNode();
        if (n == null) return null;
        return n.signalGetOut(context, args);
    }

    @Callback
    @Optional.Method(modid = Other.modIdOc)
    public Object[] signalGetIn(Context context, Arguments args) {
        ComputerProbeNode n = getNode();
        if (n == null) return null;
        return n.signalGetIn(context, args);
    }

    @Callback
    @Optional.Method(modid = Other.modIdOc)
    public Object[] wirelessSet(Context context, Arguments args) {
        ComputerProbeNode n = getNode();
        if (n == null) return null;
        return n.wirelessSet(context, args);
    }

    @Callback
    @Optional.Method(modid = Other.modIdOc)
    public Object[] wirelessRemove(Context context, Arguments args) {
        ComputerProbeNode n = getNode();
        if (n == null) return null;
        return n.wirelessRemove(context, args);
    }

    @Callback
    @Optional.Method(modid = Other.modIdOc)
    public Object[] wirelessRemoveAll(Context context, Arguments args) {
        ComputerProbeNode n = getNode();
        if (n == null) return null;
        return n.wirelessRemoveAll(context, args);
    }

    @Callback
    @Optional.Method(modid = Other.modIdOc)
    public Object[] wirelessGet(Context context, Arguments args) {
        ComputerProbeNode n = getNode();
        if (n == null) return null;
        return n.wirelessGet(context, args);
    }

    @Callback
    @Optional.Method(modid = Other.modIdOc)
    public Object[] signalList(Context context, Arguments args) {
        ComputerProbeNode n = getNode();
        if (n == null) return null;
        return n.signalList();
    }



    public ComputerProbeNode getNode() {
        return (ComputerProbeNode) super.getNode();
    }

    //return new String[]{"writeDir", "readDir", "writeOut", "readOut", "readIn"};

    @NotNull
    @Override
    public String getNodeUuid() {
        return ComputerProbeNode.getNodeUuidStatic();
    }
}
