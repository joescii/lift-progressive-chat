package code.snippet

import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds.{SetValById, SetElemById}
import net.liftweb.http.{SendUpdateDOM, SHtml, S}
import net.liftweb.util.ClearClearable
import net.liftweb.util.Helpers._

import scala.xml.NodeSeq

object Chat {
  private [this] var ms = List("default message")

  def messages = ".chat-message *" #> ms & ClearClearable

  private [this] def append(msg:String):Unit = {
    ms = (ms :+ msg).takeRight(10)
  }

  private [this] def doPost:Unit = for {
    r <- S.request if r.post_?
    msg <- S.param("in")
  } yield {
    append(msg)
    S.redirectTo("/")
  }

  def submit = {
    S.session.foreach(_.plumbUpdateDOM())
    var msg = ""

    def onAjax():JsCmd = {
      append(msg)

      SendUpdateDOM()
      SetValById("chat-in", "")
    }

    "name=in" #> (SHtml.text(msg, msg = _, "id" -> "chat-in") ++ SHtml.hidden(onAjax))
  }
}
