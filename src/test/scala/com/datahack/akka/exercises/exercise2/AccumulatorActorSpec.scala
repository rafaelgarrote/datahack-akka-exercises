package com.datahack.akka.exercises.exercise2

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

/**
  * The following test verifies that the functionality
  * of the code you've written works as expected.
  */
class AccumulatorActorSpec
  extends TestKit(ActorSystem("AccumulatorActorSpec"))
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  "Exercise2" should {

    "teach you how to handle multiple types of messages in an actor" in {
      //TODO: Create and start an instance of the AccumulatorActor

      //TODO: Verify, without sending a message to the actor, that it can accept all different message types
      //      defined in the AccumulatorActor object

      //TODO: Stop the AccumulatorActor
    }

    "teach you how to manage state inside an actor" in {
      //TODO: Create and start an instance of the AccumulatorActor

      //TODO: Send the set-message to the ActorRef and use the sum-message to verify that you get the expected result

      //TODO: Send the add-message to the ActorRef and use the sum-message to verify that you get the expected result

      //TODO: Send the set-message to the ActorRef and use the sum-message to verify that you get the expected result

      //TODO: Stop the AccumulatorActor
    }
  }

  override protected def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }
}
