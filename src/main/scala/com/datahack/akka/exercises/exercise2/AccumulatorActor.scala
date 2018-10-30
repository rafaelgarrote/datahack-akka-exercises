package com.datahack.akka.exercises.exercise2

/**
  * Exercise 2 - "Akkumulator"
  *
  * In this exercise we will learn how to manage state within an Actor,
  * how to define our own message types and how to handle different messages
  * within the same Actor.
  *
  * Below is the blueprint of our soon to be AccumulatorActor,
  * this is where you will implement the behavior of the actor.
  */

//First we create a companion object for our AccumulatorActor type,
//it's purpose will be to act as a namespace for the message types we will define.

object AccumulatorActor {
  //TODO: Create a message type that will be used to add a supplied integer number to the internal number of the Actor
  //TODO: Create a message type that will be used to query the internal number of the Actor
  //TODO: Create a message type that will be used to set a supplied integer number as the current internal number of the Actor
}

class AccumulatorActor { //TODO: We need to make sure this is an Actor
  //TODO: Here we will define field(s) that will hold our state

  //TODO: Then we need to implement the receive-method that:
  //      Given the set-message set the supplied number as the internal number
  //      Given the add-message adds the supplied number to the internal number
  //      Given the sum-message responds with the internal number
}
