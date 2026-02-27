package quantum.pipeline

data class QuantumPipeline(val state: QuantumStateExpression, val gates: List<QuantumGate>)

interface QuantumCalculator {
    fun calculate(pipeline: QuantumPipeline): QuantumState
}
