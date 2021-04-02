//import ElectionData.mapdata

import scala.io.Source
import scala.io.StdIn.readInt
import scala.io.StdIn.readLine
import scala.collection.immutable.ListMap

object ElectionData extends App {

    var mapBuffer: Map[String, List[(String, Int)]] = Map()

    var key ="Alabama (9)"
    var newList = List(("Democratic",849624),("Republican",1441170),("Libertarian",25176),("Others",7312))
    mapBuffer = mapBuffer ++ Map(key -> newList)

    key ="Alaska (3)"
    newList = List(("Democratic",153778),("Republican",189951),("Libertarian",8897),("Others",6904))
    mapBuffer = mapBuffer ++ Map(key -> newList)

    val mapdata = mapBuffer;
//    val mapdata = readFile("election_data.txt")
//    println(mapdata)

    val actionMap = Map[Int, () => Boolean](1 -> handleOne, 2 -> handleTwo, 3 -> handleThree, 4 -> handleFour, 5 -> handleFive, 6 -> handleSix, 7 -> handleSeven)
    var opt = 0
        do {
            opt = readOption
        } while (menu(opt))

        def readOption: Int = {
            println(
                """|Please select one of the following:
                   |  1 - Display all states and votes
                   |  2 - Total number of votes for state
                   |  3 - Average number of votes per party in a state
                   |  4 - Winning party of state (plus % of vote)
                   |  5 - Descending list of party & electoral votes won
                   |  6 - Total number of votes & % in a state
                   |  7 - Quit""".stripMargin)
            readInt()
        }

        def menu(option: Int): Boolean = {
            actionMap.get(option) match {
                case Some(f) => f()
                case None =>
                    println("Sorry, that command is not recognized")
                    true
            }
        }

        // handlers for menu options
        def handleOne(): Boolean = {
            showAllValues(currentVotes)
            true
        }

        def handleTwo(): Boolean = {
            true
        }

        def handleThree(): Boolean = {
            true
        }

        def handleFour(): Boolean = {
            true
        }

        def handleFive(): Boolean = {
            true
        }

        def handleSix(): Boolean = {
            true
        }

        def handleSeven(): Boolean = {
            println("selected quit")
            false
        }


// FUNCTIONS THAT INTERACT WITH USER
    def showAllValues(f:() => Map[String, List[(String, Int)]] = {
        f() foreach {
            case (s, k, v) => println(s" $s $k: $v")
        }
    })

//    def mnuShowPointsForTeam(f: (String) => (String, Int)) = {
//        val team = readLine("Enter team name: ")
//        val result = currentPointsForTeam(team)
//        println(result)
//        println("First tuple", result._1)
//        println("Second tuple", result._2)
//        val points = result._2
//        println(s"$team: $points")
//    }

//  OPERATION FUNCTIONS
    def currentVotes(): Map[String, List[(String, Int)]] = {
        ListMap(mapdata.toSeq.sortBy(_._1):_*)
    }

//    def currentPointsForTeam(team: String): (String, Int) = {
//        println(s"Team selected is $team")
//        mapdata.get(team) match {
//            case Some(x) => (team, x)
//            case None => println("Team is not in list") ; (team, 0)
//        }
//    }

//        def readFile(filename: String): Map[String, List[(String, Int)]] = {
//            var mapBuffer: Map[String, List[(String, Int)]] = Map()
//            try {
//                for (line <- Source.fromFile(filename).getLines()) {
////                    val splitline = line.split(",").map(_.trim).toList
//                        val splitline = line.split(",").map(_.split(":")).toList.map(_.toList)
//                    mapBuffer = mapBuffer ++ Map(splitline.head -> splitline.tail)
//                }
//            } catch {
//                case ex: Exception => println("Sorry, an exception happened.")
//            }
//            mapBuffer
////          mapBuffer.close (close the file when finished)
//        }

//    def readFile(filename: String): Map[String, List[(String, Int)]] = {
//        val result = for (line <- Source.fromFile(filename).getLines();
//                          array = line.split(",").map(_.trim))
//            yield Map(array.head -> array.tail.map(x => {
//            val y = x.split(":"); (y(0).toString, y(1).toInt)
//        }).toList)
//        result
//    }

//    val filename = "election_data.txt"
//    for (line <- Source.fromFile(filename).getLines()) {
//        val splitline = line.split(",").map(_.trim).toList
//        println(splitline)


//    val newMap = Map(name -> newList2.toList)
//    newMap
//        val splitagain = splitline.split(":").map().toList
//        println(splitagain)
//    }
}
