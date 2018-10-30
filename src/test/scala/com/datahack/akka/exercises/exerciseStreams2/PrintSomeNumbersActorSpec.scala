package com.datahack.akka.exercises.exerciseStreams2

import akka.actor.{ActorSystem, Props}
import akka.stream.ActorMaterializer
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class PrintSomeNumbersActorSpec
  extends TestKit(ActorSystem("PrintSomeNumbersActorSpec"))
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  "Print Some Numbers Actor" should {

    "teach you how to encapsulate a flow into an actor" in {
      implicit val materializer = ActorMaterializer()
      system.actorOf(Props(classOf[PrintSomeNumbersActor], materializer))

      // TODO ....
    }
  }
}
