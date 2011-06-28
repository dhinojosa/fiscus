package com.evolutionnext.fiscus

import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers


/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 6/23/11
 * Time: 1:17 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
class FiscusTest extends WordSpec with ShouldMatchers {

  "A Fiscus" should {
    "spit back out an instance of an object given a set of classes with one as a label" in {

      import com.evolutionnext.fiscus.Fiscus._

      class Brown
      class Green
      class Blue

      object ColorTest extends Fiscus {
        override val contents = (new Brown labeledAs "favorite color") :: new Green :: new Blue :: Nil

        override def run() {
          get("favorite color").asInstanceOf[Brown].getClass should be(classOf[Brown])
        }
      }

      ColorTest.run()
    }

    "return an object if that object has a no args constructor" in {

      class Brown(val name:String)
      class Green(val name:String)
      class Blue(val name:String)

      object ColorTest extends Fiscus {
        override val contents = new Brown("Foxy") :: new Green("Al") :: new Blue("Man Group") :: Nil

        override def run() {
          get(classOf[Brown]).name should be ("Foxy")
        }
      }

      ColorTest.run()
    }
  }
}

