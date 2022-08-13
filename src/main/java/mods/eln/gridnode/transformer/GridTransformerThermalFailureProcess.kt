package mods.eln.gridnode.transformer

import mods.eln.sim.IProcess
import mods.eln.sim.electrical.mna.misc.IRootSystemPreStepProcess

class GridTransformerThermalFailureProcess(element: GridTransformerElement) : IRootSystemPreStepProcess, IProcess {

    var gridTransformerElement: GridTransformerElement = element

    var doFailure = false

    var maxHeat = 0.0


    fun doFailure() {
        doFailure = true
    }

    fun doFailure(doFailure: Boolean) {
        this.doFailure = doFailure
    }



    override fun rootSystemPreStepProcess() {
        println("its pre time!")
        if(doFailure) {
            gridTransformerElement.primaryVoltageSource.u = gridTransformerElement.primaryVoltageSource.u * Math.random()
           // gridTransformerElement.secondaryVoltageSource.u = gridTransformerElement.secondaryVoltageSource.u * Math.random()
        }
    }

    override fun process(time: Double) {
        println("processes")
        if(gridTransformerElement.thermalLoad.t > maxHeat) {
            doFailure()
        } else {
            doFailure(false)
        }
    }
}
