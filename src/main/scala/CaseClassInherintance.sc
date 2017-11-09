
/* Inheritance

As a rule you should NOT extend a case class with another case class.

case class foo(x:Int)
case class bar(override val x:Int,z:String) extends foo(x)

doesn't compile....

however you can extend from a class, abstract class or a trait...
 */


abstract class Notification {
  def getAttention:String
}

case class Email(sender: String, title: String, body: String) extends Notification {
  def getAttention="You have mail!"
}

case class SMS(caller: String, message: String) extends Notification {
  def getAttention="Beep Beep!"
}

case class VoiceRecording(contactName: String, link: String) extends Notification {
  def getAttention="You have a message!"
}

case class PostItNote(message:String) extends Notification {
  def getAttention="Stuck on pc!"
}

// and look at the pattern matching capabilities

def notifiedWithGuards(n:Notification)={
  n match {
    case Email(sender,title,theBodyOfTheEmail)=>println(n.getAttention + s"Email says $theBodyOfTheEmail")
    case SMS(_,message)=>println(n.getAttention + s"SMS says $message")
    case VoiceRecording(cn,_)=>println(n.getAttention + s"VoiceRecording from $cn")
//  case _ => println("Someother device") //default case as we have not defined all types
  }
}

//match on types

def notified(n:Notification)={
  n match {
    case e:Email=>println("Email!")
    case s:SMS=>println("SMS")
    case v:VoiceRecording=>println("VoiceRecording" + v.contactName)
  }

}


val sms=SMS("Boss","Another Meeting!")
val postItNote=PostItNote("Password")
notified(sms)
//notified(postItNote)
