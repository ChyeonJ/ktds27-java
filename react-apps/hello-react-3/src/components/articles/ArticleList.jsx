/** @format */

import { useSelector } from "react-redux";

const ArticleList = () => {
  console.log("ArticleList");

  const { list: contents } = useSelector((store) => store.article);

  return (
    <tbody>
      {contents.map((article) => (
        <tr key={article.id}>
          <td>{article.id}</td>
          <td>{article.subject}</td>
          <td>
            {article.membersVO.name}({article.membersVO.email})
          </td>
          <td>{article.viewCnt}</td>
          <td>{article.crtDt}</td>
        </tr>
      ))}
    </tbody>
  );
};

export default ArticleList;
