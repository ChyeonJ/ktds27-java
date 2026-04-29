import TodoContext from "./contexts/TodoContext";

const TodoGrid = ({ children }) => {
  console.log("투두그리드");

  const ProviderProps = {
    componentName: "TodoGrid",
  };
  return (
    <ul className="tasks">
      <TodoContext.Provider value={ProviderProps}>
        {children}
      </TodoContext.Provider>
    </ul>
  );
};

export default TodoGrid;
