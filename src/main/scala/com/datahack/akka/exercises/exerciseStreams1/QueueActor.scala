package com.datahack.akka.exercises.exerciseStreams1

import akka.actor.Actor
import akka.stream.scaladsl.SourceQueueWithComplete
import com.datahack.akka.exercises.exerciseStreams1.QueueActor.SendItem


object QueueActor {

  case class SendItem(itemName: String)
}

// TODO: Create an actor thar receives by parameter a materialized SourceQueue
class QueueActor(queue: SourceQueueWithComplete[String]) extends Actor { //TODO: First, we need to make sure this is extends Actor

  override def receive: Receive = {
    // TODO: When receive SendItem, enqueue it into the stream
    case SendItem(itemName) =>
      queue.offer(itemName)
  }
}
