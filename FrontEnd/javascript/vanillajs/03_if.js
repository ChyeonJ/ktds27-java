window.onload = function () {
  var randomNumber = parseInt(Math.random() * 10);
  console.log(randomNumber);

  //난수가 0이라면 "연산할 수 없는 숫자입니다."
  //난수가 0보다 크다면 "0보다 큰 값입니다."
  if (randomNumber) {
    console.log("0보다 큰 값입니다.");
  } else {
    console.log("연사할 수 없는 숫자입니다.");
  }

  // 같다 비교
  // JavaScript의 값 동등비교 ==, ===
  // == <-- 값만 비교
  // === <-- 값 + 타입 비교
  console.log(1 == 1); // true
  console.log(1 == 1.0); // true
  console.log("1" == 1); // true
  console.log("a" == "a"); // true

  console.log(1 === 1, typeof 1); // true
  console.log(1 === 1.0, typeof 1, typeof 1.0); // true
  console.log("1" === 1, typeof "1", typeof 1); // false
  console.log("a" === "a", typeof "a"); // true

  var name = " ";
  if (name) {
    console.log("name의 값이 있습니다.");
  } else {
    console.log("name의 값이 없습니다.");
  }

  var age; //undefined
  if (age) {
    console.log("age값이 있습니다.");
  } else {
    console.log("age값이 없습니다");
  }

  var address = null;
  if (address) {
    console.log("addres의 값이 있습니다.");
  } else {
    console.log("addres의 값이 없습니다");
  }

  // 배열은 배열로서 존재한다
  var arr = [];
  if (arr) {
    console.log("arr값 있음");
  } else {
    console.log("arr값 없음");
  }
};
