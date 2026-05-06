/** @format */
import { useState } from "react";
import trendData from "./trend.json";
import TrendBox from "./TrendBox.jsx";
import TrendHeader from "./TrendHeader.jsx";
import TrendSelector from "./TrendSelector.jsx";
import TrendList from "./TrendList.jsx";
import TrendItem from "./TrendItem.jsx";

const TmdbMain = () => {
  const [{ sectionName, selectors, selectorsKR, items }] = useState(trendData);

  const [active, setActive] = useState(selectors[0]);
  const onSelectChangeHandler = (event) => {
    setActive(event.target.value);
  };

  return (
    <TrendBox>
      <TrendHeader>
        <h1>{sectionName}</h1>
        <TrendSelector
          selectors={selectors}
          translatedSelectors={selectorsKR}
          active={active}
          onSelectChange={onSelectChangeHandler}
        />
      </TrendHeader>
      <TrendList>
        {items[active].map((movie) => (
          <TrendItem key={movie.id} item={movie} />
        ))}
      </TrendList>
    </TrendBox>
  );
};
export default TmdbMain;
