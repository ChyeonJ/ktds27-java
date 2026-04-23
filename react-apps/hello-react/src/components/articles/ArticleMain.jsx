// Aricles.json 파일 불러오기
import ArticleHeader from "./ArticleHeader";
import ArticleList from "./ArticleList";
import ArticleWriter from "./ArticleWriter";
import articleData from "./articles.json";

const ArticleMain = () => {
  console.log(articleData);
  console.log(articleData.articles);
  console.log(articleData.articles[0]);
  console.log(articleData.articles[0].id);
  return (
    <div>
      <table>
        <ArticleHeader />
        <ArticleList articles={articleData} />
        <ArticleWriter />
      </table>
    </div>
  );
};
export default ArticleMain;
