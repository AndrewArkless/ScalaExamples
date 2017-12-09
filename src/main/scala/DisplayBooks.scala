/**
  * Created by andrew on 09/12/17.
  */
object DisplayBooks extends DisplayBooks {
   def bookService = BookService
}

trait DisplayBooks  {
  def bookService:BookService
  def displayBooks=bookService.getAllBooks//booksService.getAllBooks
}