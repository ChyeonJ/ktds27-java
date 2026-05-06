/** @format */

const obj = {
  today: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
  week: [11, 22, 33, 44, 55, 66, 77, 88, 99, 100],
};

console.log(obj.today); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
console.log(obj["today"]); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

console.log(obj.week); // [11, 22, 33, 44, 55, 66, 77, 88, 99, 100]
console.log(obj["week"]); // [11, 22, 33, 44, 55, 66, 77, 88, 99, 100]

const active = "today";
// obj객체에서 "today" 에 있는 배열을 가지고 와라!
console.log(obj[active]);
