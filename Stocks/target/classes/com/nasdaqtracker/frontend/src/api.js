export async function fetchStocks() {
  const res = await fetch('/api/stocks')
  if (!res.ok) throw new Error(`HTTP ${res.status}`)
  return await res.json()
}
