const TrendItem = ({ content }) => {
  console.log("아이템 재생");
  return (
    <>
      {content.map((itemData) => (
        <li className="trend-item">
          <img className="poster-wrapper" src={itemData.poster}></img>
          <div className="item-title">{itemData.name}</div>
          <div className="item-date">{itemData.openDate}</div>
        </li>
      ))}
    </>
  );
};

export default TrendItem;
