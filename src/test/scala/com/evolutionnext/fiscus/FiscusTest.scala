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

trait Color

class Red extends Color

class FavoriteColor(val color: Color)

class FiscusTest extends WordSpec with ShouldMatchers {

  "get(class)" should {
    "spit back out an instance of an object given a set of classes with one as a label" in {

      import com.evolutionnext.fiscus.Fiscus._

      class Brown
      class Green
      class Blue

      object ColorTest extends Fiscus {
        override val contents = (new Brown as "favorite color") :: new Green :: new Blue :: Nil

        override def run() {
          get("favorite color").asInstanceOf[Brown].getClass should be(classOf[Brown])
        }
      }

      ColorTest.run()
    }

    "return a pre-created object from asking for the class" in {

      class Brown(val name: String)
      class Green(val name: String)
      class Blue(val name: String)

      object ColorTest extends Fiscus {
        override val contents = new Brown("Foxy") :: new Green("Al") :: new Blue("Man Group") :: Nil

        override def run() {
          get(classOf[Brown]).name should be("Foxy")
        }
      }

      ColorTest.run()
    }

    "return the object if the class of the object has no arg constructor and is a first level class" in {
      object ColorTest extends Fiscus {
        override def run() {
          get(classOf[Red]).getClass.getSimpleName should be ("Red")
        }
      }

      ColorTest.run()
    }

//    """return the object if the class of the object has parameters of a constructor that can be created
//            with objects that are no-args constructor, and the object can only be itself and non-ambigous (i.e. not in a subclass) """ in {
//      object ColorTest extends Fiscus {
//        override val contents = new Red :: Nil
//
//        override def run() {
//          val favoriteColor = get(classOf[FavoriteColor])
//          favoriteColor.getClass.getSimpleName should be("FavoriteColor")
//          favoriteColor.color.getClass.getSimpleName should be("Red")
//        }
//      }
//
//      ColorTest.run()
//    }
  }


  //    "return an created object using an class if there is no mapping" in {
  //
  //      import com.evolutionnext.fiscus.Fiscus._
  //
  //      class Brown
  //      class Green
  //      class Blue
  //
  //      object ColorTest extends Fiscus {
  //        override val contents = classOf[Brown] :: new Blue :: new Green() :: Nil
  //
  //        override def run() {
  //          get(classOf[Brown]).getClass should be (classOf[Brown])
  //        }
  //      }
  //
  //      ColorTest.run()
  //    }
}

