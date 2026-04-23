const ArticleList = ({ articles }) => {
  return (
    <tbody>
      {articles.articles.map((data) => (
        <tr>
          <td>{data.id}</td>
          <td>{data.subject}</td>
          <td>{data.content}</td>
          <td>{data.email}</td>
          <td>{data.viewCnt}</td>
          <td>{data.crtDt}</td>
          <td>{data.mdfyDt}</td>
        </tr>
      ))}
    </tbody>
  );
};

export default ArticleList;
