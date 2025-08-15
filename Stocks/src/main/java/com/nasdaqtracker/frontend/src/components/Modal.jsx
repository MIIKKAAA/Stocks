import React from 'react'
import ReactDOM from 'react-dom'

function Modal({ stock, onClose }) {
  if (!stock) return null;

  const modalRoot = document.getElementById('modal-root');
  if (!modalRoot) return null; // fail safe

  return ReactDOM.createPortal(
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-2xl p-6 w-96 shadow-lg">
        <h2 className="text-xl font-bold mb-2">{stock.name} ({stock.symbol})</h2>
        <p>Current Price: ${stock.currentPrice?.toFixed(2) ?? '-'}</p>
        <p>Price Change: {stock.priceChange?.toFixed(2) ?? '-'} ({stock.priceChangePercent?.toFixed(2) ?? '-'}%)</p>
        <button
          className="mt-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
          onClick={onClose}
        >
          Close
        </button>
      </div>
    </div>,
    modalRoot
  )
}

export default Modal;
