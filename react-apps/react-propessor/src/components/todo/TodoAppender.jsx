/** @format */

const TodoAppender = ({
  onTaskKeyUp,
  onDateChange,
  onPrioritySelectChange,
  onSaveButtonClick,
}) => {
  return (
    <footer>
      <input type="text" placeholder="Input new task" onKeyUp={onTaskKeyUp} />
      <input type="date" onChange={onDateChange} />
      <select onChange={onPrioritySelectChange}>
        <option>우선순위</option>
        <option value="1">높음</option>
        <option value="2">보통</option>
        <option value="3">낮음</option>
      </select>
      <button type="button" onClick={onSaveButtonClick}>
        Save
      </button>
    </footer>
  );
};
export default TodoAppender;
