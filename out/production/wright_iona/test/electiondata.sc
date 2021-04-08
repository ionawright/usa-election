import scala.io.Source

var mapBuffer: Map[String, List[(String, Int)]] = Map()

var key ="Alabama (9)"
var newList = List(("Democratic",849624),("Republican",1441170),("Libertarian",25176),("Others",7312))
mapBuffer = mapBuffer ++ Map(key -> newList)

key ="Alaska (3)"
newList = List(("Democratic",153778),("Republican",189951),("Libertarian",8897),("Others",6904))
mapBuffer = mapBuffer ++ Map(key -> newList)

key ="Arizona (11)"
newList = List(("Democratic",1672143),("Republican",1661686),("Libertarian",51465),("Green",1557),("Others",475))
mapBuffer = mapBuffer ++ Map(key -> newList)

key ="Arkansas (6)"
newList = List(("Democratic",423932),("Republican",760647),("Libertarian",13133),("Green",2980),("Others",18377))
mapBuffer = mapBuffer ++ Map(key -> newList)

key ="California (55)"
newList = List(("Democratic",11110250),("Republican",6006429),("Libertarian",187895),("Green",81029),("Others",115278))
mapBuffer = mapBuffer ++ Map(key -> newList)

key ="Colorado(9)"
newList = List(("Democratic",1804352),("Republican",1364607),("Libertarian",52460),("Green",8986),("Others",26575))
mapBuffer = mapBuffer ++ Map(key -> newList)

key ="Connecticut (7)"
newList = List(("Democratic",1080831),("Republican",714717),("Libertarian",20230),("Green",7538),("Others",541))
mapBuffer = mapBuffer ++ Map(key -> newList)

key ="D.C. (3)"
newList = List(("Democratic",317323),("Republican",18586),("Libertarian",2036),("Green",1726),("Others",4685))
mapBuffer = mapBuffer ++ Map(key -> newList)

key ="Delaware (3)"
newList = List(("Democratic",296268),("Republican",200603),("Libertarian",5000),("Green",2139),("Others",336))
mapBuffer = mapBuffer ++ Map(key -> newList)

key ="Florida (29)"
newList = List(("Democratic",5297045),("Republican",5668731),("Libertarian",70324),("Green",14721),("Others",16635))
mapBuffer = mapBuffer ++ Map(key -> newList)

println("mapBuffer: " + mapBuffer)

val filename = "election_data.txt"
    for (line <- Source.fromFile(filename).getLines()) {
        val splitline = line.split(",").map(_.trim).toList
        println(splitline)
//        val boom = Map(splitline._1 -> splitline._2.map(x => {
//         val banana = x.split(":"); (banana(0).toString, banana(1))})
//            println(boom)
        val splitline2 = line.split(",").map(_.trim).toList.splitAt(1)
        println(splitline2)
        println("SPLITTING UP THE LINES ---------", splitline2)
//        val mapping = Map(splitline._1 -> splitline._2.map(x => {
//            val y = x.split(":"); (y(0).toString, y(1).toInt)
//        }))
//        println("MAPPING THE DATA ------------------", mapping)
    }








