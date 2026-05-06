/** @format */

import { createBrowserRouter, RouterProvider } from "react-router-dom";
import TmdbMain from "../components/tmdb/TmdbMain";
import TodoMain from "../components/todo/TodoMain";
import ArticleMain from "../components/articles/ArticleMain";
import { MainLayout } from "../components/layout/MainLayout";
import { NotFoundPage } from "../components/layout/error/NotFoundPage";
import { ArticleLayout } from "../components/layout/ArticleLayout";
import { ArticleDetail } from "../components/articles/ArticleDetail";

const HelloRouter = () => {
  // Route 설정.
  const router = createBrowserRouter([
    {
      path: "/",
      element: <MainLayout />,
      errorElement: <NotFoundPage />,
      children: [
        {
          path: "tmdb",
          element: <TmdbMain />,
        },
        {
          path: "todo",
          element: <TodoMain />,
        },
        {
          path: "article",
          element: <ArticleLayout />,
          children: [
            { path: "", element: <ArticleMain /> },
            { path: ":id", element: <ArticleDetail /> },
          ],
        },
      ],
    },
  ]);

  // Router Component 생성.
  return <RouterProvider router={router} />;
};
export default HelloRouter;
