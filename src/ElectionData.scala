import Helpers.{highestValue, getNumberInString, sumListValues}
import scala.io.Source
import scala.io.StdIn.readInt
import scala.io.StdIn.readLine
import scala.collection.immutable.ListMap

object ElectionData extends App {

    val mapdata = readFile("election_data.txt")
    println(mapdata)

    // TODO: menu input will throw exception when space / int is accidently added - catch to throw a better error.
//    val actionMap = Map[Int, () => Boolean](1 -> handleOne, 2 -> handleTwo, 3 -> handleThree, 4 -> handleFour, 5 -> handleFive, 6 -> handleSix, 7 -> handleSeven)
//    var opt = 0
//        do {
//            opt = readOption
//        } while (menu(opt))
//
//        def readOption: Int = {
//            println(
//                """|Please select one of the following:
//                   |  1 - Display all states and votes
//                   |  2 - Total number of votes for state
//                   |  3 - Average number of votes per party in a state
//                   |  4 - Winning party of state (plus % of vote)
//                   |  5 - Descending list of party & electoral votes won
//                   |  6 - Total number of votes & % in a state
//                   |  7 - Quit""".stripMargin)
//            readInt()
//        }
//
//        def menu(option: Int): Boolean = {
//            actionMap.get(option) match {
//                case Some(f) => f()
//                case None =>
//                    println("Sorry, that command is not recognized")
//                    true
//            }
//        }

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
            winningPartyOfState(mapdata)
            true
        }

        def handleFive(): Boolean = {
//            showVotesWonByParties(votesWonByParties)
            true
        }

        def handleSix(): Boolean = {
//            showTotalNumberOfVotesForParty(totalNumberOfVotesForParty)
            true
        }

        def handleSeven(): Boolean = {
            println("Selected quit, Goodbye")
            false
        }


//// FUNCTIONS INTERACT WITH USER
    def showAllValues(data: Map[String, List[(String, Int)]]) = {
        data foreach {
            case (k, v) => println(s"State: $k - Votes: $v\n")
        }
    }

    def showTotalVotesForState(f: (String) => Int) = {
        val state = readLine("Enter state: ")
        val result = totalVotesForState(state)
        println(s"$state had $result votes \n")
    }

    def showAverageVotesForParty(f: (String) => Float) = {
        val state = readLine("Enter state: ")
        val result = averageVotesPerPartyInState(state)
        println(s"$state has a average of $result votes per party. \n")
    }
    //foreach

      //Directly using the operational function
//    def showWinningParty(f: () => Map[ String, List[(String, Int)]]) = {
//        val result = winningPartyOfState(mapdata)
//        println(s"$result")
//    }

    // TODO: 2 functions
//    def showVotesWonByParties(f: (String) => Int) = {
//        val result = votesWonByParties()
//        println(s"$result")
//    }
//
//    def showTotalNumberOfVotesForParty(f: (String) => (String,Int)) = {
//        val party = readLine("Enter party: ")
//        val result = totalNumberOfVotesForParty(party)
//        println(s"The $party party gained $votes votes which is $percentage of the votes cast")
//    }

////  OPERATION FUNCTIONS
    def currentVotes(): Map[String, List[(String, Int)]] = {
        ListMap(mapdata.toSeq.sortBy(_._1):_*)
    }

        def totalVotesForState(state: String): Int = {
//            foreach { case (x,y) => val votes = y.map(_._2).sum println(s"$x $y)}
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

    def winningPartyOfState(data: Map[String, List[(String,Int)]]) = {
        data foreach { case (x, y) =>
            val party = highestValue(y)._1
            val votes = highestValue(y)._2
            val totalVotes = sumListValues(y)
            val percentage = votes / totalVotes.toFloat * 100
            val round = percentage.round
            println(s"$x is $party with $round% of the votes cast.")
        }
    }

    def votesWonByPartyElectoral(data: Map[String, List[(String,Int)]]): Unit = {
        data foreach { case (x, y) =>
            val highestParty = highestValue(y)._1
//            println(highestParty)
            val electoralVote = getNumberInString(x)
//            println(electoralVote)
          val list = highestParty :: electoralVote :: Nil
          println(list)
//            val boom = List(highestParty, electoralVote)
        }

        // RESULT Democratic 88, Republican 47
    }
//    println(votesWonByPartyElectoral(mapdata))

    def totalNumberOfVotesForParty(party: String): Unit = {
        println(s"Party selected: $party")
        val sum = mapdata.get(party)
//        match {
//            case Some(x) => x.map (_._2)
//            case None => 0
//        }
        println(sum)
        //List((Democratic,1672143), (Republican,1661686), (Libertarian,51465), (Green,1557), (Others,475))

        // List(Democratic,1672143, Democratic,423932, Democratic,153778)
        // println(sumListValues(List)
        // The $party party gained $totalVotes votes which is $percentage% of the votes cast
    }
    println(totalNumberOfVotesForParty("Democratic"))


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
            //close
        }
}
