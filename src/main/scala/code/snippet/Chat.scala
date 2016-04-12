package code.snippet

import net.liftweb.http.S
import net.liftweb.util.ClearClearable
import net.liftweb.util.Helpers._

import scala.xml.NodeSeq

object Chat {
  private [this] var ms = List.empty[String]

  def messages = ".chat-message *" #> ms & ClearClearable

  def submit(html:NodeSeq):NodeSeq = {

    for {
      r <- S.request if r.post_?
      msg <- S.param("in")
    } yield {
      ms = (ms :+ msg).takeRight(10)
      S.redirectTo("/")
    }

    html
  }
}
