package com.datahack.akka.exercises.exercisePersistence

import akka.actor.ActorLogging
import akka.persistence.{PersistentActor, RecoveryCompleted}
import com.datahack.akka.exercises.exercisePersistence.ShoppingCartActor._

// https://tudorzgureanu.com/akka-persistence-testing-persistent-actors/

object ShoppingCartActor {

  case class ShoppingItem(id: String, title: String, price: BigDecimal, quantity: Int)

  //def props(id: String): Props = Props(new ShoppingCartActor(id))

  //protocol
  case class AddItemCommand(shoppingItem: ShoppingItem)

  case class AddItemResponse(shoppingItem: ShoppingItem)

  case class UpdateItemCommand(shoppingItem: ShoppingItem)

  case class UpdateItemResponse(shoppingItem: ShoppingItem)

  case class RemoveItemCommand(shoppingItemId: String)

  case class RemoveItemResponse(shoppingItemId: String)

  case object GetItemsRequest

  case class GetItemsResponse(items: Seq[ShoppingItem])

  // events
  sealed trait ShoppingCartEvent

  case class ItemAdded(shoppingItem: ShoppingItem) extends ShoppingCartEvent

  case class ItemUpdated(shoppingItem: ShoppingItem) extends ShoppingCartEvent

  case class ItemRemoved(shoppingItemId: String) extends ShoppingCartEvent

}

class ShoppingCartActor(id: String) extends PersistentActor with ActorLogging {
  private var state: Seq[ShoppingItem] = Seq.empty
  override def persistenceId: String = id

  override def receiveCommand: Receive = {
    case AddItemCommand(item) =>
      persist(ItemAdded(item)) { evt =>
        state = applyEvent(evt)
        sender() ! AddItemResponse(item)
      }
    case UpdateItemCommand(item) =>
      persist(ItemUpdated(item)) { evt =>
        state = applyEvent(evt)
        sender() ! UpdateItemResponse(item)
      }
    case RemoveItemCommand(itemId) =>
      persist(ItemRemoved(itemId)) { evt =>
        state = applyEvent(evt)
        sender() ! RemoveItemResponse(itemId)
      }
    case GetItemsRequest =>
      sender() ! GetItemsResponse(state)
  }

  override def receiveRecover: Receive = {
    case evt: ShoppingCartEvent => state = applyEvent(evt)
    case RecoveryCompleted => log.info("Recovery completed!")
  }

  private def applyEvent(shoppingCartEvent: ShoppingCartEvent): Seq[ShoppingItem] = shoppingCartEvent match {
    case ItemAdded(item) => item +: state
    case ItemUpdated(item) => item +: state.filterNot(_.id == item.id)
    case ItemRemoved(itemId) => state.filterNot(_.id == itemId)
  }
}

