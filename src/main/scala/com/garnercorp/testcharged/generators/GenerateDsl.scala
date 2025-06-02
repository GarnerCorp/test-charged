package com.garnercorp.testcharged.generators

import com.garnercorp.testcharged.generators.api.DefaultCaller
import org.scalacheck.Gen

import scala.language.implicitConversions

trait GenerateDsl {
  implicit class GeneratorHelper[T](private val generator: Gen[T]) {
    lazy val gen = new ExtendedGeneratorsHelper[T](generator)

    def value: T = generator.sample match {
      case Some(value) => value
      case None        =>
        throw new IllegalArgumentException(GenerateDsl.GenerationFailureMessage)
    }

    def some: Option[T] = gen.some.value
    def option: Option[T] = gen.option.value
    def seq: Seq[T] = gen.seq.value
    def seqOf(size: Int): Seq[T] = gen.seq(size).value
    def nonEmptySeq: Seq[T] = gen.nonEmptySeq.value
  }

  class ExtendedGeneratorsHelper[T](private val generator: Gen[T]) {
    def some: Gen[Option[T]] = Gen.some(generator)
    def option: Gen[Option[T]] = Gen.option(generator)
    def seq: Gen[Seq[T]] = Gen.listOf(generator)
    def seq(size: Int): Gen[Seq[T]] = Gen.listOfN(size, generator)
    def nonEmptySeq: Gen[Seq[T]] = Gen.nonEmptyListOf(generator)
  }

  implicit def toGen[A, T](defaultApi: T)(implicit
      defaultCaller: DefaultCaller[A, T]
  ): Gen[A] =
    defaultCaller(defaultApi)

  implicit def toGeneratorHelper[A, T](defaultApi: T)(implicit
      defaultCaller: DefaultCaller[A, T]
  ): GeneratorHelper[A] =
    new GeneratorHelper(defaultCaller(defaultApi))
}

object GenerateDsl {
  val GenerationFailureMessage = "Unable to generate the requested data."
}
