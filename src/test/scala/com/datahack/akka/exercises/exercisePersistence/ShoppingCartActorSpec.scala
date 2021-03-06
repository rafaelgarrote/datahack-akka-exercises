package com.datahack.akka.exercises.exercisePersistence

import akka.actor.{ActorSystem, PoisonPill, Props}
import akka.testkit.{ImplicitSender, TestKit}
import com.datahack.akka.exercises.exercisePersistence.ShoppingCartActor._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class ShoppingCartActorSpec
  extends TestKit(ActorSystem("ShoppingCartActorSpec"))
  with WordSpecLike
  with Matchers
  with BeforeAndAfterAll
  with ImplicitSender {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "ShoppingCartActor" should {
    val shoppingItem = ShoppingItem("sku-000001", "Cheap headphones", 42.25, 2)

    "add an item to the shopping cart and preserve it after restart" in {
      val shoppingCartId = "sc-000001"
      val shoppingCartActor = system.actorOf(Props(new ShoppingCartActor(shoppingCartId)))

      shoppingCartActor ! AddItemCommand(shoppingItem)
      expectMsg(AddItemResponse(shoppingItem))

      shoppingCartActor ! PoisonPill

      // creating a new actor with the same persistence id
      val shoppingCartActor2 = system.actorOf(Props(new ShoppingCartActor(shoppingCartId)))

      shoppingCartActor2 ! GetItemsRequest

      expectMsg(GetItemsResponse(Seq(shoppingItem)))
    }

    "update an existing item to the shopping cart and preserve the changes after restart" in {
      val shoppingCartId = "sc-000002"
      val shoppingCartActor = system.actorOf(Props(new ShoppingCartActor(shoppingCartId)))
      val updatedShoppingItem = shoppingItem.copy(quantity = 5)

      shoppingCartActor ! AddItemCommand(shoppingItem)
      expectMsg(AddItemResponse(shoppingItem))
      shoppingCartActor ! UpdateItemCommand(updatedShoppingItem)
      expectMsg(UpdateItemResponse(updatedShoppingItem))

      shoppingCartActor ! PoisonPill

      // creating a new actor with the same persistence id
      val shoppingCartActor2 = system.actorOf(Props(new ShoppingCartActor(shoppingCartId)))
      shoppingCartActor2 ! GetItemsRequest

      expectMsg(GetItemsResponse(Seq(updatedShoppingItem)))
    }

    "remove an existing item from the shopping cart and preserve the changes after restart" in {
      val shoppingCartId = "sc-000003"
      val shoppingCartActor = system.actorOf(Props(new ShoppingCartActor(shoppingCartId)))

      shoppingCartActor ! AddItemCommand(shoppingItem)
      expectMsg(AddItemResponse(shoppingItem))
      shoppingCartActor ! RemoveItemCommand(shoppingItem.id)
      expectMsg(RemoveItemResponse(shoppingItem.id))

      shoppingCartActor ! PoisonPill

      // creating a new actor with the same persistence id
      val shoppingCartActor2 = system.actorOf(Props(new ShoppingCartActor(shoppingCartId)))
      shoppingCartActor2 ! GetItemsRequest

      expectMsg(GetItemsResponse(Seq.empty))
    }
  }
}