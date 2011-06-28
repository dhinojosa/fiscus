import sbt.{Configuration, DefaultProject, ProjectInfo}

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 6/23/11
 * Time: 1:18 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */

class FiscusProject(info: ProjectInfo) extends DefaultProject(info) {
  val scalaToolsRepository = "Scala-Tools Maven2 Repository" at "http://scala-tools.org/repo-releases"

  val jodaTime = "joda-time" % "joda-time" % "1.6.2" withSources ()
  val scalaCheck = "org.scala-tools.testing" % "scalacheck_2.8.0.RC1" % "1.7" % "test" withSources ()
  val scalaTest = "org.scalatest" % "scalatest_2.9.0" % "1.6.1" % "test" withSources ()
  val testNG = "org.testng" % "testng" % "5.14.6" % "test" withSources ()
  val specs = "org.scala-tools.testing" % "specs_2.8.0" % "1.6.5" % "test" withSources ()
  val easyMock = "org.easymock" % "easymock" % "3.0" % "test" withSources ()

  //  Workaround to keep source files out!
  override def outputPattern = "[type]/[conf]/[artifact](-[revision])(-[classifier]).[ext]"

  override def configurationPath(c: Configuration) = managedDependencyPath / "jar" / c.toString
}

/*TODO: retronym
(01:35:44 PM) retronym: https://github.com/mpeltonen/sbt-idea
(01:35:53 PM) retronym: *sbtIdeaRepo at http://mpeltonen.github.com/maven/
(01:35:57 PM) retronym: *idea is com.github.mpeltonen sbt-idea-processor 0.4.0
(01:36:00 PM) retronym: update
(01:36:04 PM) retronym: idea
(01:36:30 PM) retronym: works even better if you have withSources() on the end of your managed dependencies*/