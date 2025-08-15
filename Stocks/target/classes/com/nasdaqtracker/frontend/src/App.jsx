import React, { useEffect, useState, useMemo } from 'react'
import { fetchStocks } from './data/api'
import Modal from './components/Modal'

export default function App() {
  const [stocks, setStocks] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [sortConfig, setSortConfig] = useState({key:null, direction: "asc"})
  const [selectedStock, setSelectedStock] = useState(null)

  useEffect(() => {
    fetchStocks()
      .then((data) => setStocks(data))
      .catch((e) => setError(e?.message || 'Failed to load stocks'))
      .finally(() => setLoading(false))
  }, [])

  const requestSort = (key) => {
    let direction = "asc"
    if (sortConfig.key === key && sortConfig.direction === "asc") {
      direction = "desc"
    }
    setSortConfig({key, direction})
  }

  // Sort stocks based on sortConfig, useMemo to prevent re-sorting on every render
  const sortedStocks = useMemo(() => {
    // Create a copy of stocks to sort
    let sortable = [...stocks]
    if (sortConfig.key) { // Sort if a valid key is provided
      sortable.sort((a,b) =>{ 
      if (a[sortConfig.key] < b[sortConfig.key]) 
        return sortConfig.direction === "asc" ? -1 : 1 // Sort ascending
      if (a[sortConfig.key] > b[sortConfig.key]) 
        return sortConfig.direction === "asc" ? 1 : -1 // Sort descending
      return 0
    })
  }
  return sortable
}, [stocks, sortConfig])

// Display sort indicator
const getSortIndicator = (key) => {
  if (sortConfig.key !== key) 
    return null // no arrow if not the active sort column
  return sortConfig.direction === 'asc' ? ' ↑' : ' ↓'
}


  return (
    <div className="container">
      <h1>NASDAQ Top Stocks</h1>

      {loading && <div className="info">Loading stock data...</div>}
      {error && !loading && <div className="error">{error}</div>}

      {!loading && !error && (
        <div className="table-wrap">
          <table>
            <thead>
              <tr>
                <th onClick={() => requestSort('symbol')}>Symbol{getSortIndicator('symbol')}</th>
                <th onClick={() => requestSort('name')}>Name{getSortIndicator('name')}</th>
                <th onClick={() => requestSort('currentPrice')}>Price{getSortIndicator('currentPrice')}</th>
                <th onClick={() => requestSort('priceChange')}>Change{getSortIndicator('priceChange')}</th>
                <th onClick={() => requestSort('priceChangePercent')}>Change%{getSortIndicator('priceChangePercent')}</th>
              </tr>
            </thead>
            <tbody>
            {sortedStocks.map((s) => (
              <tr 
                key={s.symbol}
                onClick={() => {
                  console.log("clicked", s);
                  setSelectedStock(s)}}
                className="cursor-pointer hover:bg-gray-100"
              >
                <td>{s.symbol}</td>
                <td className="name">{s.name}</td>
                <td>${s.currentPrice?.toFixed?.(2) ?? '-'}</td>
                <td className={s.priceChange >= 0 ? 'pos' : 'neg'}>
                  {s.priceChange?.toFixed?.(2) ?? '-'}
                </td>
                <td className={s.priceChangePercent >= 0 ? 'pos' : 'neg'}>
                  {s.priceChangePercent?.toFixed?.(2) ?? '-'}%
                </td>
              </tr>
            ))}
          </tbody>
          </table>
        </div>
      )}

      {/* Modal */}
      <Modal stock={selectedStock} onClose={() => setSelectedStock(null)} />
    </div>
  )
}
