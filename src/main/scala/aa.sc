val s="The quick brown brown fox"
val x=s.split(" ").toList
val l=List("The","Fox")
x.filter(x=>l.contains(x)==false)

(x collect {case s:String if !(l.contains(s)) =>s}).distinct.length





