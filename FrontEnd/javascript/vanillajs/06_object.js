//String에 contains 라는 기능을 추가한다.
String.prototype.contains = function (findText) {
  console.log(findText);
  console.log(this);

  return this.indexOf(findText) >= 0;
};

// tempObject에 print 라는 기능을 추가.
Object.prototype.print = function () {
  console.log("객체의 내용", this);
};
var tempObject = {};
tempObject.print(); //Uncaught Error!; 발생 print 함수가 아니기 때문에
console.dir(tempObject);

window.onload = function () {
  var text = "abcdefg abcdefg";
  // string의 기능이 뭐가 있나?? ==> String
  console.log(String);
  console.dir(String);
  // 포함된 거 찾아라
  var contain = text.contains("z"); //Uncaught error: contains 에러
  // 자바로 치면 String이라는 클래스에 새로운 기능을 넣어줄 수 있다(js만 가능)
  console.log(contain);

  var list = document.querySelector(".list");

  var listItems = [
    { tagName: "li", text: "first", class: "list-item" },
    { tagName: "li", text: "first", class: "list-item" },
    { tagName: "li", text: "first", class: "list-item" },
  ];

  listItems.print();

  for (var i = 0; i < listItems.length; i++) {
    var item = listItems[i];

    var eachItem = document.createElement(item.tagName);
    eachItem.className = item.class;
    eachItem.innerText = item.text;

    list.appendChild(eachItem);
  }

  function getObject() {
    return {
      price: 1231398123,
      name: "asd",
      model: "asdasdasd",
      fan: 8,
      chain: ["GS", "CJ", "HANJIN", "LOTTE"],
      address: {
        city: "seoul",
        state: "guro",
      },
    };
  }
  var headphone = {
    ["serial-number"]: "", //"serial-number":"" 가능
    modelName: "XM-5",
    manufacture: "Sony",
    type: "Over-ear",
    power: false,
    powerOn: function () {
      console.log(this.modelName, "이 켜집니다.");
      this.power = true;
    },
    powerOff: function () {
      console.log(this.modelName, "이 꺼집니다.");
      this.power = false;
    },
  }; //중괄호가 객체를 의미함

  console.log(headphone, typeof headphone);

  console.log(headphone.modelName);
  console.log(headphone["modelName"]);

  console.log(headphone["serial-number"]);

  headphone.powerOn();
  console.log(headphone.power);
  headphone.powerOff();
  console.log(headphone.power);
};
