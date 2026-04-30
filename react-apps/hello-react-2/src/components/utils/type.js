export const isString = (value) => {
  if (value) {
    return typeof value === "string";
  }
};
export const isNumber = (value) => {
  if (value) {
    return typeof value === "number";
  }
};
export const isObject = (value) => {
  if (value) {
    return typeof value === "object" && Array.isArray(value) === false;
  }
};
export const isArray = (value) => {
  if (value) {
    return Array.isArray(value);
  }
};
export const isFunction = (value) => {
  if (value) {
    return typeof value === "function";
  }
};
