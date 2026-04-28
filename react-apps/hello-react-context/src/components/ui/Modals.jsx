/** @format */

import { useImperativeHandle, useRef, useState } from "react";
import { createPortal } from "react-dom";

export const Alert = ({ dialogRef }) => {
  console.log("Alert");

  const alertModalRef = useRef();
  const [errorMessage, setErrorMessage] = useState();

  /**
   * Props로 전달된 ref에게 dom이 아닌 함수 객체를 전달하기 위한 방법.
   * 부모 컴포넌트에게 전달해줄 데이터들(함수, 객체, 변수, 상수 등등)
   * 부모에게 전달해줄 데이터들은 props로 전달된 ref에 담아서 전달.
   */
  useImperativeHandle(dialogRef, () => {
    // dialogRef에게 할당해줄 데이터 들을 반환.
    return {
      // showModal: function () {},
      showModal(message) {
        alertModalRef.current.showModal();
        setErrorMessage(message);
      },
    };
  });

  const onCloseClickHandler = () => {
    alertModalRef.current.close();
  };

  return (
    <>
      {createPortal(
        <dialog className="modal" ref={alertModalRef}>
          <div className="modal-body">
            <section
              className="modal-close-button"
              onClick={onCloseClickHandler}
            >
              X
            </section>
            <div>{errorMessage}</div>
          </div>
        </dialog>,
        document.querySelector("#modals"),
      )}
    </>
  );
};

export const Confirm = ({ dialogRef, onOkClick, onCloseClick }) => {
  console.log("Confirm");

  const [confirmMessage, setConfirmMessage] = useState();
  const confirmDialogRef = useRef();

  // useRef에 dom이 아닌 객체를 할당할 수 있다.
  // useRef에 할당되는 Dom 또는 객체는 State처럼 Caching 된다.
  // 다만, ref 객체가 변경된다 하여 component가 재 실행되지는 않는다.
  const handledFromEvents = useRef({
    fired: false,
  });

  useImperativeHandle(dialogRef, () => {
    return {
      showConfirm(message) {
        setConfirmMessage(message);
        confirmDialogRef.current.showModal();
      },
    };
  });

  const onOkClickHandler = () => {
    handledFromEvents.fired = true;
    confirmDialogRef.current.close();
    onOkClick();
  };

  const onCloseClickHandler = () => {
    handledFromEvents.fired = true;
    confirmDialogRef.current.close();
    onCloseClick();
  };

  const onCloseNative = () => {
    if (!handledFromEvents.fired) {
      onCloseClick();
    }

    // dialog 가 꺼질 때, fired 값을 false로 변경되도록 한다.
    // event에 의해 닫힌 케이스도 초기화.
    handledFromEvents.fired = false;
  };

  return (
    <>
      {createPortal(
        <dialog
          className="modal"
          ref={confirmDialogRef}
          onClose={onCloseNative}
        >
          <div className="modal-body">
            <div>{confirmMessage}</div>
            <section>
              <button
                type="button"
                className="confirm-ok"
                onClick={onOkClickHandler}
              >
                OK
              </button>
              <button
                type="button"
                className="confirm-cancel"
                onClick={onCloseClickHandler}
              >
                Cancel
              </button>
            </section>
          </div>
        </dialog>,
        document.querySelector("#modals"),
      )}
    </>
  );
};
