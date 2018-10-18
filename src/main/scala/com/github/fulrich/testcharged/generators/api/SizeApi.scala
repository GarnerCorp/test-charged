package com.github.fulrich.testcharged.generators.api


trait SizeApi[T] {
  val tiny: T
  val short: T
  val default: T
  val big: T
  val huge: T
}