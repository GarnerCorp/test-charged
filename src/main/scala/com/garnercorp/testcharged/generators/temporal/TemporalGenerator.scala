package com.garnercorp.testcharged.generators.temporal

import java.time.Period
import java.time.temporal.{Temporal, TemporalAmount}
import org.scalacheck.Gen

trait TemporalGenerator[A <: Temporal] extends TemporalRanges {

  implicit private class TemporalOps(temporal: A) {
    def typedPlus(amount: TemporalAmount): A =
      temporal.plus(amount).asInstanceOf[A]
    def typedPlusWithOffset(amount: TemporalAmount = Period.ofDays(0)): A =
      temporal.plus(amount).plus(Offset).asInstanceOf[A]
    def typedMinus(amount: TemporalAmount): A =
      temporal.minus(amount).asInstanceOf[A]
    def typedMinusWithOffset(amount: TemporalAmount = Period.ofDays(0)): A =
      temporal.minus(amount).minus(Offset).asInstanceOf[A]
  }

  def distantPast(implicit now: NowProvider): Gen[A] =
    apply(
      min = _.typedMinus(DistantPast),
      max = _.typedMinusWithOffset(Past)
    )
  def past(implicit now: NowProvider): Gen[A] =
    apply(
      min = _.typedMinus(Past),
      max = _.typedMinusWithOffset(Recent)
    )
  def recent(implicit now: NowProvider): Gen[A] =
    apply(
      min = _.typedMinus(Recent),
      max = _.typedMinusWithOffset()
    )

  def default(implicit now: NowProvider): Gen[A] =
    apply(min = _.typedMinus(Recent), max = _.typedPlus(Soon))

  def soon(implicit now: NowProvider): Gen[A] = apply(
    min = _.typedPlusWithOffset(),
    max = _.typedPlus(Soon)
  )
  def future(implicit now: NowProvider): Gen[A] =
    apply(
      min = _.typedPlusWithOffset(Soon),
      max = _.typedPlus(Future)
    )
  def distantFuture(implicit now: NowProvider): Gen[A] =
    apply(
      min = _.typedPlusWithOffset(Future),
      max = _.typedPlus(DistantFuture)
    )

  def apply(min: A => A = identity[A], max: A => A = identity[A])(implicit
      now: NowProvider
  ): Gen[A]
}
