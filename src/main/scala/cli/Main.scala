import zio._
import GitHubClient._

object Main extends ZIOAppDefault {
  def run: ZIO[Any, Throwable, Unit] =
    for {
      _ <- Console.printLine("Enter GitHub username:")
      username <- Console.readLine
      events <- GitHubClient.fetchRecentActivity(username)
      _ <- ZIO.foreach(events.take(5)) { event =>
        Console.printLine(s"${event.`type`} on ${event.repo.name}")
      }
    } yield ()
}
