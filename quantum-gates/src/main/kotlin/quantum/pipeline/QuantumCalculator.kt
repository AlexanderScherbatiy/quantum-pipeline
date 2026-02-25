package quantum.pipeline

data class QuantumPipeline(val state: QuantumState, val gates: List<QuantumGate>)

interface QuantumCalculator {
    fun calculate(pipeline: QuantumPipeline): QuantumState
}
