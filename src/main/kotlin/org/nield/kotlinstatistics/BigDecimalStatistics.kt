package org.nield.kotlinstatistics

import org.apache.commons.math.stat.StatUtils
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics
import java.math.BigDecimal

fun Sequence<BigDecimal>.sum() = fold(BigDecimal.ZERO) { x,y -> x + y }!!
fun Iterable<BigDecimal>.sum() = fold(BigDecimal.ZERO) { x,y -> x + y }!!

fun Sequence<BigDecimal>.average() = toList().let { list ->
    list.sum() / BigDecimal.valueOf(list.count().toDouble())
}
fun Iterable<BigDecimal>.average() = asSequence().average()


val Iterable<BigDecimal>.descriptiveStatistics get() = DescriptiveStatistics().apply { forEach { addValue(it.toDouble()) } }
val Sequence<BigDecimal>.descriptiveStatistics get() = DescriptiveStatistics().apply { forEach { addValue(it.toDouble()) } }
val Array<out BigDecimal>.descriptiveStatistics get() = DescriptiveStatistics().apply { forEach { addValue(it.toDouble()) } }

fun Iterable<BigDecimal>.geometricMean() = StatUtils.geometricMean(asSequence().map { it.toDouble() }.toList().toDoubleArray())
fun Sequence<BigDecimal>.geometricMean() = StatUtils.geometricMean(asSequence().map { it.toDouble() }.toList().toDoubleArray())
fun Array<out BigDecimal>.geometricMean() = StatUtils.geometricMean(asSequence().map { it.toDouble() }.toList().toDoubleArray() )

fun Iterable<BigDecimal>.median() = percentile(50.0)
fun Sequence<BigDecimal>.median() = percentile(50.0)
fun Array<out BigDecimal>.median() = percentile(50.0)

fun Iterable<BigDecimal>.percentile(percentile: Double) = StatUtils.percentile(asSequence().map { it.toDouble() }.toList() .toDoubleArray(), percentile)
fun Sequence<BigDecimal>.percentile(percentile: Double) = StatUtils.percentile(asSequence().map { it.toDouble() }.toList() .toDoubleArray(), percentile)
fun Array<out BigDecimal>.percentile(percentile: Double) = StatUtils.percentile(asSequence().map { it.toDouble() }.toList().toDoubleArray() , percentile)

fun Iterable<BigDecimal>.variance() = StatUtils.variance(asSequence().map { it.toDouble() }.toList() .toDoubleArray())
fun Sequence<BigDecimal>.variance() = StatUtils.variance(asSequence().map { it.toDouble() }.toList() .toDoubleArray())
fun Array<out BigDecimal>.variance() = StatUtils.variance(asSequence().map { it.toDouble() }.toList().toDoubleArray() )

fun Iterable<BigDecimal>.sumOfSquares() = StatUtils.sumSq(asSequence().map { it.toDouble() }.toList() .toDoubleArray())
fun Sequence<BigDecimal>.sumOfSquares() = StatUtils.sumSq(asSequence().map { it.toDouble() }.toList() .toDoubleArray())
fun Array<out BigDecimal>.sumOfSquares() = StatUtils.sumSq(asSequence().map { it.toDouble() }.toList().toDoubleArray() )

fun Iterable<BigDecimal>.standardDeviation() = descriptiveStatistics.standardDeviation
fun Sequence<BigDecimal>.standardDeviation() = descriptiveStatistics.standardDeviation
fun Array<out BigDecimal>.standardDeviation() = descriptiveStatistics.standardDeviation

fun Iterable<BigDecimal>.normalize() = StatUtils.normalize(asSequence().map { it.toDouble() }.toList() .toDoubleArray())
fun Sequence<BigDecimal>.normalize() = StatUtils.normalize(asSequence().map { it.toDouble() }.toList() .toDoubleArray())
fun Array<out BigDecimal>.normalize() = StatUtils.normalize(asSequence().map { it.toDouble() }.toList().toDoubleArray() )

