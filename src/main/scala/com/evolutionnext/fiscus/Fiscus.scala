package com.evolutionnext.fiscus

import java.lang.RuntimeException

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 6/23/11
 * Time: 2:42 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */

trait Fiscus {
  val contents: Seq[Any] = Nil

  def run() {
    //    contents.flatMap(x => x)
    //    labelMap = contents.flatMap {
    //      _ match {
    //        case x: Wrapper[_] => Some((x.label, x))
    //        case _ => None
    //      }
    //    }
  }

  def get(label: String) = {
    contents.filter(_.isInstanceOf[Tuple2[_, String]]).asInstanceOf[Seq[Tuple2[_, String]]].find(_._2 == label) match {
      case Some((value, str)) => value
      case None => throw new RuntimeException("Not Found")
    }
  }

  def get[T <: AnyRef](clazz: Class[T])(implicit manifest: Manifest[T]):T = {
    contents.filter(_.isInstanceOf[AnyRef]).asInstanceOf[List[AnyRef]].find(_.getClass == clazz) match {
      case Some(value) => value.asInstanceOf[T]
      case None => clazz.newInstance()
    }
  }
}

object Fiscus {

  class Wrapper(val item: Any) {
    def as(label: String) = (item, label)
  }

  implicit def convertAnyToWrapper(t: Any): Wrapper = {
    new Wrapper(t)
  }
}