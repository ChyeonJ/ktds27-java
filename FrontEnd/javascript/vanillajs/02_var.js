window.onload = function () {
  var number = 10;
  // 자바와 다르게 문자열을 이어 붙일 때 + 말고 ,로 붙이는게 좋다
  console.log("변수의 값은", number, "변수의 타입은", typeof number);

  number = "asdasd";
  console.log("변수의 값은", number, "변수의 타입은", typeof number);

  //undifind 진짜 아무것도 할당 안됐다
  var x;
  console.log("변수의 값은", x, "변수의 타입은", typeof x);

  //null을 할당한 값 타입은 object
  var x2 = null;
  console.log("변수의 값은", x2, "변수의 타입은", typeof x2);

  var x2 = 123.123123123123;
  console.log("변수의 값은", x2, "변수의 타입은", typeof x2);

  //var를 선언하지 않아도 변수가 생성된다.window.이 생략이 되어 있어서 그렇다 뒷내용임
  x3 = "asdasd";
  console.log("변수의 값은", x3, "변수의 타입은", typeof x3);
};
