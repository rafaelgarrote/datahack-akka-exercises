package com.datahack.akka.exercises.exercise1

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

/**
  * The following test verifies that the functionality
  * of the code you've written works as expected.
  */

class EchoActorSpec
  extends TestKit(ActorSystem("EchoActorSpec"))
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  "Exercise1" should {

    "teach you how to create, start and stop an actor" in {
      //TODO: Create an ActorRef pointing to an instance of your EchoActor

      //TODO: Start the ActorRef

      //TODO: Make sure that the ActorRef is non-null and is running

      //TODO: Stop the ActorRef and make sure that it is stopped

      /*
      val testProbe = TestProbe()
      testProbe watch myActor
      myActor ! MyActor.Stop
      testProbe.expectTerminated(myActor)
       */
    }

    "teach you how to reply to the first message" in {
      //TODO: Create an ActorRef pointing to an instance of your EchoActor and start it

      //TODO: Send the ActorRef a String message using ! and make sure you get the expected response

      //TODO: Stop the ActorRef
    }

    "teach you what happens when you send a message to a stopped actor" in {
      //TODO: Create an ActorRef pointing to an instance of your EchoActor

      //TODO: Send the ActorRef a String message using ! and make sure you get the expected response
    }

    "teach you how to echo back messages consistently" in {
      //TODO: Create an ActorRef pointing to an instance of your EchoActor and start it

      //TODO: Send a lot of different kinds of String messages to the ActorRef and make sure you consistently get the expected response

      //TODO: Stop the ActorRef
    }

    "teach you how to handle the absense of a sender" in {
      //TODO: Create an ActorRef pointing to an instance of your EchoActor and start it

      //TODO: Send a String message to the ActorRef that doesn't have any sender

      //TODO: Send another String message to the ActorRef and make sure you get the expected response

      //TODO: Stop the ActorRef
    }

    "teach you how to respond with different kinds of responses" in {
      //TODO: Create an ActorRef pointing to an instance of your EchoActor and start it

      //TODO: Send a lot of different kinds of messages (numbers, null, objects etc)
      //      to the ActorRef and make sure you consistently get the expected response

      //TODO: Stop the ActorRef
    }
  }

  override protected def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }
}
