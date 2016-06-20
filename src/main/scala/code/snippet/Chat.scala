package code.snippet

import java.util.concurrent.atomic.AtomicReference
import java.util.function.UnaryOperator

import net.liftweb.actor.LiftActor
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds.SetValById
import net.liftweb.http._
import net.liftweb.util.ClearClearable
import net.liftweb.util.Helpers._

object Chat {
  private [this] val ms = new AtomicReference(List("default message"))

  def messages = ".chat-message *" #> ms.get() & ClearClearable

  private [this] def append(msg:String):Unit =
    ms.updateAndGet(new UnaryOperator[List[String]] {
      override def apply(m: List[String]) = (m :+ msg).takeRight(5)
    })

  private [this] def doPost:Unit = for {
    r <- S.request if r.post_?
    msg <- S.param("in")
  } yield {
    append(msg)
    S.redirectTo("/")
  }

  def submit = {
    S.session.foreach(_.plumbUpdateDOM(listenTo = List(ChatActor)))
    var msg = ""

    def onAjax():JsCmd = {
      append(msg)

//      SendUpdateDOM()
      ChatActor ! ""
      SetValById("chat-in", "")
    }

    "name=in" #> (SHtml.text(msg, msg = _, "id" -> "chat-in") ++ SHtml.hidden(onAjax))
  }
}

object ChatActor extends LiftActor with ListenerManager {
  override def createUpdate = ""

  override def lowPriority = {
    case _ => sendListenersMessage(UpdateDOM())
  }
}