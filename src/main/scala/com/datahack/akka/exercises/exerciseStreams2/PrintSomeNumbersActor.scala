package com.datahack.akka.exercises.exerciseStreams2

import akka.actor.Actor
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source

class PrintSomeNumbersActor(implicit materializer: ActorMaterializer) extends Actor {
  private implicit val executionContext = context.system.dispatcher

  Source(1 to 10)
    .map(_.toString)
    .runForeach(println)
    .map(_ => self ! "done")

  override def receive: Receive = {
    case "done" =>
      println("Done")
      context.stop(self)
  }
}
