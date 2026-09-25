import { useState } from 'react';

export default function App() {
  const [result, setResult] = useState<string>('');

  const pingBackend = async () => {
    try {
      const resp = await fetch('/api/catalog/categories');
      const text = await resp.text();
      setResult(`HTTP ${resp.status}\n\n${text}`);
    } catch (e: any) {
      setResult('Ошибка: ' + e.message);
    }
  };

  return (
      <div style={{ padding: 24, fontFamily: 'system-ui, sans-serif' }}>
        <h1>HouseViz</h1>
        <p>Проверка связки с бэком:</p>
        <button onClick={pingBackend} style={{ padding: '8px 16px', fontSize: 16 }}>
          Дёрнуть /api/catalog/categories
        </button>
        {result && (
            <pre style={{
              marginTop: 16,
              padding: 12,
              background: '#f5f5f5',
              border: '1px solid #ddd',
              borderRadius: 4,
              whiteSpace: 'pre-wrap',
              wordBreak: 'break-all',
            }}>
          {result}
        </pre>
        )}
      </div>
  );
}