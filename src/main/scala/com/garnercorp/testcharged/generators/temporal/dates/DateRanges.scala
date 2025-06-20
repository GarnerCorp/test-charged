package com.garnercorp.testcharged.generators.temporal.dates

import java.time.Period
import java.time.temporal.TemporalAmount

import com.garnercorp.testcharged.generators.temporal.TemporalRanges

trait DateRanges extends TemporalRanges {
  private val DistantPastYears = 5
  private val PastYears = 2
  private val RecentDays = 30

  private val SoonDays = 30
  private val FutureYears = 2
  private val DistantFutureYears = 5

  private val OffsetDays = 1

  protected override val DistantPast: TemporalAmount =
    Period.ofYears(DistantPastYears)
  protected override val Past: TemporalAmount = Period.ofYears(PastYears)
  protected override val Recent: TemporalAmount = Period.ofDays(RecentDays)

  protected override val Soon: TemporalAmount = Period.ofDays(SoonDays)
  protected override val Future: TemporalAmount = Period.ofYears(FutureYears)
  protected override val DistantFuture: TemporalAmount =
    Period.ofYears(DistantFutureYears)

  protected override val Offset: TemporalAmount = Period.ofDays(OffsetDays)
}
