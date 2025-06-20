package com.garnercorp.testcharged.generators.temporal.times

import java.time.Duration
import java.time.temporal.TemporalAmount

import com.garnercorp.testcharged.generators.temporal.TemporalRanges

trait TimeRanges extends TemporalRanges {
  private val DistantPastHours = 6
  private val PastMinutes = 60
  private val RecentSeconds = 60

  private val NearSeconds = 60
  private val FutureMinutes = 60
  private val DistantFutureHours = 6

  private val OffsetNanoSeconds = 1

  protected override val DistantPast: TemporalAmount =
    Duration.ofHours(DistantPastHours)
  protected override val Past: TemporalAmount = Duration.ofMinutes(PastMinutes)
  protected override val Recent: TemporalAmount =
    Duration.ofSeconds(RecentSeconds)

  protected override val Soon: TemporalAmount = Duration.ofSeconds(NearSeconds)
  protected override val Future: TemporalAmount =
    Duration.ofMinutes(FutureMinutes)
  protected override val DistantFuture: TemporalAmount =
    Duration.ofHours(DistantFutureHours)

  protected override val Offset: TemporalAmount =
    Duration.ofNanos(OffsetNanoSeconds)
}
