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
            showAverageVotesForParty(averageVotesPerPartyInState)
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
            println("Selected quit, Goodbye")
            false
        }


// FUNCTIONS THAT INTERACT WITH USER
    def showAllValues(f: () => Map[ String, List[(String, Int)] ] ) = {
        f() foreach {
            case (k, v) => println(s"State: $k - Votes: $v \n")
        }
    }

    def showTotalVotesForState(f: (String) => Int) = {
        val state = readLine("Enter state: ")
        val result = totalVotesForState(state)
        println(s"$state had $result votes \n")
    }
//
    def showAverageVotesForParty(f: (String) => Float) = {
        val state = readLine("Enter state: ")
        val result = averageVotesPerPartyInState(state)
        println(s"$state has a average of $result votes per party. \n")
    }

    def showWinningParties(f: (String) => (String,Int)) = {
        val party = winningPartyOfState()
        val percentage = "%"
        val state = "state"
        println(s"The winning party in $state is $party with $percentage of the votes cast")
    }

//  OPERATION FUNCTIONS
    def currentVotes(): Map[String, List[(String, Int)]] = {
        ListMap(mapdata.toSeq.sortBy(_._1):_*)
    }

    def totalVotesForState(state: String): Int = {
        println(s"State selected: $state")
        mapdata.get(state) match {
            case Some(x) => x.map (_._2).sum
            case None =>  println("State not available in list "); 0
        }
    }

    def averageVotesPerPartyInState(state: String): Float = {
        println(s"State selected: $state")
            val sum = mapdata.get(state) match {
                case Some(x) => x.map (_._2).foldLeft(0)(_+_)
                case None => 0
            }
            val total = mapdata.get(state) match {
                case Some(x) => x.map (_._2).length
                case None => 0
            }
        sum / total
    }

    def winningPartyOfState(): Unit = {


    }
//    // max - maxBy
//
//    println(winningPartyOfState())

        def readFile(filename: String): Map[String, List[(String, Int)]] = {
            var mapBuffer: Map[String, List[(String, Int)]] = Map()
            try {
                for (line <- Source.fromFile(filename).getLines()) {
                    val splitline = line.split(",").map(_.trim).toList
                    val newList = splitline.tail
                    val listOfVotes = newList map (x => {
                        val y = x.split(":")
                        (y(0).toString, y(1).toInt)
                    })
                    mapBuffer = mapBuffer ++ Map(splitline.head -> listOfVotes)
                }
            } catch {
                case ex: Exception => println("Sorry, an exception happened.")
            }
            mapBuffer
        }
}
