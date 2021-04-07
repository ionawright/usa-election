//import ElectionData.mapdata

import scala.io.Source
import scala.io.StdIn.readInt
import scala.io.StdIn.readLine
import scala.collection.immutable.ListMap

object ElectionData extends App {

    val mapdata = readFile("election_data.txt")
    println(mapdata)

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
            showTotalVotesForState(totalVotesForState)
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
    def showAllValues(f: () => Map[ String, List[(String, Int)] ] ) = {
        f() foreach {
            case (k, v) => println(s"State: $k - Votes: $v")
        }
    }

    def showTotalVotesForState(f: (String) => (String, Int)) = {
        val state = readLine("Enter state: ")
        val result = totalVotesForState(state)
        println(result)
        println(s"$state: $result")
    }

//  OPERATION FUNCTIONS
    def currentVotes(): Map[String, List[(String, Int)]] = {
        ListMap(mapdata.toSeq.sortBy(_._1):_*)
    }

    def totalVotesForState(state: String): (String, Int) = {
        println(s"State selected: $state")
        mapdata.get(state) match {
            case Some(x) => (state, x.map (_._2).sum)
            case None =>  println("Not available"); (state, 0)
        }
    }

//    def averageVotesPerPartyInState(list:List[(String,Int)]): Float = {
//        for ((key, list) <- mapdata) {
//            list.length
//        }
//        val sum = list.foldLeft(0)(_+_)
//        val total = list.foldLeft(0)((sum,_) => sum + 1)
//        sum / total
//    }

        def readFile(filename: String): Map[String, List[(String, Int)]] = {
            var mapBuffer: Map[String, List[(String, Int)]] = Map()
            try {
                for (line <- Source.fromFile(filename).getLines()) {
                    val splitline = line.split(",").map(_.trim).toList
                    // println(splitline)
                    val newList = splitline.tail
                    //  println(newList)
                    // List(Democratic:1672143, Republican:1661686, Libertarian:51465, Green:1557)
                    val boom = newList map (x => {
                        val y = x.split(":")
                        (y(0).toString, y(1).toInt)
                    })
//                    println(boom)
                    mapBuffer = mapBuffer ++ Map(splitline.head -> boom)
                }
            } catch {
                case ex: Exception => println("Sorry, an exception happened.")
            }
            mapBuffer
            // RESULT = HashMap(Arizona (11) -> List((Democratic,1672143), (Republican,1661686), (Libertarian,51465), (Green,1557), (Others,475)), Arkansas (6)
        }
}
