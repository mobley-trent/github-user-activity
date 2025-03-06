import sttp.client3._
import sttp.client3.ziojson._
import zio._
import zio.json._

case class GitHubEvent(`type`: String, repo: Repo)
case class Repo(name: String)

object GitHubEvent {
  implicit val repoDecoder: JsonDecoder[Repo]         = DeriveJsonDecoder.gen[Repo]
  implicit val eventDecoder: JsonDecoder[GitHubEvent] = DeriveJsonDecoder.gen[GitHubEvent]
}

object GitHubClient {
  def fetchRecentActivity(username: String): ZIO[Any, Throwable, List[GitHubEvent]] = {
    val backend: SttpBackend[Identity, Any] = HttpClientSyncBackend()
    val request = basicRequest
      .get(uri"https://api.github.com/users/$username/events")
      .response(asJson[List[GitHubEvent]])

    ZIO.fromEither(request.send(backend).body)
  }
}
