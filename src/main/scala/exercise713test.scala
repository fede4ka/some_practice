import org.scalatest.FunSuite

class exercise713test extends FunSuite {
  test("maptest") {
    assert(Exercise713.map1(List(1, 2, 3))(_ * 2) === List(2, 4, 6))
  }

  test("flatmaptest") {
    assert(Exercise713.flatmap1(List(1, 2, 3))(a => List(a, a * 10, a * 100))=== List(1, 10, 100, 2, 20, 200, 3, 30, 300))
  }
  test("filtertest") {
    assert(Exercise713.filter1(List(1, 2, 3))(_ % 2 == 1)===List(1, 3))
  }

}
