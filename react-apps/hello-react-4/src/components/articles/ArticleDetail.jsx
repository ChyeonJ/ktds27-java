import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchArticleById } from "../../http/articles/fetchArticles";
import { handleFileDownload } from "../../utils/download";

export const ArticleDetail = () => {
  const { id } = useParams(); //{id: "BO-YYYYMMDD-000001"}

  const [article, setArticle] = useState();
  useEffect(() => {
    const loadArticleById = async () => {
      const articleResult = await fetchArticleById(id);
      if (!articleResult.error) {
        setArticle(articleResult);
      } else {
        alert(articleResult.error);
      }
    };
    loadArticleById();
  }, [id]);

  if (!article) {
    return <div>불러오는중</div>;
  }

  return (
    <div>
      {id}게시글의 상세 내용입니다.
      <div>{article.id}</div>
      <div>{article.subject}</div>
      <div>{article.content}</div>
      <div>
        {article.membersVO.name}({article.email})
      </div>
      <div>{article.viewCnt}</div>
      <div>{article.crtDt}</div>
      <div>{article.mdfyDt}</div>
      <ul>
        {article.files?.map((f) => (
          <li key={`${f.fileNum}_${f.fileGroupId}`}>
            <a
              //   target="_blank" //창이 열리면서 이미지가 보임 => link라면
              onClick={handleFileDownload.bind(
                this,
                `http://192.168.211.25:8080/file/${f.fileGroupId}/${f.fileNum}`,
              )}
            >
              {f.displayName} ({f.fileLength} bytes)
            </a>
          </li>
        ))}
      </ul>
      {/* <ReplyList></ReplyList> 댓글은 이렇게 컴포넌트 만들어서 부르면 된다.*/}
    </div>
  );
};
