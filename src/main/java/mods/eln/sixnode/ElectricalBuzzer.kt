package mods.eln.sixnode

import mods.eln.Eln
import mods.eln.cable.CableRenderDescriptor
import mods.eln.i18n.I18N
import mods.eln.misc.Direction
import mods.eln.misc.LRDU
import mods.eln.misc.Obj3D
import mods.eln.misc.PhysicalInterpolator
import mods.eln.misc.Utils
import mods.eln.misc.UtilsClient
import mods.eln.misc.VoltageLevelColor
import mods.eln.node.NodeBase
import mods.eln.node.six.SixNode
import mods.eln.node.six.SixNodeDescriptor
import mods.eln.node.six.SixNodeElement
import mods.eln.node.six.SixNodeElementRender
import mods.eln.node.six.SixNodeEntity
import mods.eln.sim.electrical.ElectricalLoad
import mods.eln.sim.IProcess
import mods.eln.sim.thermal.ThermalLoad
import mods.eln.sim.electrical.nbt.NbtElectricalGateInput
import mods.eln.wiki.Data
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.client.IItemRenderer
import org.lwjgl.opengl.GL11
import org.lwjgl.util.Color
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.util.HashMap

/*
TODO: Create buzzer with 2 signal inputs (volume, frequency)
 */

/*
class ElectricalBuzzerDescriptor(name: String, objName: String) : SixNodeDescriptor(name, ElectricalBuzzerElement::class.java, ElectricalBuzzerRender::class.java) {
    var obj: Obj3D?
    var buzzer: Obj3D.Obj3DPart? = null

    @JvmField
    var pinDistance: FloatArray? = null
    override fun setParent(item: Item, damage: Int) {
        Data.addSignal(newItemStack())
    }

    fun draw(factorArg: Float, entity: TileEntity?) {
        var factor = factorArg
        if (factor < 0.0) factor = 0.0f
        if (factor > 1.0) factor = 1.0f
        buzzer!!.draw()
    }

    override fun addInformation(itemStack: ItemStack, entityPlayer: EntityPlayer, list: MutableList<String>, par4: Boolean) {
        super.addInformation(itemStack, entityPlayer, list, par4)
        list.add(I18N.tr("Buzzes. What else did you expect?"))
    }

    override fun shouldUseRenderHelper(type: IItemRenderer.ItemRenderType, item: ItemStack, helper: IItemRenderer.ItemRendererHelper) = type != IItemRenderer.ItemRenderType.INVENTORY

    override fun handleRenderType(item: ItemStack, type: IItemRenderer.ItemRenderType) = true

    override fun shouldUseRenderHelperEln(type: IItemRenderer.ItemRenderType?, item: ItemStack?, helper: IItemRenderer.ItemRendererHelper?) = type != IItemRenderer.ItemRenderType.INVENTORY

    override fun renderItem(type: IItemRenderer.ItemRenderType, item: ItemStack, vararg data: Any) {
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            super.renderItem(type, item, *data)
        } else {
            draw(0.0f, null)
        }
    }

    override fun getFrontFromPlace(side: Direction, player: EntityPlayer): LRDU {
        return super.getFrontFromPlace(side, player)!!.inverse()
    }

    init {
        this.name = name
        obj = Eln.obj.getObj(objName)
        if (obj != null) {
            buzzer = obj!!.getPart("Buzzer")
        }
    }


    override fun canBePlacedOnSide(player: EntityPlayer?, side: Direction) = true
}


class ElectricalBuzzerElement(sixNode: SixNode, side: Direction, descriptor: SixNodeDescriptor) : SixNodeElement(sixNode, side, descriptor) {
    /*
    @JvmField
    var inputGate = NbtElectricalGateInput("inputGate")
    @JvmField
    var descriptor: ElectricalBuzzerDescriptor = descriptor as ElectricalBuzzerDescriptor
    override fun readFromNBT(nbt: NBTTagCompound) {
        super.readFromNBT(nbt)
        val value = nbt.getByte("front")
        front = LRDU.fromInt(value.toInt() shr 0 and 0x3)
    }

    override fun writeToNBT(nbt: NBTTagCompound) {
        super.writeToNBT(nbt)
        nbt.setByte("front", (front.toInt() shl 0).toByte())
    }

    override fun getElectricalLoad(lrdu: LRDU, mask: Int): ElectricalLoad? {
        return if (front == lrdu) inputGate else null
    }

    override fun getThermalLoad(lrdu: LRDU, mask: Int): ThermalLoad? {
        return null
    }

    override fun getConnectionMask(lrdu: LRDU): Int {
        return if (front == lrdu) NodeBase.maskElectricalInputGate else 0
    }

    override fun multiMeterString(): String {
        return Utils.plotVolt("U:", inputGate.u) + Utils.plotAmpere("I:", inputGate.current)
    }

    override fun getWaila(): Map<String, String> {
        val info: MutableMap<String, String> = HashMap()
        if (descriptor.isRGB)
            info[I18N.tr("Input")] = Utils.plotVolt(inputGate.bornedU)
        else
            info[I18N.tr("Input")] = if (inputGate.stateHigh()) I18N.tr("ON") else I18N.tr("OFF")
        return info
    }

    override fun thermoMeterString(): String {
        return ""
    }

    override fun networkSerialize(stream: DataOutputStream) {
        super.networkSerialize(stream)
        try {
            stream.writeByte(front.toInt() shl 4)
            stream.writeFloat((inputGate.u / Eln.SVU).toFloat())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun initialize() {}

    init {
        electricalLoadList.add(inputGate)
        slowProcessList.add(slowProcess)
    }
    */
}


