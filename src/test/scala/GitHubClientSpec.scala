import zio._
import zio.test._
import zio.test.Assertion._
import sttp.client3._
import sttp.client3.testing.SttpBackendStub
import zio.json._

object GitHubClientSpec extends ZIOSpecDefault {

  def spec = suite("GitHubClientSpec")(
    test("fetchRecentActivity should return parsed GitHub events") {
      val result = GitHubClient.fetchRecentActivity("mobley-trent")

      assertZIO(result)(hasSize(equalTo(5)))
    },
    test("fetchRecentActivity should handle empty response") {
      val result = GitHubClient.fetchRecentActivity("testuser")

      assertZIO(result)(isEmpty)
    }
  )
}