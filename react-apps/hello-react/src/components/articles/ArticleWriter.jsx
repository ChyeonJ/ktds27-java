const ArticleWriter = () => {
  return (
    <div>
      게시글 작성 폼 (제목, 이메일, 이름, 내용)
      <form method="post" action="/writer">
        <input id="id" type="text" placeholder="제목을 입력하세요"></input>
        <input id="email" type="text" placeholder="이메일을 입력하세요"></input>
        <input id="name" type="text" placeholder="이름을 입력하세요"></input>
        <textarea id="content"></textarea>
        <button type="submit">저장</button>
      </form>
    </div>
  );
};
export default ArticleWriter;
