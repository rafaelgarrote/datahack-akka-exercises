package com.datahack.akka.exercises.exerciseStreams1

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, OverflowStrategy}
import akka.stream.scaladsl.{Keep, Source}
import akka.stream.testkit.scaladsl.TestSink
import akka.testkit.{TestActorRef, TestKit}
import com.datahack.akka.exercises.exerciseStreams1.QueueActor.SendItem
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class QueueActorSpec
  extends TestKit(ActorSystem("QueueActorSpec"))
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  // TODO: Create an actor materializer
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  "Exercise Streams 1" should {

    "teach you how to use an actor to inject messages into a stream" in {
      // TODO: Create a materialized flow with Source Queue of string and a Test Sink of string and keep booth
      val (queue, probe) = Source.queue[String](Int.MaxValue, OverflowStrategy.backpressure)
        .toMat(TestSink.probe[String])(Keep.both).run()

      // TODO: Create and start a QueueActor ActorRef and pass the source to the actor by param
      val queueActor = TestActorRef[QueueActor](new QueueActor(queue))

      // TODO: Tell the actor to Send new Item to the stream
      queueActor ! SendItem("myItem")

      // TODO: Test that the item arrives to the test sink
      probe.request(1).expectNext("myItem")
    }
  }

}