class ElectricalBuzzerRender(tileEntity: SixNodeEntity, side: Direction, descriptor: SixNodeDescriptor) : SixNodeElementRender(tileEntity, side, descriptor) {
    var descriptor: ElectricalBuzzerDescriptor = descriptor as ElectricalBuzzerDescriptor
    var interpolator: PhysicalInterpolator = PhysicalInterpolator(0.4f, 2.0f, 1.5f, 0.2f)
    var factor = 0f
    var boot = true
    override fun draw() {
        super.draw()
        drawSignalPin(front, descriptor.pinDistance)
        if (side.isY) {
            front!!.right().glRotateOnX()
        }
        descriptor.draw(if (descriptor.onOffOnly) interpolator.target else interpolator.get(), tileEntity)
    }

    override fun refresh(deltaT: Float) {
        interpolator.step(deltaT)
    }

    override fun cameraDrawOptimisation(): Boolean {
        return false
    }

    override fun publishUnserialize(stream: DataInputStream) {
        super.publishUnserialize(stream)
        try {
            val b = stream.readByte()
            this.front = LRDU.fromInt(b.toInt() shr 4 and 3)
            if (boot) {
                interpolator.setPos(stream.readFloat())
            } else {
                interpolator.target = stream.readFloat()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (boot) {
            boot = false
        }
    }

    override fun getCableRender(lrdu: LRDU): CableRenderDescriptor {
        return Eln.instance.signalCableDescriptor.render
    }
}


class ElectricalBuzzerSlowProcess(var element: ElectricalBuzzerElement) : IProcess {
    var timeCounter = 0.0
    var lastState: Boolean
    override fun process(time: Double) {
        if (element.descriptor.onOffOnly) {
            if (lastState) {
                if (element.inputGate.stateLow()) {
                    lastState = false
                    element.needPublish()
                }
            } else {
                if (element.inputGate.stateHigh()) {
                    lastState = true
                    element.needPublish()
                }
            }
        } else {
            timeCounter += time
            if (timeCounter >= refreshPeriod) {
                timeCounter -= refreshPeriod
                element.needPublish()
            }
        }
    }

    companion object {
        const val refreshPeriod = 0.25
    }

    init {
        lastState = element.inputGate.stateHigh()
    }
}
*/
