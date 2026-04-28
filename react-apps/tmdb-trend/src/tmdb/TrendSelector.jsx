const TrendSelector = ({ selectors, onSelectorClick, selectorKR }) => {
  console.log("셀렉터 재생");

  return (
    <>
      {selectors.map((choice, index) => (
        // <div className="trend-selector">
        //   <button
        //     key={choice}
        //     className="active"
        //     onClick={onSelectorClick.bind(this, { choice })}
        //   >
        //     {selectorKR[index]}
        //   </button>
        // </div>
        <input
          type="button"
          value={selectorKR[index]}
          data-info={choice}
          onClick={onSelectorClick}
        ></input>
      ))}
    </>
  );
};

export default TrendSelector;
