import TodoContext from "./contexts/TodoContext";

const TodoGrid = ({ children }) => {
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
