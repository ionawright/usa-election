object Helpers {

  def sumListValues(list: List[(String,Int)]) = {
    val totalVotes = list.map(_._2).sum
    totalVotes
  }

  def removeBrackets(string: String): String = {
    val newString = string.replaceAll("[^-\\d]+", "")
    newString
  }

  def highestValue(list: List[(String, Int)]): (String, Int) ={
    val mapList = list.maxBy(_._2)._1
    // try to use max but doing this we lose the matching string. MaxBy uses the parameter
    val highestInt = list.map(_._2).max
    (mapList, highestInt)
  }

  def getNumberInString(string: String) = {
    string.replaceAll("""\).*""", "").replaceAll(""".*\(""", "").toInt
  }

//  def mostCommon(list:List[Int]): Int = {
//    list.foldLeft(Map.empty[Int, Int].withDefaultValue(0)) {
//      case (m,v) => m.updated(v,m(v) + 1)
//    }.maxBy(_._2)._1
//  }

  // REMOVE NUMBERS FROM STATE


}
