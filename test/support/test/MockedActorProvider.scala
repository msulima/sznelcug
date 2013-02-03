package support.test

import services.actors.{ActorsConfiguration, ActorProvider}
import akka.actor.{ActorRef, Actor}
import reflect.ClassTag


trait MockedActorProvider extends ActorProvider with ActorsConfiguration[ActorRef] {

  override def actorFor[T <: Actor: ClassTag] = actorDetails(classFromTag).get

  override def createActor[T <: Actor: ClassTag] = actorDetails(classFromTag).get

  private def classFromTag[T <: Actor : ClassTag]: Class[_ <: Actor] =
    implicitly[ClassTag[T]].runtimeClass.asInstanceOf[Class[_ <: Actor]]
}