import org.scalatest.FunSuite

class traversetest1 extends FunSuite {
  test("TraverseTest1") {
    assert(traverse.traverseCurry(List (1,2,0)) === None)
  }
  test("TraverseTest2") {
    assert(traverse.traverseCurry(List (1,2,3)) === Some(List(1, 2, 3)))
  }



}