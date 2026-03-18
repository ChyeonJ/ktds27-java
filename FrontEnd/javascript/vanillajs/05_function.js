window.onload = function () {
  function addAll() {
    //arguments 배열 ==> 반복하면서 모든값을 더한다.
    var sum = 0;

    for (var i = 0; i < arguments.length; i++) {
      sum += arguments[i];
    }
    return sum;
  }

  var add = addAll(1, 2, 3, 4, 5, 6, 6, 7, 87, 8, 98);
  console.log(add);

  function calc(num1, num2) {
    console.log(arguments);
    return num1 + num2;
  }

  var result = calc(10, 30);
  console.log(result); //40

  result = calc(10, 30, 100);
  console.log(result); // 40

  result = calc(10, 30, 100, 1000);
  console.log(result); // 40

  result = calc(10);
  console.log(result); //NaN

  result = calc();
  console.log(result); //NaN
};
