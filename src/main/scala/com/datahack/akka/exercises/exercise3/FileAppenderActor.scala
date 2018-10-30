package com.datahack.akka.exercises.exercise3

import java.io.File
import java.nio.channels.FileChannel

/**
  * Exercise 3 - "File it under asynchronous"
  *
  * In this exercise we will learn how to use 'become' and 'unbecome' -
  * one of the cornerstones in the Actor Model. We will also learn how
  * to create an Actor that can asynchronously write a simple log.
  *
  * Below is the blueprint of our soon to be FileAppenderActor,
  * this is where you will implement the behavior of the actor.
  */

object FileAppenderActor {
  sealed trait FileAppenderMessage
  //Opens the supplied file, you cannot open a new file before closing the old,
  //it should only be received when a file isn't Open
  case class Open(filePath: String) extends FileAppenderMessage

  //Append should only be received when a file is Open
  case class Append(text: String) extends FileAppenderMessage

  //Close should only be received when a file is Open
  case object Close extends FileAppenderMessage

  //WhichFile can be sent at any time, and will be responded to with CurrentFile
  case object WhichFile extends FileAppenderMessage
  case class CurrentFile(filePath: Option[String]) extends FileAppenderMessage

  //This is the response to all Open, Append and Close messages that is successful
  case object OK extends FileAppenderMessage
}

class FileAppenderActor { //TODO: First, we need to make sure this is extends Actor
  import FileAppenderActor._

  //This field should have Some(File) if there is a current file, or None if there is none
  var currentFile: Option[File] = None

  //This field should have Some(FileChannel) if there is a current file, or None if there is none
  var currentChannel: Option[FileChannel] = None

  //TODO: Write a method named 'opened' that returns a Receive (PartialFunction[Any,Unit] that receives the following
  //      message types: Append, WhichFile and Close
  //


  //TODO: Write a method named 'closed' that returns a Receive (PartialFunction[Any, Unit] that receives the following
  //      message types: Open and WhichFile

  //TODO: Then we need to implement the receive-method so that the actor initially has the 'closed' behavior
}
