package com.garnercorp.testcharged.generators.temporal.dates

import java.time.{LocalDateTime, Month, LocalTime, LocalDate}
import com.garnercorp.testcharged.generators.temporal.{
  TemporalTesting,
  NowProvider
}
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks.forAll

class LocalDateGeneratorsUTest
    extends TemporalTesting
    with DateRanges
    with AnyFunSuiteLike {
  val CurrentDate: LocalDate = LocalDate.of(2020, Month.JANUARY, 1)
  implicit val NowProvider: NowProvider = now(
    LocalDateTime.of(CurrentDate, LocalTime.MIDNIGHT)
  )

  test(
    "Ensure LocalDate generation helpers generate within the defined presets"
  ) {
    forAll(LocalDateGenerators.distantPast) {
      withinRange(
        CurrentDate.minus(DistantPast),
        CurrentDate.minus(Past).minus(Offset)
      )
    }
    forAll(LocalDateGenerators.past) {
      withinRange(
        CurrentDate.minus(Past),
        CurrentDate.minus(Recent).minus(Offset)
      )
    }
    forAll(LocalDateGenerators.recent) {
      withinRange(CurrentDate.minus(Recent), CurrentDate.minus(Offset))
    }

    forAll(LocalDateGenerators.default) {
      withinRange(CurrentDate.minus(Recent), CurrentDate.plus(Soon))
    }

    forAll(LocalDateGenerators.soon) {
      withinRange(CurrentDate.plus(Offset), CurrentDate.plus(Soon))
    }
    forAll(LocalDateGenerators.future) {
      withinRange(CurrentDate.plus(Soon).plus(Offset), CurrentDate.plus(Future))
    }
    forAll(LocalDateGenerators.distantFuture) {
      withinRange(
        CurrentDate.plus(Future).plus(Offset),
        CurrentDate.plus(DistantFuture)
      )
    }
  }
}
