    var mapBuffer: Map[String, List[(String, Int)]] = Map()

    var key ="Alabama (9)"
    var newList = List(("Democratic",849624),("Republican",1441170),("Libertarian",25176),("Others",7312))
    mapBuffer = mapBuffer ++ Map(key -> newList)

    key ="Alaska (3)"
    newList = List(("Democratic",153778),("Republican",189951),("Libertarian",8897),("Others",6904))
    mapBuffer = mapBuffer ++ Map(key -> newList)

    val mapdata = mapBuffer;