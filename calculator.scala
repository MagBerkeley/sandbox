package com.twitter.univ.calculator

import scala.collection.mutable.Stack

//object CalculatorMain extends Calculator

//class Calculator {

object IntValue {
    def unapply(s: String) : Option[Int] = {
    	if (s eq null) return None //reference eq here
    	try{
    	Some(s.toInt)
    	} catch {
    	case _ : NumberFormatException =>
    	None//can you extract an Int from the String I gave you?
        }
     }
}

object splitEXP {

	def main(args: Array[String]): Unit = {
		//println(args)
	    //val input = args(0)
	    args match {
	    	case Array(i) => 
	    		val input = i
	    		println("This is my input: " + input)
			    //println((splitExp(input)(0).mkString(" ")))
			    //println((splitExp(input)(2).mkString(" ")))
			    var output_array = splitExp(input)
			    var r = calculate(output_array) 

			   	//println(calculate(output_array) )   
			    //println(r)
	    	case _ => println("too many elements in an array") //doesn't work for "4 &"
	        }
	    }

	
	def splitExp(a: String): Array[String] = {
		var ss = a.split(" ")
		return ss
	}

	def calculate(myArray: Array[String]): Option[Int] = {
		var a = " ";
		var ints = Stack[Int]() //how does this different from val ints = new Stack[Int]() why do we need the "new"?(Scala is referring to the Stack's companion object apply method?)
		try {
		for (a <- myArray) {
			 a match {
				case "+" => {
					val p = ints.pop
					val q = ints.pop
					val res = p + q
					ints.push(res)
				}
				case "-" => {
					val p = ints.pop
					val q = ints.pop
					val res = q - p
					ints.push(res)
				}
				case "*" => {
					val p = ints.pop
					val q = ints.pop
					val res = p * q
					ints.push(res)
				}
				case "/" => {
					val p = ints.pop
					val q = ints.pop
					val res = q / p
					ints.push(res)
				}
				case IntValue(n) => ints.push(n)
				case _ => return None //invalid token case
				}
				/*
				case _ => {
					try {
					var b = a.toInt
					//println("number: " + b)
					ints.push(b)
					} catch {
					case _: NumberFormatException => return None
					}
					}
				}
				*/
			}// end of loop
			Some(ints.pop)
		    } catch {
              //case x if (x.length > 1) => None //why the return type is supposed to be int?		    	
              case _ : NoSuchElementException => None
              //println("No element in stack")		    	
              //case _ => return(Some(ints.pop))
		    }

	}
}

//}



