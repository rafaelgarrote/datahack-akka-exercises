package com.datahack.akka.exercises.exercise3

import java.io.File

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class FileAppenderActorSpec
  extends TestKit(ActorSystem("FileAppenderActorSpec"))
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  //Creates a temporary file that is deleted after the test, can be useful!
  def withTempFile(fun: File => Unit) {
    val file = File.createTempFile("foo","bar")
    try {
      fun(file)
    } finally {
      file.delete
    }
  }

  "Exercise3" should {

    "teach you how to use become and unbecome to change behavior" in {
      //TODO: Create and start a FileAppenderActor ActorRef

      //TODO: Make sure that the ActorRef does not currently support the Append or Close messages

      //TODO: Verify that the Actor supports the Open and WhichFile messages

      //TODO: Test that the ActorRef initially has no open file

      //TODO: Test that the ActorRef has no open file after sending it an "Open"-message with a path that doesn't point to a file

      withTempFile { f =>
        //TODO: Test that the ActorRef points to the correct file after sending it an "Open"-message with a path that points to a file

        //TODO: Now verify that the Actor doesn't support the Open message

        //TODO: Then verify that the Actor now supports the Append, WhichFile and Close messages
      }
      //TODO: Stop the ActorRef
    }

    "teach you how you can make IO asynchronous with Actors" in {
      //TODO: Create and start a FileAppenderActor ActorRef

      withTempFile { f =>
        //TODO: Tell the FileAppender to open with a correct file

        //TODO: Create and start 2 EchoActor ActorRefs

        //TODO: Send 10 Append-messages with unique texts in each, and set the sender of the messages to be the FileAppender

        //TODO: Send a Close message to the FileAppender and await it to be processed

        //TODO: Stop the EchoActors and the FileAppender

        //TODO: Verify that each of the 10 messages are inside the file that the FileAppender was appending to
      }
    }

    "BONUS: make the actor automatically clean itself up and becoming 'closed' when stopped" in {

    }

    "BONUS: use akka.actor.Actor.spawn to send lots of text in concurrently to the same appender" in {

    }
  }

  override protected def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }
}