val Iterable<BigDecimal>.kurtosis get() = descriptiveStatistics.kurtosis
val Sequence<BigDecimal>.kurtosis get() = descriptiveStatistics.kurtosis
val Array<out BigDecimal>.kurtosis get() = descriptiveStatistics.kurtosis

val Iterable<BigDecimal>.skewness get() = descriptiveStatistics.skewness
val Sequence<BigDecimal>.skewness get() = descriptiveStatistics.skewness
val Array<out BigDecimal>.skewness get() = descriptiveStatistics.skewness


// AGGREGATION OPERATORS

inline fun <T,K> Sequence<T>.sumBy(crossinline keySelector: (T) -> K, crossinline bigDecimalMapper: (T) -> BigDecimal) =
        groupApply(keySelector, bigDecimalMapper) { it.sum() }

inline fun <T,K> Iterable<T>.sumBy(crossinline keySelector: (T) -> K, crossinline bigDecimalMapper: (T) -> BigDecimal) =
        asSequence().sumBy(keySelector, bigDecimalMapper)

inline fun <T,K> Sequence<T>.averageBy(crossinline keySelector: (T) -> K, crossinline bigDecimalMapper: (T) -> BigDecimal) =
        groupApply(keySelector, bigDecimalMapper) { it.average() }

inline fun <T,K> Iterable<T>.averageBy(crossinline keySelector: (T) -> K, crossinline bigDecimalMapper: (T) -> BigDecimal) =
        asSequence().averageBy(keySelector, bigDecimalMapper)

inline fun <T,K> Sequence<T>.minBy(crossinline keySelector: (T) -> K, crossinline bigDecimalMapper: (T) -> BigDecimal) =
        groupApply(keySelector, bigDecimalMapper) { it.min() }

inline fun <T,K> Iterable<T>.minBy(crossinline keySelector: (T) -> K, crossinline bigDecimalMapper: (T) -> BigDecimal) =
        asSequence().minBy(keySelector, bigDecimalMapper)

inline fun <T,K> Sequence<T>.maxBy(crossinline keySelector: (T) -> K, crossinline bigDecimalMapper: (T) -> BigDecimal) =
        groupApply(keySelector, bigDecimalMapper) { it.max() }

inline fun <T,K> Iterable<T>.maxBy(crossinline keySelector: (T) -> K, crossinline bigDecimalMapper: (T) -> BigDecimal) =
        asSequence().maxBy(keySelector, bigDecimalMapper)

inline fun <T,K> Sequence<T>.medianBy(crossinline keySelector: (T) -> K, crossinline bigDecimalMapper: (T) -> BigDecimal) =
        groupApply(keySelector, bigDecimalMapper) { it.median() }

inline fun <T,K> Iterable<T>.medianBy(crossinline keySelector: (T) -> K, crossinline bigDecimalMapper: (T) -> BigDecimal) =
        asSequence().medianBy(keySelector, bigDecimalMapper)

inline fun <T,K> Sequence<T>.varianceBy(crossinline keySelector: (T) -> K, crossinline bigDecimalMapper: (T) -> BigDecimal) =
        groupApply(keySelector, bigDecimalMapper) { it.variance() }

inline fun <T,K> Iterable<T>.varianceBy(crossinline keySelector: (T) -> K, crossinline bigDecimalMapper: (T) -> BigDecimal) =
        asSequence().varianceBy(keySelector, bigDecimalMapper)

inline fun <T,K> Sequence<T>.standardDeviationBy(crossinline keySelector: (T) -> K, crossinline bigDecimalMapper: (T) -> BigDecimal) =
        groupApply(keySelector, bigDecimalMapper) { it.standardDeviation() }

inline fun <T,K> Iterable<T>.standardDeviationBy(crossinline keySelector: (T) -> K, crossinline bigDecimalMapper: (T) -> BigDecimal) =
        asSequence().standardDeviationBy(keySelector, bigDecimalMapper)