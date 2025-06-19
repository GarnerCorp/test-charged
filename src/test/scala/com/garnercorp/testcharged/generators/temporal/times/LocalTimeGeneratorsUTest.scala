package com.garnercorp.testcharged.generators.temporal.times

import java.time.{LocalDateTime, LocalTime, LocalDate}
import com.garnercorp.testcharged.generators.temporal.{
  TemporalTesting,
  NowProvider,
  Epoch
}
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks.forAll

class LocalTimeGeneratorsUTest
    extends TemporalTesting
    with TimeRanges
    with AnyFunSuiteLike {
  val CurrentTime: LocalTime = LocalTime.of(12, 30)
  implicit val NowProvider: NowProvider = now(
    LocalDateTime.of(Epoch.LocalDate, CurrentTime)
  )

  test(
    "Ensure LocalTime generation helpers generate within the defined presets"
  ) {
    forAll(LocalTimeGenerators.distancePast) {
      withinRange(CurrentTime.minus(DistantPast), CurrentTime.minus(Past))
    }
    forAll(LocalTimeGenerators.past) {
      withinRange(CurrentTime.minus(Past), CurrentTime.minus(Recent))
    }
    forAll(LocalTimeGenerators.recent) {
      withinRange(CurrentTime.minus(Recent), CurrentTime)
    }

    forAll(LocalTimeGenerators.default) {
      withinRange(CurrentTime.minus(Recent), CurrentTime.plus(Soon))
    }

    forAll(LocalTimeGenerators.soon) {
      withinRange(CurrentTime, CurrentTime.plus(Soon))
    }
    forAll(LocalTimeGenerators.future) {
      withinRange(CurrentTime.plus(Soon), CurrentTime.plus(Future))
    }
    forAll(LocalTimeGenerators.distantFuture) {
      withinRange(CurrentTime.plus(Future), CurrentTime.plus(DistantFuture))
    }
  }
}
