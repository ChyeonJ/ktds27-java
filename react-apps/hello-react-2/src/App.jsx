/** @format */
import ArticleMain from "./components/articles/ArticleMain.jsx";
import Calc from "./components/homeworks/calc/Calc.jsx";
import Counter from "./components/homeworks/counter/Counter.jsx";
import TodoMain from "./components/todo/TodoMain.jsx";
import { ReactReduxProvider } from "./stores/ReactReduxProvider.jsx";
import { ToolkitProvider } from "./stores/toolkit/ToolkitProvider.jsx";

export default function App() {
  console.log("App");
  return (
    <ToolkitProvider>
      <ArticleMain />
    </ToolkitProvider>
  );
}

//export default App;
