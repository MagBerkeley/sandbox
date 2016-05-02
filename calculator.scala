package com.twitter.univ.calculator

import scala.collection.mutable.Stack

object splitEXP {

	def main(args: Array[String]): Unit = {
		//println(args)
	    //val input = args(0)
	    args match {
	    	case Array(i) => 
	    		val input = args(0)
	    		println("This is my input: " + input)
			    //println((splitExp(input)(0).mkString(" ")))
			    //println((splitExp(input)(1).mkString(" ")))
			    //println((splitExp(input)(2).mkString(" ")))
			    var output_array = splitExp(input)
			    var r = try {
			   	//println(calculate(output_array) )
			   	calculate(output_array)
			    } catch {
			        case _: NoSuchElementException => println("No element in stack")
			    }
			    println(r)
	    	case _ => println("invalid token") //doesn't work for "4 &"
	    }
	    
	}

	
	def splitExp(a: String): Array[String] = {
		var ss = a.split(" ")
		return ss
	}

	def calculate(myArray: Array[String]): Int = {
		var a = " ";
		var ints = Stack[Int]() //how does this different from val ints = new Stack[Int]() why do we need the "new"?
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
				case _ => {
					try {
					var b = a.toInt
					//println("number: " + b)
					ints.push(b)
					} catch {
					case _: NumberFormatException => {   //what if we do return 0  what does the return mean?
					//	println("symbol: " + a)
					}
					}
				}
			}
		}
	// add guard to prevent too many operands	
	ints match  {
		case x if (x.length > 1) => 0 //why the return type is supposed to be int?
		case _ => return(ints.pop)
	}
	/* why the line below didnt work
	Option[Int] = ints match {
		case x if (x.length > 1) => None
		case _ => Some(ints.pop)
	}
	*/
	}
}





