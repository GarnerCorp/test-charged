package com.garnercorp.testcharged.generators.temporal.dates

import java.time.{LocalDateTime, Month, ZonedDateTime, Clock}
import com.garnercorp.testcharged.generators.temporal.{
  TemporalTesting,
  NowProvider
}
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks.forAll

class ZonedDateTimeGeneratorsUTest
    extends TemporalTesting
    with DateRanges
    with AnyFunSuiteLike {
  val DateTime: LocalDateTime = LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0)
  val CurrentZone = Clock.systemDefaultZone.getZone
  val CurrentDateTime: ZonedDateTime = DateTime.atZone(CurrentZone)
  implicit val NowProvider: NowProvider = now(CurrentDateTime)

  test(
    "Ensure ZonedDateTime generation helpers generate within the defined presets"
  ) {
    forAll(ZonedDateTimeGenerators.distancePast) {
      withinRange(CurrentDateTime.minus(DistantPast), CurrentDateTime.minus(Past))
    }
    forAll(ZonedDateTimeGenerators.past) {
      withinRange(CurrentDateTime.minus(Past), CurrentDateTime.minus(Recent))
    }
    forAll(ZonedDateTimeGenerators.recent) {
      withinRange(CurrentDateTime.minus(Recent), CurrentDateTime)
    }

    forAll(ZonedDateTimeGenerators.default) {
      withinRange(CurrentDateTime.minus(Recent), CurrentDateTime.plus(Soon))
    }

    forAll(ZonedDateTimeGenerators.soon) {
      withinRange(CurrentDateTime, CurrentDateTime.plus(Soon))
    }
    forAll(ZonedDateTimeGenerators.future) {
      withinRange(CurrentDateTime.plus(Soon), CurrentDateTime.plus(Future))
    }
    forAll(ZonedDateTimeGenerators.distantFuture) {
      withinRange(CurrentDateTime.plus(Future), CurrentDateTime.plus(DistantFuture))
    }
  }
}
