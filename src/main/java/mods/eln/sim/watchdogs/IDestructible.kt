package mods.eln.sim.watchdogs

interface IDestructible {
    fun destructImpl()

    fun failureImpl(intensity: Float)

    fun cancelFailure()
    fun describe(): String?
}
