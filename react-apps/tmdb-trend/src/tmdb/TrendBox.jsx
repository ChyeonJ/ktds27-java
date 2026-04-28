import { useState } from "react";
import trendData from "./trend.json";
import TrendHeader from "./TrendHeader";
import TrendSelector from "./TrendSelector";
import TrendList from "./TrendList";
import TrendItem from "./TrendItem";

const TrendBox = () => {
  const [{ sectionName, selectors, selectorKR, items }, setTrendData] =
    useState(trendData);

  // state가 존재하기 때문에 box전체가 랜더링 됨
  const [selectorData, setSelectData] = useState("today");

  console.log("박스 재생");

  // 애가 state의 값을 바꾸기 때문인듯
  const onSelectorClickHandler = (event) => {
    // setSelectData((prevData) => {
    //   console.log("-------------------------------------");
    //   console.log(prevData);

    //   return event.choice;
    // });
    // setSelectData(event.choice);
    setSelectData(event.target.dataset.info);
  };

  return (
    <div className="trend-container">
      <TrendHeader>
        <h3>{sectionName}</h3>
        <TrendSelector
          selectors={selectors}
          selectorKR={selectorKR}
          onSelectorClick={onSelectorClickHandler}
        />
      </TrendHeader>
      <TrendList>
        <TrendItem content={items[selectorData]} />
      </TrendList>
    </div>
  );
};

export default TrendBox;
