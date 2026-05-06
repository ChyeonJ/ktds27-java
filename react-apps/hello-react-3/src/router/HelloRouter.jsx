import { createBrowserRouter, RouterProvider } from "react-router-dom";
// import TmdbMain from

const HelloRouter = () => {
  // // Route 설정.
  // const router = createBrowserRouter([
  //   // {path: "/", element:<MainLayout/> , children: [    {
  //     path: "tmdb",
  //     element: <TmdbMain />,
  //   },
  //   { path: "todo", element: <TodoMain /> },
  //   { path: "article", element: <ArticleMain /> },]} 아래 주석 만들고 이러 해주면 된데 2. 그리고 밑에 만든거 그거 넣어주면 된데 3. /는 다 빼래 그게 규칙이래
  // ]);
  // Route Component 생성.
  // return <RouterProvider router={router} />;
};
export default HelloRouter;

/*
layout
mainlayout, headerlayout 만듦
header는 메인 헤더바 만드는 역할
컴포넌트 구조 같음
return <header>
        <Login /> <-- 애는 따로 추가함 로그인은 헤더에 있는게 맞다고 하니까
        <nav>
          <ul>
            <li>
              <Link to="url">이름</Link> ==> a 태그 쓰면 state가 다 날아가서 Link써야함 => 이거 쓰면 안날아감
              Link => NavLink가 있데 => 내가 클릭한 메뉴가 활성화 된데
            </li>
            나머지 같음
          </ul>
        </nav>
      </header>

mainlayout
똑같고 컴포넌트 구조 바로 리턴 ㅇㅇ
<div>
 <HeaderN,,oaksd> => 위에서 만든거 넣음 ㅇㅇ
 <Outlet> ==> 이게 아까 뭐 N뭐시기 라우터 ㅇㅇ

*/

/*
/ errorHandling
error > NotFoundPage.jsx 생성
컴포넌트 구조 같음
return <div>
        <헤더 네비>
        <div> 페이치 찾기 ㄴㄴ </div>

이거 하고 Hello Router에 root path에 errorElement: <NotFoundPage> 쓰면됨
*/
