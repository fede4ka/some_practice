object processorsDB extends App{

  import doobie._
  import doobie.implicits._
  import cats._
  import cats.data._
  import cats.effect.IO
  import cats.implicits._
  import scala.concurrent.ExecutionContext
  import fs2.Stream

  implicit val cs = IO.contextShift(ExecutionContext.global)

  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver", // driver classname
    "jdbc:postgresql:supplies", // connect URL (driver-specific)
    "postgres",              // user
    ""                       // password
  )

  val drop =
    sql"""DROP TABLE IF EXISTS processors""".update.run.transact(xa).unsafeRunSync

  val create =
    sql"""CREATE TABLE processors (id   SERIAL,name VARCHAR NOT NULL UNIQUE,price  SMALLINT)""".update.run

  create.transact(xa).unsafeRunSync

  def insertOne(name: String, price: Option[Short]): Update0 =
    sql"insert into processors (name, price) values ($name, $price)".update


  insertOne("Core i7-9700K", Some(374)).run.transact(xa).unsafeRunSync

  case class proc (id: Long, name: String, price: Option[Short])
  type procInfo = (String, Option[Short])

  def insertMany(ps: List[procInfo]): Stream[ConnectionIO, proc] = {
    val sql = "insert into processors (name, price) values (?, ?)"
    Update[procInfo](sql).updateManyWithGeneratedKeys[proc]("id", "name", "price")(ps)
  }

  val data = List[procInfo](
    ("Core i7-8700",   Some(303)),
    ("Core i7-8700K", Some(359)),
    ("Core i7-9700KF", Some(374)))

  insertMany(data).compile.toList.transact(xa).unsafeRunSync

  val select = sql"select name from processors"
    .query[String]    // Query0[String]
    .stream           // Stream[ConnectionIO, String]
    .compile.toList   // ConnectionIO[List[String]]
    .transact(xa)     // IO[List[String]]
    .unsafeRunSync    // List[String]

}
