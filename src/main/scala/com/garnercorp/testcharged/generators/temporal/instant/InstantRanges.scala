package com.garnercorp.testcharged.generators.temporal.instant

import java.time.{Duration, Period}
import java.time.temporal.TemporalAmount
import com.garnercorp.testcharged.generators.temporal.TemporalRanges

trait InstantRanges extends TemporalRanges {
  private val DistantPastDays = 365
  private val PastDays = 120
  private val RecentDays = 30

  private val SoonDays = 30
  private val FutureDays = 120
  private val DistantFutureDays = 365

  private val OffsetNanoSeconds = 1

  protected override val DistantPast: TemporalAmount =
    Period.ofDays(DistantPastDays)
  protected override val Past: TemporalAmount = Period.ofDays(PastDays)
  protected override val Recent: TemporalAmount = Period.ofDays(RecentDays)

  protected override val Soon: TemporalAmount = Period.ofDays(SoonDays)
  protected override val Future: TemporalAmount = Period.ofDays(FutureDays)
  protected override val DistantFuture: TemporalAmount =
    Period.ofDays(DistantFutureDays)

  protected override val Offset: TemporalAmount =
    Duration.ofNanos(OffsetNanoSeconds)

}
